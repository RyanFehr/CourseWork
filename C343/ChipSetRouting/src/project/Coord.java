package project;

public class Coord {
    public final int x;
    public final int y;
    public Coord(final int _x, final int _y) {
        this.x = _x;
        this.y = _y;
    }
    
    public boolean equals(Object obj){
    	if (!(obj instanceof Coord)){
    		return false;
    	}
    	else{
    		Coord coord = (Coord) obj;
    		return this.x == coord.x && this.y == coord.y;
    	}
    }
    
    public int hashCode(){
    	String repr = String.valueOf(this.x) + " " + String.valueOf(this.y);
    	return repr.hashCode();
    }
    
    public String toString(){
    	return "(" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ")";
    }
}
