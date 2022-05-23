/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.jfoenix.controls.JFXTextField;
import entities.admin;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TAFARI
 */
public class LoginController implements Initializable {

   
    @FXML
    private JFXTextField FirstName;
    @FXML
    private JFXTextField logPassword;
    @FXML
    private JFXTextField LastName;
    @FXML
    private JFXTextField Password;
    @FXML
    private JFXTextField LogEmail;
    @FXML
    private JFXTextField RegEmail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RegSubmit(ActionEvent event) {
        String fn=FirstName.getText();
        String ln=LastName.getText();
        String Pword=Password.getText();
        String mail=RegEmail.getText();
        login l=new login();
        l.register(fn,ln,Pword,mail);
      
    }

    @FXML
    private void LogSubmit(ActionEvent event) {
          login f = new login();
         
        List<admin> pList = f.login();
         ListIterator<admin> itr = pList.listIterator();
         while (itr.hasNext()) {
             admin next = itr.next();
             if(next.getEmail().equalsIgnoreCase(LogEmail.getText()) && next.getPassword().equals(logPassword.getText())){
                  JOptionPane.showMessageDialog(null, "Login Sucessful!");
                  FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/flora/Fflora.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.showAndWait();;   
                 break;
             }else{
                 JOptionPane.showMessageDialog(null, "Login Unsucessful!");
                 System.out.println("Login error!!");
                 break;
             }
             
         }
          
          
          
          
         }
        
    }
    

