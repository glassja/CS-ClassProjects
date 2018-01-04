package HanoiAndSalesman;

/**
 * I certify that this code is entirely my own.
 *
 * I used the psudeocode found here: <https://en.wikipedia.org/wiki/Heap's_algorithm>
 * to aid in implementing a permutation algorithm.
 *
 * Joshua Glass
 */

import java.util.ArrayList;

public class TSProblem
{
   private static Edge nullEdge = new Edge(-1, -1, -1);
   private int[][] adjMatrix;
   private ArrayList<Integer> vertices;
   private ArrayList<Edge> edges;

   //===================================================================================================================================================
   static class Edge
   {
      private int startVert;
      private int endVert;
      private int distance;

      Edge(int start, int end, int dist)
      {

         startVert = start;
         endVert = end;
         distance = dist;
      }

      public String toString()
      {

         return "Start: " + startVert + " End: " + endVert + " Weight: " + distance;
      }

      public int getDistance()
      {

         return this.distance;
      }

      public int getStart()
      {

         return this.startVert;
      }

      public int getEnd()
      {

         return this.endVert;
      }

      public boolean equals(Edge other)
      {

         if (this.startVert == other.getStart() && this.endVert ==
                 other.getEnd())
            return true;
         else
            return false;
      }
   }
//===================================================================================================================================================

   public TSProblem(ArrayList<Edge> inputEdges)
   {

      this.edges = inputEdges;

      //initialize the vertices of the undirected digraph
      vertices = new ArrayList<Integer>();
      for (int x = 0; x < edges.size(); x++)
      {
         if (!vertices.contains(edges.get(x).startVert))
            vertices.add(edges.get(x).startVert);
         if (!vertices.contains(edges.get(x).endVert))
            vertices.add(edges.get(x).endVert);
      }

      //sort the vertices from smallet -> largest to match the matrix indices
      if (sort(vertices) == -1)
      {
         System.out.println("A problem has been encountered. " +
                 "Please make sure that no vertices have the same 'name'.");
         System.exit(-1);
      }

      adjMatrix = new int[vertices.size()][vertices.size()];

      //initialize the graph to -1 for every edge
      for (int y = 0; y < vertices.size(); y++)
      {
         for (int z = 0; z < vertices.size(); z++)
         {
            adjMatrix[y][z] = -1;
         }
      }

      //input the edge values into the graph now
      for (int e = 0; e < edges.size(); e++)
      {
         adjMatrix[edges.get(e).getStart() - 1][edges.get(e).getEnd() - 1] =
                 edges.get(e).distance;

         adjMatrix[edges.get(e).getEnd() - 1][edges.get(e).getStart() - 1] =
                 edges.get(e).distance;
      }
   }

