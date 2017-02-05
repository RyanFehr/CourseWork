#include <stdio.h>

void main(int argc, char *argv[])
{
  FILE *infile = fopen(argv[1], "r");
  FILE *outfile = fopen(argv[2],"w");
  if (infile == NULL) 
    {
      fprintf(stderr, "Can't open input file \n");
      exit(1);
    }
  else
    {
      int copyByte = fgetc(infile);
      while(copyByte != EOF)
	{
	  fputc(copyByte, outfile);
	  copyByte = fgetc(infile);
	}
    }
  fclose(infile);
  fclose(outfile);
}
