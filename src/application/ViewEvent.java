package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import Database.Connection.DBController;
import Entity.CharityEventCombine;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ViewEvent implements Initializable {
	
	
	// Main Content
	Connection conn;
	private DBController database_con = new DBController();
	
	Stage stage = new Stage();
	
	ObservableList<CharityEventCombine> data = FXCollections.observableArrayList();
	
	@FXML private TextField searchBox;
	@FXML private GridPane gridPane;
	
	ArrayList<String> eventNames = new ArrayList<String>();
	public static String passEventName;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	@FXML
	public void search(ActionEvent event) throws SQLException{
		retrieveDb();
	}

	public void retrieveDb(){
		eventNames.clear();
		gridPane.getChildren().clear();
		System.out.println("eventNames size :: " + eventNames.size());
		try {
			conn = database_con.getConnection();
			
			String query = "Select * from Events where eventName LIKE ?";// where eventName LIKE ?
			pst = conn.prepareStatement(query);
			pst.setString(1, "%" + searchBox.getText() + "%");
			rs = pst.executeQuery();
			
			int column = 0, row = 0;
			while(rs.next()){
				eventNames.add(rs.getString("eventName"));
			}
			
			for(int i = 0; i < eventNames.size(); i++){
				if(column < 5){
					Button btnLabel = new Button(eventNames.get(i));
					btnLabel.wrapTextProperty().setValue(true);
					btnLabel.setStyle("-fx-alignment: CENTER; -fx-font-weight: bold;");
					btnLabel.setPrefSize(120, 120);
					btnLabel.setOnAction(new EventHandler<ActionEvent>() {
		                @Override
		                public void handle(ActionEvent event) {
		                	passEventName = btnLabel.getText();
		                	try {
								Parent parentView = FXMLLoader.load(getClass().getResource("ViewEventDesc.fxml"));
								Scene sceneView = new Scene(parentView);
								Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
								window.setScene(sceneView);
								window.show();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                }
		            });
					gridPane.add(btnLabel, column, row);
					column++;
				} else {
					column = 0;
					row += 1;
					i--;
				}
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		retrieveDb();
	}
	
	/*
	 * Sorry Chers who are reading this codes!!
	 * Due to lack of time to integrate, i have to resort to Duplicate Code
	 * You can Ctrl + f and 
	 * key "Main Content" 
	 * and it will link to the Main Implementation
	 * Sorry for the inconvenience!!
	 */
	
	private ParallelTransition parallel;
	@FXML
    private BorderPane bigPanel;
	@FXML
	private Button searchBtn;
	@FXML
	private TextField searchBar;
	@FXML
    private Button homeBtn;
    @FXML
    private Button eventBtn;
    @FXML
    private Button feedbackBtn;
    @FXML
    private Button maintainBtn;
    @FXML
    private ImageView particularBtn;
    @FXML
    private ImageView goToSearch; 

    @FXML
    void goToFeedback(ActionEvent event) throws IOException {
    	Stage stage; 
        Parent root;
          
        stage=(Stage) bigPanel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource(feedbackBtn.getAccessibleText()));
        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToHome(ActionEvent event) throws IOException {
    	Stage stage; 
        Parent root;
          
        stage=(Stage) bigPanel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource(homeBtn.getAccessibleText()));
        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToMaintain(ActionEvent event) throws IOException {
    	Stage stage; 
        Parent root;
          
        stage=(Stage) bigPanel.getScene().getWindow();
        //load up OTHER FXML document
        root = FXMLLoader.load(getClass().getResource(maintainBtn.getAccessibleText()));
        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void popUpEventOption(ActionEvent event) {
    	try {
			PopOver editor = new PopOver();
			
			editor.setContentNode(createEventOption());
			editor.setArrowIndent(0);
			editor.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
			
			editor.show(eventBtn);
		} catch (Exception e) {
			
		}
    }

    @FXML
    void popUpParticularOption(MouseEvent event) {
    	try {
			PopOver editor = new PopOver();
			
			editor.setContentNode(createPopAccount());
			editor.setArrowIndent(0);
			editor.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
			
			editor.show(particularBtn);
		} catch (Exception e) {
			
		}
    }

    @FXML
	void searchBarClicked(MouseEvent event) {
		 
		  TranslateTransition buttonTrans = new TranslateTransition();
		  ScaleTransition btnSearchTrans = new ScaleTransition(Duration.seconds(0.6),searchBtn);
		  buttonTrans.setDuration(Duration.seconds(0.6));
		  buttonTrans.setNode(searchBtn);
		  buttonTrans.setToX(-73.5); 
		  
		  ScaleTransition searchTrans = new ScaleTransition(Duration.seconds(0.6),searchBar);
		  searchTrans.setToX(2);
		  
		//  searchTrans.setToZ(20);
		  
		  parallel = new ParallelTransition(buttonTrans,btnSearchTrans,searchTrans);
		  
		  parallel.play();
	  }
	  
	  @FXML
	    void resizeSearchBar(MouseEvent event) 
	  {
		  TranslateTransition buttonTrans = new TranslateTransition();
		  ScaleTransition btnSearchTrans = new ScaleTransition(Duration.seconds(0.6),searchBtn);
		  buttonTrans.setDuration(Duration.seconds(0.6));
		  buttonTrans.setNode(searchBtn);
		  buttonTrans.setToX(1); 
		  
		  ScaleTransition searchTrans = new ScaleTransition(Duration.seconds(0.6),searchBar);
		  searchTrans.setToX(1);
		  searchTrans.setToY(1);
		  
		  parallel = new ParallelTransition(buttonTrans,btnSearchTrans,searchTrans);
		  parallel.play();
	    }
	  
	  public VBox createEventOption(){
			VBox box = new VBox();
			box.setPrefSize(150, 100);
			box.setMaxSize(150, 100);
			box.setPadding(new Insets(10,10,10,10));
			
			Button viewEvent = new Button("View Event");
			viewEvent.setMinSize(130, 30);
			viewEvent.setMaxSize(130, 30);
			viewEvent.setOnAction(e -> {
				try {
					viewEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			box.getChildren().add(viewEvent);
			
			Button sponsorView  = new Button("View Sponsor");
			sponsorView.setMinSize(130, 30);
			sponsorView.setMaxSize(130, 30);
			sponsorView.setOnAction(e -> {
				try {
					sponsorView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			box.getChildren().add(sponsorView);
			
			Button registerEvent = new Button("Registration");
			registerEvent.setMinSize(130, 30);
			registerEvent.setMaxSize(130, 30);
			registerEvent.setOnAction(e -> {
				try {
					registerEvent();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			box.getChildren().add(registerEvent);

			return box;
		}

		private void registerEvent() throws IOException {
			Stage stage; 
	        Parent root;
	        
	        stage=(Stage) bigPanel.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
	        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
	        stage.setScene(scene);
	        stage.show();
		}

		private void sponsorView() throws IOException {
			Stage stage; 
	        Parent root;
	        
	        stage=(Stage) bigPanel.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("SponsorView.fxml"));
	        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
	        stage.setScene(scene);
	        stage.show();
		}

		private void viewEvent() throws IOException {
			Stage stage; 
	        Parent root;
	      
	        stage=(Stage) bigPanel.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("ViewEvent.fxml"));
	        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
	        stage.setScene(scene);
	        stage.show();
		} 
		
		public VBox createPopAccount(){
			VBox box = new VBox();
			box.setPrefSize(150, 100);
			box.setMaxSize(150, 100);
			//grid.setStyle("-fx-background-color: slateblue;");
			box.setPadding(new Insets(10,10,10,10));
			
			ImageView account = new ImageView(new Image("Image/User.png"));
			account.setFitHeight(67);
			account.setFitWidth(100);
			box.getChildren().add(account);
			//GridPane.setFillHeight(account, true);
			
			Label username = new Label("GDrAnimal");
			username.setMinSize(130, 67);
			username.setMaxSize(130, 67);
			username.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			username.setAlignment(Pos.CENTER);
			username.setTextAlignment(TextAlignment.CENTER);
			box.getChildren().add(username);
			
			Button updateParticulars  = new Button("Change Infomation");
			updateParticulars.setMinSize(130, 30);
			updateParticulars.setMaxSize(130, 30);
			updateParticulars.setOnAction(e -> {
				try {
					updateParticular();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			});
			box.getChildren().add(updateParticulars);
			
			Button logOut  = new Button("Log Out");
			logOut.setMinSize(130, 30);
			logOut.setMaxSize(130, 30);
			logOut.setOnAction(e -> {
				try {
					logOut();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			box.getChildren().add(logOut);
			

			return box;
		}
		
		private void updateParticular() throws IOException {
			Stage stage; 
	        Parent root;
	        
	        stage=(Stage) bigPanel.getScene().getWindow();
	        //load up OTHER FXML document
	        root = FXMLLoader.load(getClass().getResource("UpdateParticulars.fxml"));
	        Scene scene = new Scene(root, bigPanel.getWidth(), bigPanel.getHeight());
	        stage.setScene(scene);
	        stage.show();
		}

		private void logOut() throws IOException {
			Stage stage; 
	        Parent root;
	        
	        stage=(Stage) bigPanel.getScene().getWindow();
	        //load up OTHER FXML document
	        stage.close();
		}
}
