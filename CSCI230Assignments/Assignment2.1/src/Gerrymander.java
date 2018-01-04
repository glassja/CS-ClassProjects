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
 * Certification of Authenticity:
 * I certify that this code is entirely my own work.
 * ==========================================================================================
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
   public static void main(String[] args)
   {

      Scanner scanner = new Scanner(System.in);

      int methodNum = scanner.nextInt();
      int numRows = scanner.nextInt();
      int numCols = scanner.nextInt();

      ArrayList<Integer> locs = new ArrayList<Integer>();

      //Create the list of locations for asterisks
      boolean end = false;
      while (!end)
      {
         int current = scanner.nextInt();
         if (current == -1)
            end = true;
         else
            locs.add(current);
      }
      
      switch(methodNum)
      {
         case 1: System.out.println(countDistrictsRecursive(locs, numCols));
            break;
         case 2: System.out.println(countDistrictsIterative(locs, numCols));
            break;
      }
   }

   /**
    * Purpose:    recursively determine the number of districts in the grid.
    *
    * @param locs - a list of the locations of an asterisk in the grid
    * @param numCols - the number of columns in the grid
    * @return the number of independent districts in the grid.
    */
   public static int countDistrictsRecursive(List<Integer> locs, int numCols)
   {
      ArrayList<Integer> newLocs = (ArrayList<Integer>) locs;
      int numDistricts = 1;

      if(newLocs.size() == 1)
         numDistricts = 1;
      else if(((newLocs.get(0) - newLocs.get(1)) == -1) && (newLocs.contains(newLocs.get(0) + numCols)))
      {
         newLocs.remove(newLocs.indexOf((newLocs.get(0) + numCols)));
         newLocs.remove(0);
         numDistricts = numDistricts - 1 + countDistrictsRecursive(newLocs, numCols);
      }
      else if((newLocs.get(0) - newLocs.get(1)) == -1)
      {
         newLocs.remove(0);
         numDistricts = numDistricts - 1 + countDistrictsRecursive(newLocs, numCols);
      }
      else if(newLocs.contains(newLocs.get(0) + numCols))
      {
         newLocs.remove(newLocs.indexOf(newLocs.get(0) + numCols));
         numDistricts = numDistricts - 1 + countDistrictsRecursive(newLocs, numCols);
      }
      else
      {
         newLocs.remove(0);
         numDistricts = numDistricts + countDistrictsRecursive(newLocs, numCols);
      }

      return numDistricts;
   }

   /**
    * Purpose: Iteratively determine the number of districts in the grid
    *
    * @param locs - a list of the locations of an asterisk in the grid
    * @param numCols - the number of columns in the grid
    * @return the number of independent districts in the grid.
    */
   public static int countDistrictsIterative(List<Integer> locs, int numCols)
   {
      ArrayList<Integer> newLocs = (ArrayList<Integer>) locs;
      int numDistricts = newLocs.size();

      for(int i = 0; i < newLocs.size()-1; i++)
      {
         if(newLocs.get(i) - newLocs.get(i+1) == -1)
            numDistricts = numDistricts - 1;
         if(newLocs.contains(newLocs.get(i) + numCols))
            numDistricts = numDistricts - 1;
      }
      return numDistricts + 1;
   }
}