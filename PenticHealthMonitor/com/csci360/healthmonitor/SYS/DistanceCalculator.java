package SYS;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Justin Arends
 * 10/13/2017
 * Calculates distance from step data and user values.
 */

public class DistanceCalculator
{

    //approximates the User's step length for a more accurate distance calculation
    private static double stepLength()
    {
        if(UserValues.getGender() == 'm')
        {
            return ((UserValues.getHeight() * 2.54) * 0.415) / 2.54;
        }
        else
        {
            return ((UserValues.getHeight() * 2.54) * 0.413) / 2.54;
        }
    }
    //gets the daily steps and returns the daily distance in miles
    public static double calculateDistance(int number)
    {
        double stepLen = stepLength();
        double sum = 0;
        try
        {   //reads the specified day files into their respective arrays used for calculation
            char[] stepArray = StepFile.readDayFile(number);
            int size = Array.getLength(stepArray);
            //performs the calorie calculation on the array values and records the sum
            for (int i = 0; i < size; i++)
            {
                sum += stepArray[i];
            }
            //reads the hour files into arrays used for calculation
            stepArray = StepFile.readFile();
            size = Array.getLength(stepArray);
            //performs the calorie calculations on the hour values and adds to the sum
            for (int i = 0; i < size; i++)
            {
                sum += stepArray[i];
            }
            //reads the thirtySecArray data into the arrays used for calculation
            stepArray = StepData.readThirtySecArray();
            size = Array.getLength(stepArray);
            //performs the calorie calculations on the thirtySecArray values and adds to the sum
            for (int i = 0; i < size; i++)
            {
                sum += stepArray[i];
            }
        }

        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return (sum * stepLen)/63360;
    }
}
