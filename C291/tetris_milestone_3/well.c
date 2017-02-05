/* well.c --- 
 * 
 * Filename: well.c
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Tue Sep  6 14:10:06 2016
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

#include <stdlib.h>
#include <ncurses.h>
#include "well.h"

well_t *init_well(int upper_left_x, int upper_left_y, int width, int height) {
  well_t *w;
  w = malloc(sizeof(well_t));
  w->upper_left_x = upper_left_x;
  w->upper_left_y = upper_left_y;
  w->width = width;
  w->height = height;
  w->draw_char = '#';
  w->color[0] = 0;
  w->color[1] = 0;
  w->color[2] = 0;
  return (w);
}

void draw_well(well_t *w) {
  int row_counter, column_counter;
  // Draw left side of well
  for (column_counter=w->upper_left_y;column_counter<=(w->upper_left_y + w->height);column_counter++) {
    mvprintw(column_counter,w->upper_left_x,"%c",w->draw_char);
  }

  // Draw right side of well
  for (column_counter=w->upper_left_y;column_counter<=(w->upper_left_y + w->height);column_counter++) {
    mvprintw(column_counter,(w->upper_left_x + w->width),"%c",w->draw_char);
  }
  
  // Draw Bottom of well 
  for (row_counter=w->upper_left_x;row_counter<=(w->upper_left_x + w->width);row_counter++) {
    mvprintw(w->upper_left_y + w->height,row_counter,"%c",w->draw_char);
  }
}

int prune_well(well_t * well) {
  // returns the number of lines cleared. Assumes that a new tet has not been started yet. 
  int row; // indicator of the row in the well. The row furthest from the top is row 0. 
  chtype *well_original;  // pointer to an array of characters representing the state of the well. 
  chtype *well_modified;
  chtype *col_ptr; 
  int current_column_position, current_row_position, output_row;
  int num_chars;
  int i, keep_row;
  int cleared_rows = 0;

  // Setup memory to receive the status of the well 
  current_column_position =  well->upper_left_x + 1; // left most well x, not including the wall
  current_row_position = well->upper_left_y + well->height - 1; // bottom of the well. 
  well_original = malloc(sizeof(chtype) * well->width * well->height);
  well_modified = well_original;

  // Read in the well 
  while (current_row_position > well->upper_left_y) {
    num_chars = mvinchnstr(current_row_position, current_column_position, well_modified, well->width-1);
    well_modified += well->width-1;
    current_row_position--;
  }
  well_modified = well_original; // Reset to the beginning of the memory. 
  current_row_position = well->upper_left_y + well->height - 1; // bottom of the well. 
  output_row = current_row_position;
  while (current_row_position > well->upper_left_y) {
    col_ptr = well_modified; // Capture a pointer to the beginning of the row 
    keep_row = 0;

    // Test the row to see if it is complete
    for (i = 0; i < well->width-1; i++) {
      if (*well_modified == ' ') {
	keep_row = 1;
      }
      well_modified++;
    }
    // If it is not complete, write it back. Otherwise, skip this row. 
    if (keep_row) {
      well_modified = col_ptr; // restore pointer back to the beginning of the row.  
      for (i = 0; i < well->width-1; i++) {
	mvprintw(output_row,current_column_position+i,"%c",*well_modified);
	//mvprintw(output_row,i+2,"%c",*well_modified);
	well_modified++;
      }
      output_row--;
    }
    else {
      cleared_rows++;
    }
    current_row_position--;
  }
  free(well_original);
  return(cleared_rows);
}

/* well.c ends here */
