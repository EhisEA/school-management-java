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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class SetExamQuestionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
     TextArea question;
    @FXML
     TextArea correctOption;
    @FXML
     TextArea optionA;
    @FXML
     TextArea optionB;
    @FXML
     TextArea optionC;
    
     @FXML
     Button sub;
    
    @FXML
    public ComboBox classText;
    @FXML
    public ComboBox departmentText;
     @FXML
    public ComboBox idText;
     @FXML
    public ComboBox nameText;
    
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    @FXML
    private void submitQuestion(ActionEvent event){
       addQuestion( idText.getValue().toString());  
//        try{
//        ResultSet rs ;
//        Class.forName(driver);
//        Connection con = DriverManager.getConnection(url, user, pass);
//        String sql = "select * from subject where sub_id = 'phy201' ";// department=? and class=? and name=?";
//            System.out.println("one");
//        PreparedStatement pst= con.prepareStatement(sql);
//        //pst.setString(1, idText.getValue().toString());
//            System.out.println("two");
////        pst.setString(2, departmentText.getValue().toString());
////        pst.setString(3, nameText.getValue().toString());
//            System.out.println("three");
//        rs=pst.executeQuery();
//            System.out.println("four");
//        String output = rs.getString("sub_id");
//            System.out.println("kkkkkkkkkkkkkllll");
//            System.out.println("jjjjjjjjjjjj"+output);
//        if(output == null){// if you fetched null value then initialize output with blank string
//  output= "bbbbbbbbbbbbbbb";
//}
//            System.out.println("oo"+output);
//            rs.next();rs.next();
//            
//            if("".equals(rs.getString(1))){
//                System.out.println("nullllll");
//            }else{
//                System.out.println("got ya!");
//            }
//            
//        while(rs.next()){
//           String g= rs.getString(1);
//            System.out.println(g);
//           addQuestion(rs.getString("sub_id"));
//       
//       
//            
//        }
//         }
//        catch(Exception e){
//           JFrame j = new JFrame();
//           JOptionPane.showMessageDialog(j, e+"kkkkkkkkkkkkk"); 
//        }
    }
    
     @FXML
    private void addQuestion(String name) {

        try{
            System.out.println("name"+name);
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "insert into "+name +" values(?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(sql);
        pst.setString(1, question.getText() );
        pst.setString(2, correctOption.getText() );
        pst.setString(3, optionA.getText() );
        pst.setString(4, optionB.getText() );
        pst.setString(5, optionC.getText() );
       
        pst.executeUpdate();
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e+"llllllllllllllllllll"); 
        }
        
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

         
             try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select sub_id, name from subject";
        PreparedStatement pst= con.prepareStatement(sql);
         
       rs = pst.executeQuery();
       
       while(rs.next()){
        idText.getItems().add(rs.getString("sub_id"));
       
       }
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e+"ppppppppppppppppp"); 
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setValue();
    }    
    
    
    @FXML
     private void click(){
//         sub.setOpacity(0.5);
     }
    
}
