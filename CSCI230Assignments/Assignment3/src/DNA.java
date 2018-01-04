/**
 * ==========================================================================================
 *
 * @author Joshua Glass
 *         Date:         <>Tues. 3/1/16</>
 *         Class:        <>CSCI 230-01</>
 *         Assignment:   <>3</>
 *         Task:         <>Implement a doubly linked list that models DNA</>
 *
 * Certification of Authenticity:
 * I certify that this code is entirely my own work.
 * =========================f=================================================================
 */
import java.util.Scanner;
import java.util.ArrayList;


public class DNA
{
   /**
    *  lst is a List of Lists of Nucleotides to help maintain generality and reduce side effects
    *  numElements stores the logical length of the linked list
    *  leftHelix stores one of the DNA strands
    *  rightHelix stores the other, complementary, DNA strand
    *  rightStart, leftStart, rightEnd, and leftEnd are the sentinal nodes for the start and end of the list
    */

   private Nucleotide[] lst = new Nucleotide[2];
   private int numElements;
   private Nucleotide leftHelix;
   private Nucleotide rightHelix;
   private Nucleotide rightEnd, leftEnd;

   private class Nucleotide
   {
      private char base;       //private b/c access to the base should be restricted where as the links are
      public Nucleotide next;  // constantly available to be changed.
      public Nucleotide across;

      /**
       *
       * @param base the nucleotide base to be represented by the nucleotide.
       *             ' ' should be used as the "null" value.
       */
      public Nucleotide(char base)
      {
         this.base = base;
         this.next = null;
         this.across = null;
      }
      //getter for base
      public char getBase(){ return this.base;} // needed to access base value
      //setter for base
      public void changeBase(char newBase){ this.base = newBase;}

      public String toString()
      {
         return String.valueOf(this.getBase());
      }

      public Nucleotide copyNuc()
      {
         Nucleotide copyNuc;
         char cBase = this.getBase();
         String copyBase = String.valueOf(cBase);
         char newBase = copyBase.charAt(0);

         copyNuc = new Nucleotide(newBase);

         return copyNuc;
      }
   }


   /**
    * @param args command line inputs to be parsed and utilized
    */
   public static void main(String[] args)
   {
      int methodNum;

      DNA lst = new DNA();

      Scanner scanner = new Scanner(System.in);
      int numLines = scanner.nextInt();

      ArrayList<String[]> lines = new ArrayList<String[]>();
      for(int j = 0; j < numLines + 1; j++)
      {
         lines.add(scanner.nextLine().split(" "));
      }
      lines.remove(0); //the zero index entry was empty for some reason so we take it off

      int[] methodNums = new int[numLines];
      for(int i = 0; i < numLines; i++)
      {
         methodNum = Integer.parseInt(lines.get(i)[0]);
         switch(methodNum)
         {
            case 1:
               lst.insert(Integer.parseInt(lines.get(i)[1]), lines.get(i)[2]);
               break;
            case 2:
               System.out.println(lst.remove(Integer.parseInt(lines.get(i)[1])));
               break;
            case 3:
               lst.print(Integer.parseInt(lines.get(i)[1]), Integer.parseInt(lines.get(i)[2]));
               break;
            case 4:
               lst.clear();
               break;
            case 5:
               System.out.println(lst.isEmpty());
               break;
            case 6:
               System.out.println(lst.getLength());
               break;
            case 7:
               System.out.println(lst.find(lines.get(i)[1]));
               break;
            case 8:
               lst.printLeft();
               break;
            case 9:
               lst.printRight();
               break;
            case 10:
               lst.printBasePair(Integer.parseInt(lines.get(i)[1]), Integer.parseInt(lines.get(i)[2]));
               break;
            case 11:
               lst.insertSequence(Integer.parseInt(lines.get(i)[1]), lines.get(i)[2]);
               break;
            case 12:
               System.out.println(lst.findSequence(lines.get(i)[1]));
               break;
            case 13:
               System.out.println(lst.removeSequence(Integer.parseInt(lines.get(i)[1]), Integer.parseInt(lines.get(i)[2])));
               break;
         }
      }
   }

   /**
    * Purpose: Constructor for the DNA linked list object
    */

   public DNA()
   {
      numElements = 0;

      leftHelix = new Nucleotide(' ');
      leftEnd = new Nucleotide(' ');
      rightHelix = new Nucleotide(' ');
      rightEnd = new Nucleotide(' ');

      leftHelix.next = leftEnd;
      rightHelix.next = rightEnd;

      leftHelix.across = rightHelix;
      rightHelix.across = leftHelix;
      leftEnd.across = rightEnd;
      rightEnd.across = leftEnd;

      lst[0] = leftHelix;
      lst[1] = rightHelix;
   }

