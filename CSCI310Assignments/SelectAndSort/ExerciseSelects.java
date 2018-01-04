package SelectAndSort;

/**
 * I certify that this code and all code in this package is entirely my own. I received assistance only from the java class documentations.
 * Joshua Glass
 * Created on 10/12/2016
 */
public class ExerciseSelects
{
   public static void main(String[] args)
   {
      MyList[] lists = new MyList[50];
      for(int i = 0; i < 50; i++){
         lists[i] = randomList((int) Math.pow(2, Math.round(Math.random()*10)+10), 100);
      }

      for(int m = 0; m < 50; m++){
         MyList q1 = lists[m];
         MyList q2 = lists[m];
         int k = Math.toIntExact(Math.round(Math.random() * (q1.size())) + 1);

         System.out.println(q1.toString());
         quickSelect(q1, k);
         System.out.println("QuickSelect for " + k +"th element");
         System.out.println(q1.toString());
         System.out.println();
         lists[m].getMyCounter().clear();
         System.out.println(q2.toString());
         System.out.println("Medians Select "+ k + "th element");
         select(q2, k);
         System.out.println(q2.toString());
      }
   }

   /**
    *
    * @param size the size of the list to be created
    * @param maxval the maximum absolute value allowed in the list
    * @return the newly created MyList object
    */
   private static MyList randomList(int size, int maxval){
      MyList rtnLst = new MyList(size);
      for(int i = 0; i < size; i++){
         rtnLst.add(random() == 1 ? (int) Math.round(Math.random() * maxval) : (int) Math.round(Math.random() * maxval * (-1)));
      }
      return rtnLst;
   }

   /**
    *
    * @return returns the random integer value
    */
   private static int random(){
      return (int) Math.round(Math.random());
   }

   /**
    *
    * @param sublst the list from l->r from which we will select a value
    * @param k the number element we are to select
    * @return returns the int value of the kth element in the sublst
    */
   private static int quickSelect(MyList sublst, int k){
      int s = lomutoPartition(sublst);
      int right = sublst.size()-1;
      int left = 0;

      if(s == k-1)
      {
         sublst.incCount();
         return sublst.get(s);
      }
      else if(s > left+k-1)
      {
         sublst.incCount();
         return quickSelect(sublst.subList(left, s-1), k);
      }
      else
         return quickSelect(sublst.subList(s+1, right), k-1-s);
   }

   /**
    *
    * @param sublst the list passed to quickSelect
    * @return returns the s value obtained from the partitioning
    */
   private static int lomutoPartition(MyList sublst){
      int left = 0;
      int right = sublst.size()-1;
      int pivot = sublst.get(left);

      int s = left;

      for(int i = left+1; i <= right; i++){
         sublst.incCount();
         if(sublst.get(i) < pivot){
            s = s+1;
            MyList.swap(sublst, s, i);
         }
         MyList.swap(sublst, left, s);
      }
      return s;
   }

   /**
    *
    * @param subLst the sublist from l->r from which we will select the value
    * @param k the number of the element to be selected by sorted medians
    * @return the value of the kth element in the sublst
    */
   private static int select(MyList subLst, int k){
      if(subLst.size() < 50){
         subLst.bubbleSort();
         return subLst.get(k-1);
      }
      else{
         int numPartsof5 = subLst.size() / 5;

         if(subLst.size()%5 != 0){
            while(subLst.size()%5 != 0)
            {
               subLst.add(null);
            }
         }

         MyList medians = new MyList(numPartsof5);
         for(int i =0; i < numPartsof5; i++){
            MyList sub = subLst.subList(i*5, i*5+4);
            if(sub.contains(null)){
               for(int n = 4; n >=0; n--){
                  if(sub.get(n) == null)
                     sub.remove(n);
               }
            }
            while(subLst.contains(null)){
               subLst.remove(null);
            }
            //medians.add(sub);
            medians.add(i, select(sub, (int) Math.ceil(sub.size()/2)));
         }

         Integer m = select(medians, (int) Math.ceil(medians.size()/2));

         MyList s1 = new MyList();
         MyList s2 = new MyList();
         MyList s3 = new MyList();

         for(int j = 0; j < subLst.size(); j++){
            if(subLst.get(j) < m){
               subLst.incCount();
               s1.add(subLst.get(j));
            }
            else if(subLst.get(j) > m){
               subLst.incCount();
               s3.add(subLst.get(j));
            }
            else if(subLst.get(j).equals(m)){
               subLst.incCount();
               s2.add(subLst.get(j));
            }
         }

         if(s1.size() >= k)
            return select(s1, k);
         else if(s1.size() + s2.size() >= k)
            return m;
         else
            return select(s3, k-s1.size()-s2.size());
      }
   }
}