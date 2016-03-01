#include <stdio.h>
#include <dirent.h>
#include <string.h>

#define MAX_BUF	256

int main(int argc, char *argv[]) 
{
	char dir_name[MAX_BUF]; 
	
	strcpy(dir_name, argv[1]);
	printf("directory name is: %s\n", dir_name);
	
	return 0;
}
