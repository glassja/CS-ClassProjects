package HanoiAndSalesman;

/**
 * I certify that this code is entirely my own with the exception of
 * the towers algorithm for which the document posted on the Piazza was used
 * to aid in implementation.
 *
 * Joshua Glass
 */

import java.util.Stack;
public class HanoiProblem
{
   private static Stack<Disk> A = new Stack<>();
   private static Stack<Disk> B = new Stack<>();
   private static Stack<Disk> C = new Stack<>();

   private static class Disk
   {
      private int size;
      public Disk(int size){this.size = size;}

      public int getSize(){return this.size;}

      public String toString()
      {
         String str = "";
         for(int i = 0; i < size; i++)
            str += "* ";
         return str;
      }
   }

   public static void main()
   {
      final long mainStart = System.nanoTime();
      for(int i = 1; i <= 30; i++)
      {
         buildTowersPuzzle(i);
         int numCycles = 10;

         System.out.println("Problem Size: " + i + "\nAverage Time Required: " +
                 (double)avgOfN(numCycles, A, B, C, i)/1000000000 + " seconds for " + numCycles + " cycles\n");
         A.clear();
         B.clear();
         C.clear();
      }
      final long mainEnd = System.nanoTime();
      System.out.println("\nTotal Program Time: " + (double)(mainEnd - mainStart)/1000000000 +
              " seconds.");
   }

   public static long avgOfN(int numCycles, Stack<Disk> begin, Stack<Disk> auxillary, Stack<Disk> target, int n)
   {
      long sum = 0;

      for(int i = 1; i <= numCycles; i++)
      {
         buildTowersPuzzle(n);

         sum += timedHanoi(begin, auxillary, target, n);

         A.clear();
         B.clear();
         C.clear();
      }

      return sum/numCycles;
   }

   long average(long[] arr)
   {
      long sum = 0;
      for(int i = 0; i < arr.length; i++)
      {
         sum+=arr[i];
      }
      return sum/arr.length;
   }

   public static void buildTowersPuzzle(int n)
   {
      for(int i = 1; i <= n ; i++)
      {
         Disk disk = new Disk(i);
         A.push(disk);
      }
   }

   static void printTower(Stack<Disk> stack)
   {
      if(stack.empty())
         System.out.println("empty");
      else
      {
         for (int i = 0; i < stack.size(); i++)
         {
            System.out.println(stack.get(i).toString());
         }
      }
   }

   private static void Hanoi(Stack<Disk> begin, Stack<Disk> auxillary, Stack<Disk> target, int n)
   {
      if(n == 1)
      {
         moveDisk(begin, target);
      }
      else
      {
         Hanoi(begin, target, auxillary, n-1);
         moveDisk(begin, target);
         Hanoi(auxillary, begin, target, n-1);
      }
   }

   public static long timedHanoi(Stack<Disk> begin, Stack<Disk> auxillary, Stack<Disk> target, int n)
   {
      final long start = System.nanoTime();
      Hanoi(begin, auxillary, target, n);
      final long end = System.nanoTime();

      return (end - start);
   }
   private static void moveDisk(Stack<Disk> from, Stack<Disk> to){to.push(from.pop());}
}
