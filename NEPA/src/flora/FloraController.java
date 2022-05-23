/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flora;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.DBConnector;
import entities.flora;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author TAFARI
 */
public class FloraController implements Initializable {

    @FXML
    private JFXTextField GN;
    @FXML
    private JFXTextField SN;
    @FXML
    private JFXTextField FID;
    @FXML
    private JFXTextField D;
    @FXML
    private JFXTextArea MP;
    @FXML
    private TableColumn<flora,String> TID;
    @FXML
    private TableColumn<flora,String> TGN;
    @FXML
    private TableColumn<flora,String> TSN;
    @FXML
    private TableColumn<flora,String> TD;
    @FXML
    private TableColumn<flora,String> TMP;
    @FXML
    private TableView<flora> TT;
    @FXML
    private ImageView imageview;
    @FXML
    private TableColumn<flora,String> PID;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
         final ObservableList<flora> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From flora");
            while (rs.next()){      
               info.add(new flora(rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                        rs.getString("medicinalproperties"),
                        rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FloraController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Fflora f = new  Fflora();
        String q = FID.getText();
        String g = GN.getText();
        String s = SN.getText();
        String d = D.getText();
        String m = MP.getText();
        
        
        TID.setCellValueFactory(new PropertyValueFactory<flora, String>("id"));
        TGN.setCellValueFactory(new PropertyValueFactory<flora, String>("genericname"));
        TSN.setCellValueFactory(new PropertyValueFactory<flora, String>("scientificname"));
        TD.setCellValueFactory(new PropertyValueFactory<flora, String>("description"));
        TMP.setCellValueFactory(new PropertyValueFactory<flora, String>("medicinalproperties"));
        PID.setCellValueFactory(new PropertyValueFactory<flora, String>("picture"));
        TT.setItems(info);
    }    

    @FXML
    private void INSERT(ActionEvent event) {
         final ObservableList<flora> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From flora");
            while (rs.next()){      
               info.add(new flora(rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                        rs.getString("medicinalproperties"), 
                        rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FloraController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Fflora f = new  Fflora();
        String q = FID.getText();
        String g = GN.getText();
        String s = SN.getText();
        String d = D.getText();
        String m = MP.getText();
        f.enterflora(q, g, s, d, m ,getFilePath());
        
        TID.setCellValueFactory(new PropertyValueFactory<flora, String>("id"));
        TGN.setCellValueFactory(new PropertyValueFactory<flora, String>("genericname"));
        TSN.setCellValueFactory(new PropertyValueFactory<flora, String>("scientificname"));
        TD.setCellValueFactory(new PropertyValueFactory<flora, String>("description"));
        TMP.setCellValueFactory(new PropertyValueFactory<flora, String>("medicinalproperties"));
        PID.setCellValueFactory(new PropertyValueFactory<flora, String>("picture"));
        TT.setItems(info);

    }
       
    

    @FXML
    private void UPDATE(ActionEvent event) {
        Fflora f = new  Fflora();

        String g = GN.getText();
        String s = FID.getText();
        String sn = SN.getText();
        String d = D.getText();
        String m = MP.getText();
        f.updateflora(g, s, sn, d, m, getFilePath());
        
        
    }

    @FXML
    private void DELETE(ActionEvent event) {
        Fflora f = new Fflora();
        String s = FID.getText();
        f.deleteflora(s); 
    }

    
    String FilePath;
    public void setFilePath(String x){
        this.FilePath=x;
    }
    public String getFilePath (){
        return this.FilePath;
    }
    @FXML
    private void UPLOAD(ActionEvent event) throws IOException {
        //Creating an image 
      Fflora f = new Fflora();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter exeFilter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.SVG");
        fileChooser.getExtensionFilters().add(exeFilter);
        File file = fileChooser.showOpenDialog(null);

        //get selcted image
        BufferedImage img = null;

        img = ImageIO.read(file.getAbsoluteFile());
        WritableImage i = SwingFXUtils.toFXImage(img, null);
        imageview.setImage(i);

        String imagePath = file.getAbsolutePath();
        setFilePath(imagePath);
        System.out.println(getFilePath());     
    }

    @FXML
    private void REFRESH(ActionEvent event) {
            final ObservableList<flora> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From flora");
            while (rs.next()){      
               info.add(new flora(rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                        rs.getString("medicinalproperties"),
                        rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FloraController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Fflora f = new  Fflora();
        String q = FID.getText();
        String g = GN.getText();
        String s = SN.getText();
        String d = D.getText();
        String m = MP.getText();
        
        
        TID.setCellValueFactory(new PropertyValueFactory<flora, String>("id"));
        TGN.setCellValueFactory(new PropertyValueFactory<flora, String>("genericname"));
        TSN.setCellValueFactory(new PropertyValueFactory<flora, String>("scientificname"));
        TD.setCellValueFactory(new PropertyValueFactory<flora, String>("description"));
        TMP.setCellValueFactory(new PropertyValueFactory<flora, String>("medicinalproperties"));
        PID.setCellValueFactory(new PropertyValueFactory<flora, String>("picture"));
        TT.setItems(info);
        
    }

    @FXML
    private void VIEW(MouseEvent event) throws IOException {
        Fflora f = new Fflora();
        imageview.imageProperty().set(null);
        flora flo= TT.getSelectionModel().getSelectedItem();
            File file = new File( flo.getPicture());
            BufferedImage image = null;
            image = ImageIO.read(file.getAbsoluteFile());
            WritableImage i = SwingFXUtils.toFXImage(image, null);
            imageview.imageProperty().set(i);
    }
    
}
