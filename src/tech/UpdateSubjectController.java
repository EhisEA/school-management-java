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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class UpdateSubjectController implements Initializable {

     String  qusNo, cclass, dept, subjectName, subjectId;
    @FXML
    TextField subNameText, qusNoText;
    
    @FXML
    Text idText;
    
    @FXML
    ComboBox cclassCombo, deptCombo;
    
    ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    
    
   void setGlobalVariables(String qusNo,String cclass,String dept,String subjectName, String subjectId){
    this.qusNo = qusNo;
    this.cclass = cclass;
    this.dept= dept; 
    this.subjectName = subjectName;
    this.subjectId =subjectId;
    setText();
   }
   
     void setText(){
        idText.setText(subjectId);
        qusNoText.setText(qusNo);
        subNameText.setText(subjectName);
        cclassCombo.getSelectionModel().select(cclass);//.selectionModelProperty().setValue(cclass);
        deptCombo.getSelectionModel().select(dept);//.selectionModelProperty().setValue(dept);
   }
   
     
   @FXML
    public void updateAction(ActionEvent event) throws IOException{
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "update subject "+
                    "set name = ?," +
                    " class= ?," +
                    " department= ?," +
                    " question_number= ?" +
                    " where sub_id = ?";
        
        PreparedStatement pst= con.prepareStatement(sql);
        
        
           String dept= deptCombo.getSelectionModel().selectedItemProperty().getValue().toString();
           String classs= cclassCombo.getSelectionModel().selectedItemProperty().getValue().toString();
        
       pst.setString(1, subNameText.getText());
       pst.setString(2, classs);
       pst.setString(3, dept);
       pst.setString(4, qusNoText.getText());
       pst.setString(5, idText.getText());
         
        pst.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Done!");
        
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("RemoveSubject.fxml"));
        Parent root = loader.load();
        RemoveSubjectController controller = loader.getController();
       // controller.addData();
      
        
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
         }catch(Exception e){
           JOptionPane.showMessageDialog(null, e); 
        }
        
    }
   
    void setValue(){
         cclassCombo.getItems().addAll("JSS 1","JSS 2","JSS 3", "SSS 1", "SSS 2", "SSS 3");
         deptCombo.getItems().addAll("Neutral", "Science","Commercial", "Art");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setValue();
    }    
    
}
