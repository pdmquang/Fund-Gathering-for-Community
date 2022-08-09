package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class EditInterest {
	@FXML
    private RadioButton community;

    @FXML
    private RadioButton education;

    @FXML
    private RadioButton Social;

    @FXML
    private RadioButton Sports;
    
    public static ArrayList<String> radioChoice = new ArrayList<>();
    int i = 0;
    
    public void radioSelect(ActionEvent event)
    {
    	
    	if(community.isSelected()){
    		System.out.println(community.getText());
    		radioChoice.add(community.getText());
    	}
    	if(education.isSelected()){
    		radioChoice.add(education.getText());
    	}
    	if(Social.isSelected()){
    		radioChoice.add(Social.getText());
    	}
    	if(Sports.isSelected()){
    		radioChoice.add(Sports.getText());
    	}
    	
    }

}
