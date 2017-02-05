package project;
public class AlignmentFunction {

    static Result[][] align(final String s1, final String s2, StringBuilder s1_aligned, StringBuilder s2_aligned) 
    {
    	Result[][] table = new Result[s1.length()+1][s2.length()+1];
    	table[0][0] = new Result(0,null);
    	for(int j = 1; j<s2.length()+1;j++)
    	{
    		table[0][j] = new Result(table[0][j-1].score-1, Mutation.I);
    	}
    	for(int i = 1; i<s1.length()+1;i++)
    	{
    		table[i][0] = new Result(table[i-1][0].score-1,Mutation.D);
    	}
    	
    	for(int i = 1; i<s1.length()+1;i++)
    	{
    		for(int j = 1; j<s2.length()+1;j++)
    		{
    			int matches = -2;
    			if(s1.charAt(i-1) == s2.charAt(j-1)){matches = 2;}
    			
    			int M = table[i-1][j-1].score + matches;//Driver.match(s1.charAt(i-1),s2.charAt(j-1));
    			int I = table[i][j-1].score -1;
    			int D = table[i-1][j].score -1;
    			int best = Math.max(M, Math.max(I, D));
    			
    			if(best == M)
    			{
    				table[i][j] = new Result(best, Mutation.M);    				
    			}
    			else if(best == I)
    			{
    				table[i][j] = new Result(best, Mutation.I);
    			}
    			else
    			{
    				table[i][j] = new Result(best, Mutation.D);
    			}
    		}
    	}
    	
    	Mutation next;
    	int i = s1.length();
    	int j = s2.length();
    	int s1Letter = s1.length()-1;
    	int s2Letter = s2.length()-1;
    	next = table[i][j].dir;
    	
    	if(next==null){return table;}
    	do
    	{
    		switch(next){
    			case M:
    				//add a letter from both to SB
    				s1_aligned.append(s1.charAt(s1Letter));
    				s2_aligned.append(s2.charAt(s2Letter));
    				s1Letter--;
    				s2Letter--;
    				i--;
    				j--;
    				break;
    			case I:
    				//add a _ to S1 and the letter to s2
    				s1_aligned.append("_");
    				s2_aligned.append(s2.charAt(s2Letter));
    				s2Letter--;
    				j--;
    				break;
    			case D:
    				//add a letter to S1 and _ to S2
    				s1_aligned.append(s1.charAt(s1Letter));
    				s2_aligned.append("_");
    				s1Letter--;
    				i--;
    				break;
    		}
    		next = table[i][j].dir;
    	}while(next != null);
    	//s1_aligned = s1_aligned.reverse();
    	//s2_aligned = s2_aligned.reverse();
    	
	// put your solution here
    	return table;
    }

}
