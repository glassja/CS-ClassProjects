package PumpkinWalk;

import java.io.IOException;

/**
 * Created by joshuaglass on 10/30/16.
 *
 * I certify that this code is entirely my own with references to:
 *    "Introduction to The Design and Analysis of Algorithms" by Anany Levitin.
 */
public class PumpkinMatch
{

   private static int[][] subseqMatrix;

   public static void main(String[] args) throws IOException
   {
      String[] inputs = new String[8];
      inputs[0] = "ASAB";
      inputs[1] = "AJDSADB";
      inputs[2] = "ASAB";
      inputs[3] = "HDSKNAJB";
      inputs[4] = "AAA";
      inputs[5] = "AAAAAAAAAA";
      inputs[6] = "AAabSsDd";
      inputs[7] = "ASASASBSKSKDKDKD";

      for( int i = 0; i < inputs.length-1; i+=2)
      {
         int checkLength = pumpkinMatchMemo(inputs[i], inputs[i+1]);
         boolean checkBool = checkLength(inputs[i], checkLength);
         System.out.print("Sebastian's String: " + inputs[i] + "\n Pumpkin Walk String: "
                 + inputs[i+1] + "\n Output: ");
         if(checkBool)
            System.out.print("true\n\n");
         else if(!checkBool && checkLength > 0)
            System.out.print(checkLength + "\n\n");
         else
            System.out.print("false\n\n");
      }
   }

   public static int pumpkinMatchRecursive(String A, String B)
   {

      if (A.length() == 0 || B.length() == 0)
      {
         return 0;
      }
      else if (A.charAt(0) == (B.charAt(0)))
      {
         return 1 + pumpkinMatchRecursive(A.substring(1), B.substring(1));
      }
      else
         return Math.max(pumpkinMatchRecursive(A, B.substring(1)), pumpkinMatchRecursive(A.substring(1), B));
   }

   public static boolean checkLength(String in, int len){
      if(len == in.length())
         return true;
      else
         return false;
   }

   public static int pumpkinMatchMemo(String A, String B)
   {
      subseqMatrix = new int[A.length() + 1][B.length() + 1];
      int max = -1;
      for (int i = 0; i < A.length(); i++)
      {
         for (int j = 0; j < B.length(); j++)
         {
            subseqMatrix[i][j] = 0;
         }
      }

      for (int aChar = 0; aChar < A.length(); aChar++)
      {
         for (int bChar = 0; bChar < B.length(); bChar++)
         {
            if (A.charAt(aChar) == B.charAt(bChar))
            {
               subseqMatrix[aChar + 1][bChar + 1] = subseqMatrix[aChar][bChar] + 1;
               if (subseqMatrix[aChar + 1][bChar + 1] > max)
                  max = subseqMatrix[aChar + 1][bChar + 1];
            }
            else
            {
               subseqMatrix[aChar + 1][bChar + 1] = Math.max(subseqMatrix[aChar + 1][bChar], subseqMatrix[aChar][bChar + 1]);
               if (subseqMatrix[aChar + 1][bChar + 1] > max)
                  max = subseqMatrix[aChar + 1][bChar + 1];
            }
         }
      }
      return max;
   }

  /*
  non-functional. upon tracing code in the matrix the error was realized as a problem with a recursive implementation

  private static int pumpkinMatch(int x, int y, String A, String B){

      if(subseqMatrix[x][y] == 0)
      {
         if ( x == 0 || y == 0)
         {
            if(x == 0 && (A.charAt(x) == B.charAt(y)))
               subseqMatrix[x][y] = subseqMatrix[x][y] + 1;
            else if(y == 0 && (A.charAt(x) == B.charAt(y)))
               subseqMatrix[x][y] += 1;
            else
               subseqMatrix[x][y] = 0;
            if(subseqMatrix[x][y] > max)
               max = subseqMatrix[x][y];
         }
         else if (A.charAt(x) == B.charAt(y))
         {
            subseqMatrix[x][y] = 1 + pumpkinMatch(x - 1, y - 1, A, B);
            if(subseqMatrix[x][y] > max)
               max = subseqMatrix[x][y];
         }
         else
         {
            subseqMatrix[x][y] = Math.max(pumpkinMatch(x - 1, y, A, B), pumpkinMatch(x, y - 1, A, B));
            if(subseqMatrix[x][y] > max)
               max = subseqMatrix[x][y];
         }
      }
      return max;
   }*/

}
