kb_sizes = ['small', 'medium', 'large']
query_sizes = ['short', 'medium', 'long']
query_classes = ['sat', 'unsat']

def make_pred_sig(args, i):
	return "query{0}({1})".format(i, args)

def make_tmpfile_name(kb_size, query_size, query_class):
	tmpfile = "/tmp/{0}_{1}_{2}.pl".format(kb_size, query_size, query_class)
	return tmpfile

def gen_tmpfile(kb_size, query_size, query_class):
	from sh import cp
	from experiments_pl import convertToQuery
	import re

	queryfile = "./{0}-{1}-queries.txt".format(query_class, query_size)
	tmpfile = make_tmpfile_name(kb_size, query_size, query_class)
	cp(
			"{0}.pl".format(kb_size),
			tmpfile
	)

	r = []

	with open(tmpfile, "a") as tmp:
		with open(queryfile) as queries:
			for i, query in enumerate(queries.readlines()):
				rule = convertToQuery(query.strip())
				args = ",".join([chr(65+n) for n,_ in enumerate(re.finditer('tweet',rule))])
				pred_sig = make_pred_sig(args, i)
				tmp.write("{0} :- {1}.\n".format(pred_sig, rule))
				r.append({
					'args': args,
					'i': i,
					'kb_size': kb_size,
					'query_size': query_size,
					'query_class': query_class,
					'orig_query': query.strip()
				})

	return r

def run_swi(kb_size, query_size, query_class, args, i, *aargs, **kwargs):
	from sh import swipl
	from StringIO import StringIO
	import re
	
	f = StringIO()
	swipl(
			'-s', make_tmpfile_name(kb_size, query_size, query_class),
			'-g', 'time(findall([{0}],{1},ZZZ)).'.format(args, make_pred_sig(args, i)),
			'-t', 'halt.',
			'-G32g',
			_err=f
	)
	output = f.getvalue()
	matches = re.search(r'in ([0-9.]+) seconds', output)
	return float(matches.group(1))


def time_validation(query):
	from timeit import Timer
	tv = Timer(stmt="solve('{0}')".format(query), setup="from validator import solve").timeit(1)
	return tv

if __name__ == '__main__':
	from argparse import ArgumentParser
	from itertools import product
	from collections import OrderedDict
	import csv

	parser = ArgumentParser()
	parser.add_argument('outfile', help="The file where you want to store the output CSV")
	parser.add_argument('-s', '--satisfiability', help="Select which satisfiabilities you want.",
			action='append', choices=query_classes)
	parser.add_argument('-a', '--arity', help="Select which arities you want.",
			action='append', choices=query_sizes)
	parser.add_argument('-k', '--kbsize', help="Select which knowledgebase sizes you want",
			action='append', choices=kb_sizes+['tiny'])

	args = parser.parse_args()
	if args.satisfiability is None:
		args.satisfiability = query_classes
	if args.arity is None:
		args.arity = query_sizes
	if args.kbsize is None:
		args.kbsize = kb_sizes

	queue = []
	for kb_size, query_size, query_class in product(args.kbsize, args.arity, args.satisfiability):
		queries = gen_tmpfile(kb_size, query_size, query_class)
		queue.extend(queries)
	
	with open(args.outfile, "wb") as outfile:
		out_fields = OrderedDict([
			('kb_size', None),
			('query_sat', None),
			('query_size', None),
			('time_to_validate', None),
			('time_to_run', None)])
		csvwriter = csv.DictWriter(outfile, delimiter=',', fieldnames=out_fields)
		csvwriter.writeheader()

		while len(queue) > 0:
			current = queue.pop(0)
			validation_time = time_validation(current['orig_query'])
			run_time = run_swi(**current)

			csvwriter.writerow({
				'kb_size': current['kb_size'],
				'query_sat': current['query_class'],
				'query_size': current['query_size'],
				'time_to_validate': validation_time,
				'time_to_run': run_time
			})
