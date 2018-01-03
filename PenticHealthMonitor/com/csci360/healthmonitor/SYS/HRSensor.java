package SYS;

import java.util.*;

/**
 * Justin Arends
 * 10/13/2017
 * Receives heart rate data to be displayed or used by other classes.
 */

public class HRSensor {

    //fiveSecData variable stores the number of heartbeats per five seconds
    static int fiveSecData = 0;
    static int currentHR = 0;
    public static Timer timer1 = new Timer("fiveSecTimer");

    /**
     * sends the fiveSecData to SYS.HRData every five seconds and resets the counter
     */
    public static void beginOperation()
    {
        timer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                fiveSecData = fiveSecData * 12;
                currentHR = fiveSecData;
                sendData(fiveSecData);
                fiveSecData = 0;
            }
        }, 0, 5000);
    }

    /**
     * increments fiveSecData every time heartbeat method is invoked
     */
    public static void heartbeat()
    {
        fiveSecData++;
    }

    /**
     * sends fiveSecData to be stored in an array
     */
    private static void sendData(int value)
    {
        SYS.HRData.fiveSecArrayMaker(value);
    }


    public static String getCurrent()
    {
        String current = Integer.toString(currentHR);
        return current;
    }
}