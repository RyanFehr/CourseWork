package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Driver {
	
    public static Board buildBoard(ArrayList<Coord[]> points,
				   ArrayList<Integer[]> obstacles,
				   String filename){
	try {
            final FileReader reader = new FileReader(filename);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            // read in data
            int height = Integer.parseInt(bufferedReader.readLine());
            int width = Integer.parseInt(bufferedReader.readLine());
            int numObst = Integer.parseInt(bufferedReader.readLine());
            
            // build grid
            Map<Coord, Integer> grid = new HashMap<Coord, Integer>();
            for (int y=0; y != height; ++y){
            	for (int x=0; x != width; ++x){
		    grid.put(new Coord(x, y), 0);
            	}
            }
            // read obstacles and lay out obstacles
            for (int i=0; i != numObst; ++i){
            	String line = bufferedReader.readLine();
            	String nums_str[] = line.split(" ");
            	Integer nums[] = { Integer.parseInt(nums_str[0]),
				   Integer.parseInt(nums_str[1]),
				   Integer.parseInt(nums_str[2]),
				   Integer.parseInt(nums_str[3]) };
            	obstacles.add(nums);
            	for (int x=nums[0];  x <= nums[2];++x){
		    for (int y=nums[1]; y <= nums[3]; ++y){
			Coord obs = new Coord(x, y);
			grid.put(obs, -1);
		    }
            	}
            }
            
            // read points and lay out path origins and path destinations
            int numPoints = Integer.parseInt(bufferedReader.readLine());
            int pathCnt = 1;
            
            for (int i=0; i != numPoints; ++i){
            	String line = bufferedReader.readLine();
            	String nums[] = line.split(" ");
            	int originX = Integer.parseInt(nums[0]);
            	int originY = Integer.parseInt(nums[1]);
            	int destX = Integer.parseInt(nums[2]);
            	int destY = Integer.parseInt(nums[3]);
            	Coord path[] = {new Coord(originX, originY),
				new Coord(destX, destY)};
            	points.add(path);
            	
            	grid.put(path[0], pathCnt);
            	grid.put(path[1], pathCnt);
            	
            	pathCnt += 1;
            }
            bufferedReader.close();
            return new Board(grid, height, width);
	}
	catch (FileNotFoundException e) {
            System.out.println("The input file: [" + filename
			       + "] is not found.\n.");
        }catch (Exception e) {
            e.printStackTrace(System.out);
        }
	return null;
    }
	
    // test findPath() function against the given input file
    public static void test(String filename){
	System.out.println("testing " + filename);
	
	Map<Coord, Integer> grid = new HashMap<Coord, Integer>();
	ArrayList<Coord[]> points = new ArrayList<Coord[]>();
	ArrayList<Integer[]> obstacles = new ArrayList<Integer[]>();
	Board board = buildBoard(points, obstacles, filename);
	if (board == null){
	    System.out.println("Error building board");
	    return;
	}
	//System.out.println(filename); board.pretty_print_grid();
		
	// call findPaths function implemented by students
	ArrayList<ArrayList<Coord>> paths = Routing.findPaths(board, points);
		
	if (paths == null || paths.size() < points.size()){
	    System.out.println("Cannot connect any points!");
	    return;
	}
	//System.out.println("---------------------------------");
	//board.pretty_print_grid();
	if (checkCorrectness(points, paths, obstacles)){
	    System.out.println("Test Passed!");
	} else {
	    System.out.println("Incorrect solution.");
	}

    }
	
    public static boolean checkCorrectness(ArrayList<Coord[]> points,
					   ArrayList<ArrayList<Coord>> paths,
					   ArrayList<Integer[]> obstacles){
	Set<Coord> checked = new HashSet<Coord>();
	for (int i = 0; i != paths.size(); ++i) {
	    ArrayList<Coord> path = paths.get(i);
	    if (path == null) {
		System.out.println("failed to find path " + (i+1));
		return false;
	    } else {
		Coord start = path.get(0), end = path.get(path.size() - 1);
		if (! start.equals(points.get(i)[0])) {
		    System.out.println("incorrect start of path " + (i+1)
				       + " got " + start
				       + " not " + points.get(i)[0]);
		    return false;
		}
		if (! end.equals(points.get(i)[1])) {
		    System.out.println("incorrect end of path " + (i+1)
				       + " got " + end
				       + " not " + points.get(i)[1]);
		    return false;
		}
		for (int j= 0; j != path.size(); ++j) {
		    Coord point = path.get(j);
		    if (checked.contains(path.get(j))) return false;
		    for (Integer[] o : obstacles){
			if (o[0] <= point.x && point.x <= o[2]
			    && o[1] <= point.y && point.y <= o[3]){
			    return false;
			}
		    }
		    checked.add(point);
		}
	    }
	}
	return true;
    }
	
    public static void main(String[] args){
	switch(args.length){
	case 1: 
	    if (args[0].equals("batch_test")){
		java.io.File folder = new java.io.File("./inputs");
		for (java.io.File file : folder.listFiles()) {
		    if (file.isFile()) {
			try {
			    Driver.test(file.getCanonicalPath());
			} catch (java.io.IOException e) {
			    System.out.println("bummer!");
			}
		    }
		}
	    }
	    break;
	case 2:
	    if (args[0].equals("test")){
		Driver.test("inputs/" + args[1]);
	    }
	    break;
	default:
	    Driver.test("inputs/gen_chip_10_10.in");
	}
    }
}
