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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class ExamQuestionController implements Initializable {
   
      @FXML
    private void viewExamQuestion(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("ViewExamQuestion.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
        @FXML
    private void setExamQuestion(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("SetExamQuestion.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
     @FXML
    private void backAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
