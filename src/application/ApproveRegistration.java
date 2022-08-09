package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import Database.Connection.DBController;
import Entity.RegistrationDetail;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ApproveRegistration implements Initializable {
	@FXML
    private Label lblNRIC;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblDob;

    @FXML
    private Label lblReligion;

    @FXML
    private Label lblHomeAddress;

    @FXML
    private Label lblContactNumber;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNationality;

    @FXML
    private Label lblCitizenship;

    @FXML
    private Label lblEventRegistered;

    @FXML
    private Label lblEventDate;

    @FXML
    private Button btnApproveRegistration;

    @FXML
    private Button btnRejectRegistration;

    @FXML
    private Label lblDisplayName;

    @FXML
    private Label lblDisplayNric;

    @FXML
    private Label lblDisplayAge;

    @FXML
    private Label lblDisplayGender;

    @FXML
    private Label lblDisplayDob;

    @FXML
    private Label lblDisplayReligion;

    @FXML
    private Label lblDisplayLanguage;

    @FXML
    private Label lblDisplayHomeAddress;

    @FXML
    private Label lblDisplayContactNumber;

    @FXML
    private Label lblDisplayNationality;

    @FXML
    private Label lblDisplayCitizenship;

    @FXML
    private Label lblDisplayEventRegistered;

    @FXML
    private Label lblDisplayEventDate;
    
    @FXML
    private Label consoleMsg;

    @FXML
    private ComboBox<String> comboBxStatus;
	
	@FXML private TableView<RegistrationDetail> table;
	@FXML private TableColumn<RegistrationDetail, String> name;
	@FXML private TableColumn<RegistrationDetail, String> eventRegistered;
	@FXML private TableColumn<RegistrationDetail, String> eventDate;
	@FXML private TableColumn<RegistrationDetail, String> timeSlot;
	
	DBController db = new DBController();
	PreparedStatement pst;
	Connection connection;
	ResultSet rs;
	
	//TableView Test List
	public ObservableList<RegistrationDetail> list = FXCollections.observableArrayList();
	
	//Table Selection
	public void handle(MouseEvent event){
		
	}
	
	public void comboBxSelects(ActionEvent event){	
		//Declare variables
		ResultSet rs = null;
		String dbQuery;
		consoleMsg.setText("Displaying " + comboBxStatus.getValue() +" List");
		if(comboBxStatus.getValue() == "Pending"){
			list.clear();
			//Get connection to database
			db.getConnection();	
			//set the query
			dbQuery = "SELECT * FROM Person WHERE registrationStatus IS NULL";
			//get the results from database
			rs = db.readRequest(dbQuery);
			try {
				while(rs.next()){ list.add(new RegistrationDetail(
						rs.getString("personName"),
						rs.getString("eventRegistered"),
						rs.getString("eventDate"),
						rs.getString("eventTime")
						));
					table.setItems(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			db.terminate();
			System.out.println("Displaying Pending Registrations");
		}else if(comboBxStatus.getValue() == "Approved"){
			list.clear();
			db.getConnection();
			dbQuery = "SELECT * FROM Person WHERE registrationStatus = 1";
			//get the results from database
			rs = db.readRequest(dbQuery);
			try {
				while(rs.next()){ list.add(new RegistrationDetail(
						rs.getString("personName"),
						rs.getString("eventRegistered"),
						rs.getString("eventDate"),
						rs.getString("eventTime")
						));
					table.setItems(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			db.terminate();
			System.out.println("Displaying Approved Registrations");
			System.out.println("Displaying Approved Registrations");
		}else{
			list.clear();
			db.getConnection();
			dbQuery = "SELECT * FROM Person WHERE registrationStatus = 0";
			//get the results from database
			rs = db.readRequest(dbQuery);
			try {
				while(rs.next()){ list.add(new RegistrationDetail(
						rs.getString("personName"),
						rs.getString("eventRegistered"),
						rs.getString("eventDate"),
						rs.getString("eventTime")
						));
					table.setItems(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			db.terminate();
			System.out.println("Displaying Rejected Registrations");
		}
	}
	
	//Buttons and Selections
	public void approveRegistration(ActionEvent event){
		System.out.println("approveRegistration ::: ");
//		
		list.clear();
		db.getConnection();
		
		String dbQuery = "UPDATE Person SET registrationStatus = 1 WHERE nric = ?";
		try {
			pst = connection.prepareStatement(dbQuery);
			pst.setString(1, "Low");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rs = db.readRequest(dbQuery);
		try {
			while(rs.next()){ list.add(new RegistrationDetail(
					rs.getString("personName"),
					rs.getString("eventRegistered"),
					rs.getString("eventDate"),
					rs.getString("eventTime")
					));
				table.setItems(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.terminate();
		System.out.println("Registration Approved!");
		consoleMsg.setText("Registration Approved!");
	}
	
	public void rejectRegistration(ActionEvent event){
		list.clear();
		System.out.println("Registration Rejected!");
		consoleMsg.setText("Registration Rejected!");
	}
	
	
	//DB Connection
	public RegistrationDetail entity = new RegistrationDetail();
	
	
	//Status Combo Box List
	ObservableList<String> statusList = FXCollections.observableArrayList("Pending","Approved","Rejected");
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Connection Check
		
		
		//Combo Box Set Items
		comboBxStatus.setItems(statusList);
		
		//TableView Items
		name.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String> ("name"));
		System.out.println("Comlumn table :: 1 ");
		eventRegistered.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String> ("eventRegistered"));
		eventDate.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String> ("eventDate"));
		timeSlot.setCellValueFactory(new PropertyValueFactory<RegistrationDetail, String> ("timeSlot"));
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
    void goToFeedback(ActionEvent event){
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

