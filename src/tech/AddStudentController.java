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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class AddStudentController implements Initializable {
    
    @FXML
     TextField regText;
    @FXML
     TextField nameText;
    @FXML
    public ComboBox classText;
    @FXML
    public ComboBox departmentText;
    
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void addStudentButton(ActionEvent event) {
        System.out.println(classText.getValue());//.toString());
    
        try{
          ResultSet rs1 ;
        Class.forName(driver);
        Connection con1 = DriverManager.getConnection(url, user, pass);
        String sql1 = "select * from student where stu_id=? ";
        PreparedStatement pst1;
            
        pst1 = con1.prepareStatement(sql1);
        pst1.setString(1, regText.getText());
        
         rs1=pst1.executeQuery();
        
                if( !rs1.next()){
          
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "insert into student values(?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(sql);
        pst.setString(1, regText.getText() );
        pst.setString(2, nameText.getText() );
        pst.setString(3, classText.getValue().toString());//.getSelectionModel() );
        pst.setString(4, departmentText.getValue().toString());//departmentText.toString() );
        
        pst.executeUpdate();
        }else{
                    alert("Student ID already exist");
                }
    }catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, "fill all the input"); 
        }
     
    }
    
     public void alert(String msg){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error::::");
            //alert.setHeaderText("Header");
            alert.setContentText(msg);
            alert.show();
    }
       
    
    
    @FXML
    private void backAction(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    void setValue(){
         classText.getItems().addAll("JSS 1","JSS 2","JSS 3", "SSS 1", "SSS 2", "SSS 3");
       
         departmentText.getItems().addAll("Neutral", "Science","Commercial", "Art");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setValue();
    }    
    
}
