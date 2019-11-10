package br.imd;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	private AnchorPane mainScreen;
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("People");
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("vision/Main.fxml"));
			mainScreen = (AnchorPane) loader.load();
			
			Scene scene = new Scene(mainScreen);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
