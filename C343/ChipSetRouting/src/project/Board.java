package project;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Board {
	public Map<Coord, Integer> grid;
	public int width;
	public int height;
	
	public Board(Map<Coord, Integer> grid, int height, int width){
		this.grid = grid;
		this.width = width;
		this.height = height;
	}
	
	public int getValue(Coord c){
		return this.grid.get(c);
	}

	public void putValue(Coord c, int value){
		this.grid.put(c, value);
	}
	
	public boolean isObstacle(Coord c){
		return this.getValue(c) == -1; 
	}
	
	public ArrayList<Coord> adj(Coord c){
		int x = c.x;
		int y = c.y;
		ArrayList<Coord> adjs = new ArrayList<Coord>();
		if (x + 1 < this.width){
			adjs.add(new Coord(x+1, y));
		}
		if (x - 1 >= 0){
			adjs.add(new Coord(x-1, y));
		}
		if (y + 1 < this.height){
			adjs.add(new Coord(x, y+1));
		}
		if (y - 1 >= 0){
			adjs.add(new Coord(x, y-1));
		}
		return adjs;
	}
	
	public void pretty_print_grid(){
		int nSpaces = 4;
		for (int y=0; y != this.height; ++y){
			System.out.print("| ");
			for (int x=0; x != this.width; ++x){
				int v = this.grid.get(new Coord(x, y));
				String space = String.format("%"+(nSpaces-String.valueOf(v).length())+"s", "");
				if (x != this.width-1){
					System.out.print(v + space);
				}
				else{
					System.out.print(v);
				}
			}
			System.out.print(" |\n");
		}
	}
	
	//  unit test for Board class
	/*
	public static void main(String[] args){
		Map<Coord, Integer> map = new HashMap<Coord, Integer>();
		map.put(new Coord(0,0), 0);
		map.put(new Coord(0,1), 0);
		map.put(new Coord(0,2), -1);
		map.put(new Coord(1,0), 0);
		map.put(new Coord(1,1), -1);
		map.put(new Coord(1,2), -1);
		System.out.println(map.get(new Coord(1, 20)));
		
		map.put(new Coord(1, 20), 30);
		System.out.println(map.get(new Coord(1, 20)));
		
		Board b = new Board(map, 2, 3);
		b.pretty_print_grid();
	}
	*/
}

