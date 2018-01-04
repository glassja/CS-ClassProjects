package SelectAndSort;

/**
 * Created by joshuaglass on 10/9/16.
 */

import java.util.ArrayList;
import java.lang.*;

class MyList extends ArrayList
{
   private static ArrayList<Integer> myList;
   private static CompareCounter myCounter;

   MyList()
   {
      myList = new ArrayList<Integer>();
      myCounter = new CompareCounter();
   }

   public MyList(int size)
   {
      myList = new ArrayList<Integer>(size);
      myCounter = new CompareCounter();
   }

   //adds the Integer element to the list via the ArrayList add method
   public void add(Integer element)
   {
      super.add(element);
   }

   //clears the entire list via the ArrayList clear method and resets the counter
   public void clear()
   {
      super.clear();
      myCounter.clear();
   }

   //gets the Integer object from the specified index and returns it
   public Integer get(int index)
   {
      return (Integer) super.get(index);
   }

   //gets the index of the specified object
   public int indexOf(Integer o)
   {
      return super.indexOf(o);
   }

   //checks if the current list is empty
   public boolean isEmpty()
   {
      return super.isEmpty();
   }

   //removes the Integer object at the specified index from the list
   public Integer remove(int index)
   {
      return (Integer) super.remove(index);
   }

   //returns the size of the underlying ArrayList object
   public int size()
   {
      return super.size();
   }

   //returns true if the specified Integer is the in the list, false otherwise
   public boolean contains(Integer o)
   {
      return super.contains(o);
   }

   //swaps the objects at the specified indices
   static void swap(MyList list, int k, int j)
   {
      java.util.Collections.swap(list, k, j);
   }

   //increments the counter object's value by 1
   void incCount()
   {
      myCounter.increment();
   }

   //returns the specified counter object
   CompareCounter getMyCounter(){return myCounter;}

   //creates a sublist of the current list complete with the current count
   public MyList subList(int l, int r)
   {
      MyList sub = new MyList();
      sub.setCount(this.getCount());
      for(int i = l; i <= r; i++){
         sub.add(this.get(i));
      }
      return sub;
   }

   //sets the count to the specified value
   private void setCount(int newCount){myCounter.setCount(newCount);}

   //sorts the list via an insertion sort
   void sort()
   {

      for (int separator = 1; separator < myList.size(); separator++)
      {
         int valueToInsert = myList.get(separator);

         int j = separator - 1; //last item in sorted section of the list

         while (j >= this.size()-2 && valueToInsert < myList.get(j))
         {
            myCounter.increment();
            myList.set(myList.get(j + 1), j);  //shift items right, creating an 'empty' slot
            j--;
         }
         //When this loop ends j is pointing at the location to the left of the insert
         //ie if j == -1, j points to arr[0] and if j == some int, j points to arr[some int + 1]

         myList.set(myList.get(j + 1), valueToInsert); //insert the item
      }
   }

   void bubbleSort(){
      int k;
      boolean swap = true;
      Integer temp;

      while(swap){
         swap = false;
         for(k = 0; k < this.size()-1; k++){
            this.getMyCounter().increment();
            if(this.get(k).intValue()>this.get(k+1).intValue()){
               temp = this.get(k);
               this.set(k, this.get(k+1));
               this.set(k+1, temp);
               swap = true;
            }
         }
      }
   }

   //returns the current count
   public int getCount(){return myCounter.getCount();}

   //prints the elements of the list in an array format and prints the current counter objects count value
   public String toString(){
      String str = "Size: " + this.size() + "\n";
      //str += super.toString();
      str+= "Comparison Count: " + this.getCount();
      return str;
   }
}