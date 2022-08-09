package Entity;

import java.util.ArrayList;
import java.util.List;

public class CharityEventRecommendation {
	
	//String
	private String eventTitle ;
	private String eventType;
	private String startDate;
	private String endDate;
	
	//Lists
	private List<String> category = new ArrayList<String>();
	private List<String> name = new ArrayList<String>();
	private List<String> start = new ArrayList<String>();
	private List<String> end = new ArrayList<String>();
	private List<String> organization = new ArrayList<String>();
	private List<String> description = new ArrayList<String>();
	private List<String> image = new ArrayList<String>();
	
	public CharityEventRecommendation(){
		
	}
	
	public CharityEventRecommendation(String eventTitle, String eventType, String startDate, String endDate)
	{
		this.eventTitle = eventTitle;
		this.eventType = eventType;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public CharityEventRecommendation(List<String> category, List<String> name, List<String> start, List<String> end,
			List<String> organization, List<String> description, List<String> image) {
		super();
		this.category = category;
		this.name = name;
		this.start = start;
		this.end = end;
		this.organization = organization;
		this.description = description;
		this.image = image;
	}
	
	// Register Box
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
	
	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	
	//	Details Display
	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}
	
	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getStart() {
		return start;
	}

	public void setStart(List<String> start) {
		this.start = start;
	}

	public List<String> getEnd() {
		return end;
	}

	public void setEnd(List<String> end) {
		this.end = end;
	}

	public List<String> getOrganization() {
		return organization;
	}

	public void setOrganization(List<String> organization) {
		this.organization = organization;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}
	
	
}
