/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package game;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * FXML Controller class
 *
 * @author mortooox
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button btn_single;
    @FXML
    private Button btn_multi;
    @FXML
    private Button btn_setting;
    @FXML
    private Button btn_about;
    @FXML
    private Button btn_exit;
    @FXML
    private VBox main_vbox;
    @FXML
    private AnchorPane pane_multi;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_start_multi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        main_vbox.setVisible(true);
        pane_multi.setVisible(false);
     
    }

    @FXML
    private void handelBtns(ActionEvent event) {

        if (event.getSource().equals(btn_single)) {

        } else if (event.getSource().equals(btn_multi)) {
            
            main_vbox.setVisible(false);
            pane_multi.setVisible(true);

        } else if (event.getSource().equals(btn_setting)) {

        } else if (event.getSource().equals(btn_about)) {
            
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("about the authoer : ");
            alert.setContentText("sfsdfsdfsdfsfsf");
            alert.showAndWait();

        }  else if (event.getSource().equals(btn_exit)) {//exit

            System.exit(0);

        }else if (event.getSource().equals(btn_start_multi)){
            

            
        }
    }

    @FXML
    private void initialize(ActionEvent event) {
    }

}
