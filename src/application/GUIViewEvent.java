package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIViewEvent extends Application {
	Parent root;
	Stage window;
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		try {
			window = primaryStage;
			//Create stage
			
			root = FXMLLoader.load(getClass().getResource("ViewEvent.fxml"));
			//display fxml 
			
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.setTitle("My Title");
			//primaryStage.setScene(show);
			window.setScene(scene);
			
			window.show();
			
		} catch (IOException e) {
			System.out.println("Main ___ MainPage");
			e.printStackTrace();
		}	
	}
}