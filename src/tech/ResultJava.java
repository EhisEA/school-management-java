/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EMMANUEL AYEMERE
 */
public class ResultJava {
    private final SimpleStringProperty regNo;
    private final SimpleStringProperty cclass;
    private final SimpleStringProperty department;
    private final SimpleStringProperty subCode;
    private final SimpleStringProperty stuName;
    private final SimpleStringProperty subName;
    private final SimpleStringProperty score;
    
     public ResultJava(String regNo,String stuName,String cclass,String department, String subCode , String subName , String score){
        this.regNo= new SimpleStringProperty(regNo);
        this.stuName= new SimpleStringProperty(stuName);
        this.cclass= new SimpleStringProperty(cclass);
        this.department= new SimpleStringProperty(department);
        this.subCode= new SimpleStringProperty(subCode);
        this.subName= new SimpleStringProperty(subName);
        this.score= new SimpleStringProperty(score);
    }
     
     public String getRegNo() {
        return regNo.get();
    }
    
      public String getStuName() {
        return stuName.get();
    }

    public String getCclass() {
        return cclass.get();
    }

    public String getDepartment() {
        return department.get();
    }
    public String getSubCode() {
        return subCode.get();
    }
    
     public String getSubName() {
        return subName.get();
    }
    public String getScore() {
        return score.get();
    }
    
     
}
