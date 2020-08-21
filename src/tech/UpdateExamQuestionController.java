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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class UpdateExamQuestionController implements Initializable {
    @FXML
    TextArea questionArea, correctOptionArea, optionAArea, optionBArea, optionCArea;
    
    
    ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    @FXML
    Text idText;
    
     String id, question, correctOption, optionA, optionB, optionC, subjectId;
    
     
   void setGlobalVariables(String id,String question,String correctOption,String optionA,String optionB,String optionC, String subjectId){
    this.id = id;
    this.question = question;
    this.correctOption= correctOption; 
    this.optionA = optionA;
    this.optionB= optionB;
    this.optionC= optionC;
    this.subjectId =subjectId;
    setText();
   }
    
   void setText(){
        idText.setText(id);
        questionArea.setText(question);
        correctOptionArea.setText(correctOption);
        optionAArea.setText(optionA);
        optionBArea.setText(optionB);
        optionCArea.setText(optionC);
   }
   
   @FXML
    public void updateAction(ActionEvent event) throws IOException{
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "update "+subjectId+
                    " set Question= ?," +
                    "  correct= ?," +
                    "  option_a= ?," +
                    "  option_b= ?," +
                    "  option_c= ?" +
                    " where qus_id= ?";
        PreparedStatement pst= con.prepareStatement(sql);
       pst.setString(1, questionArea.getText());
       pst.setString(2, correctOptionArea.getText());
       pst.setString(3, optionAArea.getText());
       pst.setString(4, optionBArea.getText());
       pst.setString(5, optionCArea.getText());
       pst.setString(6, id);
         
        pst.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Done!");
        
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("ViewExamQuestion.fxml"));
        Parent root = loader.load();
        ViewExamQuestionController controller = loader.getController();
        controller.sub_id= subjectId;
        controller.addData();
      
        
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
         }catch(Exception e){
           JOptionPane.showMessageDialog(null, e); 
        }
        
    }
      
   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
