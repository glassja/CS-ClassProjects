package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneStepsTodayController extends guiNavigation {

    @FXML
    Label StepsDisplay;

    @FXML
    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                e -> StepsDisplay.setText("Steps = " + (SYS.StepData.stepsToday()))),
                new KeyFrame((Duration.seconds(1))));

        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }


}