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
public class Exam {
    private final SimpleStringProperty id;
    private final SimpleStringProperty question;
    private final SimpleStringProperty correct;
    private final SimpleStringProperty optionA;
    private final SimpleStringProperty optionB;
    private final SimpleStringProperty optionC;
    
    public Exam(String id,String question,String correctOption,String optionA, String optionB, String optionC){
        this.id= new SimpleStringProperty(id);
        this.question= new SimpleStringProperty(question);
        this.correct= new SimpleStringProperty(correctOption);
        this.optionA= new SimpleStringProperty(optionA);
        this.optionB= new SimpleStringProperty(optionB);
        this.optionC= new SimpleStringProperty(optionC);
    }
    
        public String getId() {
        return id.get();
    }
    
      public String getQuestion() {
        return question.get();
    }

    
    public String getCorrect() {
        return correct.get();
    }

    public String getOptionA() {
        return optionA.get();
    }
    
    public String getOptionB() {
        return optionB.get();
    }
     
    public String getOptionC() {
        return optionC.get();
    }
    
}
