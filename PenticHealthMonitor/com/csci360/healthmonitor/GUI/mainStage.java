package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Main of GUI
 */
public class mainStage extends Application {

    //Window Size
    private final int width = 450;
    private final int height = 600;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Loads FXML Docs and sets Panes for each Scene
        //sceneWelcome
        FXMLLoader paneHomeLoaderA = new FXMLLoader(getClass().getResource("sceneWelcome.fxml"));
        Parent paneA = paneHomeLoaderA.load();
        Scene sceneWelcome = new Scene(paneA, width, height);

        //sceneUserValues
        //FXMLLoader paneHomeLoaderB = new FXMLLoader(getClass().getResource("sceneUserValues.fxml"));
        //Parent paneB = paneHomeLoaderB.load();
        //Scene sceneUserValues = new Scene(paneB, width, height);

        //sceneHome
        FXMLLoader paneHomeLoader1 = new FXMLLoader(getClass().getResource("sceneHome.fxml"));
        Parent pane1 = paneHomeLoader1.load();
        Scene sceneHome = new Scene(pane1, width, height);

            //sceneAlarm
            FXMLLoader paneLoader1a = new FXMLLoader(getClass().getResource("sceneAlarm.fxml"));
            Parent pane1a = paneLoader1a.load();
            Scene sceneAlarm = new Scene(pane1a, width, height);

            //sceneSettings
            FXMLLoader paneLoader1b = new FXMLLoader(getClass().getResource("sceneSettings.fxml"));
            Parent pane1b = paneLoader1b.load();
            Scene sceneSettings = new Scene(pane1b, width, height);

        //sceneHRCurrent
        FXMLLoader paneLoader2 = new FXMLLoader(getClass().getResource("sceneHRCurrent.fxml"));
        Parent pane2 = paneLoader2.load();
        Scene sceneHRCurrent = new Scene(pane2, width, height);

            //sceneHR1Day
            FXMLLoader paneLoader2a = new FXMLLoader(getClass().getResource("sceneHR1Day.fxml"));
            Parent pane2a = paneLoader2a.load();
            Scene sceneHR1Day = new Scene(pane2a, width, height);

            //sceneHR5Day
            FXMLLoader paneLoader2b = new FXMLLoader(getClass().getResource("sceneHR5Day.fxml"));
            Parent pane2b = paneLoader2b.load();
            Scene sceneHR5Day = new Scene(pane2b, width, height);


        // sceneCaloriesToday
        FXMLLoader paneLoader3 = new FXMLLoader(getClass().getResource("sceneCaloriesToday.fxml"));
        Parent pane3 = paneLoader3.load();
        Scene sceneCaloriesToday = new Scene(pane3, width, height);

            //sceneCalories1Day
            FXMLLoader paneLoader3a = new FXMLLoader(getClass().getResource("sceneCalories1Day.fxml"));
            Parent pane3a = paneLoader3a.load();
            Scene sceneCalories1Day = new Scene(pane3a, width, height);

            //sceneCalories5Day
            FXMLLoader paneLoader3b = new FXMLLoader(getClass().getResource("sceneCalories5Day.fxml"));
            Parent pane3b = paneLoader3b.load();
            Scene sceneCalories5Day = new Scene(pane3b, width, height);

        // sceneDistanceToday
        FXMLLoader paneLoader4 = new FXMLLoader(getClass().getResource("sceneDistanceToday.fxml"));
        Parent pane4 = paneLoader4.load();
        Scene sceneDistanceToday = new Scene(pane4, width, height);

            //sceneHR1Day
            FXMLLoader paneLoader4a = new FXMLLoader(getClass().getResource("sceneDistance1Day.fxml"));
            Parent pane4a = paneLoader4a.load();
            Scene sceneDistance1Day = new Scene(pane4a, width, height);

            //sceneHR5Day
            FXMLLoader paneLoader4b = new FXMLLoader(getClass().getResource("sceneDistance5Day.fxml"));
            Parent pane4b = paneLoader4b.load();
            Scene sceneDistance5Day = new Scene(pane4b, width, height);

        //sceneStepsToday
        FXMLLoader paneLoader5 = new FXMLLoader(getClass().getResource("sceneStepsToday.fxml"));
        Parent pane5 = paneLoader5.load();
        Scene sceneStepsToday = new Scene(pane5, width, height);

            //sceneSteps1Day
            FXMLLoader paneLoader5a = new FXMLLoader(getClass().getResource("sceneSteps1Day.fxml"));
            Parent pane5a = paneLoader5a.load();
            Scene sceneSteps1Day = new Scene(pane5a, width, height);

            //sceneHR5Day
            FXMLLoader paneLoader5b = new FXMLLoader(getClass().getResource("sceneSteps5Day.fxml"));
            Parent pane5b = paneLoader5b.load();
            Scene sceneSteps5Day = new Scene(pane5b, width, height);


        // Injects adjacent Scenes to allow switching between them

