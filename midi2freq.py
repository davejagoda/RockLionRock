#!/usr/bin/python

def midi2freq(m):
    return( 2**((m-69)/12.0) * 440.0)

print 'As'
print midi2freq(45)
print midi2freq(57)
print midi2freq(69)
print midi2freq(81)
print 'D'
print midi2freq(86)
print 'E'
print midi2freq(88)
print 'D/A'
print midi2freq(86)/midi2freq(81)
print 'E/A'
print midi2freq(88)/midi2freq(81)
