package Entity;

import java.util.ArrayList;
import java.util.List;

public class Feedback {
	List<Integer> answer = new ArrayList<>();
	
	//Set
	public void setAnswer(List<Integer> answerQ1) {
		this.answer = answerQ1;
		
		for(int i : answerQ1){
			System.out.println("Entity_Feedback :: "+ i);
		}
	}
	
	//Get
	public List<Integer> getAnswer()  {
		return answer;
	}
	
	//Leif
	private int id;
	private String title;
	private String date;
	// Constructors
	public Feedback() {
		
	}
	public Feedback(int id, String title, String date) {
		this.id = id;
		this.title = title;
		this.date = date;
	}	
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	// Methods
	public void pFeedback() {
		
	}
	
}
