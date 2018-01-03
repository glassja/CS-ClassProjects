package GUI;

import SYS.*;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import java.io.*;


/**
 * W. Scott Palmer II
 * 11/22/2017
 * Graph is setup to display over twenty four hours, this can be changed by deleting any
 * number of series additions and modifying the labels(xValue) to how you see fit. It may
 * be that you only need 4 data points to represent a day.
 */
public class sceneDistance1DayController extends guiNavigation {

    @FXML
    LineChart Dist1DayGraph;

    @FXML
    public void initialize() throws IOException{
        XYChart.Series series = new XYChart.Series();
        int fileNumber = StepFile.getCurrentFile();
        series.setName("Distance:1 Day");

        char[] dayStepArray = StepFile.readDayFile(fileNumber);
        double value1 = 0;
        double value2 = 0;
        double value3 = 0;
        double value4 = 0;
        double value5 = 0;
        for (int i = 0; i < 576; i++)
        {
            value1 += dayStepArray[i];
        }
        value1 = value1 / 2100;
        for (int i = 576; i < 1152; i++)
        {
            value2 += dayStepArray[i];
        }
        value2 = value2 / 2100;
        for (int i = 1152; i < 1728; i++)
        {
            value3 += dayStepArray[i];
        }
        value3 = value3 / 2100;
        for (int i = 1728; i < 2304; i++)
        {
            value4 += dayStepArray[i];
        }
        value4 = value4 / 2100;
        for (int i = 2304; i < 2880; i++)
        {
            value5 += dayStepArray[i];
        }
        value5 = value5 / 2100;
        series.getData().add(new XYChart.Data<>("1",value1));
        series.getData().add(new XYChart.Data<>("2",value2));
        series.getData().add(new XYChart.Data<>("3",value3));
        series.getData().add(new XYChart.Data<>("4",value4));
        series.getData().add(new XYChart.Data<>("5",value5));
        Dist1DayGraph.getData().add(series);
    }



}
