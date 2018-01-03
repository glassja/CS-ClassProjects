package GUI;

import SYS.StepFile;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import SYS.*;
import java.io.*;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Graph is setup to display over twenty four hours, this can be changed by deleting any
 * number of series additions and modifying the labels(xValue) to how you see fit. It may
 * be that you only need 4 data points to represent a day.
 */
public class sceneCalories1DayController extends guiNavigation {

    @FXML
    LineChart Cal1DayGraph;

    @FXML
    public void initialize() throws IOException{
        XYChart.Series series = new XYChart.Series();
        series.setName("Calories:1 Day");
        int fileNumber = StepFile.getCurrentFile();
        char[] dayStepArray = StepFile.readDayFile(fileNumber);
        char[] dayHRArray = StepFile.readDayFile(fileNumber);
        double value1 = 0;
        double value2 = 0;
        double value3 = 0;
        double value4 = 0;
        double value5 = 0;
        for (int i = 0; i < 576; i++)
        {
            int temp = dayStepArray[i] + dayHRArray[i];
            value1 += (.0175 * (temp / 45) * UserValues.getWeight());
        }
        for (int i = 576; i < 1152; i++)
        {
            int temp = dayStepArray[i] + dayHRArray[i];
            value2 += (.0175 * (temp / 45) * UserValues.getWeight());
        }
        for (int i = 1152; i < 1728; i++)
        {
            int temp = dayStepArray[i] + dayHRArray[i];
            value3 += (.0175 * (temp / 45) * UserValues.getWeight());
        }
        for (int i = 1728; i < 2304; i++)
        {
            int temp = dayStepArray[i] + dayHRArray[i];
            value4 += (.0175 * (temp / 45) * UserValues.getWeight());
        }
        for (int i = 2304; i < 2880; i++)
        {
            int temp = dayStepArray[i] + dayHRArray[i];
            value5 += (.0175 * (temp / 45) * UserValues.getWeight());
        }

        series.getData().add(new XYChart.Data<>("1",value1));
        series.getData().add(new XYChart.Data<>("2",value2));
        series.getData().add(new XYChart.Data<>("3",value3));
        series.getData().add(new XYChart.Data<>("4",value4));
        series.getData().add(new XYChart.Data<>("5",value5));

        Cal1DayGraph.getData().add(series);
    }
}