        //These three scenes will be the setup screens and will be one way
        //sceneWelcome to sceneUserValues
        sceneWelcomeController paneWelcomeController = paneHomeLoaderA.getController();
        paneWelcomeController.setSceneRight(sceneHome);

        //sceneUserValues to sceneHome
        //sceneUserValuesController paneUserValuesController = paneHomeLoaderB.getController();
        //paneUserValuesController.setSceneRight(sceneHome);

            //The following scenes will be able to loop left to right excluding the offshoot scenes
            //sceneHome to sceneHRCurrent, sceneStepsToday, Alarm, Settings
            sceneHomeController paneHomeController = paneHomeLoader1.getController();
            paneHomeController.setSceneRight(sceneHRCurrent);
            paneHomeController.setSceneLeft(sceneStepsToday);
            paneHomeController.setSceneUp(sceneAlarm);
            paneHomeController.setSceneDown(sceneSettings);

                //These two scenes will be offshoots of the Home Screen (up and down)
                //SceneAlarm to sceneHome
                sceneAlarmController paneAlarmController = paneLoader1a.getController();
                paneAlarmController.setSceneDown(sceneHome);

                //sceneSettings to sceneHome
                sceneSettingsController PaneController1b = paneLoader1b.getController();
                PaneController1b.setSceneUp(sceneHome);

            //sceneHRCurrent to sceneCaloriesToday and sceneHome
            sceneHRCurrentController PaneController2 = paneLoader2.getController();
            PaneController2.setSceneRight(sceneCaloriesToday);
            PaneController2.setSceneLeft(sceneHome);
            PaneController2.setSceneUp(sceneHR1Day);
            PaneController2.setSceneDown(sceneHR5Day);

                //These two scenes will be offshoots of the HRCurrent Screen (up and down)
                //SceneHR1Day to sceneHRCurrent
                sceneHR1DayController PaneController2a = paneLoader2a.getController();
                PaneController2a.setSceneDown(sceneHRCurrent);

                //sceneHR5Day to sceneHRCurrent
                sceneHR5DayController PaneController2b = paneLoader2b.getController();
                PaneController2b.setSceneUp(sceneHRCurrent);

            //sceneCaloriesToday to sceneDistanceToday and sceneHRCurrent
            sceneCaloriesTodayController PaneController3 = paneLoader3.getController();
            PaneController3.setSceneRight(sceneDistanceToday);
            PaneController3.setSceneLeft(sceneHRCurrent);
            PaneController3.setSceneUp(sceneCalories1Day);
            PaneController3.setSceneDown(sceneCalories5Day);

                //These two scenes will be offshoots of the CaloriesToday Screen (up and down)
                //sceneCalories1Day to sceneCaloriesToday
                sceneCalories1DayController PaneController3a = paneLoader3a.getController();
                PaneController3a.setSceneDown(sceneCaloriesToday);

                //sceneCalories5Day to sceneCaloriesToday
                sceneCalories5DayController PaneController3b = paneLoader3b.getController();
                PaneController3b.setSceneUp(sceneCaloriesToday);

            //sceneDistanceToday to sceneStepsToday and sceneCaloriesToday
            sceneDistanceTodayController PaneController4 = paneLoader4.getController();
            PaneController4.setSceneRight(sceneStepsToday);
            PaneController4.setSceneLeft(sceneCaloriesToday);
            PaneController4.setSceneUp(sceneDistance1Day);
            PaneController4.setSceneDown(sceneDistance5Day);

                //These two scenes will be offshoots of the DistanceToday Screen (up and down)
                //sceneDistance1Day to sceneDistanceToday
                sceneDistance1DayController PaneController4a = paneLoader4a.getController();
                PaneController4a.setSceneDown(sceneDistanceToday);

                //sceneDistance1Day to sceneDistanceToday
                sceneDistance5DayController PaneController4b = paneLoader4b.getController();
                PaneController4b.setSceneUp(sceneDistanceToday);

            //sceneStepsToday to sceneHome and sceneDistanceToday
            sceneStepsTodayController PaneController5 = paneLoader5.getController();
            PaneController5.setSceneRight(sceneHome);
            PaneController5.setSceneLeft(sceneDistanceToday);
            PaneController5.setSceneUp(sceneSteps1Day);
            PaneController5.setSceneDown(sceneSteps5Day);

                //These two scenes will be offshoots of the StepsToday Screen (up and down)
                //sceneSteps1Day to sceneStepsToday
                sceneSteps1DayController PaneController5a = paneLoader5a.getController();
                PaneController5a.setSceneDown(sceneStepsToday);

                //sceneDistance1Day to sceneDistanceToday
                sceneSteps5DayController PaneController5b = paneLoader5b.getController();
                PaneController5b.setSceneUp(sceneStepsToday);

        //Stage Setup
        primaryStage.setTitle("GUI");
        primaryStage.setScene(sceneWelcome);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
