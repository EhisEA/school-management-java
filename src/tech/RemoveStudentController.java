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
public class RemoveStudentController implements Initializable {

   
    @FXML private TableView<User> table;
    @FXML private TableColumn<User, String> stu_id;
    @FXML private TableColumn<User, String> stu_name;
    @FXML private TableColumn <User, String> stu_class;
    @FXML private TableColumn<User, String> department;
    
    
    @FXML
    public ComboBox classText;
    @FXML
    public ComboBox departmentText;
    
    ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    public ObservableList<User> data = FXCollections.observableArrayList(
           
    );
    
   
    
    
    private void arrangeTable(){
        stu_id.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        stu_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        stu_class.setCellValueFactory(new PropertyValueFactory<User, String>("cclass"));
        department.setCellValueFactory(new PropertyValueFactory<User, String>("department"));
        table.setItems(data);  
    }
    
    private void addData(){
        
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from student";
        PreparedStatement pst= con.prepareStatement(sql);
       
         
        rs=pst.executeQuery();
            while(rs.next()){
                data.add(new User(rs.getString("stu_id"), rs.getString("name"), rs.getString("class"), rs.getString("department")));
              
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
               String sql = "select * from student where class = ? and department=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classText.getValue().toString() );
                pst.setString(2, departmentText.getValue().toString() );
                
               rs=pst.executeQuery();
             
                   while(rs.next()){
                       data.add(new User(rs.getString("stu_id"), rs.getString("name"), rs.getString("class"), rs.getString("department")));

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
               String sql = "select * from student where class = ?  ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, classText.getValue().toString() );
                
               rs=pst.executeQuery();
                   while(rs.next()){
                       data.add(new User(rs.getString("stu_id"), rs.getString("name"), rs.getString("class"), rs.getString("department")));

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
               String sql = "select * from student where department=? ";
               PreparedStatement pst= con.prepareStatement(sql);
                pst.setString(1, departmentText.getValue().toString() );
                
               rs=pst.executeQuery();
                   while(rs.next()){
                       data.add(new User(rs.getString("stu_id"), rs.getString("name"), rs.getString("class"), rs.getString("department")));

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
        String sql = "delete from student where stu_id=?";
        PreparedStatement pst= con.prepareStatement(sql);
       pst.setString(1, g );
                System.out.println("pppppppp");
       pst.executeUpdate();
                System.out.println("mmmmmmmmmmmmm");
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

    @FXML
    private void pli(ActionEvent event) {
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
       
        for(int i=0; i<6; i++){
         String class1[]={/*"'SSS 3'",*/"'SSS 2'","'SSS 1'","'JSS 3'","'JSS 2'","'JSS 1'"};
         String class2[]={/*"'Graduated'",*/"'SSS 3'","'SSS 2'","'SSS 1'","'JSS 3'","'JSS 2'"};
         
         String sql = "UPDATE student SET class = "+class2[i]+"WHERE class = "+class1[i];
            PreparedStatement pst= con.prepareStatement(sql);
           pst.executeUpdate();
            System.out.println(i);
         }    
                JOptionPane.showMessageDialog(null, "done");
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e);
            System.out.println(e);
        }
    }
    
}
