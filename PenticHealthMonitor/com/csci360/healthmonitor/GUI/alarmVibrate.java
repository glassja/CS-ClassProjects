package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class alarmVibrate {

    public static void popWindow(String title, String messageOne, String messageTwo){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(250);

        Label vibrateSimulation = new Label();
        vibrateSimulation.setText(messageOne);
        Label explanation = new Label();
        explanation.setText(messageTwo);
        Button close = new Button("Close");
        close.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(vibrateSimulation, explanation, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
