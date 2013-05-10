import re
import timeit
from validator import solve
from mysql_runner import time_query
from sys import stdout
import mysql.connector

def convertToQuery(formula, table):
	n = int(max([int(n) for n in re.findall(r'\[(\d+)\]', formula)])/2)
	query = "SELECT count(*) from ";
	for i in range(0,n+1):
		query += table + " x" + str(i) + ", ";
	query = query[:-2] + " WHERE ";
	formula = re.sub(r'\[(\d+)\]',lambda m : str(float(m.group(1))/2),formula)
	query += formula.replace('.0','.start').replace('.5','.finish').replace(',', ' and').replace('==','=')
	return query
		
def validate(formula):
	return solve(formula)

def runExperiments():
	filename = 'all-new-formulas';
	f = open(filename+'.txt','r')
	lines = [line.strip() for line in f.readlines()]
	f.close()
	with open(filename+'-times.txt','w') as f:
		f.write('query_class,query_length,db_size,v_time,r_time\n')
		f.flush()
		
		for line in lines:
			(query_class,query_length,formula) = line.split("|")
			v_time = timeit.Timer(stmt='solve("'+formula+'")', setup="from validator import solve").timeit(100)/100
			for db_size in ['small','medium','large']:
				query = convertToQuery(formula,db_size)
				print "running %s,%s,%s\n\t%s" % (query_class,query_length,db_size,query)
				stdout.flush()
				try:
					r_time = time_query(query)
					f.write("%s,%s,%s,%f,%f\n" % (query_class,query_length,db_size,v_time,r_time))
					f.flush()
				except mysql.connector.Error as err:
					print("Something went wrong: {}".format(err))
					stdout.flush()
	
if __name__ == "__main__":
	runExperiments()
