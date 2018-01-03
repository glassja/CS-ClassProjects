import SYS.*;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import static org.junit.Assert.assertEquals;

public class CalorieCalculatorTest
{
    @Before
    public void setUp() throws Exception
    {
        File HRFile = new File("dayHRFile5.txt");
        File StepFile = new File("dayStepFile5.txt");
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(HRFile, true));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(StepFile, true));
        for (int i = 0; i < 2880; i++)
        {
            writer1.append((char) 60);
            writer2.append((char) 50);

        }
        writer1.close();
        writer2.close();
    }

    @Test
    public void testCalculateCalories()
    {
        double calorieValue = CalorieCalculator.calculateCalories(5);
        assertEquals(5947.2, calorieValue, 0.001);
    }
}
