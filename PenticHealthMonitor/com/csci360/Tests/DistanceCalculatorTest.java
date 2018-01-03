import SYS.*;
import org.junit.Before;
import org.junit.Test;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest
{
    @Before
    public void setUp() throws Exception
    {
        File StepFile = new File("dayStepFile5.txt");
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(StepFile, true));
        for (int i = 0; i < 2880; i++)
        {
            writer2.append((char) 50);

        }
        writer2.close();
    }

    @Test
    public void testCalculateDistance()
    {
        double distanceValue = DistanceCalculator.calculateDistance(5);
        assertEquals(64.136, distanceValue, 0.001);
    }
}
