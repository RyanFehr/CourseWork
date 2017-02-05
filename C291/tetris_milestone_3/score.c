/* score.c --- 
 * 
 * Filename: score.c
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Tue Oct  4 15:22:20 2016
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


int compute_score(int previous_score, int lines_cleared) {
  enum {NORMAL, TETRIS};
  int new_score;

  static int state = NORMAL;
  switch (lines_cleared) {
  case 0:
    new_score = 0;
    break;
  case 1:
    new_score = 100;
    break;
  case 2:
    new_score = 250;
    break;
  case 3:
    new_score = 500;
    break;
  case 4: 
    new_score = 800;
    state = TETRIS;
    break;
  }
  switch (state) {
  case NORMAL: 
    return (previous_score + new_score); 
    break;
  case TETRIS: 
    if (lines_cleared == 4) {
      return (previous_score + 1200);
    }
    else {
      state = NORMAL;
      return (previous_score + new_score); 
    }
    break;
  default:
    state = NORMAL;
    return (previous_score + new_score);
    break;
  }
}

void display_score(int score, int x, int y) {
  mvprintw(y,x,"*** SCORE ***",score);
  mvprintw(y+1,x,"%8d",score);
}






/* score.c ends here */
