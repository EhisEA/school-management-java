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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class ViewExamQuestionController implements Initializable {
    
    @FXML private TableView<Exam> table;
    @FXML private TableColumn<Exam, String> id;
    @FXML private TableColumn<Exam, String> question;
    @FXML private TableColumn <Exam, String> correct;
    @FXML private TableColumn<Exam, String> optionA;
    @FXML private TableColumn<Exam, String> optionB;
    @FXML private TableColumn<Exam, String> optionC;
    
     public ObservableList<Exam> data = FXCollections.observableArrayList(
           
    );
    
    @FXML
    public ComboBox sub_idCombo;
    
    String sub_id;
    
    ///////////////////////////---------------SQL
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
    
    
    private void arrangeTable(){
        id.setCellValueFactory(new PropertyValueFactory<Exam, String>("id"));
        question.setCellValueFactory(new PropertyValueFactory<Exam, String>("question"));
        correct.setCellValueFactory(new PropertyValueFactory<Exam, String>("correct"));
        optionA.setCellValueFactory(new PropertyValueFactory<Exam, String>("optionA"));
        optionB.setCellValueFactory(new PropertyValueFactory<Exam, String>("optionB"));
        optionC.setCellValueFactory(new PropertyValueFactory<Exam, String>("optionC"));
        table.setItems(data);  
    }
    
    void addData(){
        
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from "+sub_id;
        PreparedStatement pst= con.prepareStatement(sql);
       
         
        rs=pst.executeQuery();
            while(rs.next()){
                data.add(new Exam(rs.getString("qus_id"), rs.getString("Question"), rs.getString("correct"), rs.getString("option_a"),rs.getString("option_b"),rs.getString("option_c")));
              
            }
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e); 
        }
         arrangeTable();
           }
    
     @FXML 
    public void searchAction(ActionEvent event){
        
        if(!sub_idCombo.getSelectionModel().isEmpty() ){
            data.clear();
            sub_id= sub_idCombo.getSelectionModel().selectedItemProperty().getValue().toString();
            
            addData();
            }else{
            JOptionPane.showMessageDialog(null, "You must select a subject id"); 
        }
        
         arrangeTable();
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
    public void deleteQuestion(ActionEvent event){
      
        if(table.getSelectionModel()!= null){
        String id;
         id = table.getSelectionModel().getSelectedItem().getId();
         
            try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "delete from "+sub_id +" where  qus_id=?";
        PreparedStatement pst= con.prepareStatement(sql);
       pst.setString(1, id );
       pst.executeUpdate();
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e); 
        }
       table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
        }
    }
    
     @FXML
    public void updateQuestion(ActionEvent event) throws IOException{
      
        if(table.getSelectionModel()!= null){
        String id, question, correctOption, optionA, optionB, optionC;
         id = table.getSelectionModel().getSelectedItem().getId();
         question = table.getSelectionModel().getSelectedItem().getQuestion();
         correctOption = table.getSelectionModel().getSelectedItem().getCorrect();
         optionA = table.getSelectionModel().getSelectedItem().getOptionA();
         optionB = table.getSelectionModel().getSelectedItem().getOptionB();
         optionC = table.getSelectionModel().getSelectedItem().getOptionC();
         
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateExamQuestion.fxml"));
        Parent root = loader.load();
        UpdateExamQuestionController controller = loader.getController();
        controller.setGlobalVariables(id, question, correctOption, optionA, optionB, optionC, sub_id );
      
        
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
         
        }
    }
    
    
    void fillSubIdCombo(){
         try{
        ResultSet rs ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select sub_id, name from subject";
        PreparedStatement pst= con.prepareStatement(sql);
         
       rs = pst.executeQuery();
       
       while(rs.next()){
        sub_idCombo.getItems().add(rs.getString("sub_id"));
       
       }
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e+"ppppppppppppppppp"); 
        }
    }
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillSubIdCombo();
    }    
    
}
