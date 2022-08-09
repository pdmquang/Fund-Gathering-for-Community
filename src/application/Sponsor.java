package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Database.Connection.DBSponsorship;
import Entity.Sponsorship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Sponsor implements Initializable{

	private DBSponsorship sponsorDB = new DBSponsorship();
    @FXML private StackPane stackPane;
    
    @FXML private ScrollPane scrollPane2; 
    @FXML private ScrollPane scrollPane1;
    @FXML private VBox vBox;
    @FXML private VBox vBox1;
    
    @FXML private GridPane gridPane2;   
    @FXML private GridPane gridPane;
    
    @FXML private Label eventNameLbl;

	@FXML private TextArea eventDetailsTxtArea;
    @FXML private TextArea requestTxtArea;
    
    @FXML private Button addButton;
    @FXML private Button deleteBtn;
    @FXML private ComboBox<String> comboBox = new ComboBox();
    private ArrayList<ComboBox> cbArr = new ArrayList<ComboBox>();
	@FXML private TextField amtFld = new TextField();   
    private ArrayList<TextField> txtFldArr = new ArrayList<TextField>();
    @FXML private TextArea commentsTxtArea;

    @FXML private Button backwardBtn;
    @FXML private Label pagination;    
    @FXML private Button forwardBtn;
    
    @FXML private Label eventNameLbl2;

	@FXML private Label noLbl;
    private ArrayList<Label> noLabelArr = new ArrayList<Label>();

    @FXML private Label typeLbl;
    private ArrayList<Label> typeLblArr = new ArrayList<Label>();

    @FXML private Label amtLbl;   
    private ArrayList<Label> amtLblArr = new ArrayList<Label>();

    @FXML private TextArea commentsTxtArea2;
    
    @FXML private Button submitBtn;
    @FXML private Button backwardBtn2;
    @FXML private Label pagination2;
    @FXML private Button forwardBtn2;
    
	private int itemIndex = 0;
	private int totalRowIndex = 3;
	private final int secondRowIndex = 2;
	private int secondItemIndex = 0;
	private	boolean removeChecker = true;
	private ArrayList<Sponsorship> sponsorshipArray = new ArrayList<Sponsorship>();
	private int eventID = 0;
	
	 public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public Label getEventNameLbl() {
			return eventNameLbl;
		}

	 public void setEventNameLbl(Label eventNameLbl) {
		 this.eventNameLbl = eventNameLbl;
	 }

	 public TextArea getEventDetailsTxtArea() {
		 return eventDetailsTxtArea;
	 }

	 public void setEventDetailsTxtArea(TextArea eventDetailsTxtArea) {
		 this.eventDetailsTxtArea = eventDetailsTxtArea;
	 }

	 public TextArea getRequestTxtArea() {
		 return requestTxtArea;
	 }

	 public void setRequestTxtArea(TextArea requestTxtArea) {
		 this.requestTxtArea = requestTxtArea;
	 }

	 public ArrayList<ComboBox> getCbArr() {
		 return cbArr;
	 }

	 public void setCbArr(ArrayList<ComboBox> cbArr) {
		 this.cbArr = cbArr;
	 }
	 
	 public Label getEventNameLbl2() {
		 return eventNameLbl2;
	 }

	 public void setEventNameLbl2(Label eventNameLbl2) {
		 this.eventNameLbl2 = eventNameLbl2;
	 }

	//Combobox's list
	private ObservableList<String> comboBoxList = FXCollections.observableArrayList();
	
	public void initializeDropDown(){
		comboBox.getItems().addAll(comboBoxList);
	}
    
    @FXML
    void onEnter(ActionEvent event) {

    }

    public void setItems(){
		comboBoxList.add("Canned Food");
		comboBoxList.add("Fresh Food(Please Specify): ");
		comboBoxList.add("Shoes");
		comboBoxList.add("Shirt");
		comboBoxList.add("Pants");
		comboBoxList.add("Money");
		comboBoxList.add("Services");
		
	}
    
    @FXML
    public void addItem(ActionEvent event) {
    	//Increases the count of the number of items
    	itemIndex++;
    	//Makes sure the first drop does not double value every time a new row is made. Don't ask me why I don't
    	//know how these codes that has nothing to do with that had an effect :/
    	comboBox.getItems().clear();
    	
		//Add components into their ArrayList
		cbArr.add(new ComboBox<String>());
		txtFldArr.add(new TextField());
		
		//Set component and layout's properties
		cbArr.get(itemIndex - 1).setPrefWidth(147);
		cbArr.get(itemIndex - 1).getItems().addAll(comboBoxList);
		cbArr.get(itemIndex - 1).setPromptText("Donation Items");
		cbArr.get(itemIndex - 1).setEditable(true);
		txtFldArr.get(itemIndex - 1).setPromptText("Amount of Donation");
			
		//Loops through to add new row and components. i is the index for the row.
		 for(int i = 1; i < itemIndex + 1; i++){
			 // Prevents adding the same components back into the same rows
			 if (i < itemIndex){
			 }else{
	            //add them to the GridPane
				 gridPane.add(cbArr.get(itemIndex - 1), 2, i); //  (child, columnIndex, rowIndex)
				 gridPane.add(txtFldArr.get(itemIndex - 1), 3, i);
				 totalRowIndex += 2;
	            }
	         }
		 // So the alignment is proper. The above loop somehow adds a constraint ONCE which somehow messes
		 // with my alignment. Checks to remove only once since there's only one or there's indexoutofbound error
			if (removeChecker){
				gridPane.getRowConstraints().remove(0);
				removeChecker = false;
			}
    }
    
    @FXML
    void removeItem(ActionEvent event) {
    	//Doesn't allow user to remove the very first row of items
    	if(itemIndex == 0){
		}
		else{	
		//checks if it's the last items in the array
		if(itemIndex == 1){
			itemIndex--;
			
			//Removes the components displayed in the gridPane
			gridPane.getChildren().remove(totalRowIndex);
			gridPane.getChildren().remove(totalRowIndex - 1);
			
			//Removes the components from their respective arrays
			cbArr.remove(itemIndex);
			txtFldArr.remove(itemIndex);
			totalRowIndex -= 2;
			
		}else{
			itemIndex--;
			
			//Removes the components displayed in the gridPane
			
			gridPane.getChildren().remove(totalRowIndex);
			gridPane.getChildren().remove(totalRowIndex - 1);
			
			//Removes the components from their respective arrays
			cbArr.remove(itemIndex - 1);
			txtFldArr.remove(itemIndex - 1);
			totalRowIndex -= 2;
		}
		}
    }
    
    @FXML
    void forwardChangeScene(ActionEvent event) {
    	//stackPaneNode is just a counter. Since the stack pane stores the scroll panes in observable list,
    	//there will be indexes for it. In order for me to switch between the different panes, I need to move the
    	//"children"(the nodes) to the first place to display. Since I only have 2 items in the stack pane, 0 and 1.
    	if(!(comboBox.getValue() == null)){
        	stackPane.getChildren().get(0).toFront();
        	addPaneTwoRows();
        	commentsTxtArea2.setText(commentsTxtArea.getText());
    	}else{
    		Alert alert = new Alert(Alert.AlertType.ERROR); 
    		alert.setHeaderText("There is no item in the first row. Please add something before proceeding");
    		alert.show();
    	}
    }
    
    @FXML
    void backwardChangeScene(ActionEvent event){
    	removePaneTwoRows();
    	stackPane.getChildren().get(0).toFront();
    }
    
    public void addPaneTwoRows(){
    	addOnlyOne();
    	for (int i = 1 , j = itemIndex; i < itemIndex + 1;i++, j--){
    		if(componentsChecker(cbArr.get(i - 1),txtFldArr.get(i - 1))){
    			//Adds components into their arrayLists
    			noLabelArr.add(new Label());
    			typeLblArr.add(new Label());
    			amtLblArr.add(new Label());
    			
    			//Sets components' name
    			noLabelArr.get(i).setText(itemIndex + 2 - j + "");
    			typeLblArr.get(i).setText(cbArr.get(itemIndex - j).getValue().toString());
    			amtLblArr.get(i).setText(txtFldArr.get(itemIndex - j).getText());
    			
    			//Places them into the gridPane
    			gridPane2.add(noLabelArr.get(i), 0, i + 1);
    			gridPane2.add(typeLblArr.get(i), 1, i + 1);
    			gridPane2.add(amtLblArr.get(i), 2, i + 1);  
    			
    			secondItemIndex += 3;
    		}
    		else{
    			itemIndex--;
    		}
    	}
    }
    
    //If comboBox is empty, there will be an error trying to get it's text
    public boolean componentsChecker(ComboBox<String> cb,TextField l){
    	if (cb.getValue() == null || l.getText().equals("")){
    		return false;
    	}else
    	return true;
    }
    
    public void addOnlyOne(){
    		//Checks if components are empty
    		if(componentsChecker(comboBox,amtFld)){

    			//Adds components into their arrayLists
    			noLabelArr.add(new Label());
    			typeLblArr.add(new Label());
    			amtLblArr.add(new Label());
    			amtFld.getText();
    			
    			//Sets components' name
    			noLabelArr.get(0).setText(""+ 1);
    			typeLblArr.get(0).setText(comboBox.getValue().toString());
    			
    			amtLblArr.get(0).setText(amtFld.getText());
    			
    			//Places them into the gridPane
    			gridPane2.add(noLabelArr.get(0), 0, 1);
    			gridPane2.add(typeLblArr.get(0), 1, 1);
    			gridPane2.add(amtLblArr.get(0), 2, 1);  
    			
    			secondItemIndex += 3;
    		}
    }
    
    //Loops through, deletes all the rows such that there will be no duplication
    public void removePaneTwoRows(){
    	if (noLabelArr.isEmpty()){
    	}else{
    	for (int i = 0; i < itemIndex + 1; i++){
    		noLabelArr.remove(0);
    		typeLblArr.remove(0);
    		amtLblArr.remove(0);
        	
    		gridPane2.getChildren().remove(secondRowIndex + secondItemIndex--);
    		gridPane2.getChildren().remove(secondRowIndex + secondItemIndex--);
    		gridPane2.getChildren().remove(secondRowIndex + secondItemIndex--);
    		
    	}		
    	}
    }
    
    @FXML
    public void createSponsorshipRecord(){
    	String message = null;
    	if (validateInput(typeLblArr, amtLblArr)){
    	System.out.println("Hi");
    	System.out.println(secondItemIndex);
    	for (int i = 0; i < secondItemIndex;  i++, secondItemIndex-=2){
    		sponsorshipArray.add(new Sponsorship("160324J", typeLblArr.get(i).getText() , Double.parseDouble(amtLblArr.get(i).getText().toString())));
    		
    		}
    	sponsorDB.createSponsor(sponsorshipArray, eventID);
    
    	}
    	else{
    		message = "You have not added any donation items.";
    	}
    	if(sponsorshipArray.isEmpty()){
    		message = "There is an error in your entered fields. Please check your entered items.";
    	}else{
    		message = "Your sponsorship has been created.";
    	}
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
    	alert.showAndWait();
    }
    
    
    //Checks if amount of donation is actually numbers
    private static boolean isAllNumbers(String str) {
		boolean result = true;
		try {
			Double.parseDouble(str);
		}catch(NumberFormatException e){
		
			result = false;
		}
		return result;
	}
    
    private static boolean isAllLetters(String str) {
		boolean result = false;
		try {
			Double.parseDouble(str);
		}catch(NumberFormatException e){
		
			result = true;
		}
		return result;
    }
    
    public static boolean validateInput(ArrayList<Label> typeLblArr, ArrayList<Label> amtLblArr){
    	boolean valid = true;
    	for (int i = 0; i < typeLblArr.size(); i++){
    		//Checks if it's all letters. If it is, this attack does nothing
    		if(isAllLetters(typeLblArr.get(i).getText())){
    		}else{
    			typeLblArr.get(i).setStyle("-fx-color: red");
    			valid = false;
    		}
    		//checks if all numbers. If it is, this attack does nothing
    			if(isAllNumbers(amtLblArr.get(i).getText())){
    			}else{
    				amtLblArr.get(i).setStyle("-fx-color: red");
        			valid = false;
    			}
    	}
    	return valid;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setItems();
		initializeDropDown();
	}        
}
