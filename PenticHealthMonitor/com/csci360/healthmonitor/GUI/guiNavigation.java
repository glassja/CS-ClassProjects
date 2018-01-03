package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Provides basic navigational functionality enabling the user to switch between scenes.
 * The Controller classes for the corresponding FXML Docs extend this class.
 */
public class guiNavigation extends Label{
    @FXML
    Button buttonSceneRight;
    @FXML
    Button buttonSceneLeft;
    @FXML
    Button buttonSceneUp;
    @FXML
    Button buttonSceneDown;

    private Scene sceneRight, sceneLeft, sceneUp, sceneDown;

    void setSceneRight(Scene scene) {

        sceneRight = scene;
    }
    void setSceneLeft(Scene scene) {

        sceneLeft = scene;
    }
    void setSceneUp(Scene scene) {

        sceneUp = scene;
    }
    void setSceneDown(Scene scene) {

        sceneDown = scene;
    }

    public void shiftSceneRight(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneRight);

    }

    public void shiftSceneLeft(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneLeft);

    }

    public void shiftSceneUp(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneUp);

    }

    public void shiftSceneDown(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(sceneDown);

    }
}
