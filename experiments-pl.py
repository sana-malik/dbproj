import re
import timeit
from validator import solve
from pyswip import Prolog

def convertToQuery(formula):
	n = int(max([int(n) for n in re.findall(r'\[(\d+)\]', formula)])/2)+1
	query = ""
	for i in range(0,n):
		query += "tweet(" + chr(65+i) + ",X" + chr(97+i) + ",Y" + chr(97+i) + "), "

	formula = re.sub(r'\[(\d+)\]',lambda m : str(float(m.group(1))/2),formula)
	formula = re.sub(r'x(\d+).0', lambda m : 'X'+chr(97+int(m.group(1))),formula)
	formula = re.sub(r'x(\d+).5', lambda m : 'Y'+chr(97+int(m.group(1))),formula)
	query += formula.replace('==','=')
	return query

def validate(formula):
	return solve(formula)

def runExperiments(queryLength):
	iter = 1  # number of runs 
#	for filename in ['sat-'+queryLength, 'unsat-'+queryLength]:
	for filename in ['test']:
		times = []
		f = open(filename+'-queries.txt','r')
		lines = [line.strip() for line in f.readlines()]
		f.close()
		f = open('times/' + filename+'-times.txt','w')
		
		for formula in lines:
			query = convertToQuery(formula)
			stmtStr = 'prolog.query("'+query+'")'
			setUpStr = lambda x : "from pyswip import Prolog; prolog = Prolog(); prolog.consult('"+x+"')"
			print(query)
			s = timeit.Timer(stmt=stmtStr, setup=setUpStr('small.pl')).timeit(iter)
			print(s)
			'''			m = timeit.Timer(stmt='runQuery("'+queryMd+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			l = timeit.Timer(stmt='runQuery("'+queryLg+'",psql)', setup="from __main__ import runQuery; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			sV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+querySm+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			mV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+queryMd+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			lV = timeit.Timer(stmt='validate("'+formula+'"); runQuery("'+queryLg+'",psql)', setup="from __main__ import runQuery, validate; import postgresql; psql = postgresql.open('pq://localhost/dbproj')").timeit(100)
			f.write("%f,%f,%f,%f,%f,%f\n" % (s, m, l, sV, mV, lV))
			'''
		f.close()

if __name__ == "__main__":
	queryLength = 'short'
	runExperiments(queryLength)
