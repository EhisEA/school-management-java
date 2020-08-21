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
public class RemoveSubjectController implements Initializable {
    
    /**
     * Initializes the controller class.
     */
    
    @FXML private TableView<Subject> table;
    @FXML private TableColumn<Subject, String> sub_id;
    @FXML private TableColumn<Subject, String> sub_name;
    @FXML private TableColumn <Subject, String> sub_class;
    @FXML private TableColumn<Subject, String> department;
    @FXML private TableColumn<Subject, String> qusNo;
    
    
    @FXML
    public ComboBox classText;
    @FXML
    public ComboBox departmentText;
    
    ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    public ObservableList<Subject> data = FXCollections.observableArrayList(
           
    );
    
   
    
    
    private void arrangeTable(){
        sub_id.setCellValueFactory(new PropertyValueFactory<Subject, String>("id"));
        sub_name.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        sub_class.setCellValueFactory(new PropertyValueFactory<Subject, String>("cclass"));
        department.setCellValueFactory(new PropertyValueFactory<Subject, String>("department"));
        qusNo.setCellValueFactory(new PropertyValueFactory<Subject, String>("qusNo"));
        table.setItems(data);  
    }
    
    private void addData(){
        
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from subject";
        PreparedStatement pst= con.prepareStatement(sql);
       
         
        rs=pst.executeQuery();
            while(rs.next()){
                data.add(new Subject(rs.getString("sub_id"), rs.getString("name"), rs.getString("class"), rs.getString("department"), rs.getString("question_number")) );
              
            }
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e); 
        }
        
           }
    
    @FXML 
    public void searchAction(ActionEvent event){
         data.clear();
              
// System.out.println("11111");
        if(classText.getValue().toString()!="None" || departmentText.getValue().toString()!="None"  ){
           // System.out.println("222222222222222");
            if(classText.getValue().toString()!="None" && departmentText.getValue().toString()!="None"){
            //    System.out.println("3333333333333333333333");
                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from subject where class = ? and department=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classText.getValue().toString() );
                pst.setString(2, departmentText.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
                    data.add(new Subject(rs.getString("sub_id"), rs.getString("name"), rs.getString("class"), rs.getString("department"), rs.getString("question_number")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(classText.getValue().toString()!="None" ){
                try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from subject where class = ?  ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classText.getValue().toString() );
                
               rs=pst.executeQuery();
                   while(rs.next()){
                        data.add(new Subject(rs.getString("sub_id"), rs.getString("name"), rs.getString("class"), rs.getString("department"), rs.getString("question_number")) );
              
                   }
               }
               catch(Exception e){
                  JFrame j = new JFrame();
                  JOptionPane.showMessageDialog(j, e); 
               }

            }else if(departmentText.getValue().toString()!="None"){
             try{
               ResultSet rs ;
               Class.forName(driver);
               Connection con = DriverManager.getConnection(url, user, pass);
               String sql = "select * from subject where department=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, departmentText.getValue().toString() );
                
               rs=pst.executeQuery();
                   while(rs.next()){
                        data.add(new Subject(rs.getString("sub_id"), rs.getString("name"), rs.getString("class"), rs.getString("department"), rs.getString("question_number")) );
              
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
    public void removeCol(ActionEvent event){
      
        if(table.getSelectionModel()!= null){
        String g;
         g = table.getSelectionModel().getSelectedItem().getId();
         System.out.println(g);
         
            try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "delete from subject where sub_id=?";
        PreparedStatement pst= con.prepareStatement(sql);
        pst.setString(1, g );
        pst.executeUpdate();
       
       String sql2 = "drop table "+g;
       PreparedStatement pst2= con.prepareStatement(sql2);
       pst2.execute();
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e); 
        }
       table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
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
    
    @FXML
    public void updateQuestion(ActionEvent event) throws IOException{
      
        if(table.getSelectionModel()!= null){
        String qusNo, cclass, dept, subjectName,sub_id;
         qusNo = table.getSelectionModel().getSelectedItem().getQusNo();
         cclass = table.getSelectionModel().getSelectedItem().getCclass();
         dept = table.getSelectionModel().getSelectedItem().getDepartment();
         subjectName = table.getSelectionModel().getSelectedItem().getName();
         sub_id = table.getSelectionModel().getSelectedItem().getId();
         
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateSubject.fxml"));
        Parent root = loader.load();
        UpdateSubjectController controller = loader.getController();
        controller.setGlobalVariables(qusNo, cclass, dept, subjectName, sub_id );
      
        
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
         
        }
    }
    
    
       void setValue(){
         classText.getItems().addAll("None","JSS 1","JSS 2","JSS 3", "SSS 1", "SSS 2", "SSS 3");
         classText.getSelectionModel().select(0);
         departmentText.getItems().addAll("None", "Neutral", "Science","Commercial", "Art");
         departmentText.getSelectionModel().select(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setValue();
        addData();
         arrangeTable();
    }    
    
}
