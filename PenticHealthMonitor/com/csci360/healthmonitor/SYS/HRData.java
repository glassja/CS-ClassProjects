package SYS;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

/**
 * Justin Arends
 * 11/10/2017
 * Manages heart rate date received from SYS.HRSensor to send to HRFile
 */

public class HRData
    {
    //fiveSecArray holds thirty secs worth of data, one value per five seconds
    static ArrayList fiveSecArray = new ArrayList(6);
    //thirtySecArray holds one hour worth of data, one value per thirty seconds
    static ArrayList thirtySecArray = new ArrayList(120);

        /**
         * sends fiveSecArray data to calcThirtySecAvg when full
         */
    static void fiveSecArrayMaker(int fiveSecData) {

        if (fiveSecArray.size() < 6)
        {
            fiveSecArray.add(fiveSecData);
        }
        else
        {
            calcThirtySecAvg();
            fiveSecArray.clear();
            fiveSecArray.add(fiveSecData);
        }
    }

        /**
         * gets the thirty sec average of values in the fiveSecArray
         */
    public static void calcThirtySecAvg()
    {
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            sum += (int)fiveSecArray.remove(0);
        }
        int average = sum / 6;
        thirtySecArrayMaker(average);
    }

        /**
         * creates the one hour array with the averages obtained from calcThirtySecAvg
         */
    public static void thirtySecArrayMaker(int average)
    {
        if (thirtySecArray.size() < 120)
        {
            thirtySecArray.add(average);
        }
        else
        {
            sendData();
            thirtySecArray.clear();
            thirtySecArray.add(average);
        }
    }

        /**
         * sends the thirty second array to HRFile to be stored when full
         */
    public static void sendData()
    {
        try
        {
            HRFile.writeFile(thirtySecArray);
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }

    }

        /**
         *returns the average HR for the specified day
         */
        public static int dailyHR(int number)
        {
            int sum = 0;
            int result = 0;
            try
            {
                char[] dayHRArray = HRFile.readDayFile(number);
                int size = Array.getLength(dayHRArray);
                for (int i = 0; i < size; i++) {
                    sum += dayHRArray[i];
                }
                result = sum / size;
            }
            catch(IOException e)
            {
                System.err.println("Caught IOException in daily steps: " + e.getMessage());
            }
            return result;
        }

        /**
         *returns the values of the current hour thirty sec data
         */
        public static char [] readThirtySecArray()
        {
            int size = thirtySecArray.size();
            char [] array = new char [size];
            for (int i = 0; i < size; i++)
            {
                int val = (int) thirtySecArray.get(i);
                array[i] = (char) val;
            }

            return array;
        }

}