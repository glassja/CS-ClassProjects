package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneHR5DayController extends guiNavigation {

    @FXML
    LineChart HR5DayGraph;

    @FXML
    public void initialize(){
        XYChart.Series series = new XYChart.Series();
        series.setName("HR:5 Day");

        int day1 = SYS.HRData.dailyHR(1);
        int day2 = SYS.HRData.dailyHR(2);
        int day3 = SYS.HRData.dailyHR(3);
        int day4 = SYS.HRData.dailyHR(4);
        int day5 = SYS.HRData.dailyHR(5);

        series.getData().add(new XYChart.Data<>("1",day1));
        series.getData().add(new XYChart.Data<>("2",day2));
        series.getData().add(new XYChart.Data<>("3",day3));
        series.getData().add(new XYChart.Data<>("4",day4));
        series.getData().add(new XYChart.Data<>("5",day5));

        HR5DayGraph.getData().add(series);
    }

}
