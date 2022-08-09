package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import Controller.FeedbackModel;
import Database.Connection.DBController;
import Database.Connection.DBFeedback;
import Database.Connection.DBQuestion;
import Entity.Feedback;
import Entity.Question;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FeedbackController implements Initializable {
	// Main Content
	private int feedbackId;
	private DBFeedback feedbackRetrieve = new DBFeedback();
	private DBQuestion questionRetrieve = new DBQuestion();
	private FeedbackModel feedModel = new FeedbackModel();

	private String q1Answer;
	private String q2Answer;
	private String q3Answer;
	private String q4Answer;
	private String q5Answer;
	private String q6Answer;
	private String q7Answer;
	
    @FXML
    private ScrollPane mainScroll;

    @FXML
    private Label titleText;
    
    @FXML
    private HBox A;

    @FXML
    private Label q1;

    @FXML
    private Label q2;

    @FXML
    private Label q3;

    @FXML
    private Label q4;

    @FXML
    private Label q5;

    @FXML
    private Label q6;

    @FXML
    private Label q7;

    @FXML
    private Label q1text;

    @FXML
    private Label q2text;

    @FXML
    private Label q3text;

    @FXML
    private Label q4text;

    @FXML
    private Label q5text;

    @FXML
    private Label q6text;

    @FXML
    private Label q7text;

    @FXML
    private RadioButton radioBtnQ1SA;

    @FXML
    private ToggleGroup Q1Button;

    @FXML
    private RadioButton radioBtnQ1A;

    @FXML
    private RadioButton radioBtnQ1N;

    @FXML
    private RadioButton radioBtnQ1D;

    @FXML
    private RadioButton radioBtnQ1SD;
    
    @FXML
    private RadioButton radioBtnQ2SA;

    @FXML
    private ToggleGroup Q2Button;
    
    @FXML
    private RadioButton radioBtnQ2A;

    @FXML
    private RadioButton radioBtnQ2N;

    @FXML
    private RadioButton radioBtnQ2D;

    @FXML
    private RadioButton radioBtnQ2SD;

    @FXML
    private RadioButton radioBtnQ3SA;
    
    @FXML
    private ToggleGroup Q3Button;
    
    @FXML
    private RadioButton radioBtnQ3A;

    @FXML
    private RadioButton radioBtnQ3N;

    @FXML
    private RadioButton radioBtnQ3D;

    @FXML
    private RadioButton radioBtnQ3SD;

    @FXML
    private RadioButton radioBtnQ4SA;
    
    @FXML
    private ToggleGroup Q4Button;
    
    @FXML
    private RadioButton radioBtnQ4A;

    @FXML
    private RadioButton radioBtnQ4N;

    @FXML
    private RadioButton radioBtnQ4D;

    @FXML
    private RadioButton radioBtnQ4SD;

    @FXML
    private RadioButton radioBtnQ5SA;
    
    @FXML
    private ToggleGroup Q5Button;
    
    @FXML
    private RadioButton radioBtnQ5A;

    @FXML
    private RadioButton radioBtnQ5N;

    @FXML
    private RadioButton radioBtnQ5D;

    @FXML
    private RadioButton radioBtnQ5SD;
    
    @FXML
    private RadioButton radioBtnQ6SA;

    @FXML
    private ToggleGroup Q6Button;
    
    @FXML
    private RadioButton radioBtnQ6A;

    @FXML
    private RadioButton radioBtnQ6N;

    @FXML
    private RadioButton radioBtnQ6D;

    @FXML
    private RadioButton radioBtnQ6SD;
    
    @FXML
    private RadioButton radioBtnQ7SA;

    @FXML
    private ToggleGroup Q7Button;

    @FXML
    private RadioButton radioBtnQ7A;

    @FXML
    private RadioButton radioBtnQ7N;

    @FXML
    private RadioButton radioBtnQ7D;

    @FXML
    private RadioButton radioBtnQ7SD;
    
    @FXML
    void Q1A(MouseEvent event) {
    	q1Answer = "A";
    }

    @FXML
    void Q1D(MouseEvent event) {
    	q1Answer = "D";
    }

    @FXML
    void Q1N(MouseEvent event) {
    	q1Answer = "N";
    }

    @FXML
    void Q1SA(MouseEvent event) {
    	q1Answer = "SA";
    }

    @FXML
    void Q1SD(MouseEvent event) {
    	q1Answer = "SD";
    }

    @FXML
    void Q2A(MouseEvent event) {
    	q2Answer = "A";
    }

    @FXML
    void Q2D(MouseEvent event) {
    	q2Answer = "D";
    }

    @FXML
    void Q2N(MouseEvent event) {
    	q2Answer = "N";
    }

    @FXML
    void Q2SA(MouseEvent event) {
    	q2Answer = "SA";
    }

    @FXML
    void Q2SD(MouseEvent event) {
    	q2Answer = "SD";
    }

    @FXML
    void Q3A(MouseEvent event) {
    	q3Answer = "A";
    }

    @FXML
    void Q3D(MouseEvent event) {
    	q3Answer = "D";
    }

    @FXML
    void Q3N(MouseEvent event) {
    	q3Answer = "N";
    }

    @FXML
    void Q3SA(MouseEvent event) {
    	q3Answer = "SA";
    }

    @FXML
    void Q3SD(MouseEvent event) {
    	q3Answer = "SD";
    }

    @FXML
    void Q4A(MouseEvent event) {
    	q4Answer = "A";
    }

    @FXML
    void Q4D(MouseEvent event) {
    	q4Answer = "D";
    }

    @FXML
    void Q4N(MouseEvent event) {
    	q4Answer = "N";
    }

    @FXML
    void Q4SA(MouseEvent event) {
    	q4Answer = "SA";
    }

    @FXML
    void Q4SD(MouseEvent event) {
    	q4Answer = "SD";
    }

    @FXML
    void Q5A(MouseEvent event) {
    	q5Answer = "A";
    }

    @FXML
    void Q5D(MouseEvent event) {
    	q5Answer = "D";
    }

    @FXML
    void Q5N(MouseEvent event) {
    	q5Answer = "N";
    }

    @FXML
    void Q5SA(MouseEvent event) {
    	q5Answer = "SA";
    }

    @FXML
    void Q5SD(MouseEvent event) {
    	q5Answer = "SD";
    }

    @FXML
    void Q6A(MouseEvent event) {
    	q6Answer = "A";
    }

    @FXML
    void Q6D(MouseEvent event) {
    	q6Answer = "D";
    }

    @FXML
    void Q6N(MouseEvent event) {
    	q6Answer = "N";
    }

    @FXML
    void Q6SA(MouseEvent event) {
    	q6Answer = "SA";
    }

    @FXML
    void Q6SD(MouseEvent event) {
    	q6Answer = "SD";
    }

    @FXML
    void Q7A(MouseEvent event) {
    	q7Answer = "A";
    }

    @FXML
    void Q7D(MouseEvent event) {
    	q7Answer = "D";
    }

    @FXML
    void Q7N(MouseEvent event) {
    	q7Answer = "N";
    }

    @FXML
    void Q7SA(MouseEvent event) {
    	q7Answer = "SA";
    }

    @FXML
    void Q7SD(MouseEvent event) {
    	q7Answer = "SD";
    }
    
    @FXML
    void btnCancel(MouseEvent event) {
    	
    }

    @FXML
    void btnSave(MouseEvent event) {

    }

    @FXML
    void btnSubmit(MouseEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Feedback");
    	alert.setHeaderText("Are you sure you want to submit?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		
    	}
    }
    
public void events() {
		
	}
	
	public void pDetails() {
		
	}
	
	public void pFeedback() throws Exception {
		ArrayList<Feedback> list =  feedbackRetrieve.retrieveAllFeedback();
		Feedback feedback = list.get(0);
		this.feedbackId = feedback.getId();
		titleText.setText(feedback.getTitle());
	}
	
	public void fQuestion() throws Exception {
		ArrayList<Question> list =  questionRetrieve.retrieveAllQuestion();
		
		Question question1 = list.get(0);
		if (!question1.getText().isEmpty()) {			
			q1text.setText(question1.getText());
		} else {
			q1.setText("");
			q1text.setText("");
			radioBtnQ1SA.setVisible(false);
			radioBtnQ1A.setVisible(false);
			radioBtnQ1N.setVisible(false);
			radioBtnQ1D.setVisible(false);
			radioBtnQ1SD.setVisible(false);
		}
		
		Question question2 = list.get(1);
		if (!question2.getText().isEmpty()) {			
			q2text.setText(question2.getText());
		} else {
			q2.setText("");
			q2text.setText("");
			radioBtnQ2SA.setVisible(false);
			radioBtnQ2A.setVisible(false);
			radioBtnQ2N.setVisible(false);
			radioBtnQ2D.setVisible(false);
			radioBtnQ2SD.setVisible(false);
		}
		
		Question question3 = list.get(2);
		if (!question3.getText().isEmpty()) {			
			q3text.setText(question3.getText());
		} else {
			q3.setText("");
			q3text.setText("");
			radioBtnQ3SA.setVisible(false);
			radioBtnQ3A.setVisible(false);
			radioBtnQ3N.setVisible(false);
			radioBtnQ3D.setVisible(false);
			radioBtnQ3SD.setVisible(false);
		}
		
		Question question4 = list.get(3);
		if (!question4.getText().isEmpty()) {			
			q4text.setText(question4.getText());
		} else {
			q4.setText("");
			q4text.setText("");
			radioBtnQ4SA.setVisible(false);
			radioBtnQ4A.setVisible(false);
			radioBtnQ4N.setVisible(false);
			radioBtnQ4D.setVisible(false);
			radioBtnQ4SD.setVisible(false);
		}
		
		Question question5 = list.get(4);
		if (!question5.getText().isEmpty()) {			
			q5text.setText(question5.getText());
		} else {
			q5.setText("");
			q5text.setText("");
			radioBtnQ5SA.setVisible(false);
			radioBtnQ5A.setVisible(false);
			radioBtnQ5N.setVisible(false);
			radioBtnQ5D.setVisible(false);
			radioBtnQ5SD.setVisible(false);
		}
		
		Question question6 = list.get(5);
		if (!question6.getText().isEmpty()) {			
			q6text.setText(question6.getText());
		} else {
			q6.setText("");
			q6text.setText("");
			radioBtnQ6SA.setVisible(false);
			radioBtnQ6A.setVisible(false);
			radioBtnQ6N.setVisible(false);
			radioBtnQ6D.setVisible(false);
			radioBtnQ6SD.setVisible(false);
		}
		
		Question question7 = list.get(6);
		if (!question7.getText().isEmpty()) {			
			q7text.setText(question7.getText());
		} else {
			q7.setText("");
			q7text.setText("");
			radioBtnQ7SA.setVisible(false);
			radioBtnQ7A.setVisible(false);
			radioBtnQ7N.setVisible(false);
			radioBtnQ7D.setVisible(false);
			radioBtnQ7SD.setVisible(false);
		}
		
	}

	public void fAnswer() {
		
	}
    
    @FXML
	private Label isConnected;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DBController c = new DBController();
		c.getConnection();
		// TODO Auto-generated method stub
		if (feedModel.isDbConnected()) {
			isConnected.setText("Connected");
			try {
				pFeedback();
				fQuestion();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			isConnected.setText("Not Connected");
		}
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



