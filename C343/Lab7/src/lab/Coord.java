package lab;

class Coord {
	Coord(int init_x, int init_y) {
		x = init_x;
		y = init_y;
	}

	int x;
	int y;
	
	public String toString() {
		return "x: " + x + " y: " + y;
	}
}
