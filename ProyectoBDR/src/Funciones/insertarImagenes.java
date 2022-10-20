package Funciones;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class insertarImagenes {
	public static ImageIcon ResizableImage(String path, JLabel imgLabel) {
		ImageIcon myimagen = new ImageIcon(path);
		Image img = myimagen.getImage();
		Image newimg = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimg);
		return image;
	}
	public static String generarRutaImg(String relativa,JLabel imgLabel) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter soloImg = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "jpeg");
		fileChooser.setFileFilter(soloImg);
		fileChooser.showSaveDialog(null);
		relativa=".\\Imagenes\\Fotos\\";
		if (fileChooser.getSelectedFile() != null) {
			File selectedFile = fileChooser.getSelectedFile();
			String path = selectedFile.getAbsolutePath();
			File Rutarelativa=new File(".\\Imagenes\\Fotos\\"+selectedFile.getName());
			
			try {
				Files.copy(Paths.get(selectedFile.getAbsolutePath()), Paths.get(Rutarelativa.getPath()));
			}catch (FileAlreadyExistsException f1) {
				// TODO Auto-generated catch block
				f1.getMessage();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			relativa+=selectedFile.getName();
			imgLabel.setIcon(ResizableImage(relativa, imgLabel));
			return relativa;
		}
		return relativa;
		
	}

}
