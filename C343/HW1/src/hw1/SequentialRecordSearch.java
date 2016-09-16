package hw1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SequentialRecordSearch {
	
	Object[] recordArray;
	
	public SequentialRecordSearch(Object[] recordArray)
	{
		this.recordArray = recordArray;
	}
	
	
	//SequentialSearchMethods for different key types
	public Object SequentialSearch(String key, String value)
	{
		Method toCall = null;
		try {
			toCall = recordArray[0].getClass().getMethod("get"+key);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		Object a = null;
				for(Object record : recordArray)
				{
							String out = null;
							try {
								out = (String) toCall.invoke(record);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							if(out == value)
							{
								System.out.println(record);
								return record;
							}
				}
		return a;
	}

	public Object SequentialSearch(String key, int value)
	{
		Method toCall = null;
		try {
			toCall = recordArray[0].getClass().getMethod("get"+key);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		Object a = null;
				for(Object record : recordArray)
				{
							int out = -1;
							try {
								out = (int) toCall.invoke(record);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							if(out == value)
							{
								System.out.println(record);
								return record;
							}
				}
		return a;
	}
	
	public Object SequentialSearch(String key, Double value)
	{
		Method toCall = null;
		try {
			toCall = recordArray[0].getClass().getMethod("get"+key);
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		}
		Object a = null;
				for(Object record : recordArray)
				{
							Double out = -1.0;
							try {
								out = (Double) toCall.invoke(record);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							if(out.doubleValue() == value.doubleValue())
							{
								System.out.println(record);
								return record;
							}
				}
		return a;
	}
	//////////////////////////////////////////////////
	
	public static void main(String[] args) 
	{
		//Instantiating Object Arrays
		Student s1 = new Student("47172", "Bob", 12, 3.5);
		Student s2 = new Student("47170", "Dan", 15, 4.0);
		Student s3 = new Student("47070", "Cindy", 31, 2.1);
		Student s4 = new Student("47404", "Sue", 21, 2.9);
		Student s5 = new Student("47404", "John", 9, 3.7);
		
		Object[] studentRecords = {s1,s2,s3,s4,s5};
		
		Professor p1 = new Professor("Jeremy",32,"C343", 42);
		Professor p2 = new Professor("Dirk",54,"B461", 51);
		Professor p3 = new Professor("Diana",52,"A202", 45);
		Professor p4 = new Professor("Robert",41,"H211", 38);
		Professor p5 = new Professor("Steve",43,"C291", 65);
		
		Object[] professorRecords = {p1, p2, p3, p4, p5};
		///////////////////////////////////////////////////
		
		SequentialRecordSearch test = new SequentialRecordSearch(studentRecords);
		SequentialRecordSearch test2 = new SequentialRecordSearch(professorRecords);
		test.SequentialSearch("Zipcode","47070");
		test.SequentialSearch("Name","Dan");
		test.SequentialSearch("Salary",21);
		test.SequentialSearch("Gpa",3.5);
		
		System.out.println("");
		test2.SequentialSearch("Age", 41);
		test2.SequentialSearch("Name", "Jeremy");
		test2.SequentialSearch("ClassNumber", "B461");
		test2.SequentialSearch("Salary", 65);
		
		
	}

}



///Additional Exercises
/// 2.2
/////1. Proof: a and b are arbitrary integers
/////  		We assume  a=b therefore we can see that a+b. 
/////   From here we can see that a+b = a+a = 2a which is 
/////   divisible by 2 thus making it even			 
/////2. Counter-Example: a= 1/2, b = 1/2  a=b but (1/2)/(1/2) != integer

/// 2.3
/////a. 	Father of is not a partial ordering  because it is not transitive in that 
/////   a cannot be a father of b and b be a father of c and then a be a father of c
/////   because a would be a grandfather of c
/////b. 	It is a partial ordering because a can be a ancestor of b but b cannot be a 
/////   ancestor to a if the first part holds true making it antisymmetric. In addition
/////   to this we know that that if a is a ancestor to b and b is a ancestor to c then
/////   we can clearly see that a is also a n ancestor of c thus making it transitive.
/////c. 	IsOlder than is antisymmetric because if a is older than b then necessarily
/////   b cannot be older than a. In addition is older than is transitive in that if a 
/////   is older than b and b is older than c then we know a is older than c because he
/////   must be in order to be older than c. a>b and b>c therefore a>c    a>b  b!>a
/////d. 	Sister of is not antisymmetric in that a can be a sister of b and b can be 
/////   a sister of a where a and b are two different girls
/////e. 	This is not partial ordering because <b,b> does not exist for the transitive
/////   pair <b,a> and <a,b>
/////f. 	This is a partial ordering because the only transitive pair is complete, which
/////   goes from pair <2,1> and <1,3> to its' transitive <2,3> and there are no 
////    symmetric pairs, thus making it anti-symmetric.


/// 2.5 An ADT for a set of Integers
/////   Type: Set of Integers				 Input     Output
/////   *Add an Integer to the set           int       void      
/////   *Find the cardinality of the set	 void      int       
/////   *Return true if the set is empty	 void      boolean
/////   *Delete a integer out of the set     int	   void
/////   *List the contents of the set        void      String
/////   *See if a Integer is in the set      int       boolean

/// 2.15 Permutations Function is in hw1 folder under Permutations class

/// 2.19 
/////   n^2-n is always even
/////a. Basis Step: n = 1  1*1 = 1 -1 = 0 which is even
/////   Assume then that (n^2)-n is even.
/////   Now lets consider the case ((n+1)^2)-(n+1)
/////   n^2 + 2n +1 - n- 1
/////   (n^2 - n) + 2n + 1 - 1
/////   (n^2 - n) + 2n
/////   Above we assumed n^2-n is even and we know that 2*n is even because it is divisible 
/////   by 2 and an even plus an even is also divisible by two because individually they are 
/////   divisible by two thus proving that n^2-n is always even
/////
/////b. n^2 - n is always even because an odd number times and odd number is a odd number and 
/////   an odd minus an odd is always even. In addition to this an even times and even is always 
/////   an even and a even number minus an even is always even.
/////
/////c. Basis Step: n=1 , 1^3 - 1 = 0 which is divisible by 3 
/////   We assume n^3 -n is divisible by 3
/////   Now lets consider the case ((n+1)^3) - (n+1)
/////   ((n^2 + 2n +1)(n+1)) - (n+1)
/////   (n^3  + 3n^2 + 3n + 1) - (n+1)
/////   n^3  + 3n^2 + 3n + 1 - n - 1
/////   (n^3 - n) + 3n^2 + 3n
/////   (n^3 - n) + 3(n^2 + n)
/////   Above we assumed that (n^3 - n) was divisible by 3 and we see that 3(n^2 + n) is divisible 
/////   by three because it is multiplied by three. In addition to this we know that the sum of 
/////   two numbers divisible by three is also divisible by three.
/////
/////d. (n^5) - n is always divisible by 5 because if we look at problems 2a and 2c above, we can 
/////   use 2a as our base case for a problem saying (n^x)-n is always divisible by x where x>=2.
/////   Thus making 2c our (n^(x+1))-n is divisible by x+1 case and thus proving that (n^5)-n is
/////   indeed divisible by 5.

/// 2.30 Pigeon hole principle:
/////2.		Basis Step: k=1, we have only one hole and two pigeons already placed into that 
/////  one hole. So, it is clear that the conclusion is true for n=1. 

/////  Assume the principle holds true for k  with k+1 pigeons.

/////  Consider the case of k+2 objects distributed among k + 1 boxes. 
/////  We know that a box will have 0, 1, or two+ pigeons in it. If it has two+ pigeons in it,
/////  we then our assumption holds. If it has one pigeon, the remaining k+1 objects are distributed among
/////  the k other boxes, but by the assumption, one of those k boxes contains at least
/////  two pigeons. If it has zero pigeons, then,  k + 1 of the remaining
/////  k + 2 pigeons are spread among the k boxes. Which means one of those k boxes has 2 pigeons.

///4.7 sort function is located in hw1 folder under MergedSort class

///4.15 palindrome algorithm is located in detect function in hw1 folder under Palindrome class
/////   This algorithm stores the first half of the word in one stack and the second half in 
/////   a second stack. It then skips over a middle letter if it is a word with an odd number
/////   of characters and then it pops all the characters out of the second stack and into a
/////   third stack so that they are ordered identical to the first stack. Then it compares
/////   a pop from stack 1 and 3 returning false if a pair of pops does not match and returning 
/////   true if the bottom of the stacks was reached.

///4.3 functions are located in hw1 folder under SinglyLinkedListOperations class















