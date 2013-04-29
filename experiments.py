from pymongo import MongoClient
import timeit

def runQuery(query, collectionName):
	client = MongoClient()
	db = client['dbproj']
	collection = db[collectionName]
	collection.find(query)

def validate(formula):
	# meow

def runExperiments():
	times = []
	f = open('queries.txt','r')
	lines = f.readlines()
	for line in lines:
		(formula, query) = line.split("|")
		s = timeit.timeit('runQuery('+query+',small)')
		m = timeit.timeit('runQuery('+query+',medium)')
		l = timeit.timeit('runQuery('+query+',large)')
		sV = timeit.timeit('validate('+formula+'); runQuery('+query+',small)')
		mV = timeit.timeit('validate('+formula+'); runQuery('+query+',medium)')
		lV = timeit.timeit('validate('+formula+'); runQuery('+query+',large)')
		times.append((s,m,l))

if __name__ == "__main__":
	runExperimets()