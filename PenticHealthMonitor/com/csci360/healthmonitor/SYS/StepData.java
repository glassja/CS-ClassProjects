package SYS;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Justin Arends
 * 11/10/2017
 * Manages footstep date received from SYS.StepSensor to send to StepFile
 */

public class StepData
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
            calcThirtySecTotal();
            fiveSecArray.clear();
            fiveSecArray.add(fiveSecData);
        }
    }

    /**
     * gets the thirty sec average of values in the fiveSecArray
     */
    static void calcThirtySecTotal()
    {
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            sum += (int)fiveSecArray.remove(0);
        }
        thirtySecArrayMaker(sum);
    }

    /**
     * creates the one hour array with the averages obtained from calcThirtySecAvg
     */
    public static void thirtySecArrayMaker(int sum)
    {
        if (thirtySecArray.size() < 120)
        {
            thirtySecArray.add(sum);
        }
        else
        {
            sendData();
            thirtySecArray.clear();
            thirtySecArray.add(sum);
        }
    }

    /**
     * sends the thirty second array to StepFile to be stored when full
     */
    public static void sendData()
    {
        try
        {
            StepFile.writeFile(thirtySecArray);
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException in send data: " + e.getMessage());
        }
    }

    /**
     *sums the total steps from the current day file, hour file, and thirtySecArray
     */
    public static int stepsToday()
    {
        int sum = 0;
        try
        {
            int fileNumber = StepFile.getCurrentFile();
            char[] dayStepArray = StepFile.readDayFile(fileNumber);
            char[] hourStepArray = StepFile.readFile();
            int size1 = Array.getLength(dayStepArray);
            int size2 = Array.getLength(hourStepArray);
            for (int i = 0; i < size1; i++) {
                sum += dayStepArray[i];
            }
            for (int i = 0; i < size2; i++) {
                sum += hourStepArray[i];
            }
            for (int i = 0; i < thirtySecArray.size(); i++) {
                sum += (int) thirtySecArray.get(i);
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException in steps today: " + e.getMessage());
        }
        return sum;
    }

    /**
     *sums the steps in the specified day file
     */
    public static int dailySteps(int number)
    {
        int sum = 0;
        try
        {
            char[] dayStepArray = StepFile.readDayFile(number);
            int size = Array.getLength(dayStepArray);
            for (int i = 0; i < size; i++) {
                sum += dayStepArray[i];
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException in daily steps: " + e.getMessage());
        }
        return sum;
    }

    /**
     *returns the values in the thirtySecArray
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