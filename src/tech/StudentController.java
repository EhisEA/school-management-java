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
import java.util.ArrayList;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
   

public class StudentController implements Initializable {
 
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    
    @FXML
     public ComboBox classCombo;
      
    ArrayList<Integer> array1 = new ArrayList();
    
    @FXML
     public ComboBox departmentCombo;
      
      @FXML
     public ComboBox subjectCombo;
    /**
     * Initializes the controller class.
     */
      String studentName;
      String studentID;
      String studentClass;
      String sub_name;
      String studentDepartment;
      String sub_id;
      public void setInfo(String name, String id, String dept, String sClass){
          //------passing values from login controller into global variable so it  
                    //could be use all around the controller 
          
                    
          studentName= name;
          studentID = id;
          studentDepartment= dept; 
          studentClass=sClass;
          
          //----confirming if this method is passing the values correctly
                ///===System.out.println(studentName+"   "+studentID+"   "+studentDepartment+"  "+studentClass);
      }
     
    
     
       @FXML
    private void logout(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
      @FXML
    private void startExam(ActionEvent event) throws IOException {
        
        if(!departmentCombo.getSelectionModel().isEmpty() || !classCombo.getSelectionModel().isEmpty() || 
           !subjectCombo.getSelectionModel().isEmpty()){
            
//---assigning the combo boxes selected values to a variable----
        
           String dept= departmentCombo.getSelectionModel().selectedItemProperty().getValue().toString();
           String classs= classCombo.getSelectionModel().selectedItemProperty().getValue().toString();
            sub_id = subjectCombo.getSelectionModel().selectedItemProperty().getValue().toString();;
       
            
            if(studentClass.equals(classs)&& studentDepartment.equals(dept)){
                   try {
        ResultSet rs1 ;
        Class.forName(driver);
        Connection con1 = DriverManager.getConnection(url, user, pass);
        String sql1 = "select * from result where sub_id=? and stu_id=?";
        PreparedStatement pst1;
            
        pst1 = con1.prepareStatement(sql1);
        pst1.setString(1, sub_id);
        pst1.setString(2, studentID);
        
       
         //-----checking if the values match with any esiting values in the database
        rs1=pst1.executeQuery();
        
                if( !rs1.next()){

                if(studentClass.equals(classs)&& studentDepartment.equals(dept)){
                       try {
            ResultSet rs ;
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from subject where sub_id=? and class=?";
            PreparedStatement pst;

            pst = con.prepareStatement(sql);
            pst.setString(1, sub_id);
            pst.setString(2, classs);


             //-----checking if the values match with any esiting values in the database
            rs=pst.executeQuery();

                if( rs.next()){
                    System.out.println("in");
                    System.out.println(sub_id);
                    int i = Integer.parseInt(rs.getString("question_number"));

                    sub_name=rs.getString("name");
                    callExamPage(event,i);

                }else{
                    alert("WRONG INFORMATION:\n this subject was not design for your class!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

                }else{
                    alert("you must make sure the class and department selected is the same as the one you were registered with");

                }
         }else{
                alert("You have written this exam already");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
           alert("you must select a value from all the drop down box");
            
            
        }
        
    }
    }
    
    public void alert(String msg){
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error::::");
            //alert.setHeaderText("Header");
            alert.setContentText(msg);
            alert.show();
    }
    
    public void callExamPage(ActionEvent event, int num) throws IOException{
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("WriteExam.fxml"));
        Parent root = loader.load();
        WriteExamController controller = loader.getController();
        controller.setSubjectName(sub_id,sub_name,studentDepartment,studentClass,studentName,studentID, num);
       // controller.saved();
       
        
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
     
     void setValue(){
         classCombo.getItems().addAll("JSS 1","JSS 2","JSS 3", "SSS 1", "SSS 2", "SSS 3");
         
         
         departmentCombo.getItems().addAll("Neutral ", "Science","Commercial", "Art");
         
           try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select sub_id, name from subject";
        PreparedStatement pst= con.prepareStatement(sql);
         
       rs = pst.executeQuery();
       
       while(rs.next()){
        subjectCombo.getItems().add(rs.getString("sub_id"));
       
       }
     }   catch (ClassNotFoundException ex) {
             Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
    
     
     
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              
        //-------this is setting all the values for the combo box(department, class, subject Id) 
                setValue();
    }    
    
}
