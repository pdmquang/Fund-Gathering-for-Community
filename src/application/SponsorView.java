package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import Database.Connection.DBEvent;
import Entity.CharityEventCombine;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SponsorView implements Initializable{
	Alert alert = new Alert(Alert.AlertType.WARNING, "I Warn You!", ButtonType.OK);
	
    @FXML private StackPane stackPane;

    @FXML private VBox colOneVbox;
    @FXML private VBox colTwoVbox;
    @FXML private VBox colThreeVbox;
    
    @FXML private ScrollPane eventScrollPane;

    @FXML private ImageView Forward;

    @FXML
    private ScrollPane eventScrollPane1;
    
    @FXML
    private Button sponsorEventBtn;

    @FXML
    private TextArea firstPic = new TextArea();
    
    @FXML private Label lbl = new Label();
    
    @FXML private GridPane gridPane;
    
     ArrayList<CharityEventCombine> eventList;
    
    private ArrayList<TextArea> txtAreaList = new ArrayList<TextArea>();
    private boolean changeChecker = true;    
    static Stage stage = new Stage();

    @FXML
    private void displayEvents(ActionEvent event) {
    	lbl.setText("WOW");
    	lbl.setStyle("-fx-color: white");
    	//Setting their paddings and stuff
    	colOneVbox.setPadding(new Insets(5,5,5,5));
    	colTwoVbox.setPadding(new Insets(5,5,5,5));
    	colThreeVbox.setPadding(new Insets(5,5,5,5));
    	colOneVbox.setSpacing(5);
    	colTwoVbox.setSpacing(5);
    	colThreeVbox.setSpacing(5);
    	
    	//stackPane.getChildren().get(0).toFront();
    	if (changeChecker){
    	for (int i = 0 ; i < eventList.size(); i++){
    		
    		txtAreaList.get(i).setPrefHeight(129);
    		txtAreaList.get(i).setPrefWidth(157);
    		if( (i + 1) % 3 == 1){
    			
    			colOneVbox.getChildren().add(txtAreaList.get(i));
    		}else if((i + 1) % 3 == 2){
    			
    			colTwoVbox.getChildren().add(txtAreaList.get(i));
    		}else if((i + 1) % 3 == 0){
    			
    			colThreeVbox.getChildren().add(txtAreaList.get(i));
    		}
    	}
   		changeChecker = false;
    	}else{
    		colOneVbox.getChildren().removeAll(colOneVbox.getChildren());
    		colTwoVbox.getChildren().removeAll(colTwoVbox.getChildren());
    		colThreeVbox.getChildren().removeAll(colThreeVbox.getChildren()); 
    		
    		changeChecker = true;
    	}
    	//colOneVbox.getChildren().add(txtAreaList.get(2));
    	//gridPane.getChildren().add(txtAreaList.get(1));
    	//gridPane.setPadding(new Insets(5,5,5,5));
    }
    
    @FXML
    private void hideSponsorOption() {

    }

    @FXML
    private void showSponsorOption(MouseEvent event) {
    	
    	 ColorAdjust colorAdjust = new ColorAdjust();
         colorAdjust.setBrightness(0.5);
    	 Timeline fadeOutTimeline = new Timeline(
    			 	new KeyFrame(Duration.seconds(0), 
                		 new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)), 
                         new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), 0, Interpolator.LINEAR)
                         ));
    	 fadeOutTimeline.setCycleCount(1);
    	 fadeOutTimeline.setAutoReverse(true);
    	 fadeOutTimeline.play();
    }
    
    public void openSponsorForm(CharityEventCombine e){
    	
    	try{         
    		String message = "The form is not yet filled up. Are you sure you want to exit?";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/SponsorForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Sponsor formController = (Sponsor) fxmlLoader.getController();
            formController.getEventNameLbl().setText(e.getEventName());
            formController.getEventNameLbl2().setText(e.getEventName());
            formController.getEventDetailsTxtArea().setText(e.getDescription());
            formController.getRequestTxtArea().setText(e.getDonationRequest());
            formController.setEventID(e.getEventId());
          
            stage.setScene(new Scene(root1));  ;
            stage.show();

          }catch(Exception e1){
          }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		eventList = DBEvent.retrieveAllEvent();
		for (int i = 0; i < eventList.size(); i++){
			int index = i;
			txtAreaList.add(new TextArea());
			txtAreaList.get(i).setText(eventList.get(i).getEventName());
			txtAreaList.get(i).setEditable(false);
			if( eventList.get(i).getStatus() == 0){
			txtAreaList.get(i).setOnMouseClicked(e ->{
				openSponsorForm(eventList.get(index));
				});
			}else{
				txtAreaList.get(i).setOpacity(0.5);
				txtAreaList.get(i).setText("This event has been sponsored.");
			}
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
