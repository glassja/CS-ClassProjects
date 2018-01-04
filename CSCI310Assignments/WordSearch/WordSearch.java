package WordSearch; /**
 * ==========================================================================================
 *
 * @author Joshua Glass
 *         Date:         <09/23/2016>
 *         Class:        <CSCI 310-01>
 *         Assignment:   <Assignment 3>
 *         Task:         <Solve a word search puzzle given a grid of characters
 *                        and a list of words to find.>
 * @return [description of the results of the program execution
 * exclude prompts]
 * <p>
 * Certification of Authenticity:   <include one of the following>
 * I certify that this code is entirely my own work.
 * I certify that this code is my work but, I recieved some assistance from:
 *    Dr. McCauley
 * ==========================================================================================
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class WordSearch
{
   private static char[][] wordGrid;
   private static ArrayList<String> wordList;
   private static int MAX_INDEX;

   /**
    * Purpose:          <description of method actions>
    *
    * @param args the name of the file to be read from
    */
   public static void main(String[] args) throws Exception
   {
      wordList = new ArrayList<String>();
      String filename= "WordSearch/puzzlesTest1.txt";
      //Exception e = new FileNotFoundException();
     // try
     // {
     //    if(args.length == 1)
     //       filename = args[0];
     //    else
     //       throw e;
     // }
      //catch (Exception e1)
     // {
     //    e1.printStackTrace();
     // }

      try
      {
         BufferedReader buffer = new BufferedReader(new FileReader(filename));

         MAX_INDEX = Integer.parseInt(buffer.readLine());
         wordGrid = new char[MAX_INDEX+1][MAX_INDEX+1];

         String line;
         for(int i = 0; i <= MAX_INDEX; i++)
         {
            line = buffer.readLine();
            if(line.length() != MAX_INDEX)
               System.exit(0);

            for(int j = 0; j <= MAX_INDEX; j++)
            {
               wordGrid[i][j] = line.charAt(j);
            }
         }

         String row = buffer.readLine();
         while( row != null)
         {
            wordList.add(row);
            row = buffer.readLine();
         }
         buffer.close();


      }
      catch(java.io.IOException e2)
      {
         e2.printStackTrace();
      }

      for(int x = 0; x <= MAX_INDEX; x++){
         for(int y = 0; y <= MAX_INDEX; y++){
            System.out.print(wordGrid[x][y]+" ");
         }
         System.out.println();
      }

      solve();

   }

   /**
    * Purpose:    solves the crossword puzzle from the object's grid and word list
    */

   public static void solve()
   {
      while(wordList.size()>0){
         if(findWord(wordList.get(0)))
            wordList.remove(0);
      }
   }

   /**
    * Purpose:    Finds a given word in the grid by a sequential style search
    * @param word The word to be found
    * @return true if the word is found, false otherwise.
    */
   private static boolean findWord(String word)
   {
      boolean wordFound = false;

      if(checkHorizontal(word))
         return true;
      else if(checkVertical(word))
         return true;
      else if(checkDiagonal(word))
         return true;
      return false;
   }

   /**
    *Purpose:   Checks the horizontal rows of character for the given word
    * @param word The word to be found
    * @return true if the word is found, false otherwise.
    */
   private static boolean checkHorizontal(String word)
   {
      boolean wordFound = false;
      for(int row = 0; row <= MAX_INDEX; row++){
         String gridRow = "";
         for(int i = 0; i <= MAX_INDEX; i++){
            gridRow += wordGrid[row][i];
         }
         if(checkWordForward(word, gridRow)){
            System.out.println("found the word: " + word + " in the horizontal string: " + gridRow );
            return true;
         }
         else if(checkWordBackward(word, gridRow))
         {
            System.out.println("found the word: " + word + " in the horizontal string: " + gridRow);
            return true;
         }
      }
      return false;
   }

   /**
    *
    * @param word the word to be found
    * @param gridSequence the particular string of chars from the grid to search in
    * @return true if the word is found, false otherwise
    */
   private static boolean checkWordForward(String word, String gridSequence)
   {
      String checkAgainst = gridSequence+gridSequence;
      boolean checkBool = false;

      for(int i = 0; i < checkAgainst.length()-word.length(); i++){
         String checkSubstring = checkAgainst.substring(i, i+word.length());
         if(checkSubstring.equalsIgnoreCase(word))
            return true;
      }
      return false;
   }

   /**
    *
    * @param word the word to be found
    * @param gridSequence the particular string of chars from the grid to search in
    * @return true if the word is found, false otherwise
    */
   private static boolean checkWordBackward(String word, String gridSequence)
   {
      String gridStringRev = "";
      for(int x = gridSequence.length()-1; x >= 0; x--)
      {
         gridStringRev += gridSequence.charAt(x);
      }

      String checkAgainst = gridStringRev+gridStringRev;

      boolean checkBool = false;

      for(int i = 0; i < checkAgainst.length()-word.length(); i++){
         String checkSubstring = checkAgainst.substring(i, i+word.length());
         if(checkSubstring.equalsIgnoreCase(word)){
            return true;
         }
      }

      return false;
   }

   /**
    *
    * @param word the word to be found
    * @return true if the word is found, false otherwise
    */
   private static boolean checkVertical(String word)
   {
      boolean wordFound = false;
      for(int col = 0; col <= MAX_INDEX; col++){
         String gridCol = "";
         for(int row = 0; row <= MAX_INDEX; row++){
            gridCol = gridCol + wordGrid[row][col];
         }
         if(checkWordForward(word, gridCol))
         {
            System.out.println("found the word: " + word + " in the vertical string: " + gridCol);
            return true;
         }
         else if(checkWordBackward(word, gridCol))
         {
            System.out.println("found the word: " + word + " going backwards in the vertical string: " + gridCol);
            return true;
         }
      }
      //System.out.println("word not found");
      return false;
   }

   /**
    *
    * @param word the word to be found
    * @return true if the word is found, false otherwise
    */
   private static boolean checkDiagonal(String word)
   {
      boolean wordFound = false;
      for(int row = 0; row <= MAX_INDEX; row++)
      {
         String gridDiagLR = "";
         for (int col = 0; col <= MAX_INDEX; col++)
         {
            gridDiagLR = gridDiagLR + wordGrid[(row+col)%MAX_INDEX][col];
         }
         if(checkWordForward(word, gridDiagLR))
         {
            System.out.println("found the word: " + word + " in the diagonal string: " + gridDiagLR);
            return true;
         }
         else if (checkWordBackward(word, gridDiagLR))
         {
            System.out.println("found the word: " + word + " going backwards in the diagonal string: " + gridDiagLR);
            return true;
         }

         String gridDiagRL = "";
         for(int col = 10; col >= 0; col--)
         {
            gridDiagRL = gridDiagRL + wordGrid[row][col];
         }
         if(checkWordForward(word, gridDiagRL))
         {
            System.out.println("found the word: " + word  + " in the diagonal string: " + gridDiagRL);
            return true;
         }
         else if(checkWordBackward(word, gridDiagRL))
         {
            System.out.println("found the word: " + word + " going backwards in the diagonal string: " + gridDiagRL);
            return true;
         }
      }
      return false;
   }
}