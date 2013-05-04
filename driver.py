from validator import *

## Main

######################
## Generate Queries ##
######################
query_file = open('queries.txt', 'w')

num_queries = 1000  ## How many queries should be generated.
num_intervals = 10 ## How many intervals will be in the query 
num_clauses = num_intervals/2  ## Number of clauses in the query (note, due to randomness (and laziness) not all intervals will necessarily be in the query)

generate_queries(num_queries, num_intervals, num_clauses, query_file)
query_file.close()


######################
## Validate Queries ##
######################
query_file = open('queries.txt', 'r')
unsat = 0.0
cnt = 0
for query in query_file:
    cnt = cnt + 1
    #print "Processing query " + str(cnt)
    sat = str(solve( query ))

    if sat == 'unsat':
        unsat = unsat + 1.0

print "\nTotal queries: " + str(cnt)
print "Percentage Unsatisfiable: " + str( 100*unsat/cnt )
print "Percentage Satisfiable: " + str( 100*(cnt-unsat)/cnt )
    
query_file.close()
