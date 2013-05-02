from z3.bin.z3 import *
import random


def solve( query ):
    split_query = query.split(':')
    x = []
    var_num = int( split_query[0] )
    for var in range( var_num ):
        x.append( Real( 'x[' + str(var) + ']' ) )

    s = Solver()
    s.add( eval(split_query[1]) )
    
    ## Ensure that an interval's start time is before its endtime when validating queries
    for interval in range( var_num/2 ):
        s.add(eval("x[" + str(2*interval) + "] < x[" + str(2*interval+1) + "]"))
             
    return s.check()

def generate_queries(num_queries, num_intervals, num_clauses, output_file):
    for i in range(num_queries):
        query = str(2*num_intervals) + ": "

        ## Add random interval relationships to the query
        for rel in range( num_clauses ):
            interval1 = interval2 = random.randint(0, num_intervals-1)
            while interval2 == interval1:
                interval2 = random.randint(0, num_intervals-1)

            query = query + randomRelationshipString(interval1, interval2)
            if rel != num_clauses-1:
                query = query + ", "
        
        query = query + "\n"
        output_file.write( query )

def randomRelationshipString( e, e_prime ):
    relationship = ""

    relation = random.randint(1, 13)
    
    if relation == 1: #before
        relationship = "x[" + str(2*e+1) + "] < x[" + str(2*e_prime) + "]"
    elif relation == 2: #after
        relationship = "x[" + str(2*e) + "] > x[" + str(2*e_prime+1) + "]"
    elif relation == 3: #during
        relationship = "x[" + str(2*e) + "] > x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] < x[" + str(2*e_prime+1) + "]"
    elif relation == 4: # contain
        relationship = "x[" + str(2*e) + "] < x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] > x[" + str(2*e_prime+1) + "]"
    elif relation == 5: #meet
        relationship = "x[" + str(2*e+1) + "] == x[" + str(2*e_prime) + "]"
    elif relation == 6: #met by
        relationship = "x[" + str(2*e) + "] == x[" + str(2*e_prime+1) + "]"
    elif relation == 7: #overlap
        relationship = "x[" + str(2*e) + "] < x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] > x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] < x[" + str(2*e_prime+1) + "]"
    elif relation == 8: #overlapped by
        relationship = "x[" + str(2*e) + "] > x[" + str(2*e_prime) + "], x[" + str(2*e) + "] < x[" + str(2*e_prime+1) + "], x[" + str(2*e+1) + "] > x[" + str(2*e_prime+1) + "]"
    elif relation == 9: #start
        relationship = "x[" + str(2*e) + "] == x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] < x[" + str(2*e_prime+1) + "]"
    elif relation == 10: #started by
        relationship = "x[" + str(2*e) + "] == x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] > x[" + str(2*e_prime+1) + "]"
    elif relation == 11: #finish
        relationship = "x[" + str(2*e) + "] > x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] == x[" + str(2*e_prime+1) + "]"
    elif relation == 12: #finished
        relationship = "x[" + str(2*e) + "] < x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] == x[" + str(2*e_prime+1) + "]"
    elif relation == 13: #equal
        relationship = "x[" + str(2*e) + "] == x[" + str(2*e_prime) + "], x[" + str(2*e+1) + "] == x[" + str(2*e_prime+1) + "]"
        
    return relationship

## Main
        
query_file = open('queries.txt', 'w')

num_queries = 1000  ## How many queries should be generated.
num_intervals = 100 ## How many intervals will be in the query 
num_clauses = num_intervals/2  ## Number of clauses in the query (note, due to randomness (and laziness) not all intervals will necessarily be in the query)

generate_queries(num_queries, num_intervals, num_clauses, query_file)
query_file.close()

query_file = open('queries.txt', 'r')


##query_file = open('queries.txt', 'r')

unsat = 0.0
cnt = 0
for query in query_file:
    cnt = cnt + 1
   # print "Processing query " + str(cnt)
    sat = str(solve( query ))
   # print sat
    if sat == 'unsat':
        unsat = unsat + 1.0

print "\nTotal queries: " + str(cnt)
print "Percentage Unsatisfiable: " + str( 100*unsat/cnt )
print "Percentage Satisfiable: " + str( 100*(cnt-unsat)/cnt )
    
query_file.close()