   public boolean isEmpty(){ return numElements == 0;}

   public int getLength(){ return numElements;}

   /**
    * Clears the entire list leaving only the sentinal nodes.
    */
   public void clear()
   {
      this.leftHelix.next = leftEnd;
      this.rightHelix.next = rightEnd;
   }

   /**
    * @param index location of the desired nucleotide in the sequence in leftHelix
    * @return The nucleotide at that location.
    */
   public Nucleotide getLeftNuc(int index)
   {

      Nucleotide nuc;

      if(index == -1)
         nuc = leftHelix.next;
      else if (index < 0 || index > numElements)
         throw new IndexOutOfBoundsException();
      else
      {
         nuc = leftHelix;
         for (int i = 0; i <= index; i++)
            nuc = nuc.next;
      }
      return nuc;
   }

   /**
    * @param index Location of the desired nucleotide in the sequence in rightHelix
    * @return The nucleotide at that location
    */
   public Nucleotide getRightNuc(int index)
   {
      return getLeftNuc(index).across;
   }

   /**
    * @param index Location at which basePair is to be inserted
    * @param basePair The cross helix nucleotide pair to be inserted at index.
    */
   public void insert(int index, String basePair) //Didn't insert back at zero index properly after elements were in lst
   {
      Nucleotide leftNuc = new Nucleotide(basePair.charAt(0));
      Nucleotide rightNuc = new Nucleotide(basePair.charAt(1));
      leftNuc.across = rightNuc;
      rightNuc.across = leftNuc;

      Nucleotide lTemp;
      Nucleotide rTemp;

      if(index == 0)
      {
         lTemp = leftHelix;
         rTemp = rightHelix;

         leftNuc.next = lTemp.next;
         rightNuc.next = rTemp.next;

         leftHelix.next = leftNuc;
         rightHelix.next = rightNuc;
      }
      else
      {
         lTemp = getLeftNuc(index - 1);
         rTemp = getRightNuc(index - 1);

         leftNuc.next = lTemp.next;
         rightNuc.next = rTemp.next;

         lTemp.next = leftNuc;
         rTemp.next = rightNuc;
      }
      this.numElements += 1;
   }

   /**
    * @param index The location of the cross helix nucleotide pair to be removed.
    * @return The string representaiton of the pair removed
    */
   public String remove(int index)
   {
      Nucleotide l = getLeftNuc(index-1);
      Nucleotide r = getRightNuc(index-1);
      String basePair = String.valueOf(l.next.getBase()) + String.valueOf(r.next.getBase());

      l.next.across = null;
      r.next.across = null;
      l.next = l.next.next;
      r.next = r.next.next;

      numElements -= 1;
      return basePair;
   }

   /**
    * Prints the entire leftHelix
    */
   public void printLeft()
   {
      String leftSeq = "";
      int i = 1; //index counter
      Nucleotide l = leftHelix;
      leftSeq = leftSeq + String.valueOf(l.next.getBase());

      while(i < numElements)
      {
         l = getLeftNuc(i);
         leftSeq = leftSeq + String.valueOf(l.next.getBase());
         i++;
      }

      System.out.println(leftSeq);
   }

   /**
    * Prints the entire rightHelix
    */
   public void printRight()
   {
      String rightSeq = "";
      int i = 1;
      Nucleotide r = rightHelix;
      rightSeq = rightSeq + String.valueOf(r.next.getBase());

      while(i < numElements)
      {
         r = getRightNuc(i);
         rightSeq = rightSeq + String.valueOf(r.next.getBase());
         i++;
      }
      System.out.println(rightSeq);
   }

   /**
    * Prints the base pair at the desired with the nucleotide occuring on the specified
    * helix appearing first in the pair.
    * ie. Given the sequence ATGCGC which is zero indexed, printBasePair(3, 1) would print "GC"
    *                        TACGCG
    */
   public void printBasePair(int index, int helix)
   {
      Nucleotide p;

      if(((index < 0) || (index > numElements - 1)) || ((helix != 0) && (helix != 1)))
      {
         throw new IndexOutOfBoundsException();
      }
      else
      {
         if(helix == 0)
         {
            p = getLeftNuc(index);
            System.out.println(String.valueOf(p.next.getBase()) + String.valueOf(p.next.across.getBase()));
         }
         else
         {
            p = getRightNuc(index);
            System.out.println(String.valueOf(p.next.getBase()) + String.valueOf(p.next.across.getBase()));
         }
      }
   }


