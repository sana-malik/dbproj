from z3 import *
import random
#from sets import Set

def solve( query ):
    x = []
    var_num = num_vars(query)

    for var in range( var_num ):
        x.append( Real( 'x[' + str(var) + ']' ) )

    s = Solver()
    s.add( eval(query) )
    
    ## Ensure that an interval's start time is before its endtime when validating queries
    for interval in range( int(var_num/2) ):
        s.add(eval("x[" + str(2*interval) + "] < x[" + str(2*interval+1) + "]"))

    #print str(var_num/2) + " " + str(s.check())
    return s.check()

def generate_queries(num_queries, num_intervals, num_clauses, sat, output_file):
    i = 0
    while i < num_queries:
        query = ""

        ## Add random interval relationships to the query
        for rel in range( int(num_clauses) ):
            interval1 = interval2 = random.randint(0, num_intervals-1)
            while interval2 == interval1:
                interval2 = random.randint(0, num_intervals-1)

            query = query + randomRelationshipString(interval1, interval2)
            if rel != num_clauses-1:
                query = query + ", "
        
        query = query + "\n"

        uniques = identifyUniqueElements( query )
  
        next_assignment = 0
        for index in range(len(uniques)):
            var_index = uniques[index]
            if index>0:
                if var_index%2!=0 and var_index-1!=uniques[index-1]:
                    next_assignment = next_assignment + 1
            if var_index > next_assignment:
                query = query.replace("["+str(var_index)+"]", "["+str(next_assignment)+"]")
            if index<len(uniques)-1:
                if var_index%2==0 and var_index+1!=uniques[index+1]:
                    next_assignment = next_assignment + 1
            next_assignment = next_assignment + 1

        if (str(solve(query)) == 'unsat') == (not sat):
            i = i + 1
            print("Generated query " + str(i))
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

def identifyUniqueElements( query ):
 ## Identify unique elements
    els = query.replace("<", " ").replace(">", " ").replace("==", " ").replace(",", " ").replace("x["," ").replace("]"," ").split()
    unique_els = []
    for el in els:
        num = int(el)
        if num not in unique_els:
            unique_els.append(num)

    unique_els.sort()
    return unique_els

def num_vars( query ):
    uniques = identifyUniqueElements(query)
    max_index = uniques[ len(uniques)-1 ]  # Find biggest variable index
    max_index = max_index + (1-max_index%2) # If it's even, we need to add that intervals endpoint
    return max_index + 1 # Account for zero indexing (since this is a count)

#init('/fs/junkfood/maliks/Courses/724/dbproj/libz3.so')
