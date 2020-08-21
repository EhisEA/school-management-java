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
import java.util.Random;
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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author EMMANUEL AYEMERE
 */
public class WriteExamController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Text textA;
    @FXML
    Text textB;
    @FXML
    Text textC;
    @FXML
    Text textD;
    @FXML
    Text textNum;
    @FXML
    Text questionText;
   
     @FXML
    Button nextbtn;
    
      @FXML
    Button prevbtn;
     
    @FXML
    RadioButton radio1;
      
    @FXML
    RadioButton radio2;
    
    @FXML
    RadioButton radio3;
    
    @FXML
    RadioButton radio4;
    
    String subjectId, subjectName, studentName, studentClass, studentReg, studentDepartment;
    
    int score=0;
    
    int number=0;
    
    int totalNumber = 0;
    
    ArrayList<Integer> array = new ArrayList();
    ArrayList<Integer> array1 = new ArrayList();
    
    ArrayList<String> questionArray = new ArrayList();
    ArrayList<String> correctArray = new ArrayList();
    ArrayList<String> option1Array = new ArrayList();
    ArrayList<String> option2Array = new ArrayList();
    ArrayList<String> option3Array = new ArrayList();
    String[] answer;
    String[] mainA= new String[totalNumber];
    //---------------what all options of a paticular question would be sent to before it been shuffle--------
    ArrayList<String> textArray = new ArrayList();
    
    //---------------all text that would be displayed as options----------
    ArrayList<String> text1Array = new ArrayList();
    ArrayList<String> text2Array = new ArrayList();
    ArrayList<String> text3Array = new ArrayList();
    ArrayList<String> text4Array = new ArrayList();
  
    String driver ="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    String url = "jdbc:sqlserver://DESKTOP-IE4NO74;databaseName=cbt";
    String user= "emmy";
    String pass = "emmy";
    
  
    
     public void saved(){
        try {
           
        ResultSet rs, rs2 ;
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "select * from "+ subjectId;
        PreparedStatement pst= con.prepareStatement(sql);
//---it didn't work because you can;t write a statement without the table name        
//pst.setString(1, subjectId);
        rs=pst.executeQuery();
            
            while(rs.next()){
                array1.add(Integer.parseInt(rs.getString("qus_id")));
            }
            pst.close();
        
            for(int i = 0; i<totalNumber; i++){
           Random r = new Random();
            int rr = r.nextInt(array1.size());
            array.add(array1.get(rr));
            array1.remove(rr);
        } 
        
        String sql2 = "select * from "+ subjectId +" where qus_id = ? ";
        PreparedStatement pst2= con.prepareStatement(sql2);
        
         for(int i = 0; i<array.size(); i++){
       pst2.setString(1, array.get(i).toString());
        rs2=pst2.executeQuery();
        while(rs2.next()){
                         //System.out.println(rs2.getString("Question").toString());

        questionArray.add(rs2.getString("Question"));
        correctArray.add(rs2.getString("correct"));
        option1Array.add(rs2.getString("option_a"));
        option2Array.add(rs2.getString("option_b"));
        option3Array.add(rs2.getString("option_c"));
        }
            System.out.println(array.get(i));
        }
           System.out.println(questionArray.size()+"kkk");
         
             for(int i = 1; i<array.size(); i++){
       
        
        
//            System.out.println(questionArray.get(i)+"   ---question");
//            System.out.println(correctArray.get(i)+"   ---correct");
//            System.out.println(option1Array.get(i)+"   ---optiona");
//            System.out.println(option2Array.get(i)+"   ---optionb");
//            System.out.println(option3Array.get(i)+"   ---optionc");
        }
         
        for(int i = 0; i<totalNumber; i++){
            ///-----adding all options to the text array -------
            textArray.add(correctArray.get(i));
            textArray.add(option1Array.get(i));
            textArray.add(option2Array.get(i));
            textArray.add(option3Array.get(i));
            
            //-----shuffling them to their respective options (e.g option A, option B etc.)-----
            Random rand = new Random();
            int randInt = rand.nextInt(textArray.size());
            text1Array.add(textArray.get(randInt));
            textArray.remove(randInt);
            
            randInt = rand.nextInt(textArray.size());
            text2Array.add(textArray.get(randInt));
            textArray.remove(randInt);
            
            randInt = rand.nextInt(textArray.size());
            text3Array.add(textArray.get(randInt));
            textArray.remove(randInt);
            
            randInt = rand.nextInt(textArray.size());
            text4Array.add(textArray.get(randInt));
            textArray.remove(randInt);
           
        }   
           // System.out.println(text1Array.size()+"===");
        for(int i = 0; i<totalNumber; i++){
          //------------------testing to it working perfectly-----well it failed at first trial
          ///----finally debug the error the value of i was not well set at the above for loop
            System.out.println(i);  
          System.out.println(questionArray.get(i)+"   ---question");
          System.out.println(text1Array.get(i)+"   ---optiona");
          System.out.println(text2Array.get(i)+"   ---optionb");
          System.out.println(text3Array.get(i)+"   ---optionc");
          System.out.println(text4Array.get(i)+"   ---optiond");
          System.out.println(correctArray.get(i)+"   ---correct");
        }
        
        condition();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("error"+ex);        }
      setOption();      
     }
     
      public void setOption(){
          textA.setText(text1Array.get(number));
          textB.setText(text2Array.get(number));
          textC.setText(text3Array.get(number));
         textD.setText(text4Array.get(number));
         questionText.setText(questionArray.get(number));
         textNum.setText(Integer.toString(number+1));
         
//         
//          textA.setText("jjjjjjjjjjjjjjjjjjjjj");
//          textB.setText("lllllllllllll");
//          textC.setText("mmmmmmmmmmmmmmmm");
//         textD.setText("kkkkkkkkkkkkkkkkk");
      } 
      
       @FXML
    private void next(ActionEvent event) throws IOException{
        number++;
        setOption();
        condition();
    }
    
    @FXML
    private void prev(ActionEvent event) throws IOException{
        number--;
        setOption();
        condition();
    }
    
    public void condition(){
        
        System.out.println(number);
        
        if(number==totalNumber-1){
           nextbtn.setVisible(false);
        }else if(number==0){
            prevbtn.setVisible(false);
        }else{
            prevbtn.setVisible(true);
            nextbtn.setVisible(true);
        }
        System.out.println("outer");
       radioCondition();
      }
    
    public void radioCondition(){
      String text =  answer[number];
      
      //-------------testing if text is getting the actual value and it is----
      //System.out.println(text);
        System.out.println("innnnnnnn"); 
      radio1.setSelected(false);
       radio2.setSelected(false);
       radio3.setSelected(false);
       radio4.setSelected(false);
        System.out.println("qqqqqqqq");
      if(text==textA.getText()){
         radio1.setSelected(true); 
      }else if(text==textB.getText()){
          radio2.setSelected(true);
      }else if(text==textC.getText()){
          radio3.setSelected(true);
      }else if(text==textD.getText()){
          radio4.setSelected(true);
      }
      
    }
    
      @FXML
    private void radio1Action(ActionEvent event) throws IOException{
       radio2.setSelected(false);
       radio3.setSelected(false);
       radio4.setSelected(false);
       
       answer[number]= textA.getText();
    }
     @FXML
    private void radio2Action(ActionEvent event) throws IOException{
       radio1.setSelected(false);
       radio3.setSelected(false);
       radio4.setSelected(false);
       
       answer[number]= textB.getText();
    }
    
     @FXML
    private void radio3Action(ActionEvent event) throws IOException{
       radio1.setSelected(false);
       radio2.setSelected(false);
       radio4.setSelected(false);
       
       answer[number]= textC.getText();
    }
    
      @FXML
    private void radio4Action(ActionEvent event) throws IOException{
       radio1.setSelected(false);
       radio2.setSelected(false);
       radio3.setSelected(false);
       
       answer[number]= textD.getText();
    }
    
       @FXML
    private void submitAction(ActionEvent event) throws IOException{
       for(int i = 0; i<totalNumber; i++){
         
          //System.out.println(answer[i]+"   ---correct");
          if(answer[i]!= null){
              if( answer[i].equals(correctArray.get(i))){
              System.out.println(answer[i]);
              score++;
                }else{
              System.out.println("incorrect");
              }
            }
        }
           System.out.println("you got:: "+score);
           
             try{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, user, pass);
        String sql = "insert into result values(?,?,?,?,?,?,?)";
        PreparedStatement pst= con.prepareStatement(sql);
        pst.setString(1, studentReg);
        pst.setString(2, studentName);
        pst.setString(3, studentClass);//.getSelectionModel() );
        pst.setString(4, studentDepartment);//departmentText.toString() );
        pst.setString(5, subjectId);
        pst.setString(6, subjectName);//departmentText.toString() );
        pst.setString(7, Integer.toString(score) );
         
        pst.executeUpdate();
        }
        catch(Exception e){
           JFrame j = new JFrame();
           JOptionPane.showMessageDialog(j, e); 
        }
     logout(event);
    }
    
    @FXML
    private void logout(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene sce= new Scene(root);
        Stage  stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(sce);
        stage.show();
    }
    
    
    public void setSubjectName(String subId,String subName,String dept,String cclass,String stuName,String stuReg, int num){
        this.subjectId = subId;
        totalNumber= num;
        studentReg= stuReg ;
        studentName=  stuName;
         studentClass= cclass;
        studentDepartment=dept ;
        this.subjectName= subName ;
       
        answer= new String[num];
        
        
        saved();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
