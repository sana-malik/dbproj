from pymongo import MongoClient
import postgresql

client = MongoClient()
mongodb = client['dbproj']
psql = postgresql.open("pq://localhost/dbproj")

format_as_datetime = lambda x : x.strftime('%Y-%m-%d %H:%M:%S')

insert_func = psql.prepare("INSERT INTO large VALUES ($1, $2, $3)")
collection = mongodb['data']
for doc in collection.find().sort('_id',1).skip(280622):
 # print(doc)
  insert_func(
    doc['start'],
    doc['end'],
    doc['text'],
    )


