/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import entities.admin;
import java.awt.HeadlessException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author TAFARI
 */
public class login extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void register(String a, String b , String c ,String d){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
        
     admin u=new admin();   
     u.setFirstName(a);
     u.setLastName(b);
     u.setPassword(c);
     u.setEmail(d);
        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (HeadlessException ex) {
            em.getTransaction().rollback();
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "NOT Saved");
        }
        
        
    }
    
    public List<admin> login(){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
          EntityManager em =  em = emf.createEntityManager();
          try{
             
              TypedQuery<admin> TQ = em.createQuery("SELECT e FROM admin e",admin.class);
              return TQ.getResultList();
           
          }catch(Exception ex){
              System.out.println(ex);
          }
        return null;
    
       
}
}


