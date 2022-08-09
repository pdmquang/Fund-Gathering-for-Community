package Entity;

public class Answer {
	private int id;
	private int questionId;
	private int feedbackId;
	private String answerText;
	// Constructors
	public Answer() {
		
	}
	public Answer(String answerText) {
		this.answerText = answerText;
	}
	public Answer(int questionId, int feedbackId, String answerText) {
		this.questionId = questionId;
		this.feedbackId = feedbackId;
		this.answerText = answerText;
	}
	// Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	// Methods
	public void fAnswer() {
		
	}
}
