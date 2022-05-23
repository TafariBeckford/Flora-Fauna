/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fauna;



import entities.fauna;

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
public class Ffauna extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Ffauna.fxml"));
        
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
      public void enterfauna( String aa, String bb ,String cc,String dd, String ff ){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         
        fauna j =new fauna();
        
        j.setId(aa);
        j.setGenericname(bb);
        j.setScientificname(cc);
        j.setDescription(dd);
        j.setPicture(ff);
        
        
 try {
            em.getTransaction().begin();
            em.persist(j);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (HeadlessException ex) {
            em.getTransaction().rollback();
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "NOT Saved");
        }
        
        
    }
      
       public void deletefauna (String f){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         TypedQuery<fauna> TQ =  em.createQuery("DELETE FROM fauna n WHERE n.id=:F",fauna.class);
        
         TQ.setParameter("F", f);
             try {
            em.getTransaction().begin();
            int updateCount = TQ.executeUpdate();
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Deleted");
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "NOT Deleted");
        }
    }
    
    public void updatefauna (String a,String f,String s,String d,String p){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         TypedQuery<fauna> TQ =  em.createQuery("UPDATE fauna n SET n.genericname =:A,n.scientificname=:S,n.description=:D,n.picture=:P WHERE n.id=:F",fauna.class);
         TQ.setParameter("A", a);
          TQ.setParameter("S", s);
         TQ.setParameter("P", p);
         TQ.setParameter("D", d);
//       TQ.setParameter("E", e);
         TQ.setParameter("F", f);
             try {
            em.getTransaction().begin();
            int updateCount = TQ.executeUpdate();
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Updated");
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "NOT Updated");
        }
    
    
    }
      
      
      
      
        
    }
      
    

