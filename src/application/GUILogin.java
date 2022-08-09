package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.Connection.DBController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class GUILogin extends Application {
	Connection conn;
	private DBController database_con = new DBController();
	
	String username = "", password = "";
	ArrayList<String> accountDetails = new ArrayList<String>();
	
	@FXML private TextField txtUsername;
	@FXML private TextField txtPassword;

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Charity Organizer System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public void login(ActionEvent event){
		if(loginValidation() == true){
			alert("Entered");
		} else {
			alert("Invalid Username/Password!");
		}
	}
	
	
	
	private boolean loginValidation(){
		boolean let_in = false;
		try {
			conn = database_con.getConnection();
			String query = "Select * from Account where username = '"+txtUsername.getText()+"' AND password = '"+txtPassword.getText()+"'";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				if (rs.getString(1) != null && rs.getString(2) != null){
					let_in = true;
				}
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return let_in;
	}
	
	public void alert(String input){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText( null );
		alert.setContentText(input);
		alert.showAndWait();
	}
}
