package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneDistanceTodayController extends guiNavigation {

    @FXML
    Label DistanceDisplay;

    @FXML
    public void initialize(){
        int stepsToday = SYS.StepData.stepsToday();
        int fileNumber = SYS.StepFile.getCurrentFile();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                e -> DistanceDisplay.setText("Distance = " + SYS.DistanceCalculator.calculateDistance(fileNumber))),
                new KeyFrame((Duration.seconds(1))));

        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }
}