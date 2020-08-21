/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author EMMANUEL AYEMERE
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    
    LoginController l =new LoginController();
    
    @FXML
    private void teacherAction(ActionEvent event) throws IOException {
        
         FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setIndividual("teacher");
        Scene sce = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();  
        
       
       
    }
    
    @FXML
     private void studentAction(ActionEvent event) throws IOException {

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        controller.setIndividual("student");
        Scene sce = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();  
        
       
       
    }
    
    
    // Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    
     /*
     private void loginCall(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene sce = new Scene(root);//, screenBounds.getWidth(), screenBounds.getHeight());
        
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.setX(-4);
        //stage.setY(0);
        stage.setScene(sce);
        
        //stage.setFullScreen(true);
        stage.show();  
        
    }

*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
