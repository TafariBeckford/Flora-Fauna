/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ochemagbegor
 */
public class Sample {

    public EntityManager getEntityManager() {

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("NEPAPU");
            return emf.createEntityManager();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return null;
    }

    public File getImageFromSource() {
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", "jpeg", "jpg", "tif", "Tiff", "png", "bmp"); //setting up the file filter    
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(imageFilter); //setting the file filer - types of file that must be displayed in the show open dialog
        fileChooser.showOpenDialog(null);

        return fileChooser.getSelectedFile();
    }

    public Image scaleImageToContainer(String imagePath, JLabel photoAreaLabel) {

        ImageIcon picture = new ImageIcon(imagePath);
        Image image = picture.getImage();

        return image.getScaledInstance(photoAreaLabel.getPreferredSize().width, photoAreaLabel.getPreferredSize().height, Image.SCALE_SMOOTH);

    }

    public File uploadPhoto(JLabel photoLabel) {
        File photoFile = null;
        try {

            photoFile = getImageFromSource();

            if (photoFile != null) {
                Image img = scaleImageToContainer(photoFile.getPath(), photoLabel);

                photoLabel.setIcon(new ImageIcon(img));
            }

        } catch (Exception ex) {
        }

        return photoFile;
    }

    public void setImage(String imagePath) {
        try {
            File image = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(image);

            ImageIO.write(bufferedImage, "png", new File(imagePath));

        } catch (IOException ex) {
        }
    }

    /**
     * Convert the photo to a byte array
     * @param imagePath
     * @return 
     */
    public byte[] getImage(String imagePath) {

        byte[] img = null;
        try {
            File image = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(image);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            img = outputStream.toByteArray();

        } catch (IOException ex) {
        }

        return img;
    }

    /**
     * This method encode the photo to a string using BAse64 encoder
     *
     * @param personList
     * @return
     */
//    public static List<Person> convertPersonImage(List<Person> personList) {
//
//        List<Person> perList = new ArrayList();
//
//        for (int i = 0; i < personList.size(); i++) {
//
//            Person per = personList.get(i);
//
//            String photoString = "";
//            if (personList.get(i).getPhoto() != null) {
//                photoString = Base64.getEncoder().encodeToString(personList.get(i).getPhoto());
//                per.setEncodedPhoto(photoString);
//            } else {
//                per.setEncodedPhoto(photoString);
//            }
//            perList.add(per);
//        }
//        return perList;
//    }

}
