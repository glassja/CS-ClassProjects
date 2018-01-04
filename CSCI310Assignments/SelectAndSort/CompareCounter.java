package SelectAndSort;

/**
 * Created by joshuaglass on 10/9/16.
 */
public class CompareCounter
{
   private static int count;

   CompareCounter(){count = 0;}

   //increments the value of count by 1
   static void increment(){count++;}

   //allows us to set the count by means other than single value
   //increments for when a sublist is made
   void setCount(int newCount){count = newCount;}

   //returns the current count
   int getCount(){return count;}

   //clears the count back to 0
   public void clear(){count = 0;}

   //provides a comparison for counter objects
   public int compareTo(CompareCounter other)
   {
      if(this.getCount() - other.getCount() < 0)
         return -1;
      else if(this.getCount() - other.getCount() > 0)
         return 1;
      else
         return 0;
   }
}