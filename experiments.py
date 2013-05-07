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
	for filename in ['sat-short', 'unsat-short', 'sat-medium','unsat-medium','sat-long','unsat-long']:
#	for filename in ['test']:
		times = []
		f = open(filename+'-queries.txt','r')
		lines = [line.strip() for line in f.readlines()]
		f.close()
		f = open(filename+'-times.txt','w')
		
		for formula in lines:
			querySm = convertToQuery(formula,"small")
			queryMd = convertToQuery(formula,"medium")
			queryLg = convertToQuery(formula,"large")
			s = timeit.Timer(stmt='runQuery("'+querySm+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			m = timeit.Timer(stmt='runQuery("'+queryMd+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			l = timeit.Timer(stmt='runQuery("'+queryLg+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			sV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+querySm+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			mV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+queryMd+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			lV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+queryLg+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			f.write("%f,%f,%f,%f,%f,%f\n" % (s, m, l, sV, mV, lV))
			
if __name__ == "__main__":
	runExperiments()
