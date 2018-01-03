package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

import java.time.LocalTime;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneSteps5DayController extends guiNavigation {

    @FXML
    LineChart Steps5DayGraph;

    @FXML
    public void initialize(){

        XYChart.Series series = new XYChart.Series();
        series.setName("Steps: 5 Day");
        int day1 = SYS.StepData.dailySteps(1);
        int day2 = SYS.StepData.dailySteps(2);
        int day3 = SYS.StepData.dailySteps(3);
        int day4 = SYS.StepData.dailySteps(4);
        int day5 = SYS.StepData.dailySteps(5);

        series.getData().add(new XYChart.Data<>("1",day1));
        series.getData().add(new XYChart.Data<>("2",day2));
        series.getData().add(new XYChart.Data<>("3",day3));
        series.getData().add(new XYChart.Data<>("4",day4));
        series.getData().add(new XYChart.Data<>("5",day5));

        Steps5DayGraph.getData().add(series);

    }

}
