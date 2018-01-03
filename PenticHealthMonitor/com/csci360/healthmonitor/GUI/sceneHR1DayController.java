package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import SYS.*;
import java.io.*;
import java.lang.*;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Graph is setup to display over twenty four hours, this can be changed by deleting any
 * number of series additions and modifying the labels(xValue) to how you see fit. It may
 * be that you only need 4 data points to represent a day.
 */
public class sceneHR1DayController extends guiNavigation {

    @FXML
    LineChart HR1DayGraph;

    @FXML
    public void initialize() throws IOException{
        int fileNumber = HRFile.getCurrentFile();
        XYChart.Series series = new XYChart.Series();
        series.setName("HR:1 Day");

        char[] dayHRArray = HRFile.readDayFile(fileNumber);
        int value1 = 0;
        int value2 = 0;
        int value3 = 0;
        int value4 = 0;
        int value5 = 0;
        for (int i = 0; i < 575; i++)
        {
            value1 += dayHRArray[i];
        }
        value1 = value1 / 576;
        for (int i = 575; i < 1151; i++)
        {
            value2 += dayHRArray[i];
        }
        value2 = value2 / 576;
        for (int i = 1151; i < 1727; i++)
        {
            value3 += dayHRArray[i];
        }
        value3 = value3 / 576;
        for (int i = 1727; i < 2303; i++)
        {
            value4 += dayHRArray[i];
        }
        value4 = value4 / 576;
        for (int i = 2303; i < 2879; i++)
        {
            value5 += dayHRArray[i];
        }
        value5 = value5 / 576;
        series.getData().add(new XYChart.Data<>("1",value1));
        series.getData().add(new XYChart.Data<>("2",value2));
        series.getData().add(new XYChart.Data<>("3",value3));
        series.getData().add(new XYChart.Data<>("4",value4));
        series.getData().add(new XYChart.Data<>("5",value5));

        HR1DayGraph.getData().add(series);
    }

}
