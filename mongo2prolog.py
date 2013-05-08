from pymongo import MongoClient
from time import mktime

client = MongoClient()
mongodb = client['dbproj']

format_time = lambda x : int(mktime(x.timetuple()))

collection = mongodb['data']
f = open('large.pl', 'w')
for doc in collection.find().sort('_id',1):
  f.write("tweet(\"{0}\", {1}, {2}).\n".format(str(doc['_id']), format_time(doc['start']), format_time(doc['end'])))

f.close()


