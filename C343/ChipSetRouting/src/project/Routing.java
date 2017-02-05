package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Routing {

	static ArrayList<Coord> checkedVertices = new ArrayList<>();
    public static ArrayList<ArrayList<Coord>>
	findPaths(Board board, ArrayList<Coord[]> points)
    {
	/*-- Students Code --*/
    	
    	ArrayList<ArrayList<Coord>> output = new ArrayList<ArrayList<Coord>>();
    	
    	
    	Map<Coord, Integer> originalGrid = new HashMap<Coord, Integer>();
    	originalGrid.putAll(board.grid);
    	Board SDboard = new Board(originalGrid, board.height, board.width);
    	
    	
    	
    	output = runBFS(board, points);
    	
    	if(output.size() != points.size())
    	{
    		output = runShortestDistance(SDboard, points);
    	}
    	
    	return output;
    }
    
    
    static ArrayList<ArrayList<Coord>> runBFS(Board board, ArrayList<Coord[]> points)
    {
    	ArrayList<ArrayList<Coord>> output = new ArrayList<ArrayList<Coord>>();
    	
		
    	for(Coord[] route : points)
    	{
    		Coord start = route[0];
    		Coord finish = route[1];
    		ArrayList<ArrayList<Coord>> paths = bfs(board, start, finish);
    		for(ArrayList<Coord> p : paths)
    		{
    			if(p.get(p.size()-1).equals(finish))
    			{
    				//mark this path
    				for(Coord c : p)
    				{
    					if(c.equals(start) || c.equals(finish))
    					{
    					}
    					else
    					{
    						board.putValue(c, board.getValue(start));    						
    					}
    				}
    				//add to output
    				output.add(p);
    			};
    		}
    		
    	}
    	
    //board.pretty_print_grid();
    if(output.size() == 0){return null;}    
	return output;
    }
    
    
    static ArrayList<ArrayList<Coord>> runShortestDistance(Board board, ArrayList<Coord[]> points)
    {
    	ArrayList<ArrayList<Coord>> output = new ArrayList<ArrayList<Coord>>();
    	ArrayList<Coord[]> ordered = rearrange(points);
    	
		
    	for(Coord[] route : ordered)
    	{
    		Coord start = route[0];
    		Coord finish = route[1];
    		ArrayList<ArrayList<Coord>> paths = bfs(board, start, finish);
    		for(ArrayList<Coord> p : paths)
    		{
    			if(p.get(p.size()-1).equals(finish))
    			{
    				//mark this path
    				for(Coord c : p)
    				{
    					if(c.equals(start) || c.equals(finish))
    					{
    					}
    					else
    					{
    						board.putValue(c, board.getValue(start));    						
    					}
    				}
    				//add to output
    				output.add(p);
    			};
    		}
    		
    	}
    	
    //board.pretty_print_grid();
    if(output.size() == 0){return null;} 
    
    output = re_rearrange(output, points);
    
	return output;
    }
    
    
    public static ArrayList<ArrayList<Coord>> bfs(Board board, Coord start, Coord finish)
	{
		// BFS uses Queue data structure
    	HashMap<Coord, Boolean> visited = new HashMap<>();
    	ArrayList<ArrayList<Coord>> paths = new ArrayList<ArrayList<Coord>>();
		Queue queue = new LinkedList();
		queue.add(start);
		ArrayList<Coord> tmp = new ArrayList<Coord>();
		tmp.add(start);
		paths.add(tmp);
		//printNode(start);
		visited.put(start, true);
		while(!queue.isEmpty()) {
			Coord node = (Coord)queue.remove();
			//Coord child = null;
			for(Coord c : board.adj(node))
			{
				if(visited.get(c) == null){
					if(board.getValue(c) == 0)//Open coordinate
					{
						ArrayList<Coord> tmp2 = new ArrayList<Coord>(getPath(paths,node));  
						tmp2.add(c);
						paths.add(tmp2);
						//paths.addAll()
						queue.add(c);
					}
					else if(c.equals(finish))//Destination reached
					{
						ArrayList<Coord> tmp2 = new ArrayList<Coord>(getPath(paths,node));  
						tmp2.add(c);
						paths.add(tmp2);
						//paths.addAll()
						queue.add(c);
						return paths;						
					}
					else//Obstacle hit
					{
						
					}
					visited.put(c,true);
					//printNode(child);
				}
			}
		}
		return paths;
	}
    
    public static ArrayList<Coord> getPath(ArrayList<ArrayList<Coord>> paths, Coord node)
    {
    	for(ArrayList<Coord> p : paths)
    	{
    		if(node.equals(p.get(p.size()-1)))
    		{
    			return p;
    		}
    	}
    	return null;
    }
    
	//simply executing the distance formula on two coordinates
	public static double distanceTo(Coord co1, Coord co2) {
		
		return Math.sqrt(Math.pow(co2.x - co1.x, 2) + Math.pow(co2.y - co1.y, 2));
		
	}
	
	//helper method to find the shortest distance between two coordinates
		public static ArrayList<Coord[]> rearrange(ArrayList<Coord[]> points) {
			
			ArrayList<Coord[]> output = new ArrayList<Coord[]>(points.size());
			
			output.add(0, points.get(0));
			
			int pointsCounter = 1;
			int outputCounter = 0;
			
			while(pointsCounter < points.size()) 
			{
				
				if(distanceTo(points.get(pointsCounter)[0], points.get(pointsCounter)[1]) < 
				   distanceTo(output.get(outputCounter)[0], output.get(outputCounter)[1])) 
				{
					output.add(outputCounter, points.get(pointsCounter));
					pointsCounter++;
				}
				else if(outputCounter == output.size() - 1) 
				{
					output.add(output.size() - 1, points.get(pointsCounter));
					pointsCounter++;
					outputCounter = 0;
				}
				else 
				{ 
					outputCounter++;
				}
			}
			return output;	
		
			
		}
		
		
		public  static ArrayList<ArrayList<Coord>> re_rearrange(ArrayList<ArrayList<Coord>> output, ArrayList<Coord[]> order)
		{
			ArrayList<ArrayList<Coord>> correctOutput = new ArrayList<ArrayList<Coord>>(output);
			
			
			for(ArrayList<Coord> path : output)
			{
				Coord oldSpot = path.get(0);
				
				int j = 0;
				for(Coord[] newSpot : order)
				{
					if(oldSpot == newSpot[0])
					{
						correctOutput.set(j, path);
					}
					j++;
				}
			}
			return correctOutput;
		}

}
