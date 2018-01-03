package SYS;

import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;

/**
 * Justin Arends
 * 10/13/2017
 * Calculates calories from step and HR data based on user values.
 */

public class CalorieCalculator
{

    public static double calculateCalories(int number)
    {
        double sum = 0;
        try
        {   //reads the specified day files into their respective arrays used for calculation
            char[] HRArray = HRFile.readDayFile(number);
            char[] stepArray = StepFile.readDayFile(number);
            int size = Array.getLength(stepArray);
            //performs the calorie calculation on the array values and records the sum
            for (int i = 0; i < size; i++)
            {
                int temp = (int) stepArray[i] + (int) HRArray[i];
                sum += (.0175 * (temp / 45) * UserValues.getWeight());
            }
            //reads the hour files into arrays used for calculation
            HRArray = HRFile.readFile();
            stepArray = StepFile.readFile();
            size = Array.getLength(HRArray);
            //performs the calorie calculations on the hour values and adds to the sum
            for (int i = 0; i < size; i++)
            {
                int temp = (int) stepArray[i] + (int) HRArray[i];
                sum += (.0175 * (temp /45) * UserValues.getWeight());
            }
            //reads the thirtySecArray data into the arrays used for calculation
            HRArray = HRData.readThirtySecArray();
            stepArray = StepData.readThirtySecArray();
            size = Array.getLength(HRArray);
            //performs the calorie calculations on the thirtySecArray values and adds to the sum
            for (int i = 0; i < size; i++)
            {
                int temp = (int) stepArray[i] + (int) HRArray[i];
                sum += (.0175 * (temp /45) * UserValues.getWeight());
            }
        }

        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return sum;
    }
}