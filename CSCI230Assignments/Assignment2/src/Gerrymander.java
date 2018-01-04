/**
 * ==========================================================================================
 *
 * @author Joshua Glass
 * Date:         <Feb. 9, 2016 >
 * Class:        <CSCI 230 - 01>
 * Assignment:   <2>
 * Task:         Take a 2D array and a list of locations for a symbol and determine how many 'districts' are in the array
 * Inputs:       number of method to be called, number of rows, number of columns
 *               locations of asterisks
 * @return       number of districts in the grid
 * <p>
 * Certification of Authenticity:   <include one of the following>
 * I certify that this code is entirely my own work.
 * ==========================================================================================
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Gerrymander
{
   /**
    *  []locs contains the index number of each asterisk in the grid
    *  numRows holds the number of rows input by the user
    *  numCols holds the number of rows input by the user
    *  numDistricts keeps track of the number of districts found in a virtual 2D grid
    *  grid represents the 2D array as a single D array where true = asterisk and false = no asterisk
    *  startIndex keeps track of the current index for testing
    */

   private static int numRows; //numRows and numCols are class attributes in the same manner that length is an attribute
   private static int numCols; // of an array, this will make things much simpler.

   /**
    * Purpose:          <description of method actions>
    *
    * @param args inputs from the user
    * @return the result of the method call to either countDistrictsRecursive() or countDistrictsIterative()
    */
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);

      int methodNum = scanner.nextInt();
      int numRows = scanner.nextInt();
      int numCols = scanner.nextInt();

      ArrayList<Integer> locs = new ArrayList<Integer>;

      //Create the list of locations for asterisks
      boolean end = false;
      while(!end)
      {
         int current = scanner.nextInt();
         if(current == -1)
            end = true;
         else
            locs.add(scanner.nextInt());
      }

      int[] grid = new int[numRows*numCols];

      //Add the "asterisks" to grid and leave the other slots "empty"
      for(int i = 0; i < grid.length; i++)
      {
         if(binaryIsMember(locs, i))
            grid[i] = 2;
         else
            grid[i] = 0;
      }

      System.out.println(countDistrictsRecursive(grid, 0));
   }

   /**
    * Purpose:    <description of method actions>
    *
    * @param grid 1D representation of the user defined 2D array
    * @param startIndex keeps track of where in the array we currently are to help avoid repeat checks
    * @return the number of districts in the grid
    */

   public static int countDistrictsRecursive(int[] grid, int startIndex)
   {
      int numDistricts = 0;

      if(startIndex == 0 && grid[startIndex] == 2)
      {
         numDistricts++;
         numDistricts = numDistricts + countDistrictsRecursive(grid, startIndex + 1);
      }
      else if(startIndex == )
   }

   /**
    * Performs a binary search for the target value in the input array
    * @param arr - the array in which the search will be performed. In this class it will always be locs
    * @param targetValue - the index in grid we are looking for in locs
    * @return Returns true if the value is in the array and false otherwise.
    */
   public static boolean binaryIsMember(int[] arr, int targetValue)
   {
      int min = 0;
      int max = arr.length-1;
      int mid;
      boolean rtnval = false;

      while(min <= max)
      {
         mid = (min + max) / 2;

         if(arr[mid] < targetValue)
            min = mid+1;
         else if(arr[mid] > targetValue)
            max = mid-1;
         else
            rtnval = true;
      }
      return rtnval;
   }

   /**
    *
    * @param grid -the grid we will be accessing values from
    * @param index -the location within the grid
    * @return -returns true if the value in grid[index] >= 2 and false otherwise
    */
   public static boolean checkDegree(int[] grid, int index)
   {
      boolean rtnval = false;

      if(grid[index] >= 2)
         rtnval = true;

      return rtnval;
   }

   /**
    *
    * @param grid -using the int[] grid created, go through the array and calculates the degree of every index by the formula
    *             deg(v) = 0 if no asterisk
    *             deg(v) = 2 if asterisk with no adjacencies
    *             deg(v) = 3 if asterisk with 1 adjacency
    *             deg(v) = 4 if asterisk with 2 adjacencies
    *             deg(v) = 5 if asterisk with 3 adjacencies
    *             deg(v) = 6 if asterisk with 4 adjacencies
    * @return -a new grid with the total degree of each cell
    */
   public static int[] setDegreesInGrid(int[] grid)
   {
      int[] returnGrid = new int[grid.length];

      for(int currentIndex = 0; currentIndex < grid.length; currentIndex++)
      {
         if(currentIndex == 0) //Top left corner of the virtual 2D array
         {
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if(currentIndex > 0 && currentIndex < numCols) //top edge between the corners of the virtual 2D array
         {
            if(checkDegree(grid, currentIndex -1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex = returnGrid[currentIndex] + 1;
         }
         if(currentIndex != 0 && currentIndex % numCols == 0) //left edge between the corners of the virtual 2D array
         {
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if(currentIndex == numCols - 1) //top right corner
         {
            if(checkDegree(grid, currentIndex - 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if(currentIndex > numCols - 1 && currentIndex < grid.length && currentIndex % numCols == 14) //right edge
         {
            if(checkDegree(grid, currentIndex - 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if((currentIndex > (numCols * (numRows - 1)) - 1)) && (currentIndex < grid.length - 1) //bottom edge
         {
            if(checkDegree(grid, currentIndex - 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if(currentIndex == (numCols * (numRows - 1)) - 1) //bottom left corner
         {
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         if(currentIndex == grid.length-1) //bottom right corner
         {
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex - 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }
         else
         {
            if(checkDegree(grid, currentIndex - numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + numCols))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex - 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
            if(checkDegree(grid, currentIndex + 1))
               returnGrid[currentIndex] = returnGrid[currentIndex] + 1;
         }

      }
      return returnGrid;
   }
}