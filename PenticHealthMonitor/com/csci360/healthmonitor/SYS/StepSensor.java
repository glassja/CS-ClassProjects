package SYS;

import java.util.*;

/**
 * Justin Arends
 * 10/13/2017
 * Receives footstep data to be displayed or used by other classes.
 */

public class StepSensor {

    //fiveSecData variable stores the number of steps per five seconds
    static int fiveSecData = 0;
    static int currentSteps = 0;
    public static Timer timer1 = new Timer("fiveSecTimer");

    /**
     * sends the fiveSecData to SYS.StepData every five seconds and resets the counter
     */
    public static void beginOperation()
    {
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentSteps = fiveSecData;
                sendData(fiveSecData);
                fiveSecData = 0;
            }
        }, 0, 5000);
    }

    /**
     * increments fiveSecData every time footstep method is invoked
     */
    public static void footstep()
    {
        fiveSecData++;
    }

    /**
     * sends fiveSecData to be stored in an array
     */
    private static void sendData(int value)
    {
        SYS.StepData.fiveSecArrayMaker(value);
    }

    public static int getCurrent()
    {
        return currentSteps;
    }
}