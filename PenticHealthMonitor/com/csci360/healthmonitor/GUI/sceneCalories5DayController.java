package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneCalories5DayController extends guiNavigation {

    @FXML
    LineChart Cal5DayGraph;

    @FXML
    public void initialize(){
        XYChart.Series series = new XYChart.Series();
        series.setName("Calories: 5 Day");
        double day1 = SYS.CalorieCalculator.calculateCalories(1);
        double day2 = SYS.CalorieCalculator.calculateCalories(2);
        double day3 = SYS.CalorieCalculator.calculateCalories(3);
        double day4 = SYS.CalorieCalculator.calculateCalories(4);
        double day5 = SYS.CalorieCalculator.calculateCalories(5);


        series.getData().add(new XYChart.Data<>("1",day1));
        series.getData().add(new XYChart.Data<>("2",day2));
        series.getData().add(new XYChart.Data<>("3",day3));
        series.getData().add(new XYChart.Data<>("4",day4));
        series.getData().add(new XYChart.Data<>("5",day5));

        Cal5DayGraph.getData().add(series);
    }
}
