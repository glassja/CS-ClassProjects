package SYS;

import java.util.*;
import java.io.*;

/**
 * Justin Arends
 * 10/13/2017
 * Writes data received from SYS.HRData to files
 */

public class HRFile {
    private static int fileNumber = 1;
    private static int hourCounter= 0;
    private static int dayCounter = 0;

    /**
     * returns an array of 120 values from the hour file
     */
    static char[] readFile() throws IOException
    {
        String fileName = "hourHRFile.txt";
        File readFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        char [] readArray = new char[120];
        reader.read(readArray, 0, 120);
        return readArray;
    }

    /**
     * returns an array for 2880 values from the specified day file
     */
    public static char[] readDayFile(int number) throws IOException
    {
        String fileName = "dayHRFile" + number + ".txt";
        File readFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        char [] readArray = new char[2880];
        reader.read(readArray, 0, 2880);
        return readArray;
    }

    /**
     * writes from the thirtySecAvg (one hour) array into a file
     */
    public static void writeFile(ArrayList<Integer> array) throws IOException
    {
        hourCounter++;
        char [] oneHourArray = readFile();
        writeOneDayFile(oneHourArray);
        File hourHRFile = new File("hourHRFile.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(hourHRFile, true));
        for (int i = 0; i < 120; i++) {
            //writer.append(' ');
            int val = array.remove(0);
            writer.append((char) val);
        }
        writer.close();

    }

    /**
     *copies the 120 values from the previous hour file into current day file
     */
    public static void writeOneDayFile(char [] array) throws IOException
    {
        if (hourCounter > 24)
        {
            fileNumber++;
            hourCounter=0;
        }
        if (fileNumber > 5)
        {
            fileNumber--;
           char [] longTermArray = readDayFile(5);
           writeLongTermFile(longTermArray);
            File deleteFile = new File("dayHRFile1.txt");
            deleteFile.delete();
            for (int i = 2; i < 6; i++)
            {
                String fileName = "dayHRFile" + i;
                File temp = new File(fileName);
                String newFileName = "dayHRFile" + (i-1);
                File temp2 = new File(newFileName);
                temp.renameTo(temp2);
            }

        }
        String fileName = "dayHRFile" + fileNumber + ".txt";
        File dayHRFile = new File(fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(dayHRFile, true));
        for (int i = 0; i < 120; i++)
        {
           // writer.append(' ');
            int val = array[i];
            writer.append((char) val);

        }
        writer.close();

    }

    /**
     *writes files older than five days to longTermFile and syncs after 21 days of data
     */
    static void writeLongTermFile(char [] array) throws IOException
    {
        dayCounter++;
        File longTermHRFile = new File("longTermHRFile.txt");
        if (dayCounter > 20)
        {
            //syncData(longTermHRFile);
            //longTermHRFile.delete();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(longTermHRFile, true));
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