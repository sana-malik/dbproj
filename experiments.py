import re
import timeit
from validator import solve

def convertToQuery(formula, table):
	n = int(max([int(n) for n in re.findall(r'\[(\d+)\]', formula)])/2)
	query = "SELECT * from ";
	for i in range(0,n+1):
		query += table + " x" + str(i) + ", ";
	query = query[:-2] + " WHERE ";
	formula = re.sub(r'\[(\d+)\]',lambda m : str(float(m.group(1))/2),formula)
	query += formula.replace('.0','.start').replace('.5','.finish').replace(',', ' and').replace('==','=')
	return query
		
def runQuery(query, conn):
	conn.execute(query)

def validate(formula):
	return solve(formula)

def runExperiments():
	filename = 'all-formulas';
	f = open(filename+'.txt','r')
	lines = [line.strip() for line in f.readlines()]
	f.close()
	f = open(filename+'-times.txt','w')
	f.write('query_class,query_length,db_size,v_time,r_time\n')
	
	for line in lines:
		(query_class,query_length,formula) = line.split("|")
		v_time = timeit.Timer(stmt='solve("'+formula+'")', setup="from validator import solve").timeit(100)/100
		for db_size in ['small','medium','large']:
			query = convertToQuery(formula,db_size)
			#r_time = timeit.Timer(stmt='runQuery("'+querySm+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			r_time = 0.0
			f.write("%s,%s,%s,%f,%f\n" % (query_class,query_length,db_size,v_time,r_time))
	
if __name__ == "__main__":
	runExperiments()
