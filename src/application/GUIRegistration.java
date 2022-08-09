package application;
	
import java.io.IOException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GUIRegistration extends Application {
	
	Parent root;
	Stage window;
	Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
			
			try {
				window = primaryStage;
				//Create stage
				
				root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
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
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
