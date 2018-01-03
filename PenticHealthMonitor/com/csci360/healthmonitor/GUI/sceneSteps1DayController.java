package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import SYS.*;
import java.io.*;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Graph is setup to display over twenty four hours, this can be changed by deleting any
 * number of series additions and modifying the labels(xValue) to how you see fit. It may
 * be that you only need 4 data points to represent a day.
 */
public class sceneSteps1DayController extends guiNavigation {

    @FXML
    LineChart Steps1DayGraph;

    @FXML
    public void initialize() throws IOException{
        int fileNumber = StepFile.getCurrentFile();
        Series series = new Series();
        series.setName("Steps: 1 Day");
        char[] dayStepArray = StepFile.readDayFile(fileNumber);
        int value1 = 0;
        int value2 = 0;
        int value3 = 0;
        int value4 = 0;
        int value5 = 0;
        for (int i = 0; i < 576; i++)
        {
            value1 += dayStepArray[i];
        }
        for (int i = 576; i < 1152; i++)
        {
            value2 += dayStepArray[i];
        }
        for (int i = 1152; i < 1728; i++)
        {
            value3 += dayStepArray[i];
        }
        for (int i = 1728; i < 2304; i++)
        {
            value4 += dayStepArray[i];
        }
        for (int i = 2304; i < 2880; i++)
        {
            value5 += dayStepArray[i];
        }


        series.getData().add(new XYChart.Data<>("1",value1));
        series.getData().add(new XYChart.Data<>("2",value2));
        series.getData().add(new XYChart.Data<>("3",value3));
        series.getData().add(new XYChart.Data<>("4",value4));
        series.getData().add(new XYChart.Data<>("5",value5));

        Steps1DayGraph.getData().add(series);
    }

}