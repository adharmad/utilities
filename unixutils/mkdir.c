/* mkdir.c */
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>

/* function prototypes */
void usage();

int main(int argc, char ** argv)
{
	if (argc < 3) {	
		usage();
	}

	
}

void usage() 
{
	printf("Usage: mkdir <MODE> <PATH>\n");
	printf("\tMODE: A string in the form xxxyyyzzz that specifies permissions\n");
	exit(0);
}
