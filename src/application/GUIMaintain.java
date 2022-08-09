package application;

import java.io.IOException;
import java.sql.Connection;

import javax.print.DocFlavor.URL;

// import Database.Connection.DBController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class GUIMaintain extends Application {
//	Connection connection;
	Parent root;
	Stage window;
	Scene scene,scene2;
	Button button = new Button("Home");

	@Override
	public void start(Stage primaryStage) {
		
			try {
				window = primaryStage;
				//Create stage
				
				root = FXMLLoader.load(getClass().getResource("MaintainEvents.fxml"));
				//display fxml 
				
				scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				window.setTitle("Provide Feedback");
				//primaryStage.setScene(show);
				window.setScene(scene);
				
				window.show();
				
			} catch (IOException e) {
				System.out.println("ProvideFeedback ___ ProvideFeedbackPage");
				e.printStackTrace();
			}	
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}