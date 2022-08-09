package application;
	
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GUISponsor extends Application {
	Connection connection;
	Parent firstPane;	
	Stage window;
	Scene firstScene;
	Button button = new Button();	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
	        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("/application/SponsorView.fxml"));
	       
	        Parent firstPane = firstPaneLoader.load();
	        Scene firstScene = new Scene(firstPane);
			firstPane.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			
	        primaryStage.setScene(firstScene);
	        primaryStage.show();
	        primaryStage.setOnCloseRequest(e ->{
	        	e.consume();
	        	showConfirmBox("Are you sure you want to close the program?", primaryStage);
	        });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void showConfirmBox(String message, Stage primarystage){
		
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION); 
    	alert.setHeaderText(message);
    	alert.setResizable(false);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	if(!result.isPresent()){
	    	    // alert is exited, no button has been pressed.
    	}
    	else if(result.get() == ButtonType.OK){
    		//oke button is pressed
    		primarystage.close();
    	}
    	else if(result.get() == ButtonType.CANCEL){
    		// cancel button is pressed
    	}
    	
}
	


}
