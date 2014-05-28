#!/usr/bin/python

import sys, re

p = re.compile('(\D+)(\d+)(\D+)')

f = open(sys.argv[1])

for line in f.readlines():
    m = p.search(line)
    if m:
        print m.group(1) + str(int(m.group(2)) - 12) + m.group(3).rstrip()
    else:
        print line.rstrip()
f.close()
