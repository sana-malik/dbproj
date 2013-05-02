from z3.bin.z3 import *



def solve( query ):
    split_query = query.split(':')
    x = []
    var_num = int( split_query[0] )
    for var in range( var_num ):
        x.append( Real( 'x[' + str(var) + ']' ) )

    s = Solver()
    s.add( eval(split_query[1]) )
    return s.check()

    
## Main
query_file = open('queries.txt', 'r')

for query in query_file:
    print solve( query )
    
    

