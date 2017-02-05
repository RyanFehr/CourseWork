/* highscore.c --- 
 * 
 * Filename: highscore.c
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Sun Oct  2 21:10:24 2016
 * Last-Updated: 
 *           By: 
 *     Update #: 0
 * Keywords: 
 * Compatibility: 
 * 
 */

/* Commentary: 
 * 
 * 
 * 
 */

/* Change log:
 * 
 * 
 */

/* Copyright (c) 2014-2015 Analog Computing Solutions  
 * 
 * All rights reserved. 
 * 
 * Additional copyrights may follow 
 */

/* Code: */
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include "highscore.h"

highscore_t *parse_line(char *line) {
  highscore_t *sptr;
  char *p;
  char *p_end;
  const char delim = ',';
  enum {NAME, SCORE, EXIT};
  int state = NAME;
  
  // Make sure that the line ptr is not NULL
  if (!line) {
    return (NULL);
  }

  // Make Space for a new structure 
  sptr = malloc(sizeof(highscore_t));
  
  // Assumnig that the new space is available, break the line down into parts and load the struct.
  if (sptr) {
    p = strtok(line,&delim); // initialize strtok to break line into tokens delimited by ','
    while (p) {
      switch (state) {
      case NAME:
	if (strlen(p)<NAME_SIZE) {
	  strcpy(sptr->initials,p);
	  state = SCORE;
	}
	else {
	  return (NULL);
	}
	break;
      case SCORE:
	errno = 0;
	sptr->score = strtol(p,&p_end,10);
	if (errno) {
	  return (NULL);
	}
	return (sptr);
	break;
      default:
	return(NULL);
      }
      p = strtok(NULL,&delim); // Read the next token
    }
  }
  return (NULL);
}

highscore_t *load_scores(char *filename) {
  char linebuf[MAX_LINE];
  FILE *fp;
  char *lp;
  int status;
  highscore_t *current = NULL;
  highscore_t *next = NULL;
  highscore_t *head = NULL;

  // If file exists, open it for reading and writing. 
  errno = 0;
  fp = fopen(filename,"r");
  if (errno) {
    fp = fopen(filename,"w");
    sprintf(linebuf,"END,0");
    head = parse_line(linebuf);
    close(fp);
    return (head);
  }

  // Read the file, line-by-line
  while (lp = fgets(linebuf,MAX_LINE-1,fp)) {
    next = parse_line(linebuf);
    if (next) {
      if (current) {
	current->next = next;
	next->next = NULL;
      }
      else {
	head = next;
	next->next = NULL;
      }
      current = next;
    }    
  }
  close(fp);
  return (head);
}

highscore_t *insert_score(highscore_t *list, char * initials, int score) {
  highscore_t *current = NULL;
  highscore_t *next = NULL;
  highscore_t *new_item = NULL;
  highscore_t *last = NULL;

  new_item = malloc(sizeof(highscore_t));
  if (new_item) {
    strncpy(new_item->initials,initials,NAME_SIZE-1);
    new_item->score = score;
  }
  if (!list) {
    // the list does not exist, need to create it and add this as the first element
    // Return the list. 
    new_item->next = NULL;
    return (new_item);
  }
  else {
    current = list;
    while (current) {
      if (score > current->score) {
	if (last) {
	  last->next = new_item;
	  new_item->next=current;
	  return (list);
	}
	else {
	  // New first item in the list
	  new_item->next = current;
	  return (new_item);
	}
      }
      last = current;
      current = current->next;
    }
    // Lowest score in the list, add it to the end. 
    last->next = new_item;
    new_item->next = NULL;
  }
  return(list);
}

int store_scores(char *filename, highscore_t *list) {
  int counter = 0;
  FILE *fp;
  highscore_t *current = NULL;

  // Open the high score file for writing. 
  fp = fopen(filename,"w");
  current = list;
  while (current) {
    fprintf(fp,"%s,%d\n",current->initials,current->score);
    current = current->next;
  }
  close(fp);
  return (0);
}

int free_score_list (highscore_t *list) {
  highscore_t *current = list;
  highscore_t *next = NULL;

  while (current) {
    next = current->next;
    free(current);
    current = next;
  }
  return(0);
}

int print_score_list (highscore_t *list, int x_start, int y_start, int numscores) {
  /* Prints the scores to the screen. If -1 or 0 is passed for numscores, 
     then the function prints all of the scores. Otherwise, it prints only numscores
     intials/scores to the screen. 
   */
  int i = 0;
  while ((list) && (i<numscores)){
    if (strcmp(list->initials,"END")) {
      mvprintw(y_start+i,x_start,"%s %d",list->initials, list->score);
      if (numscores > 0) {
	i++;
      }
    }
    list = list->next;
  }
  return(0);
}

int compare_highscore(highscore_t *list, int score, int numscores) {
  int i = 0;
  // Compares the passed score with the scores in the list. If 
  // -1 or 0 is passed for numscores, then the function compares against all scores in the list. 
  while ((list) && (i<numscores)) {
    if (score >= list->score) {
      return (1);
    }
    list = list->next;
    if (numscores > 0) {
      i++;
    }
  }
  return(0);
}  

/* highscore.c ends here */
