package GUI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * W. Scott Palmer II
 * 12/1/2017
 */
public class sceneAlarmController extends guiNavigation {

    @FXML
    Label Alarm;

    @FXML
    Button alarmSet;

    @FXML
    Button Hour;

    @FXML
    Button Min;

    private final DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm a");
    private final String am = "AM";
    private final String pm = "PM";

    private int hr =12;
    private int min =0;

    private String Hr = String.format("%02d", hr);
    private String Mn = String.format("%02d", min);
    private String timeCon = pm;
    private String alarmFormat;

    private boolean alarmOnOff = false;

    @FXML
    private void incHour(){
        if (hr>0 && hr<12){
            hr++;
        }else{
            hr = 1;
        }

        if (hr == 12){
            if (timeCon.equalsIgnoreCase(pm)){
                timeCon = am;
            }else{
                timeCon = pm;
            }
        }

        Hr = String.format("%02d", hr);
        updateAlarmLabel();

    }

    @FXML
    private void incMin(){
        if (min>=0 && min<59){
            min++;
        }else{
            min = 0;
            incHour();
        }

        Mn = String.format("%02d", min);
        updateAlarmLabel();
    }

    @FXML
    private void alarmOnOff(){
        if (alarmOnOff){
            alarmSet.setText("Set");
            Hour.setVisible(true);
            Min.setVisible(true);
        }else{
            alarmSet.setText("Turn off");
            Hour.setVisible(false);
            Min.setVisible(false);
        }
        alarmOnOff = !alarmOnOff;
    }

    private void updateAlarmLabel(){
        alarmFormat = Hr + ":" + Mn + " " + timeCon;
        Alarm.setText(alarmFormat);
    }

    private void compareTime(){
        if (alarmOnOff && alarmFormat.equals(LocalTime.now().format(time))){
            alarmVibrate.popWindow("Vibration", "BZZZZT...", "(This window is simulating vibration.)");
            alarmOnOff();
        }
    }

    public void initialize(){
        updateAlarmLabel();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                e -> compareTime()), new KeyFrame((Duration.seconds(1))));

        timeline.setCycleCount((Animation.INDEFINITE));
        timeline.play();
    }


}