   public static void main()
   {
      ArrayList<Edge> input4 = new ArrayList<>();
      input4.add(new Edge(1,2,2));
      input4.add(new Edge(2,4,3));
      input4.add(new Edge(4,3,3));
      input4.add(new Edge(3,1,4));
      input4.add(new Edge(1,4,7));
      input4.add(new Edge(2,3,1));

      TSProblem graph4 = new TSProblem(input4);

      ArrayList<Edge> input5 = new ArrayList<>();
      input5.add(new Edge(1, 2, randomDistance()));
      input5.add(new Edge(2, 4, randomDistance()));
      input5.add(new Edge(4, 5, randomDistance()));
      input5.add(new Edge(5, 3, randomDistance()));
      input5.add(new Edge(3, 1, randomDistance()));
      input5.add(new Edge(1, 5,randomDistance()));
      input5.add(new Edge(3, 2, randomDistance()));
      input5.add(new Edge(3, 4, randomDistance()));

      TSProblem graph5 = new TSProblem(input5);

      ArrayList<Edge> input6 = new ArrayList<>();
      input6.add(new Edge(1,4,randomDistance()));
      input6.add(new Edge(1,5, randomDistance()));
      input6.add(new Edge(1,6,randomDistance()));
      input6.add(new Edge(2,4,randomDistance()));
      input6.add(new Edge(2,5,randomDistance()));
      input6.add(new Edge(2,6,randomDistance()));
      input6.add(new Edge(3,4,randomDistance()));
      input6.add(new Edge(3,5,randomDistance()));
      input6.add(new Edge(3,6,randomDistance()));

      TSProblem graph6 = new TSProblem(input6);

      ArrayList<Edge> input7 = new ArrayList<>();
      input7.add(new Edge(1,2,randomDistance()));
      input7.add(new Edge(1,4,randomDistance()));
      input7.add(new Edge(1,6,randomDistance()));
      input7.add(new Edge(2,5,randomDistance()));
      input7.add(new Edge(2,7,randomDistance()));
      input7.add(new Edge(3,4,randomDistance()));
      input7.add(new Edge(3,5,randomDistance()));
      input7.add(new Edge(4,6,randomDistance()));
      input7.add(new Edge(4,7,randomDistance()));
      input7.add(new Edge(5,6,randomDistance()));
      input7.add(new Edge(6,7,randomDistance()));

      TSProblem graph7 = new TSProblem(input7);

      ArrayList<Edge> input10 = new ArrayList<>();
      input10.add(new Edge(1,2,randomDistance()));
      input10.add(new Edge(1,4,randomDistance()));
      input10.add(new Edge(1,9,randomDistance()));
      input10.add(new Edge(2,3,randomDistance()));
      input10.add(new Edge(2,4,randomDistance()));
      input10.add(new Edge(2,6,randomDistance()));
      input10.add(new Edge(3,5,randomDistance()));
      input10.add(new Edge(3,9,randomDistance()));
      input10.add(new Edge(3,10,randomDistance()));
      input10.add(new Edge(4,6,randomDistance()));
      input10.add(new Edge(4,7,randomDistance()));
      input10.add(new Edge(4,8,randomDistance()));
      input10.add(new Edge(5,7,randomDistance()));
      input10.add(new Edge(5,8,randomDistance()));
      input10.add(new Edge(6,9,randomDistance()));
      input10.add(new Edge(7,9,randomDistance()));
      input10.add(new Edge(7,10,randomDistance()));
      input10.add(new Edge(8,10,randomDistance()));

      TSProblem graph10 = new TSProblem(input10);

      ArrayList<TSProblem> graphs = new ArrayList<>();
      graphs.add(graph4);
      graphs.add(graph5);
      graphs.add(graph6);
      graphs.add(graph7);
      //graphs.add(graph10);

      Integer[] solutionVerts;
      for(int pNum = 0; pNum < graphs.size(); pNum++){
         ArrayList<Edge> answer = TSPSolver(graphs.get(pNum));
         solutionVerts = new Integer[graphs.get(pNum).getVertices().size()];

         for(int b = 0; b < solutionVerts.length; b++){
            solutionVerts[b] = answer.get(b).getStart();
         }
         System.out.print("Path: " + arrayToString(solutionVerts) + "\nPath Length: " + sumLength(answer)+"\n\n");
      }

      //String str = "azsddeeerrcctthuiiiokll";

      
   }

   private static int randomDistance(){return (int) (Math.random()* 20.0) + 1;}

   //basic insertion sort that checks itself once
   //chose insertion sort b/c number of vertices will always be small
   private static int sort(ArrayList<Integer> arr)
   {

      for (int s = 1; s < arr.size(); s++)
      {
         if (arr.get(s) < arr.get(s - 1))
            swap(arr, s - 1, s);
      }

      //after the first sort pass we verify it is sorted.
      int sorted = -1;
      for (int c = 0; c < arr.size() - 1; c++)
      {
         if (arr.get(c) < arr.get(c + 1))
            sorted = 0;

            //if not totally sorted, try once more and check. If sorted rtn 0
         else
         {
            sorted = -1;
            if (sort(arr) == 0)
               sorted = 0;
         }
      }
      return sorted;
   }

   public ArrayList<Integer> getVertices()
   {

      return this.vertices;
   }

