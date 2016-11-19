# Calculates the size of all the files in directories recursively
# Useful for checking where disk space is being used

import sys, os, string

def walk(dirName):
    pass 

def usage():
    usageString = '''Usage:
        python3 dirsize.py [toplevel-directory] 
        eg - python3 dirsize.py .
    '''
    print(usageString)
    sys.exit(0)

if __name__ == '__main__':
    if len(sys.argv) < 2:
        usage()

    dirName = sys.argv[1]
