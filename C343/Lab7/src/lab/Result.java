package lab;

public class Result {

	Coord pixel;
	int cost;
	Result bottom;
	Result(Coord p, int c, Result b)
	{
		pixel = p;
		cost = c;
		bottom = b;
	}
}
