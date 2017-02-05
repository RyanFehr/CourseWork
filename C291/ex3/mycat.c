#include <stdio.h>

void mycat(int);  //mycat prototype

//Create a function that has the following prototype: 

char dest_buffer[100];

char str1[] = "Hello";

char str2[] = "World";

void main()
{
  mycat(2);
  mycat(6);
  mycat(30);
}

void mycat(int n)
{
  
  
  int i = 0; 
  while(str1[i]!='\0')
    {
      dest_buffer[i]= str1[i];
      i++;
    }
  
  int j = 0;
    while(str2[j]!='\0')
      {
	dest_buffer[i+j] = str2[j];
	j++;
      }
  
    
  
  
  dest_buffer[n]='\0';
  
  
  printf("%s\n",dest_buffer);
  
}

