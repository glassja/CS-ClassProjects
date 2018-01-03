import java.util.*;
import GUI.*;
import java.io.*;

/**
 * Justin Arends
 * 10/13/2017
 * Simulates a user's heart rate and step data.
 */

public class TestUser {

    static int temp = 0;

    //timer1 object for simulated heartbeat, timer2 for steps.
    Timer timer1 = new Timer();
    Timer timer2 = new Timer();

    /**
     * schedules the heartbeat based on seconds parameter
     */
    private void heart(int value)
    {
        if (value == 0)
        {
            timer1.cancel();
        }
        else {
            int seconds = value;
            timer1.cancel();
            timer1 = new Timer("heartbeat");
            timer1.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SYS.HRSensor.heartbeat();
                }
            }, 0, seconds);
        }
    }

    /**
     * schedules steps based on seconds parameter
     */
    private void step(int value)
    {
        if (value == 0)
        {
            timer2.cancel();
        }
        else {
            int seconds = value;
            timer2.cancel();
            timer2 = new Timer("steprate");
            timer2.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    SYS.StepSensor.footstep();
                }
            }, 0, seconds);
        }
    }

    /**
     * calculates seconds parameter for heartrate schedule parameter from desired rate per minute
     */
    private static int heartrate(int rate)
    {
        if (rate != 0) {
            int seconds = 60000 / rate;
            return seconds;
        }
        else
        {
            return rate;
        }
    }
    /**
     * calculates seconds parameter for step schedule parameter from desired rate per minute
     */
    private static int steprate(int rate)
    {
        if (rate != 0) {
            int seconds = 60000 / rate;
            return seconds;
        }
        else
        {
            return rate;
        }
    }

    public static void main(String [] args) throws IOException
    {
        //begins sensor operations
        SYS.HRSensor.beginOperation();
        SYS.StepSensor.beginOperation();
        Scanner input = new Scanner(System.in);
        //value variable used to convert desired heartrate into usable seconds parameter
        int value;
        //exit variable used to stop the timer schedules
        int exit = 0;
        TestUser User = new TestUser();
        //three different options for simulated heartrate / steps
        System.out.println("Select 1 for walking (3 mph), 2 for jogging (5 mph), 3 for running (8 mph).");
        while (exit != 1)
        {
            int selection = input.nextInt();
            if (selection == 1)
            {
                value = heartrate(60);
                User.heart(value);
                value = steprate(100);
                User.step(value);
            }
            else if (selection == 2)
            {
                value = heartrate(120);
                User.heart(value);
                value = steprate(242);
                User.step(value);
            }
            else if (selection == 3)
            {
                value = heartrate(160);
                User.heart(value);
                value = steprate(409);
                User.step(value);
            }
            else if (selection == 4)
            {
                /**
                 * Uncomment to create five simulated days of data to display in graphs.
                 *
                File HRFile1 = new File("dayHRFile1.txt");
                File StepFile1 = new File("dayStepFile1.txt");
                File HRFile2 = new File("dayHRFile2.txt");
                File StepFile2 = new File("dayStepFile2.txt");
                File HRFile3 = new File("dayHRFile3.txt");
                File StepFile3 = new File("dayStepFile3.txt");
                File HRFile4 = new File("dayHRFile4.txt");
                File StepFile4 = new File("dayStepFile4.txt");
                File HRFile5 = new File("dayHRFile5.txt");
                File StepFile5 = new File("dayStepFile5.txt");
                BufferedWriter writer1a = new BufferedWriter(new FileWriter(HRFile1, true));
                BufferedWriter writer1b = new BufferedWriter(new FileWriter(StepFile1, true));
                BufferedWriter writer2a = new BufferedWriter(new FileWriter(HRFile2, true));
                BufferedWriter writer2b = new BufferedWriter(new FileWriter(StepFile2, true));
                BufferedWriter writer3a = new BufferedWriter(new FileWriter(HRFile3, true));
                BufferedWriter writer3b = new BufferedWriter(new FileWriter(StepFile3, true));
                BufferedWriter writer4a = new BufferedWriter(new FileWriter(HRFile4, true));
                BufferedWriter writer4b = new BufferedWriter(new FileWriter(StepFile4, true));
                BufferedWriter writer5a = new BufferedWriter(new FileWriter(HRFile5, true));
                BufferedWriter writer5b = new BufferedWriter(new FileWriter(StepFile5, true));
                for (int i = 0; i < 2880; i++)
                {
                    writer1a.append((char) (60 + (int)(Math.random() * 80)));
                    writer2a.append((char) (60 + (int)(Math.random() * 80)));
                    writer3a.append((char) (60 + (int)(Math.random() * 80)));
                    writer4a.append((char) (60 + (int)(Math.random() * 80)));
                    writer5a.append((char) (60 + (int)(Math.random() * 80)));
                    writer1b.append((char) (0 + (int)(Math.random() * 30)));
                    writer2b.append((char) (0 + (int)(Math.random() * 30)));
                    writer3b.append((char) (0 + (int)(Math.random() * 30)));
                    writer4b.append((char) (0 + (int)(Math.random() * 30)));
                    writer5b.append((char) (0 + (int)(Math.random() * 30)));
                }

                writer1a.close();
                writer2a.close();
                writer3a.close();
                writer4a.close();
                writer5a.close();
                writer1b.close();
                writer2b.close();
                writer3b.close();
                writer4b.close();
                writer5b.close();
                 */

                mainStage.main(args);
            }
            else
            {
                System.out.println("Terminating.");
                exit = 1;
                User.heart(0);
                User.step(0);
                SYS.HRSensor.timer1.cancel();
                SYS.StepSensor.timer1.cancel();
            }
        }
    }
}