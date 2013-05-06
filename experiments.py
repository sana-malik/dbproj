import postgresql
import timeit
from validator import solve

def runExperiments():
	for filename in ['sat-short', 'unsat-short', 'sat-medium','unsat-medium','sat-long','unsat-long']:
		times = []
		f = open('queries.txt','r')
		lines = f.readlines()
		f.close()
		f = open(filename+'times.txt','w')
		
		for formula in lines:
			query = convertToQuery(formula)
			#s = timeit.timeit('runQuery("'+query+'","small")')
			#m = timeit.timeit('runQuery("'+query+'","medium")')
			#l = timeit.timeit('runQuery("'+query+'","large")')
			#sV = timeit.timeit('solve("'+formula+'"); runQuery("'+query+'","small")')
			#mV = timeit.timeit('solve("'+formula+'"); runQuery("'+query+'","medium")')
			#lV = timeit.timeit('solve("'+formula+'"); runQuery("'+query+'","large")')
			#times.append((s,m,l))
			print(timeit.timeit('solve("'+formula+'")'));

if __name__ == "__main__":
	runExperiments()
