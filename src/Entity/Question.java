package Entity;

public class Question {
	private int id;
	private String text;
	private int feedbackId;
	// Constructors
	public Question () {
		
	}
	public Question (int id, String text, int feedbackId) {
		this.id = id;
		this.text = text;
		this.feedbackId = feedbackId;
	}
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	// Methods
	public void fQuestion() {
		
	}
}
