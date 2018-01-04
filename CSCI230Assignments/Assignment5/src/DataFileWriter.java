/**
 * ==========================================================================================
 *
 * @author Joshua Glass
 * Date:         n/a testing class
 * Class:        CSCI 230-01
 * Assignment:   5 (testing class)
 * Task:         Collect thorough testing data of the sorts in compareSorts.java for
 * analysis and reporting
 * @return text file containing all necessary data for analysis
 *
 * Certification of Authenticity:
 * I certify that this code is entirely my own work except where cited otherwise.
 * ==========================================================================================
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataFileWriter
{
   /**
    *
    * @param args command line arguments parsed from the keyboard via scanner
    */
   public static void main(String[] args)
   {
      long startTime = System.nanoTime();   // start timing

      String[] dataLines1 = collectData(20875, 1, 1000);
      String[] dataLines2 = collectData(20875, 2, 1000);
      String[] dataLines3 = collectData(20875, 3, 1000);

      try
      {
         writeToFile(dataLines1, "insertionSortDataMulti.csv");
         writeToFile(dataLines2, "mergeSortDataMulit.csv");
         writeToFile(dataLines3, "quickSortDataMulit.csv");
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      long endTime = System.nanoTime();    // end timing
      double runtimeInMilliseconds = (double) (endTime - startTime) / 1000000.0;
      System.out.println("Runtime in milliseconds: " + runtimeInMilliseconds);
   }

   /**
    * The majority of this code was taken from an answer on stackoverflow.
    * http://stackoverflow.com/a/26805827\
    *
    * @param dataLines String array of all lines to be written to the file
    * @throws IOException
    */
   public static void writeToFile(String[] dataLines, String fileName) throws IOException
   {
      File dataFile = null;
      FileWriter fw = null;

      try
      {
         dataFile = new File(fileName);
         if (!dataFile.exists())
            dataFile.createNewFile();
         fw = new FileWriter(dataFile);
         for (int i = 0; i < dataLines.length-1; i++)
         {
            fw.write(dataLines[i]);
         }
         fw.flush();
         fw.close();
         System.out.println("File write was successful!");
      }
      catch(IOException e)
      {
         e.printStackTrace();
      }
   }

   public static String[] collectData(int sampleSize, int methodNum, int rangeLimit)
   {
      String[] dataLines = new String[sampleSize + 1]; // all of the dataLines plus 1 info line

      int step = 1;
      int arrSize = 0;
      int count = 0;
      while(count < 20875)
      {
         if (count < 5000)
         {
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
         else if (count < 4500)
         {
            step = 5;
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
         else if (count < 9500)
         {
            step = 10;
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
         else if (count < 14500)
         {
            step = 25;
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
         else if(count < 18250)
         {
            step = 50;
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
         else
         {
            step = 100;
            for (int i = 0; i < 10; i++)
            {
               dataLines[count] = compareSorts.getData(methodNum, arrSize, rangeLimit);
               count++;
            }
            arrSize += step;
         }
      }
      return dataLines;
   }
}