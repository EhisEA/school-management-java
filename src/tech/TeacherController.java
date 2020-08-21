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
public class TeacherController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void addStudentAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
     @FXML
    private void addSubjectAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("AddSubject.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
   
         @FXML
    private void ExamQuestion(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("ExamQuestion.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
    
         @FXML
    private void removeSubject(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("RemoveSubject.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
           @FXML
    private void removeStudent(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("RemoveStudent.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
         @FXML
    private void result(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("Result.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
    
       @FXML
    private void logout(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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
