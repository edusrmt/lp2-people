package br.imd.controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import br.imd.exception.UnicodeFilePathException;
import br.imd.model.Video;
import br.imd.vision.People;

public class EventHandler {
	@FXML
	private Button loadButton;
	
	@FXML
	private TextArea outputTextArea;
	
	@FXML
	private void initialize() {
		loadButton.setOnAction(this::selectVideoFile);
	}
	
	private void selectVideoFile (ActionEvent event) {		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource Video");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Video Files", "*.mp4", "*.avi"));
		
		File selectedFile = fileChooser.showOpenDialog(null);
		
		if(selectedFile != null) {
			String videoPath = selectedFile.getPath().replace("\\", "/");
			
			try {
				Video inputVideo = VideoReader.read(videoPath, 5);
				People.detect(inputVideo, 5, outputTextArea);
			} catch (UnicodeFilePathException e) {
				Alert alert = new Alert(AlertType.ERROR, "Can't open a file with Unicode characters in its path!", ButtonType.CLOSE);
				alert.showAndWait();
			}
		}
	}
}