   public ArrayList<Edge> getEdges()
   {

      return this.edges;
   }

   public int[][] getGraph()
   {

      return this.adjMatrix;
   }

   public static ArrayList<Edge> TSPSolver(TSProblem graphToSolve)
   {

      final long startTime = System.nanoTime();

      ArrayList<Edge> minHamiltonianCycle;

      ArrayList<Integer[]> permutations =
              permute(graphToSolve.getVertices(), graphToSolve.getVertices().size());

      ArrayList<ArrayList<Edge>> hamiltonianCycles = new ArrayList<ArrayList<Edge>>();

      //iterate through each permutation of possible cycle paths

      int start;
      int end;
      int distance;
      for (int currentPerm = 0; currentPerm < permutations.size(); currentPerm++)
      {
         //set the current cycle to the kth permutation in the list of them.
         ArrayList<Edge> possibleCycle = new ArrayList<>();
         int index = permutations.get(currentPerm).length;

         for (int v = 0; v < index; v++)
         {
            //if we are checking the edge from the last vertex back to the first vertex
            //we go into this if block
            if (v == index - 1)
            {
               start = permutations.get(currentPerm)[v];
               end = permutations.get(currentPerm)[0];
               distance = graphToSolve.getGraph()[start-1][end-1];
            }
            else
            {
               start = permutations.get(currentPerm)[v];
               end = permutations.get(currentPerm)[v + 1];
               distance = graphToSolve.getGraph()[start-1][end-1];
            }
            //if the cycle contains an edge that doesn't exist, ie. adjMatrix value is -1
            //then we stop constructing that cycle and move on to the next iteration.
            if (distance == -1)
               possibleCycle.add(nullEdge);
            else
            {
               Edge currentEdge = new Edge(start, end, distance);
               possibleCycle.add(currentEdge);
            }
         }
         if(!possibleCycle.contains(nullEdge))
         {
            hamiltonianCycles.add(possibleCycle);
         }
      }

      minHamiltonianCycle = minHamCycle(hamiltonianCycles);

      final long endTime = System.nanoTime();

      System.out.println("Solved traveling salesperson problem for " +
              graphToSolve.getVertices().size() + " vertices in " + (endTime - startTime)/1000 + " microseconds");

      return minHamiltonianCycle;
   }

   private static ArrayList<Edge> minHamCycle(ArrayList<ArrayList<Edge>> cycles)
   {

      ArrayList<Edge> minCycle = null;

      int cycleLength = sumLength(cycles.get(0));
      int minLength;

      for (int r = 1; r < cycles.size(); r++)
      {
         if(cycleLength > 0){
            minLength = cycleLength;
            cycleLength = sumLength(cycles.get(r));
            if(cycleLength > 0 && cycleLength < minLength)
            {
               minLength = cycleLength;
               minCycle = cycles.get(r);
            }
         }
         else
         {
            cycleLength = sumLength(cycles.get(r));
         }
      }
      return minCycle;
   }

   private static int sumLength(ArrayList<Edge> path)
   {

      int sum = 0;
      if (path.contains(nullEdge))
         sum = -1;
      else
      {
         for (int q = 0; q < path.size(); q++)
            sum += path.get(q).getDistance();
      }
      return sum;
   }

   public static ArrayList<Integer[]> permute(ArrayList<Integer> verts, int n)
   {

      ArrayList<Integer[]> permutations = new ArrayList<>((int) Math.pow(2,
              verts.size()) / 2);

      recPermute(verts, n, permutations);

      ArrayList<Integer[]> dedupedPermutations = new ArrayList<>();
      for(int k = 0; k < permutations.size(); k++){
         if(arrNotIn(dedupedPermutations, permutations.get(k)))
            dedupedPermutations.add(permutations.get(k));
         else
            continue;
      }

      return dedupedPermutations;

   }

