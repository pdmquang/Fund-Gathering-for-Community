package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.fxml.FXMLLoader;

public class StackBrowser{
	
	Stack<String> browsers = new Stack<String>();
	List<String> popBrowser = new ArrayList<String>();
	
	// when user navigates to new screen
	public void addBrowserStack(String name){
		browsers.add(name);
	}
	
	// when user clicks back button 
	public void popBrowswerStack(){
		String pop = browsers.pop();
		popBrowser.add(pop);
	}
	
	public void displaySelectedBrowser(String name){
		
	}
	
}
