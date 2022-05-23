/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flora;


import login.*;
import entities.admin;
import entities.flora;
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
public class Fflora extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Fflora.fxml"));
        
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
    public void enterflora( String aa, String bb ,String cc,String dd,String ee, String ff ){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         
        flora e =new flora();
        
        e.setId(aa);
        e.setGenericname(bb);
        e.setScientificname(cc);
        e.setDescription(dd);
        e.setMedicinalproperties(ee);
        e.setPicture(ff);
        
        
 try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Saved");
        } catch (HeadlessException ex) {
            em.getTransaction().rollback();
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "NOT Saved");
        }
        
        
    }
    public void deleteflora (String f){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         TypedQuery<flora> TQ =  em.createQuery("DELETE FROM flora n WHERE n.id=:F",flora.class);
        
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
    
    public void updateflora (String a,String f,String s,String d,String m,String p){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
        EntityManager em = emf.createEntityManager();
         TypedQuery<flora> TQ =  em.createQuery("UPDATE flora n SET n.genericname =:A,n.scientificname=:S,n.description=:D,n.medicinalproperties=:M,n.picture=:P WHERE n.id=:F",flora.class);
         TQ.setParameter("A", a);
          TQ.setParameter("S", s);
         TQ.setParameter("M", m);
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

