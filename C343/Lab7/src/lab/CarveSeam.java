package lab;

import java.util.ArrayList;

public class CarveSeam {

	static int carve_seam(int[][] D, ArrayList<Coord> seam) {

		Result[][] table = new Result[D.length][D[0].length];
		Result best = null;

		// base case, just check bottom row
		for (int i = 0; i < D.length; i++) {
			table[D.length - 1][i] = new Result(new Coord(D.length - 1, i), D[D.length - 1][i], null);
		}

		// traverse up the picture
		for (int i = D[0].length - 2; i >= 0; i--) {
			for (int j = 0; j < D.length; j++) {
				//left side
				if (j == 0) {
					table[i][j] = new Result(new Coord(i, j), 
							D[i][j] + min2(table[i+1][j].cost, table[i+1][j+1].cost), 
									min2c(table[i+1][j], table[i+1][j+1]));
					}
				//right side
				else if(j == D.length - 1) {
					table[i][j] = new Result(new Coord(i, j),
							D[i][j] + min2(table[i+1][j].cost, table[i+1][j-1].cost),
							min2c(table[i+1][j], table[i+1][j-1]));
					
				}
				//middle case
				else {
					table[i][j] = new Result(new Coord(i, j),
							D[i][j] + min3(table[i+1][j].cost, table[i+1][j+1].cost, table[i+1][j-1].cost),
							min3c(table[i+1][j], table[i+1][j+1], table[i+1][j-1]));
				}
			}
		}
		
		//finding the best of top row
		for(int i=0; i<table.length; i++) {
			if(best == null || table[0][i].cost < best.cost) {
				best = new Result(new Coord(0, i), table[0][i].cost, table[0][i].bottom);
			}
		}
		
		int output = best.cost;
		
		//adding to seam
		int temp = table[0].length - 1;
		while(temp != -1) {
			seam.add(best.pixel);
			best = best.bottom;
			temp--;
		}
		
		
		return output;
		

	}
	
	//finds the minimum of two values
	private static int min2(int a, int b) {
		if(a < b) {
			return a;
		}
		else {
			return b;
		}
	}
	
	//finds minimum Result
	private static Result min2c(Result a, Result b) {
		
		if(a.cost < b.cost) {
			return a;
		}
		else {
			return b;
		}
	}
	
	//finds the minimum of three values
	private static int min3(int a, int b, int c) {
		
		if(a <= b && a <= c) {
			return a;
		}
		else if(b <= a && b <= c) {
			return b;
		}
		else {
			
			return c;
		}
		
	}
	
	//finds the minimum Result
	private static Result min3c(Result a, Result b, Result c) {
		
		if(a.cost <= b.cost && a.cost <= c.cost) {
			return a;
		}
		else if(b.cost <= a.cost && b.cost <= c.cost) {
			return b;
		}
		else {
			
			return c;
		}
	}

	// main method used as a test client
	public static void main(String[] args) {
		
		//test 1
		int[][] D = new int[3][3];
	    D[0][0] = 1; D[0][1] = 0; D[0][2] = 1;
	    D[1][0] = 1; D[1][1] = 0; D[1][2] = 1;
	    D[2][0] = 1; D[2][1] = 0; D[2][2] = 1;

	    ArrayList<Coord> seam = new ArrayList<Coord>();
	    int disruption = carve_seam(D, seam);
	    System.out.println(disruption);
	    assert disruption == 0;
	    for (int i = 0; i != seam.size(); ++i) {
	    Coord c = seam.get(i);
	    assert c.x == i;
	    assert c.y == 1;
	    }
		
		//test2
//		int[][] D = new int[3][3];
//	    D[0][0] = 0; D[0][1] = 1; D[0][2] = 2;
//	    D[1][0] = 0; D[1][1] = 0; D[1][2] = 1;
//	    D[2][0] = 1; D[2][1] = 1; D[2][2] = 0;
//
//	    ArrayList<Coord> seam = new ArrayList<Coord>();
//	    int disruption = carve_seam(D, seam);
//	    assert disruption == 0;
//	    System.out.println(disruption);
//	    
//	    for (int i = 0; i != seam.size(); ++i) {
//	    Coord c = seam.get(i);
//	    assert c.x == i;
//	    assert c.y == i;
//	    }
		
		//test3
//		int[][] D = new int[3][3];
//	    D[0][0] = 7; D[0][1] = 10; D[0][2] = 15;
//	    D[1][0] = 3; D[1][1] = 5; D[1][2] = 9;
//	    D[2][0] = 3; D[2][1] = 1; D[2][2] = 8;
//
//	    ArrayList<Coord> seam = new ArrayList<Coord>();
//	    int disruption = carve_seam(D, seam);
//	    assert disruption == 11;
//	    System.out.println(disruption);
//	    
//	    for (int i = 0; i != seam.size(); ++i) {
//	    Coord c = seam.get(i);
//	    assert c.x == i;
//	    assert c.y == i;
//	    }
		
		//test4
//		int[][] D = new int[4][4];
//	    D[0][0] = 7; D[0][1] = 10; D[0][2] = 15; D[0][3] = 20;
//	    D[1][0] = 3; D[1][1] = 5; D[1][2] = 9; D[1][3] = 4;
//	    D[2][0] = 3; D[2][1] = 1; D[2][2] = 8; D[2][3] = 6;
//	    D[3][0] = 2; D[3][1] = 5; D[3][2] = 9; D[3][3] = 10;
//
//	    ArrayList<Coord> seam = new ArrayList<Coord>();
//	    int disruption = carve_seam(D, seam);
//	    assert disruption == 13;
//	    System.out.println(disruption);
//	    for(int i=0; i<seam.size(); i++) {
//	    	System.out.println(seam.get(i));
//	    }
//	    
//	    for (int i = 0; i != seam.size(); ++i) {
//	    Coord c = seam.get(i);
//	    assert c.x == i;
//	    assert c.y == i;
//	    }
		

	}

}