   private static boolean arrNotIn(ArrayList<Integer[]> checkIn, Integer[] checkFor)
   {

      boolean rtn = true;


      for (int i = 0; i < checkIn.size(); i++)
      {
         int checkAgainstInt = 0;
         int checkForInt = 0;

         if(checkIn.get(i).length != checkFor.length)
         {
            System.out.println("Something has gone very wrong. A permutation was longer than is possible.");
            System.exit(-1);
         }
         else
         {
            for(int j = checkFor.length-1; j >= 0; j--)
            {
               checkAgainstInt += checkIn.get(i)[j]*(Math.pow(10, j));
               checkForInt += checkFor[j]*(Math.pow(10, j));
            }
            if(checkAgainstInt == checkForInt)
            {
               rtn = false;
            }
         }
      }
      return rtn;
   }

   private static ArrayList<Integer[]> recPermute(ArrayList<Integer> verts, int n, ArrayList<Integer[]> addTo)
   {

      Integer[] nthPermute = new Integer[n];
      Integer[] vertsArr = new Integer[verts.size()];

      if (n == 1)
      {
         vertsArr = verts.toArray(vertsArr);
         if (!(containsForwardOrReverse(addTo, vertsArr)))
         {
            nthPermute = verts.toArray(nthPermute);
            addTo.add(nthPermute);
         }
         arrayClear(vertsArr);
      }
      else
      {
         for (int i = 0; i < n; i++)
         {
            recPermute(verts, n - 1, addTo);
            if (n % 2 == 1)
            {
               swap(verts, 0, n - 1);
               vertsArr = verts.toArray(vertsArr);
               if (!(containsForwardOrReverse(addTo, vertsArr)))
               {
                  nthPermute = verts.toArray(nthPermute);
                  addTo.add(nthPermute);
               }
               arrayClear(vertsArr);
            }
            else
            {
               swap(verts, i, n - 1);
               vertsArr = verts.toArray(vertsArr);
               if (!(containsForwardOrReverse(addTo, vertsArr)))
               {
                  nthPermute = verts.toArray(nthPermute);
                  addTo.add(nthPermute);
               }
               arrayClear(vertsArr);
            }
         }
      }

      return addTo;
   }

   private static void arrayClear(Integer[] arr)
   {

      Integer[] newArr = new Integer[arr.length];
      arr = newArr;
   }

   private static void printPermutations(ArrayList<Integer[]> permutations)
   {

      for (int k = 0; k < permutations.size(); k++)
      {
         System.out.println(arrayToString(permutations.get(k)));
      }
   }

   private static String arrayToString(Integer[] intsToPrint)
   {

      String intStr = "";
      for (int i = 0; i < intsToPrint.length; i++)
      {
         intStr += intsToPrint[i].toString();
      }
      return intStr;
   }

   private static void swap(Integer[] seq, int index1, int index2)
   {

      int temp = seq[index1];
      seq[index1] = seq[index2];
      seq[index2] = temp;
   }

   private static void swap(ArrayList<Integer> seq, int index1, int index2)
   {

      int temp = seq.get(index1);
      seq.set(index1, seq.get(index2));
      seq.set(index2, temp);
   }

   private static Integer[] reverse(Integer[] arr)
   {

      Integer[] revArr = new Integer[arr.length];

      int track = 0;
      for (int t = revArr.length-1; t >= 0; t--)
      {
         revArr[track] = arr[t];
      }
      return revArr;
   }

   private static boolean containsForwardOrReverse(ArrayList<Integer[]> checkIn, Integer[] checkFor)
   {

      return (checkIn.contains(checkFor) || checkIn.contains(reverse(checkFor)));
   }

   public String toString()
   {
      String rtnStr = "";
      for(int i = 0; i < vertices.size(); i++)
      {
         for(int j = 0; j < vertices.size(); j++)
         {
            if(j == 0)
               rtnStr = rtnStr + "["+adjMatrix[i][j];
            else if(j == vertices.size()-1)
               rtnStr = rtnStr + adjMatrix[i][j] + "]\n";
            else
               rtnStr = rtnStr + " " + adjMatrix[i][j] + " ";
         }
      }
      return rtnStr;
   }
}