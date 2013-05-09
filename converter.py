from experiments_pl import convertToQuery
from sys import stdin

for query in stdin:
	print convertToQuery(query)
