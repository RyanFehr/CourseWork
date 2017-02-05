/* key.c --- 
 * 
 * Filename: key.c
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Thu Sep 15 16:35:07 2016
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
#include <ncurses.h> 
#include "key.h"

int read_escape(int *read_char) {
  int c; 
  if ((c = getch()) == ERR) {
    return (NOCHAR);
  }
  else if (c==0x1b) {
    if ((c = getch()) == '[') {
      c=getch();
      switch (c) {
      case 'A':
	return (UP);
	break;
      case 'B':
	return (DOWN);
	break;
      case 'C':
	return (RIGHT);
	break;
      case 'D':
	return (LEFT);
	break;
      default: 
	return (BADESC);
      }
    }
  }
  else {
    *read_char = c;
    return (REGCHAR);
  }
}

/* key.c ends here */
