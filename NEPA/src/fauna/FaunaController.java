package fauna;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.DBConnector;
import entities.fauna;
import flora.*;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author TAFARI
 */
public class FaunaController implements Initializable {

    @FXML
    private JFXTextField FUGN;
    @FXML
    private JFXTextField FUSN;
    @FXML
    private JFXTextField FUID;
    @FXML
    private JFXTextField FUD;
    @FXML
    private TableColumn<fauna, String> FAID;
    @FXML
    private TableColumn<fauna, String> FAGN;
    @FXML
    private TableColumn<fauna, String> FASN;
    @FXML
    private TableColumn<fauna, String> FAD;
    @FXML
    private TableView<fauna> FATT;
    @FXML
    private ImageView imageview;
    @FXML
    private TableColumn<fauna, String> FID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final ObservableList<fauna> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From fauna");
            while (rs.next()){      
               info.add(new fauna (rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                        rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FaunaController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Ffauna f = new  Ffauna();
        String q = FUID.getText();
        String g = FUGN.getText();
        String s = FUSN.getText();
        String d = FUD.getText();
        
        
        FAID.setCellValueFactory(new PropertyValueFactory<fauna, String>("id"));
        FAGN.setCellValueFactory(new PropertyValueFactory<fauna, String>("genericname"));
        FASN.setCellValueFactory(new PropertyValueFactory<fauna, String>("scientificname"));
        FAD.setCellValueFactory(new PropertyValueFactory<fauna, String>("description"));
        FID.setCellValueFactory(new PropertyValueFactory<fauna, String>("picture"));
        FATT.setItems(info);

    }    

    @FXML
    private void FUINSERT(ActionEvent event) {
        final ObservableList<fauna> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From fauna");
            while (rs.next()){      
               info.add(new fauna (rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                        rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FaunaController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Ffauna f = new  Ffauna();
        String q = FUID.getText();
        String g = FUGN.getText();
        String s = FUSN.getText();
        String d = FUD.getText();
        f.enterfauna(q, g, s, d ,getFilePath());
        
        FAID.setCellValueFactory(new PropertyValueFactory<fauna, String>("id"));
        FAGN.setCellValueFactory(new PropertyValueFactory<fauna, String>("genericname"));
        FASN.setCellValueFactory(new PropertyValueFactory<fauna, String>("scientificname"));
        FAD.setCellValueFactory(new PropertyValueFactory<fauna, String>("description"));
        FID.setCellValueFactory(new PropertyValueFactory<fauna, String>("picture"));
        FATT.setItems(info);

        
    }

    @FXML
    private void FUPDATE(ActionEvent event) {
        Ffauna f = new  Ffauna();

        String g = FUGN.getText();
        String s = FUID.getText();
        String sn = FUSN.getText();
        String d = FUD.getText();
        f.updatefauna(g, s, sn, d, getFilePath());
        
    }

    @FXML
    private void FDELETE(ActionEvent event) {
        
        Ffauna f = new Ffauna();
        String s = FUID.getText();
        f.deletefauna(s);
        
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
        final ObservableList<fauna> info = FXCollections.observableArrayList();
        try {
            DBConnector db = new DBConnector();
            ResultSet rs = db.connection().createStatement().executeQuery(" Select * From fauna");
            while (rs.next()){      
               info.add(new fauna (rs.getString("id"),
                        rs.getString("genericname"),
                        rs.getString("scientificname"),
                        rs.getString("description"),
                       rs.getString("picture")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FaunaController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
        System.out.println("please work");
        Ffauna f = new  Ffauna();
        String q = FUID.getText();
        String g = FUGN.getText();
        String s = FUSN.getText();
        String d = FUD.getText();
        
        
        FAID.setCellValueFactory(new PropertyValueFactory<fauna, String>("id"));
        FAGN.setCellValueFactory(new PropertyValueFactory<fauna, String>("genericname"));
        FASN.setCellValueFactory(new PropertyValueFactory<fauna, String>("scientificname"));
        FAD.setCellValueFactory(new PropertyValueFactory<fauna, String>("description"));
        FID.setCellValueFactory(new PropertyValueFactory<fauna, String>("picture"));
        FATT.setItems(info);
        
    }

    @FXML
    private void VIEW(MouseEvent event) throws IOException {
         Ffauna f = new Ffauna();
        imageview.imageProperty().set(null);
        fauna flo= FATT.getSelectionModel().getSelectedItem();
            File file = new File( flo.getPicture());
            BufferedImage image = null;
            image = ImageIO.read(file.getAbsoluteFile());
            WritableImage i = SwingFXUtils.toFXImage(image, null);
            imageview.imageProperty().set(i);
    
}
}
