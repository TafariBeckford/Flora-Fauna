/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author TAFARI
 */
@Entity
public class fauna implements Serializable {
    
    
    public fauna(String id,String g,String s,String d,String p){
        this.id= id;
        this.genericname= g;
        this.scientificname=s;
        this.description=d;
        this.picture=p;
    }
    
  public fauna(){
 
 }
    

    @Id
    private String id;
    
private String genericname;
private String scientificname;
private String picture;
private String description;


   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the genericname
     */
    public String getGenericname() {
        return genericname;
    }

    /**
     * @param genericname the genericname to set
     */
    public void setGenericname(String genericname) {
        this.genericname = genericname;
    }

    /**
     * @return the scientificname
     */
    public String getScientificname() {
        return scientificname;
    }

    /**
     * @param scientificname the scientificname to set
     */
    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    /**
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
