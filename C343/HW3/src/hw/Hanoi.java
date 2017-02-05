package hw;

public class Hanoi
{
   static String[] solve(int n)
   { 
	   String[] previous = { null, "", "", "", null, "", "", ""  };
       String[] current = new String[8];   
       String[] tmp;   

      for (int i = 1; i <= n; i++)
      {  
         for (int j = 0; j < current.length; j++)
         {
            int cpeg = j/3;
            int dpeg = j%3;
            int tmp2 = 3 - cpeg - dpeg;

            if (cpeg == dpeg)
            {}
            else
            {
            	current[j] = previous[cpeg*3+tmp2] + (cpeg*3+dpeg) + previous[tmp2*3+dpeg];
            }
            
         }
         // Swap current and prev
         tmp = current;  
         current = previous;  
         previous = tmp;
      }

      return previous;
   }


   public static void main(String[] args)
   {  
	  //I found these test cases online so I could test my function
	  // Specifications for the recursive calls
      int[][] prob = { { 0, 1, 2 },
                       { 0, 2, 1 },
                       { 1, 0, 2 },
                       { 1, 2, 0 },
                       { 2, 0, 1 },
                       { 2, 1, 0 } };
      String[] soln;
      int n = args.length > 0 ? Integer.parseInt(args[0]): 4;
      long elapsed = -System.nanoTime();

      soln = solve(n);
      elapsed += System.nanoTime();
      System.out.printf("Dynamic solutions in %.3f sec.\n", 1E-9*elapsed);
      elapsed = -System.nanoTime();
   }
}
