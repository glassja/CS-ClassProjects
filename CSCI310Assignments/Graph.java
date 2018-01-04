/**
 * Joshua Glass
 * Assignment 1
 * I certify that this code is entirely my own with references to the following sources:
 * "Introduction to the Design and Analysis of Algorithms" Anany Levinin pg. 29-30
 *
 * Originally my plan was to build an int[][] adjacency matrix from two sets represented by ArrayLists,
 * a vertices set and and edges set. Upon further reflection I decided to instead approach
 * the problem using a data structure much more closely resembling an adjacency list
 * in order to save space and runtime as I no longer have to rebuild a new "graph" every time
 * a new vertex is added or removed. Now I simply remove the vertex and it's edges.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph
{

   /**
    * vertices will hold the list of vertex values from input
    * graphList will be the LinkedList holding Vertex objects which
    * contain all of the neccessary information
    */

   private ArrayList<Vertex> vertices;
   private static LinkedList<Vertex> graphList;
   private static int count;

   public Graph(ArrayList<Vertex> vertices)
   {
      graphList = new LinkedList<>();

      for (Vertex vertice : vertices)
      {
         graphList.add(vertice);
      }
   }

   protected static class Vertex
   {
      private String name;
      private ArrayList<Vertex> edges = new ArrayList<Vertex>();
      private boolean visited;
      private int visitNum;

      /**
       * Constructor for the vertex object with degree 0 initially
       *
       * @param name the name for this vertex
       */
      public Vertex(String name)
      {
         this.name = name;
         this.visited = false;
         this.visitNum = 0;
      }

      /**
       *
       * @return Returns the name of this vertex
       */
      public String getName()
      {
         return this.name;
      }

      /**
       * Sets a new name for the vertex
       *
       * @param newName the new name for the vertex
       */
      public void setName(String newName)
      {
         this.name = newName;
      }

      /**
       *
       * @return Returns an array of Strings in the format:
       *         "{firstVertexName, secondVertexName}"
       */
      public String[] getEdges()
      {
         String[] thisEdges = new String[this.getDegree()];
         for(int i = 0; i < thisEdges.length; i++)
         {
            thisEdges[i] = "{" + this.getName() + ", " + this.edges.get(i).getName() + "}";
         }

         return thisEdges;
      }

      /**
       * Removes all edges from the vertex and removes all references to this
       * vertex from the previously adjacent vertices
       */
      public void removeAllEdges()
      {
         while(this.edges.size() != 0)
         {
            Vertex currentTerminalVertex = this.edges.get(0);
            int index = currentTerminalVertex.edges.indexOf(this);
            currentTerminalVertex.edges.remove(index);
            this.edges.remove(0);
         }
      }

      public void removeEdge(Vertex endingVertex)
      {
         endingVertex.edges.remove(this);
         this.edges.remove(endingVertex);
      }

      /**
       * Adds a new edge to the ArrayList of edges for the vertex
       * @param endingVertex the vertex to be added to the list of edges
       */
      public void addEdge(Vertex endingVertex)
      {
         this.edges.add(endingVertex);
      }

      /**
       *
       * @return the degree of this vertex
       */
      public int getDegree()
      {
         return this.edges.size();
      }

      public String toString()
      {
         return this.getName() + " " + Arrays.toString(this.getEdges()) + " Degree: " + this.getDegree();
      }

      public void visit()
      {
         this.visited = true;
      }

      public void setVisitNum(int i)
      {
         this.visitNum = i;
         if(i == 0)
            this.unvisit();
         else
            this.visit();
      }

      public void unvisit()
      {
         this.visited = false;
      }

      public boolean checkVisited()
      {
         return this.visited;
      }

      public boolean equals(Vertex v)
      {
         boolean rtnval = false;
         if(this.name.equals(v.getName()))
            rtnval = true;
         else
            rtnval = false;
         return rtnval;
      }

   }

   /**
    *
    * @param vertexValue Vertex object whose degree we are looking for
    * @return the degree of vertexValue
    */
   public static int degree(String vertexValue)
   {
      int rtnval = 0;
      for (Vertex vert: graphList)
      {
         if(vert.getName().equalsIgnoreCase(vertexValue))
            rtnval = vert.getDegree();
      }
      return rtnval;
   }

   /**
    *
    * @param vertexValue The vertex we are looking for
    * @return True if the vertex searched for is already in the graph, false otherwise
    */
   public static boolean isVertex(String vertexValue)
   {
      boolean rtnval = false;
      for(Vertex vert: graphList)
      {
         if(vert.getName().equalsIgnoreCase(vertexValue))
            rtnval = true;
      }
      return rtnval;
   }

   /**
    *
    * @param vertexValue adds the specified vertex to the graph
    */
   public void addVertex(String vertexValue)
   {
      Vertex newVertex = new Vertex(vertexValue);

      if(!(graphList.contains(newVertex)))
         graphList.add(newVertex);
      else
         System.out.println("That vertex already exists.");
   }

   /**
    *
    * @param originVertex the vertex from which the edge 'originates'
    * @param terminalVertex the vertex at which the edge 'terminates'
    */
   public void addEdge(String originVertex, String terminalVertex)
   {

      for (Vertex vert1 : graphList)
      {
         for (Vertex vert2 : graphList)
         {

            if (vert1.getName().equalsIgnoreCase(originVertex) && vert2.getName().equalsIgnoreCase(terminalVertex))
            {
               vert1.addEdge(vert2);
               vert2.addEdge(vert1);
            }
            else
               System.out.println("One or both of the specified vertices does not exist");

         }
      }
   }

   /**
    *
    * @param vertexValue the vertex to be removed
    * @return the vertex that was removed
    */
   public Vertex removeVertex(String vertexValue)
   {
      Vertex rtnval = null;

      for (Vertex vert : graphList)
      {
         if (vert.getName().equalsIgnoreCase(vertexValue))
         {
            int index = graphList.indexOf(vert);
            graphList.get(index).removeAllEdges();
            rtnval = graphList.remove(index);
         }
      }
      if(rtnval == null)
         System.out.println("That vertex does not exist");
      return rtnval;
   }

   /**
    *
    * @param vertexValue1 the origin vertex of the edge to be removed
    * @param vertexValue2 the terminal vertex of the edge to be removed
    * @return
    */
   public String removeEdge(String vertexValue1, String vertexValue2)
   {
      String rtnval = null;

      for (Vertex vert1 : graphList)
      {
         for (Vertex vert2 : graphList)
         {

            if (vert1.getName().equalsIgnoreCase(vertexValue1) && vert2.getName().equalsIgnoreCase(vertexValue2))
            {
               vert1.removeEdge(vert2);
               vert2.removeEdge(vert1);
               rtnval = vert1.getName() + vert2.getName();
            }
         }
      }
      if(rtnval == null)
         System.out.println("One or both of the specified vertices does not exist");
      return rtnval;
   }

   /**
    *
    * @param vertexValue1
    * @param vertexValue2
    * @return
    */
   public static boolean isAdjacent(String vertexValue1, String vertexValue2)
   {

      boolean rtnval = false;
      for (Vertex vert1 : graphList)
      {
         for(Vertex vert2: graphList)
         {
            if (vert1.getName().equalsIgnoreCase(vertexValue1) &&
                    vert2.getName().equalsIgnoreCase(vertexValue2))
               if (vert1.edges.contains(vert2))
                  rtnval = true;
         }
      }
      return rtnval;
   }

   /**
    *
    * @param startVertex
    * @param stopVertex
    * @return
    */
   public boolean isPath(String startVertex, String stopVertex)
   {
      boolean rtnval = false;
      LinkedList<Vertex> rtnList = null;
      for(Vertex vert1: graphList)
      {
         for (Vertex vert2 : graphList)
         {
            if (vert1.getName().equalsIgnoreCase(startVertex) &&
                    vert2.getName().equalsIgnoreCase(stopVertex))
               rtnList = BFS(this, vert1, vert2);
         }
      }
      if(rtnList != null && rtnList.size() > 0)
         rtnval = true;
      else
         rtnval = false;
      return rtnval;
   }

   /**
    * this method to be called on the graph object ie. GraphPkg g.getPath(start, stop)
    * @param startVertex
    * @param stopVertex
    * @return
    */
   public LinkedList<Vertex> getPath(String startVertex, String stopVertex)
   {
      LinkedList rtnList = null;
      for(Vertex vert1: graphList)
      {
         for(Vertex vert2: graphList)
         {
            if(vert1.getName().equalsIgnoreCase(startVertex) &&
                    vert2.getName().equalsIgnoreCase(stopVertex))
            {
               if(this.isPath(startVertex, stopVertex))
                  rtnList = BFS(this, vert1, vert2);
            }
         }
      }
      return rtnList;
   }

   private LinkedList<Vertex> BFS(Graph g, Vertex start, Vertex end)
   {
      LinkedList<Vertex> rtnval = new LinkedList<Vertex>();

      count = 0;
      if(!g.graphList.get(g.graphList.indexOf(start)).checkVisited())
      {
         rtnval = bfs(g.graphList.get(g.graphList.indexOf(start)), end);
      }
      return rtnval;
   }

   private LinkedList<Vertex> bfs(Vertex v, Vertex targetVertex)
   {
      Vertex nextVert = null;

      LinkedList<Vertex> path = new LinkedList<Vertex>();
      LinkedList<Vertex> q = new LinkedList<Vertex>();
      q.addLast(v);
      path.addLast(v);

      count++;
      v.setVisitNum(count);

      while(q.size() != 0 && v != targetVertex)
      {
         for(int i = 0; i < v.edges.size(); i++)
         {
            if (!v.edges.get(i).checkVisited())
            {
               count++;
               v.edges.get(i).setVisitNum(i);
               q.addLast(v.edges.get(i));
            }
            nextVert = q.removeFirst();
            path.addLast(nextVert);
         }
      }
      return path;
   }

   public static void main(String[] args)
   {
      ArrayList<Vertex> verts = new ArrayList<Vertex>();
      Graph g = null;
      Vertex v;

      String fileName = "GraphPkg/input.txt";

      try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
      {
         String nextLine;
         while((nextLine = reader.readLine()) != null)
         {
            String[] currLine = nextLine.split(" ");
            if(currLine[0].equalsIgnoreCase("v"))
            {
               v = new Vertex(currLine[1]);
               verts.add(v);
            }
            else if(currLine[0].equalsIgnoreCase("e"))
            {
               for(int i = 0; i < verts.size(); i++)
               {
                  for (int j = 0; j < verts.size(); j++)
                  {
                     if (verts.get(i).getName().equalsIgnoreCase(currLine[1].substring(0, 1)))
                     {
                        if (verts.get(j).getName().equalsIgnoreCase(currLine[1].substring(1)))
                           verts.get(i).addEdge(verts.get(j));
                     }
                  }
               }
            }
            g = new Graph(verts);
         }
      }
      catch (IOException e)
      {
         System.out.println("An error ocurred: " + e);
         System.exit(0);
      }

      g.addVertex("Q");
      g.addEdge("Q", "S");
      g.addEdge("Q", "D");
      g.removeEdge("I", "E");
      degree("K");
      g.removeVertex("L");
      isAdjacent("A", "P");
      g.isPath("D", "Z");
      g.getPath("A","C");
      isVertex("V");
   }
}
