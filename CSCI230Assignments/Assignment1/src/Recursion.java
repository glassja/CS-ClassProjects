/**
 * ================================================================================
 * Author:       <Joshua Glass>
 * Date:         <Thurs. Jan. 21, 2016>
 * Class:        <CSCI 230-01>
 * Assignment:   <1>
 * Task:         <To implement recursive fucntions of common operations on arrays>
 * Input:        <Scanner object used to take in all inputs
 *                integer value to determine which method to call
 *                a list of integers where the first value is the length of the following list
 *                Optional depending on method called:
 *                an integer value for a starting index
 *                an integer value to search for in the list>
 * Output:       <Outputs of the program are the raw values returned by the method calls>
 *
 * Certification of Authenticity:  
 * I certify that this code is entirely my own work.
 * ================================================================================
 **/

import java.util.Scanner;

public class Recursion
{
   /*
    *  Purpose:          <Take and store inputs from the user, use a switch case to
    *                     execute a method and return the approriate value>
    *  Preconditions:   <No formal parameters but first action is a scanner prompt>
    *  Postconditons:   <Return value of the method called>
   **/
   public static void main(String[] args)
   {
      int[] list;        //all variables needed for any possible method called
      int item;
      int startIndex;

      Scanner scanner = new Scanner(System.in);
      int methodNum = scanner.nextInt();

      int listLength = scanner.nextInt();
      list = new int[listLength];
      for(int x = 0; x < listLength; x++)
      {
         list[x] = scanner.nextInt();
      }

      // switch statement obtains the needed variable values and calls the appropriate method from the input value
      switch (methodNum)
      {
         case 1:
         {
            item = scanner.nextInt();
            startIndex = scanner.nextInt();
            System.out.println(isMember(list, item, startIndex));
            break;
         }

         case 2:
         {
            startIndex = scanner.nextInt();
            System.out.println(numberItems(list, startIndex));
            break;
         }

         case 3:
         {
            startIndex = scanner.nextInt();
            System.out.println(numberItemsReverse(list, startIndex));
            break;
         }

         case 4:
         {
            item = scanner.nextInt();
            startIndex = scanner.nextInt();
            System.out.println(countItem(list, item, startIndex));
            break;
         }
         case 5:
         {
            System.out.println(reverseList(list));
            break;
         }
      }


   }
   /*
   *  Purpose:          searches list from startIndex to end to determine if
    *                   item is in list
   *  Preconditions:    integer array from user, target integer, starting index
   *  Postconditons:    list = list
   *                    item = item
   *                    startIndex = startIndex
   *                    currentIndex = list.length-1
   *                    rtnval = true or false
  **/
   public static boolean isMember(int[] list, int item, int startIndex)
   {
      boolean rtnval = false;

      if(startIndex == list.length-1 && list[startIndex] != item) //Ensures the index doesn't exceed the size of the array
      {
         rtnval = false;
      }
      else if(list[startIndex] == item)
      {
         rtnval = true;
      }
      else
         rtnval = isMember(list, item, startIndex+1);

      return rtnval;

   }

   /**
    * Purpose:          Return the number of items in the list, inclusively, from
    *                   a startIndex to the end of the list
    * Preconditions:    int array from the user, starting index
    * Postconditions:   list = list
    *                   startIndex = startIndex
    *                   rtnval = number of items in list
    * */
   public static int numberItems(int[] list, int startIndex)
   {
      int count = 0;  //count will be the return value. starts at 0 b/c the recursive call adds 1

      if(startIndex != list.length) //Check if the index entered is in the array
      {                             //to avoid ArrayIndexOutOfBounds exceptions
         count = 1 + numberItems(list, startIndex+1);
      }

      return count;
   }

   /**
    * Purpose:     Return the number of items in the list, inclusively, from a StartIndex
    *              backwards to the beginning of the list.
    * Preconditions:     int array from the user, and a starting index
    * Postconditions:     list = list
    *                     startIndex = -1
    *                     count = number of items in list from original [startIndex, index0]
    */
   public static int numberItemsReverse(int[] list, int startIndex)
   {
      int count = 0; //count will be the return value. starts at 0 b/c the recursive call adds 1.

      if(startIndex >= 0)
      {
         count = 1 + numberItemsReverse(list, startIndex - 1);
      }

      return count;
   }
   /**
    * Purpose:     Return the number of occurances of an item in the list, inclusively, from a StartIndex
    *              to the end of the list.
    * Preconditions:     int array from the user, and a starting index
    * Postconditions:     list = list
    *                     startIndex = -1
    *                     count = number of copies of item encountered in the list
    */
   public static int countItem(int[] list, int item, int startIndex)
   {
      int count = 0;  // No copies of item found yet

      if(startIndex != list.length-1 && list[startIndex] == item) //an encounter with an item
      {
         count = 1 + countItem(list, item, startIndex+1);
      }
      else if((startIndex != list.length-1) && (list[startIndex] != item)) // no item but not the end
      {
         countItem(list, item, startIndex+1);
      }
      else if(startIndex == list.length && list[startIndex] == item) // last index is an item
         count++;                                                    // it it's not we just go to the return statement

      return count;
   }
   /**
    * Purpose:     Return a reversed copy of the input list
    * Preconditions:     int array from the user
    * Postconditions:     list = list
    *                     revList = list in reverse order
    */
//   public static int[] reverseList(int[] list)
//   {
//      int[] revList = new int[list.length];
//      int[] tempList = new int[list.length-1];
//      int currentIndex = list.length-1;
//      int listIndex = 0;
//
//      if(currentIndex != 0)
//      {
//         revList[currentIndex] = list[listIndex];
//         for (int i = 0; i < tempList.length; i++)  //for loop creates the new parameter list for the recursive call
//            tempList[i] = list[i];
//
//         revList = reverseList(tempList);
//      }
//
//      return revList;
//   }

   public static int[] reverseList(int[] list)
   {
      int[] result;

      if(list.length == 0)
      {
         result = list;
      }
      else
      {
         //provide enough space
         result = new int[list.length];
         //create subproblem
         int[] subList = new int[list.length-1];

         //copy list[1:] into subList
         for(int i = 1; i < list.length; i++)
            subList[i-1] = list[i];

         //recur on subList
         int[] subResult = reverseList(subList);

         //combine results
         for(int i = 0; i < subResult.length; i++)
            result[i] = subResult[i];

         result[list.length-1] = list[0];
      }
      return result;
   }
}