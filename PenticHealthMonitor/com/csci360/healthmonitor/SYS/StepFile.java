package SYS;

import java.util.*;
import java.io.*;

/**
 * Justin Arends
 * 10/13/2017
 * Writes data received from SYS.StepData to files
 */

public class StepFile {
    static int fileNumber = 1;
    static int hourCounter= 0;
    static int dayCounter = 0;

    /**
     * returns an array of 120 values from the specified file
     */
    public static char[] readFile() throws IOException
    {
        String fileName = "hourStepFile.txt";
        File readFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        char [] readArray = new char[120];
        reader.read(readArray, 0, 120);
        return readArray;
    }

    public static char[] readDayFile(int number) throws IOException
    {
        while (number < fileNumber)
        {
            number--;
        }

        String fileName = "dayStepFile" + number + ".txt";
        File readFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        char[] readArray = new char[2880];
        reader.read(readArray, 0, 2880);
        return readArray;


    }

    /**
     * writes from the thirtySecAvg (one hour) array into a one hour file
     */
    public static void writeFile(ArrayList<Integer> array) throws IOException
    {
        hourCounter++;
        char [] oneHourArray = readFile();
        writeOneDayFile(oneHourArray);
        File hourStepFile = new File("hourStepFile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(hourStepFile, true));
        for (int i = 0; i < 120; i++) {
           // writer.append(' ');
            int val = array.remove(0);
            writer.append((char) val);
        }

        writer.close();

    }

    /**
     *writes one hour file into new day file
     */
    public static void writeOneDayFile(char [] array) throws IOException
    {
        if (hourCounter > 23)
        {
            fileNumber++;
            hourCounter=0;
        }
        if (fileNumber > 5)
        {
            fileNumber--;
            char [] longTermArray = readDayFile(5);
            writeLongTermFile(longTermArray);
            File deleteFile = new File("dayStepFile1.txt");
            deleteFile.delete();
            for (int i = 2; i < 6; i++)
            {
                String fileName = "dayStepFile" + i;
                File temp = new File(fileName);
                String newFileName = "dayStepFile" + (i-1);
                File temp2 = new File(newFileName);
                temp.renameTo(temp2);
            }

        }
        String fileName = "dayStepFile" + fileNumber + ".txt";
        File dayStepFile = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(dayStepFile, true));
        for (int i = 0; i < 120; i++)
        {
           // writer.append(' ');
            int val = array[i];
            writer.append((char) val);

        }
        writer.close();

    }

    /**
     *writes old one day file into long term file and syncs data after 21 days
     */
    static void writeLongTermFile(char [] array) throws IOException
    {
        dayCounter++;
        File longTermStepFile = new File("longTermStepFile.txt");
        if (dayCounter > 20)
        {
            //syncData(longTermStepFile);
            //longTermStepFile.delete();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(longTermStepFile, true));
        for (int i = 0; i < 2880; i++)
        {
            //writer.append(' ');
            int val = array[i];
            writer.append((char) val);

        }
        writer.close();

    }

    public static int getCurrentFile()
    {
        return fileNumber;
    }
}