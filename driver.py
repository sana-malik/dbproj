from validator import *

SAT = True
UNSAT = False


## Main
filename = 'queries.txt'
num_queries = 10  ## How many queries should be generated.
num_intervals = 100 ## How many intervals will be in the query 
sat = SAT

######################
## Generate Queries ##
######################
query_file = open(filename, 'w')

num_clauses = num_intervals/2  ## Number of clauses in the query (note, due to randomness (and laziness) not all intervals will necessarily be in the query)

generate_queries(num_queries, num_intervals, num_clauses, sat, query_file)
query_file.close()


######################
## Validate Queries ##
######################
query_file = open(filename, 'r')
unsat = 0.0
cnt = 0
var_sum = 0.0
for query in query_file:
    var_sum = var_sum + num_vars(query)
    cnt = cnt + 1
    print("Processing query " + str(cnt))
    sat = str(solve( query ))

    if sat == 'unsat':
        unsat = unsat + 1.0

print("\nTotal queries: " + str(cnt))
print("Average Number of Variables: " + str(var_sum/cnt))
print("Percentage Unsatisfiable: " + str( 100*unsat/cnt ))
print("Percentage Satisfiable: " + str( 100*(cnt-unsat)/cnt ))
    
query_file.close()
