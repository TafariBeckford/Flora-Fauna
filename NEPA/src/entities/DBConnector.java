/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author TAFARI
 */
public class DBConnector {
     public Connection connection(){
    try {
                String url = "jdbc:mysql://localhost:3306/nepa";
                String root = "root";
                String password = "";
        
           
            Connection conn = DriverManager.getConnection(url, root, password);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
