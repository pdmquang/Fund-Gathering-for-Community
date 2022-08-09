package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

import Controller.RetrieveDisplay;
import Controller.SlidePictureMainDisplay;
import Controller.SlidePictureRecommendation;
import Database.Connection.DBEvent;
import Entity.CharityEvent;
import Entity.CharityEventRecommendation;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Registration implements Initializable {
	
	//Classes
	private DBEvent db_function  = new DBEvent();
	private CharityEvent event_entity = new CharityEvent();
	private RetrieveDisplay displayCharityEvent = new RetrieveDisplay();
	
	//Primitive Types
	private final int AMOUNT_OF_IMAGES = 6;
	private final int COLUMN = 3;
	private int count = 0;
	private int twiceCheck = 0;
	private int morePicture = 0; 
	private int indexVBoxChild = 0;
	private long dayDifference;

	//Reference Types
	private String str;  
	private String firstDateInList;
	
	//FXML Types
	@FXML
	private ImageView flipImage ;
	@FXML
    private ImageView editInterest;
	private ImageView displayImageView;
	private Image leftImage ;
	private SlidePictureMainDisplay flip_display = new SlidePictureMainDisplay();   
	private SlidePictureRecommendation flip_recommend = new SlidePictureRecommendation(); 
    private ClipboardContent cb = new ClipboardContent();

	//Lists
	private Set<String> hashListFile = new LinkedHashSet<String>();
	private ArrayList<String> arrayFile = new ArrayList<String>();
	private HashMap<String,ObservableList<String>> dateStoreHashMap = new HashMap<>();
	private Set<String> setOfSelectedDate = new HashSet<>();
	
	// Label
	@FXML
	private Label behindLabel;
	@FXML
	private Label resetStatement;
	@FXML
    private Label destination;
	//TextField
	@FXML
	private TextField searchBar;
	//Button
	@FXML
	private Button viewMorePicture;
	@FXML
    private Button btnName;
	@FXML
	private Button btn;
	@FXML
	private Button searchBtn;
	@FXML
    private Button homeNavigation;
	//VBox
	@FXML
	private VBox optionVBox;
	@FXML
    private VBox vImageBox; 
	@FXML
	private VBox centerVbox;
	@FXML
    private VBox vBoxRecommend;
	
	//Display
	@FXML
	private ScrollPane bigPanel;
	@FXML
    private GridPane mainBorderDisplayImage;  
	@FXML
    private GridPane recommendGrid;
	@FXML
    private BorderPane borderParent;
	@FXML
    private BorderPane borderMain;

    public int getMorePicture() {
		return morePicture;
	}

	public void setMorePicture(int morePicture) {
		this.morePicture = morePicture;
	}
	
	@FXML
    void editInterestPage(MouseEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Date Selection");
    	alert.setHeaderText(null);
    	AnchorPane root = FXMLLoader.load(getClass().getResource("EditInterest.fxml"));
		alert.getDialogPane().setContent(root);
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	}
    }

	@FXML
    void submitRegistration(ActionEvent event) {
		ArrayList<String> keySetList = new ArrayList<>(dateStoreHashMap.keySet());
		ArrayList<Integer> eventIdList = db_function.converterFileToId(keySetList);
	
		ArrayList<ObservableList<String>> valueSetList = new ArrayList<>(dateStoreHashMap.values());
		ArrayList<String> eventDateList = new ArrayList<>();
		for(int i = 0; i < valueSetList.size(); i++)
		{
			ObservableList<String> setOfValue = valueSetList.get(i);
			StringBuilder dateInputString = new StringBuilder(setOfValue.size());
			for(int valueI = 0; valueI < setOfValue.size(); valueI++)
			{
				dateInputString.append(setOfValue.get(valueI));
				if(valueI < (setOfValue.size() - 1)) 	{dateInputString.append(",");}			
			}
			eventDateList.add(dateInputString.toString());
		}
		if(db_function.createExpense("S9054321C", eventIdList, eventDateList)){
			Notifications notify = Notifications.create()
					.title("Update Status")
					.text("Your registration has been saved successfully")
					.graphic(null)
					.hideAfter(Duration.seconds(3))
					.position(Pos.BOTTOM_RIGHT);
//					.onAction(new EventHandler<ActionEvent>(){
//						@Override
//						public void handle(ActionEvent event) {
//							System.out.println("clicked");
//							
//						}
//				});
			notify.show();
			reset();
		}
    }
	
	@FXML
    void loadPicture(ActionEvent event) {
		morePicture = 6;
    }

	void reset(){
		twiceCheck = 0;
		resetStatement.setVisible(true);
		optionVBox.getChildren().setAll(resetStatement);
		hashListFile.clear();
		count = 0;
		dateStoreHashMap.clear();
		setOfSelectedDate.clear();
	}
	@FXML
	void reset(ActionEvent event) 
	{
		reset();
	}  
	  
	  @FXML
		void dragOver(DragEvent event) {
				try {
					if(event.getDragboard().hasString()){
						event.acceptTransferModes(TransferMode.ANY);
						}
				} catch (Exception e) {
					System.out.println("Drag Done Fails");
					e.printStackTrace();
				}
				
		 }

	public ListSelectionView<String> calculateDate(String str){
		ObservableList<String> eventDateAvailable = FXCollections.observableArrayList();
    	event_entity = db_function.retrieveRegisterDetail(str);
    	
    	try {
    		DateFormat d_start = new SimpleDateFormat("dd/MM/yyyy"); 
    		Date startDate = d_start.parse(event_entity.getStartDate());
        	
    		DateFormat d_end = new SimpleDateFormat("dd/MM/yyyy"); 
    		Date endDate = d_end.parse(event_entity.getEndDate());
    		
    		Calendar c = Calendar.getInstance(); 
    		
    		long diffInMillies = endDate.getTime() - startDate.getTime();
    		
    		TimeUnit timeUnit=TimeUnit.DAYS; 
    		dayDifference = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS) + 1; 
    		
    		// List of Dates for ListSelectionView
    		for (int i = 0; i < dayDifference; i++)
    		{
    			c.setTime(startDate); 
    			if(i >= 1)
    			{
    				c.add(Calendar.DATE, 1);
    			}
        		startDate = c.getTime();
        		eventDateAvailable.add(d_end.format(startDate));	
    		}
    	}
    	catch(Exception e){}		
    	//ArrayList<String> rejectedDate = new ArrayList<String>();
     	ListSelectionView<String> view = new ListSelectionView<>();
     	eventDateAvailable.removeAll(setOfSelectedDate);
     	
     	view.getSourceItems().addAll(eventDateAvailable);
		return view;
	}
    
    @FXML
    void dragDrop(DragEvent event) throws IOException {
    	str = event.getDragboard().getString();	
    	
    	hashListFile.add(str);	
		twiceCheck++;
		
		if(twiceCheck > hashListFile.size()) 
		{
			Alert twiceErrorAlert = new Alert(AlertType.WARNING);
			twiceErrorAlert.setTitle("WARNING");
			twiceErrorAlert.setHeaderText(null);
			twiceErrorAlert.setContentText("One event cannot be selected twice !");
			twiceCheck = hashListFile.size();
			twiceErrorAlert.showAndWait();
		}
    	
		else{
			try {
			ListSelectionView<String> view = calculateDate(str);
			if(view.getSourceItems().size() == 0){
				
				Alert duplicateDate = new Alert(AlertType.WARNING);
				duplicateDate.setTitle("Duplicate Dates");
				duplicateDate.setHeaderText("One of the dates that you have "
									+ "selected is duplicate with this event dates");
				duplicateDate.setContentText("If you wish to participate in this event"
							+ ", please remove duplicate event or reselect the dates");
				hashListFile.remove(str);
	    		twiceCheck = hashListFile.size();
				duplicateDate.showAndWait();
			}
			else{
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Date Selection");
		    	alert.setHeaderText(null);

		    	
				alert.getDialogPane().setContent(view);

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		if(view.getTargetItems().size() < 1){
			    		hashListFile.remove(str);
			    		twiceCheck = hashListFile.size();
		    		}
		    		else
		    		{
		    			firstDateInList = str;
		    			
			    		dateStoreHashMap.put(str, view.getTargetItems());
			        		
						for(int i = 0; i < view.getTargetItems().size(); i++)
						{
							setOfSelectedDate.add(view.getTargetItems().get(i));
						}
						display();	
		    		}
		    	}
		    	else{
		    		hashListFile.remove(str);
		    		twiceCheck = hashListFile.size();
		        	
		    	}
			}
			
		} catch (Exception e) {
					System.out.println("PROBLEM IN DROP setOfSelectedDate");
					e.printStackTrace();
				}
	        		
	    	}	
    }	 
		  
    public void removePictureInSet(String fileName)
    {
    	
		indexVBoxChild = arrayFile.indexOf(fileName);
		hashListFile.remove(fileName);
		arrayFile.remove(fileName);
		twiceCheck = hashListFile.size();
		
		count --;  
    }  

	public void display(){
		BorderPane border = new BorderPane();
		border.setPrefSize(300, 180);
		arrayFile.clear();
		try 
		{
		for(String i : hashListFile)
		{
			try { arrayFile.add(i); } catch (Exception e1) {}
		}

		if(twiceCheck <= hashListFile.size() && arrayFile.size() == hashListFile.size())
		{
			for(int i = count; i < arrayFile.size(); i++)
			{
				HBox option = new HBox();
					leftImage = new Image(new FileInputStream(arrayFile.get(i)));
					displayImageView = new ImageView(leftImage);
					displayImageView.setFitHeight(170);
					displayImageView.setPickOnBounds(true);
					displayImageView.setFitWidth(135);

					GridPane registerEvent = new GridPane();
					registerEvent.setPadding(new Insets(10,10,14,20));
					
		    		Label eventTitle = new Label(event_entity.getEventTitle());
		    		eventTitle.setFont(Font.font ("Arial Rounded MT Bold", 17));
		    		eventTitle.setWrapText(true);
		    		eventTitle.setTextAlignment(TextAlignment.LEFT);
		    		eventTitle.setPrefHeight(60);;
		
		    		//eventTitle.setPrefSize(, 25);
		    		registerEvent.add(eventTitle, 0, 0);
		    		Label eventType = new Label(event_entity.getEventType());
		    		eventType.setFont(Font.font ("Arial", 12));
		    		eventType.setPadding(new Insets(10,0,10,0));
		    		registerEvent.add(eventType, 0, 1);
		    		
		    		Button eventDate = new Button("Date Selected");	
		    		eventDate.setFont(Font.font ("Arial", 12));
		    		eventDate.setPrefSize(100, 33);
		    		eventDate.setTextAlignment(TextAlignment.CENTER);
		    		eventDate.setAccessibleText(firstDateInList);
//		    		eventDate.setAccessibleRoleDescription(String.valueOf(i));
		    		registerEvent.add(eventDate, 0, 2);
		    		eventDate.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent arg0) {
							
							Alert alert = new Alert(AlertType.INFORMATION);
					    	alert.setTitle("Date Selection");
					    	alert.setHeaderText("You have chosen these days");
					    	
					    	ObservableList<String> listOfDate = dateStoreHashMap.get(eventDate.getAccessibleText());
					    
					    	VBox dateChosenVbox = new VBox(listOfDate.size());
					    	dateChosenVbox.setAlignment(Pos.CENTER);
					    	
					    	Label dateChoseLabel = new Label();
					    	for(int i = 0; i < listOfDate.size(); i++)
					    	{
					    		dateChoseLabel = new Label(listOfDate.get(i));
					    		dateChoseLabel.setTextAlignment(TextAlignment.CENTER);
					    		dateChosenVbox.getChildren().add(dateChoseLabel);		 
					    	}
					    	
							alert.getDialogPane().setContent(dateChosenVbox);
							alert.showAndWait();
						}
		    		});
		    		
		    		Button delete = new Button("Remove");	
		    		delete.setTextAlignment(TextAlignment.CENTER);
		    		delete.setPrefSize(100, 33);
		    		delete.setFont(Font.font ("Arial", 12));
		    		delete.setAccessibleText(str);
		    		delete.setAccessibleRoleDescription(firstDateInList);
		    		registerEvent.add(delete, 0, 3);
					option.getChildren().setAll(registerEvent);
					border.setLeft(displayImageView); 
					border.setCenter(option);
					border.prefWidth(80);
					border.setAccessibleText(str);
					border.setId(str);
					
					VBox.setMargin(border, new Insets(13));
					optionVBox.getChildren().add(i,border);
		    		delete.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>(){
						@Override
						public void handle(MouseEvent event) {
							removePictureInSet(delete.getAccessibleText());
							setOfSelectedDate.removeAll(dateStoreHashMap.get(delete.getAccessibleRoleDescription()));
							dateStoreHashMap.remove(delete.getAccessibleRoleDescription());
							
							optionVBox.getChildren().remove(indexVBoxChild);
						}
		    		});	
			}
		}
		} catch (IOException e) {System.out.println(e);}
			count++;
			if(count == 1)
			{
				resetStatement.setVisible(false);
			}
		} 	    

	public void initialize(URL arg0, ResourceBundle arg1) {
	
		borderParent.setMaxSize(bigPanel.getMaxWidth(),bigPanel.getMaxHeight());;
		
		CharityEvent eventCharityDisplay = db_function.retrieveDescription();
		ObservableList<ImageView> tempCharity = displayCharityEvent.pictureForFundRaise(eventCharityDisplay);
		ObservableList<ImageView> raise_fund_pics = FXCollections.observableArrayList(tempCharity);
	    ObservableList<StackPane> pictureStacks = flip_display.addFlipPane(raise_fund_pics,eventCharityDisplay);
			
		try {
			
			GridPane gridAll = new GridPane();
    		gridAll.setPadding(new Insets(30,30,30,30));
    		gridAll.setVgap(40);
    		gridAll.setHgap(30);
    		
    		int row = 0;
    		int column = 0;
    		
    		for(int i = 0; i < pictureStacks.size(); i++)
    		{
    			StackPane picture = new StackPane(pictureStacks.get(i));
    			if(column < COLUMN) // Define constant 
    			{
    				GridPane.setConstraints(picture, column, row);
            		gridAll.getChildren().add(picture);
            		column++;
    			}
    			else 
    			{
    				column = 0;
    				row += 1;
    				i--;
    			}
    		}

    		centerVbox.getChildren().add(gridAll);
    		centerVbox.setMinSize(mainBorderDisplayImage.getMaxWidth(), mainBorderDisplayImage.getMaxHeight());;
			//780, 870
    		//centerVbox.setPrefSize(1000, 1500);
    		centerVbox.setMaxSize(mainBorderDisplayImage.getMaxWidth(), mainBorderDisplayImage.getMaxHeight());
    		
    		for(int i=0 ; i<raise_fund_pics.size();i++){
    		    int index = i;
    		gridAll.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>(){
    	    	@Override
    	        public void handle(MouseEvent mouseDrag) {
    	    		raise_fund_pics.get(index).setOnDragDetected(new EventHandler<Event>() {
    					@Override
    					public void handle(Event event) {
    						raise_fund_pics.get(index).getAccessibleText();
    	
    						Dragboard db = null;
    						try {
    							db = raise_fund_pics.get(index).startDragAndDrop(TransferMode.ANY);
    								
    						} catch (Exception e) {
    								e.printStackTrace();
    						}
    	
    			    	    cb.putString(raise_fund_pics.get(index).getAccessibleText());    	
    			    	    db.setContent(cb);
    			    	    event.consume();
    					}
    	 			});
    	    	}
    	    });
    		
    		
    	}
   			
    		CharityEventRecommendation eventRecommend = db_function.retrieveRecommend();
    		
    		ObservableList<ImageView> tempRecommend = displayCharityEvent.pictureForRecommendation(eventRecommend);
    		ObservableList<ImageView> recommendImage = FXCollections.observableArrayList(tempRecommend);
    		ObservableList<StackPane> recommendStacks = flip_recommend.addFlipPane(recommendImage,eventRecommend);
    		
    		for(int i = 0; i < AMOUNT_OF_IMAGES; i++)
    		{
    			StackPane picture = new StackPane(recommendStacks.get(i));
    			GridPane.setConstraints(picture, i, 0);
    			recommendGrid.getChildren().add(picture);
    		}
		} catch (Exception e1) {
			System.out.println("ERRO in flowAll");
			e1.printStackTrace();
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
			box.getChildren().add(registerEvent);

			return box;
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
	
	// Main Content
	
}
