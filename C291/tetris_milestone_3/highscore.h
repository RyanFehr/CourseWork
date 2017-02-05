/* highscore.h --- 
 * 
 * Filename: highscore.h
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Mon Oct  3 09:34:47 2016
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

/* Copyright (c) 2016 The Trustees of Indiana University and 
 * Indiana University Research and Technology Corporation.  
 * 
 * All rights reserved. 
 * 
 * Additional copyrights may follow 
 */

/* Code: */
#ifndef HIGHSCORE_H
#define HIGHSCORE_H

#define MAX_LINE 100
#define NAME_SIZE 4
#define NUM_SCORES_STORED 10

typedef struct highscore {
  char initials[NAME_SIZE+1];
  int score;
  struct highscore *next;
} highscore_t;

highscore_t *parse_line(char *);
int compare_highscore(highscore_t *, int, int);
highscore_t *load_scores(char *);
highscore_t *insert_score(highscore_t *, char *, int);
int store_scores(char *, highscore_t *);
int compare_highscore(highscore_t *, int, int);
int print_score_list (highscore_t *, int, int, int);

#endif
/* highscore.h ends here */
