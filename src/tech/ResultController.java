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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class ResultController implements Initializable {

     ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    @FXML
    private TextField regText;
    @FXML
    private TextField nameText;
    @FXML
    private ComboBox subIdCombo;
    @FXML
    private ComboBox classCombo;
    @FXML
    private ComboBox deptCombo;
    @FXML
    private ComboBox subNameCombo;
    @FXML
    private TableColumn<ResultJava, String> regNo;
    @FXML
    private TableColumn<ResultJava, String> studName;
    @FXML
    private TableColumn<ResultJava, String> cclass;
    @FXML
    private TableColumn<ResultJava, String> department;
    @FXML
    private TableColumn<ResultJava, String> subCode;
    @FXML
    private TableColumn<ResultJava, String> subName;
    @FXML
    private TableColumn<ResultJava, String> score;
    @FXML
    private TableView<ResultJava> table;
    
     public ObservableList<ResultJava> data = FXCollections.observableArrayList(
           
    );
    
     void setValue(){
         classCombo.getItems().addAll("None","JSS 1","JSS 2","JSS 3", "SSS 1", "SSS 2", "SSS 3");
         classCombo.getSelectionModel().select(0);
         deptCombo.getItems().addAll("None", "Neutral", "Science","Commercial", "Art");
         deptCombo.getSelectionModel().select(0);
         subNameCombo.getItems().addAll("None");
         subNameCombo.getSelectionModel().select(0);
         
          try{
        ResultSet rs, rs1 ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select sub_id, name from subject";
        PreparedStatement pst= con.prepareStatement(sql);
         
       rs = pst.executeQuery();
       
       while(rs.next()){
        subIdCombo.getItems().add(rs.getString("sub_id"));
       
       }
         String sql1 = "select  DISTINCT name from subject ";
        PreparedStatement pst1= con.prepareStatement(sql);
         
       rs1 = pst1.executeQuery();
       while(rs1.next()){
        subNameCombo.getItems().add(rs1.getString("name"));
       
       }
          }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e+"ppppppppppppppppp"); 
        }
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setValue();
        addData();
         arrangeTable();
        
    }
    
     private void arrangeTable(){
        regNo.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("regNo"));
        studName.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("stuName"));
        cclass.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("cclass"));
        department.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("department"));
        subCode.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("subCode"));
        subName.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("subName"));
        score.setCellValueFactory(new PropertyValueFactory<ResultJava, String>("score"));
        table.setItems(data);  
    }
    
    private void addData(){
        
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from result";
        PreparedStatement pst= con.prepareStatement(sql);
       
         
        rs=pst.executeQuery();
            while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
            }
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e); 
        }
        
    }
    
    

    @FXML
    private void backAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Teacher.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }

    
    @FXML
    private void searchAction1(ActionEvent event) {
        
          data.clear();
              
// System.out.println("11111");
        if(!regText.getText().equals("")|| !nameText.getText().equals("")  ){
           // System.out.println("222222222222222");
            if(!regText.getText().equals("")&& !nameText.getText().equals("")){
            //    System.out.println("3333333333333333333333");
                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where stu_id = ? and stu_name=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, regText.getText() );
                pst.setString(2, nameText.getText() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(!regText.getText().equals("") ){
                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where stu_id = ?  ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, regText.getText() );
                
               rs=pst.executeQuery();
                   while(rs.next()){
   data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(!nameText.getText().equals("")){
             try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where stu_name=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, nameText.getText());
                
               rs=pst.executeQuery();
                   while(rs.next()){
   data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }
   
            }
        }else{
            addData();
        }
        
         arrangeTable();
        
    }

    @FXML
    private void searchAction2(ActionEvent event) {
        
        data.clear();
     
            if(subIdCombo.getValue().toString()!="None"){
                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where sub_id = ?  ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, subIdCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            
            }else{
            addData();
        }
        
         arrangeTable();
        
    }

    @FXML
    private void searchAction3(ActionEvent event) {
        
        data.clear();
              
        if(classCombo.getValue().toString()!="None" || deptCombo.getValue().toString()!="None"  || subNameCombo.getValue().toString()!="None" ){

            if(classCombo.getValue().toString()!="None" && deptCombo.getValue().toString()!="None" && subNameCombo.getValue().toString()!="None"){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where class = ? and department=? and sub_name =? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classCombo.getValue().toString() );
                pst.setString(2, deptCombo.getValue().toString() );
                pst.setString(3, subNameCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(classCombo.getValue().toString()!="None" && subNameCombo.getValue().toString()!="None"){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where class = ? and sub_name =? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classCombo.getValue().toString() );
                pst.setString(2, subNameCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(classCombo.getValue().toString()!="None" && deptCombo.getValue().toString()!="None"){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where class = ? and department=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classCombo.getValue().toString() );
                pst.setString(2, deptCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if( deptCombo.getValue().toString()!="None" && subNameCombo.getValue().toString()!="None"){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where department=? and sub_name =? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, deptCombo.getValue().toString() );
                pst.setString(2, subNameCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(classCombo.getValue().toString()!="None" ){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where class = ? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if( deptCombo.getValue().toString()!="None" ){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where department=?  ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, deptCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if( subNameCombo.getValue().toString()!="None"){

                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from result where sub_name =? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, subNameCombo.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
            data.add(new ResultJava(rs.getString("stu_id"), rs.getString("stu_name"), rs.getString("class"), rs.getString("department"), rs.getString("sub_id"), rs.getString("sub_name"), rs.getString("score")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }
            
        }else{
            addData();
        }
        
         arrangeTable();
        
    }
    
}
