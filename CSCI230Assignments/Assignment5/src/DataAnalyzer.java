/**
 * ==========================================================================================
 *
 * @param [arg names] [arg descriptions]
 * @author Joshua Glass
 *         Date:         <due date>
 *         Class:        <course and section>
 *         Assignment:   <assignment number>
 *         Task:         <brief description of the task performed>
 * @return [description of the results of the program execution
 * exclude prompts]
 * <p>
 * Certification of Authenticity:   <include one of the following>
 * I certify that this code is entirely my own work.
 * I certify that this code is my work but, I recieved some assistance from:
 * <Name(s) and links>
 * ==========================================================================================
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DataAnalyzer
{

   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);

      System.out.println("Please enter a comma separated list of filenames to read. Ex. data1.txt,data2.txt,data3.txt NO SPACES");

      String argsString = scanner.next();

      args = argsString.split(",");


      for(int j = 0; j < args.length; j++)
      {
         File file = new File(args[j]);
         readFromFile(file);
         System.out.println("\n\n");
      }
   }

   /**
    * Code in this method is largely taken from an answer on stackoverflow
    * </http://stackoverflow.com/q/22741638>
    * @param filename the file to be read
    */
   public static void readFromFile(File filename)
   {
      double[][] data = new double[1000][2];

      BufferedReader buffRead = null;

      try
      {
         FileReader fileRead = new FileReader(filename);
         buffRead = new BufferedReader(fileRead);

         String dataLine;
         int i = 0;
         while((dataLine = buffRead.readLine()) != null && i < 1000)
         {
            if(dataLine.charAt(0) == 'd')
            {
               String[] fields = dataLine.split(", ");
               data[i][0] = Double.parseDouble(fields[1]);
               data[i][1] = Double.parseDouble(fields[2]);
               i++;
            }
         }
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      finally
      {
         try
         {
            buffRead.close();
         }
         catch(IOException e)
         {
            System.out.println("Unable to close file: " + filename.toString());
         }
         catch(NullPointerException ex)
         {}
      }
      System.out.println("List sizes");
      for (int i = 0; i < data.length; i++)
      {
         System.out.println((int)(data[i][0]));
      }
      System.out.println("\n\nRuntimes: " + filename.toString());
      for(int j = 0; j < data.length; j++)
         System.out.println(data[j][1]);
   }
}