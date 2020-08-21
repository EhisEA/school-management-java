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
public class User {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty cclass;
    private final SimpleStringProperty department;
    
    public User(String id,String name,String cclass,String department){
        this.id= new SimpleStringProperty(id);
        this.name= new SimpleStringProperty(name);
        this.cclass= new SimpleStringProperty(cclass);
        this.department= new SimpleStringProperty(department);
    }

    public String getId() {
        return id.get();
    }
    
      public String getName() {
        return name.get();
    }

    public String getCclass() {
        return cclass.get();
    }

    public String getDepartment() {
        return department.get();
    }
    
    
}
