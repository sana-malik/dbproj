from experiments import convertToQuery
from sys import stdin

for query in stdin:
	print convertToQuery(query,"small")
	print convertToQuery(query,"medium")
	print convertToQuery(query,'new_medium')
	print convertToQuery(query,"large")
