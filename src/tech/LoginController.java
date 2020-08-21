/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField nameText;
    
    String title;
    
    @FXML
    private TextField idText;
    /**
     * Initializes the controller class.
     */
    
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    String stud_dept;
    String stud_class;
    String individual;
    
    /**
     *
     * @param individual
     */
    public void setIndividual(String individual){
        this.individual = individual;
    }
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
       //-----------this login details are for the teacher's their login details are fixed
        if(individual.equals("teacher")){
                if(nameText.getText().toLowerCase().equals("emmanuel")&& idText.getText().toLowerCase().equals("techhive") ){
                    teacherCall(event);
                }else{
                     alert("Username Or Id is inccorect");
                }
        }else if(individual.equals("student")){
        
            try {
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from student where stu_id=? and name=?";
        PreparedStatement pst;
            
        pst = con.prepareStatement(sql);
        pst.setString(1, idText.getText());
        pst.setString(2, nameText.getText());
       
         //-----checking if the values match with any esiting values in the database
        rs=pst.executeQuery();
            if( rs.next()){
             //-----calling the page where the student has to select the examination subject
             stud_dept = rs.getString("department");
             stud_class = rs.getString("class");   
             studentCall(event);
               
            }else{
                     alert("Username Or Id is inccorect");
                }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
               
        }
    
     
    }
    
       public void alert(String msg){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error::::");
            //alert.setHeaderText("Header");
            alert.setContentText(msg);
            alert.show();
    }
    
    
    private void teacherCall(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        Scene sce= new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();  
    }
    
     private void studentCall(ActionEvent event) throws IOException{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("Student.fxml"));
        Parent root = loader.load();
        StudentController controller = loader.getController();
        //
        //---sending values to the student controller
        //
        controller.setInfo(nameText.getText(), idText.getText(),stud_dept, stud_class);Scene sce= new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();  
    }
    
     @FXML
    private void backAction(ActionEvent event) throws IOException{
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
