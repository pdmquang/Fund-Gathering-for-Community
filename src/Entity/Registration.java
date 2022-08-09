package Entity;

import java.util.*;

import Database.Connection.DBController;


public class Registration {
	private Date registrationDate;
	private String registrationStatus;
	private String registrationTimeSlot;
	private String registeredEvent;
	
	DBController db = new DBController();
	
	// Methods
	public String registeredEvent() {
		return "";
	}
	
	// Getters and setters
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationStatus() {
		return registrationStatus;
	}
	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	public String getRegistrationTimeSlot() {
		return registrationTimeSlot;
	}
	public void setRegistrationTimeSlot(String registrationTimeSlot) {
		this.registrationTimeSlot = registrationTimeSlot;
	}
	public String getRegisteredEvent() {
		return registeredEvent;
	}
	public void setRegisteredEvent(String registeredEvent) {
		this.registeredEvent = registeredEvent;
	}
	
	
}
