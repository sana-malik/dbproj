from mysql import connector
from timeit import Timer

def time_query(query):
	tr = Timer(
		stmt="cursor.execute('{0}');list(cursor)".format(query),
		setup=("from mysql import connector;"
			"ctx = connector.connect(user='root', database='sana', password='s3cr3t');"
			"cursor = ctx.cursor()")
	).timeit(1)
	return tr

