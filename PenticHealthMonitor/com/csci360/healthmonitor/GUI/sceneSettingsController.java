package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * W. Scott Palmer II
 * 11/22/2017
 * Performs a "Factory Reset" by closing the program.
 */
public class sceneSettingsController extends guiNavigation {

    @FXML
    Button FacReset;

    @FXML
    private void FactoryReset(){
        Stage stage = (Stage) FacReset.getScene().getWindow();
        stage.close();
    }
}
