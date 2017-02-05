Align function explanation:

First I intialize a 2D array of Results
Then I intialize the first row and the first column of the 2D results array using two iterative for loops

Next I use a double nested for loop to iterate through all the remaining null indices
At each indices I calculate the cost of a match, insert and delete
Then I choose the max of the three options and insert a result with its score and Mutation value
After overwriting every null index I use a while loop to follow the path from the bottom right to the top left
As I do this traversal I build strings based on the mutations that I visit and then I return my 2D array at then end