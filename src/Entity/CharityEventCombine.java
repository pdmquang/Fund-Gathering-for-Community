package Entity;

public class CharityEventCombine {
    private int eventId;
    private String eventType;
    private String eventName;
    private String startDate;
    private String endDate;
    private String organization;
    private String description;
    private String donationRequest;
    private int status;
    
    public CharityEventCombine(){}
    //Brendan
	public CharityEventCombine(int eventId, String eventType, String eventName, String startDate,
			String endDate, String organization, String description) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organization = organization;
		this.description = description;
	}
	
	//Arno
	public CharityEventCombine(int eventId, String eventName, String description,String donationRequest, int status){
		this.eventId = eventId;
		this.eventName = eventName;
		this.description = description;
		this.donationRequest = donationRequest;
		this.status = status;
	}
	
	public String getDonationRequest() {
		return donationRequest;
	}

	public void setDonationRequest(String donationRequest) {
		this.donationRequest = donationRequest;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}