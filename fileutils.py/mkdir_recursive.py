# Utility to do a recursive mkdir
import sys, os, string

def createDirs(dirList, mode):
    directory = ''
    for d in dirList:
        directory += d
        try:
            os.mkdir(directory, mode)
        except OSError as e:
            print("Error while creating directory ", directory, " : " , e)
        directory += '/'

def splitDir(dirName):
    return dirName.split('/')

def usage():
    usageString = '''Usage:
        python3 mkdir_recursive.py [directories] [mode]
        eg - python3 mkdir_recursive.py "test/foo/bar" 0755
    '''
    print(usageString)
    sys.exit(0)

if __name__ == '__main__':
    if len(sys.argv) < 3:
        usage()

    dirName = sys.argv[1]
    mode = int(sys.argv[2])
    dirList = splitDir(dirName)

    createDirs(dirList, mode)
