package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIUpdateParticulars extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		String status = "Admin"; //Change this to access Admin or non Admin mode
		
		if(status != "Admin"){
			Parent root = FXMLLoader.load(getClass().getResource("UpdateParticulars.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Charity Organizer System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} else {
			Parent root = FXMLLoader.load(getClass().getResource("UpdateParticularsA.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Charity Organizer System");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
}