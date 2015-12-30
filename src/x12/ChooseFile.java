package x12;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFile {
	
	public File getXMLFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xml", "xml");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose XML File");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile;
		}
		
		return null;
	}
	
	public File saveXMLFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("fileName.xml"));
		fileChooser.setDialogTitle("Save XML File");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile;
		}
		
		return null;
	}
	
	public File getEDIFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".edi", "edi");
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose EDI File");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile;
		}
		
		return null;
	}
	
	public File saveEDIFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File("X12.edi"));
		fileChooser.setDialogTitle("Save EDI File");
		fileChooser.setCurrentDirectory(new java.io.File("."));
		int result = fileChooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
