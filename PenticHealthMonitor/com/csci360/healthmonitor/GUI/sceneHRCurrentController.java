package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * W. Scott Palmer II
 * 11/22/2017
 */
public class sceneHRCurrentController extends guiNavigation {

    @FXML
    Label HRDisplay;

    @FXML
    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                e -> HRDisplay.setText("HR = " + SYS.HRSensor.getCurrent())),
                new KeyFrame((Duration.seconds(1))));

        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }
}