   public String getLeftHelix()
   {
      String leftSeq = "";
      Nucleotide l = leftHelix;

      while(l.next != null)
      {
         leftSeq += String.valueOf(l.next.getBase());
         l = l.next;
      }

      return leftSeq;
   }

   public String getRightHelix()
   {
      String rightSeq = "";
      Nucleotide r = rightHelix;

      while(r.next != null)
      {
         rightSeq += String.valueOf(r.next.getBase());
         r = r.next;
      }

      return rightSeq;
   }

   public void print(int startIndex, int endIndex)
   {
      String dnaSeq = "";
      String leftSeq = this.getLeftHelix();
      String rightSeq = this.getRightHelix();

      for(int i = 0; i < leftSeq.length(); i++)
      {
         if(i >= startIndex && i < endIndex)
            dnaSeq = dnaSeq + String.valueOf(leftSeq.charAt(i)) + String.valueOf(rightSeq.charAt(i));
      }
      System.out.println(dnaSeq);
   }

   /**
    *
    * @param basePair the target base pair being searched for
    * @return the index of basePair, if it occurs in the linked list or -1 otherwise
    */
   public int find(String basePair)
   {
      Nucleotide l = new Nucleotide(basePair.charAt(0));
      Nucleotide r = new Nucleotide(basePair.charAt(1));
      int rtnval = -1;
      boolean found = false;
      int current = 0;

      while(current < numElements && !found)
      {
         if(getLeftNuc(current).next.getBase() == l.getBase())
         {
            if(getRightNuc(current).next.getBase() == r.getBase())
            {
               found = true;
               rtnval = current;
            }
         }
         current ++;
      }

      return rtnval;
   }

   /**
    *
    * @param index The location at which insertion of the sequence should begin.
    * @param sequence The string representation of the sequence to be inserted.
    */
   public void insertSequence(int index, String sequence)
   {
      if(index < 0 || index > numElements)
         throw new IndexOutOfBoundsException();
      else if(index >= 0)
      {

         for(int i = sequence.length(); i > 0; i -= 2)
         {
            this.insert(index, sequence.substring(i - 2, i));
         }
      }
   }

   /**
    *
    * @param startIndex The location at which removals begin, inclusive
    * @param endIndex The location immediatley after the last term removed, ie. exclusive
    * @return The String representation of the base pairs that were removed on the interval [startIndex, endIndex)
    */
   public String removeSequence(int startIndex, int endIndex)
   {
      String removedSequence = "";
      int i = 0;
      Nucleotide l = leftHelix;

      while(l.next != null)
      {
         if(i >= startIndex && i < endIndex)
         {
            removedSequence += this.remove(i);
            l = l.next;
            endIndex --;
            //i++;
         }
         else
         {
            l = l.next;
            i++;
         }
      }

      return removedSequence;
   }

   /**
    *
    * @param subsequence The target sequence of nucleotide base pairs
    * @return The index at which the target sequence starts if it exists or -1 otherwise.
    */
   public int findSequence(String subsequence)
   {
      int rtnval = -1;
      boolean found = false;
      int leftCurrent = 0;
      int count = 0;
      int charIndex = 0;

      while(leftCurrent < numElements - (subsequence.length()/2) && !found)
      {
         if(this.getLeftNuc(leftCurrent).getBase() == subsequence.charAt(charIndex))
         {
            count ++;
            rtnval = leftCurrent;
            int rightCurrent = leftCurrent;
            boolean maybe = true;
            charIndex++;

            while(count < subsequence.length() && maybe)
            {
               if(charIndex % 2 != 0 && (this.getRightNuc(rightCurrent).getBase() == subsequence.charAt(charIndex)))
               {
                  count++;
                  rightCurrent++;
                  charIndex++;
                  if(count == subsequence.length())
                     found = true;
               }
               else if(this.getLeftNuc(leftCurrent).getBase() == subsequence.charAt(charIndex))
               {
                  count++;
                  leftCurrent++;
                  charIndex++;
                  if(count == subsequence.length())
                     found = true;
               }
               else
               {
                  count = 0;
                  maybe = false;
                  leftCurrent++;
                  charIndex = 0;
               }
            }

         }
         else
         {
            leftCurrent++;
         }
      }
      return rtnval;
   }
}