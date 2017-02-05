#include <stdio.h>

void main()
{
  int top = 100;
  int prime = 1;
  int i;
  int j;

  for(i=2;i<=top;i++)
    {
      prime = 1;
      for(j=2;j<i;j++)
	{
	  if(i%j==0)
	    {
	      prime = 0;
	    }
	}
      if(prime != 0)
	{
	  printf("%d\n",i);
	}
    }

  
}
