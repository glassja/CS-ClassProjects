/**
 * Created by joshuaglass on 3/22/16.
 */
public class Sorts
{
   public int[] insertionSort(int [] arr)
   {

      for (int separator = 1; separator < arr.length; separator++)
      {
         int valueToInsert = arr[separator];

         int j = separator - 1; //last item in sorted section of the list

         while( j >= 0 && valueToInsert < arr[j])
         {
            arr[j+1] = arr[j];  //shift items right, creating an 'empty' slot
            j--;
         }
         //When this loop ends j is pointing at the location to the left of the insert
         //ie if j == -1, j points to arr[0] and if j == some int, j points to arr[some int + 1]

         arr[j+1] = valueToInsert; //insert the item
      }

      return arr; //This way we can say something like someArray = insertionSort(someArray);
   }

   /**
    * Merge Sort
    *
    * Start with a list, subdivide the list into 2*log_2(n) items and look at the individual comparisons and then
    * merge the, now sorted, sublists into the major list.
    *
    * 36 20 17 13 | 28 14 23 15
    * 36 20 | 17 13 | 28 14 | 23 15            O(log(n)) part
    * 36 | 20 |17 | 13 | 28 | 14 | 23 | 15
    *
    * now we compare and merge into sublists
    * 20 36 | 13 17 | 14 28 | 15 23
    * 13 17 20 36 | 14 15 23 28                O(n)
    * 13 14 15 17 20 23 28 36                Therefore mergeSort is O(nlog(n))
    */

   public int[] mergeSort(int [] arr)
   {
      int[] temp = new int[arr.length];

      mergeSort(arr, temp, 0, arr.length - 1);

      return arr;
   }

   private void mergeSort(int [] a, int [] temp, int left, int right)
   {
      if(left < right) //more remaining to sort?
      {
         int center = (left + right) / 2; //find mid point

         mergeSort(a, temp, left, center);    //sort left sublist
         mergeSort(a, temp, center+1, right); //sort right sublist

         merge(a, temp, left, center + 1, right);

         for(int i = left; i <= right; i++)
         {
            temp[i] = a[i];
         }

         int i1 = left;
         int i2 = center + 1;
      }
   }

   private void merge(int [] a, int [] temp, int left, int right, int rightEnd)
   {
      int leftEnd = right - 1;
      int tempPos = left;
      int numElements = right - left + 1;

      while(left <= leftEnd && right <= rightEnd)
      {
         if(a[left] < a[right])
            temp[tempPos++] = a[left++];
         else
            temp[tempPos++] = a[right++];
      }

      while(left <= leftEnd)
         temp[tempPos++] = a[left++];

      while(right <= rightEnd)
         temp[tempPos++] = a[right++];

      for(int i = 1; i < numElements; i++, rightEnd--)
         a[rightEnd] = temp[rightEnd];
   }
}

