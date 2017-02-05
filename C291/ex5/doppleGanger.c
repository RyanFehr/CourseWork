
#include <stdio.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>



struct Person {
  char *name;
  int age;
  int height;
  int weight;
};



struct Person *Person_create(char *name, int age, int height, int weight) {
  struct Person *who = malloc(sizeof(struct Person));
  assert(who != NULL);
  who->name = strdup(name);
  who->age = age;
  who->height = height;
  who->weight = weight;
  return who;
}

void Person_destroy(struct Person *who) {
  assert(who != NULL);
  free(who->name);
  free(who);
}

void Person_print(struct Person *who) {
  printf("Name: %s\n", who->name);
  printf("\tAge: %d\n", who->age);
  printf("\tHeight: %d\n", who->height);
  printf("\tWeight: %d\n", who->weight);
}

struct Person *Doppelganger (struct Person *who, char * newname) {
  // Create a copy of a Person with only the name being different. 
  
  int newAge;
  int newHeight;
  int newWeight;
  newAge = who->age;
  newHeight = who->height;
  newWeight = who->weight;
  return Person_create(newname, newAge, newHeight, newWeight);
   // Change to return the correct pointer once complete. 
}

int main(int argc, char *argv[])
{
  struct Person *frank = Person_create("Frank Jones", 20, 72, 180);
  struct Person *bob; 

  printf("Frank is at memory location %p:\n", frank);
  Person_print(frank);

  bob = Doppelganger (frank,"Bob Smith");
  if (bob) {
    printf("Bob is at memory location %p:\n", bob);
    Person_print(bob);
  }
  else {
    printf("Bob is sadly still NULL\n");
  }
  free(bob);
  free(frank);
  return 0;
}
