package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneDistance5DayController extends guiNavigation {

    @FXML
    LineChart Dist5DayGraph;

    @FXML
    public void initialize(){
        XYChart.Series series = new XYChart.Series();
        series.setName("Distance:5 Day");
        double day1 = SYS.DistanceCalculator.calculateDistance(1);
        double day2 = SYS.DistanceCalculator.calculateDistance(2);
        double day3 = SYS.DistanceCalculator.calculateDistance(3);
        double day4 = SYS.DistanceCalculator.calculateDistance(4);
        double day5 = SYS.DistanceCalculator.calculateDistance(5);


        series.getData().add(new XYChart.Data<>("1",day1));
        series.getData().add(new XYChart.Data<>("2",day2));
        series.getData().add(new XYChart.Data<>("3",day3));
        series.getData().add(new XYChart.Data<>("4",day4));
        series.getData().add(new XYChart.Data<>("5",day5));

        Dist5DayGraph.getData().add(series);
    }

}
