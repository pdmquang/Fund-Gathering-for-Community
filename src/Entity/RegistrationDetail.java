package Entity;

import java.sql.*;

import javafx.beans.property.SimpleStringProperty;

public class RegistrationDetail {
	private SimpleStringProperty nric;
	private SimpleStringProperty name;
	private SimpleStringProperty gender;
	private SimpleStringProperty dob;
	private SimpleStringProperty religion;
	private SimpleStringProperty language;
	private SimpleStringProperty homeAddress;
	private SimpleStringProperty homeNumber;
	private SimpleStringProperty contactNumber;
	private SimpleStringProperty email;
	private SimpleStringProperty nationality;
	private SimpleStringProperty citizenship;
	private SimpleStringProperty eventRegistered;
	private SimpleStringProperty eventDate;
	private SimpleStringProperty timeSlot;
	private SimpleStringProperty status;
	
	Connection connection;
	
	public RegistrationDetail(){
		
	}
			
	public RegistrationDetail(String nric, String name, String gender, String dob,String religion, String language,
			String homeAddress, String homeNumber, String contactNumber, String email, String nationality, String citizenship,
			String eventRegistered, String eventDate, String timeSlot, String status){
		this.nric = new SimpleStringProperty(nric);
		this.name = new SimpleStringProperty(name);
		this.gender = new SimpleStringProperty(gender);
		this.dob = new SimpleStringProperty(dob);
		this.religion = new SimpleStringProperty(religion);
		this.language = new SimpleStringProperty(language);
		this.homeAddress = new SimpleStringProperty(homeAddress);
		this.homeNumber = new SimpleStringProperty(homeNumber);
		this.contactNumber = new SimpleStringProperty(contactNumber);
		this.email = new SimpleStringProperty(email);
		this.nationality = new SimpleStringProperty(nationality);
		this.citizenship = new SimpleStringProperty(citizenship);
		this.eventRegistered = new SimpleStringProperty(eventRegistered);
		this.eventDate = new SimpleStringProperty(eventDate);
		this.timeSlot = new SimpleStringProperty(timeSlot);
		this.status = new SimpleStringProperty(status);	
	}
	
	public RegistrationDetail(String name,String eventRegistered, String eventDate, String timeSlot){
		this.name = new SimpleStringProperty(name);
		this.eventRegistered = new SimpleStringProperty(eventRegistered);
		this.eventDate = new SimpleStringProperty(eventDate);
		this.timeSlot = new SimpleStringProperty(timeSlot);
	}
	
	//Accessor and Mutator methods
	
	public String getNric() {
		return nric.get();
	}

	public String getName() {
		return name.get();
	}

	public String getGender() {
		return gender.get();
	}

	public String getDob() {
		return dob.get();
	}

	public String getReligion() {
		return religion.get();
	}

	public String getLanguage() {
		return language.get();
	}

	public String getHomeAddress() {
		return homeAddress.get();
	}

	public String getHomeNumber() {
		return homeNumber.get();
	}

	public String getContactNumber() {
		return contactNumber.get();
	}

	public String getEmail() {
		return email.get();
	}

	public String getNationality() {
		return nationality.get();
	}

	public String getCitizenship() {
		return citizenship.get();
	}

	public String getEventRegistered() {
		return eventRegistered.get();
	}

	public String getEventDate() {
		return eventDate.get();
	}

	public String getTimeSlot() {
		return timeSlot.get();
	}

	public String getStatus() {
		return status.get();
	}
	
	public void setNric(SimpleStringProperty nric) {
		this.nric = nric;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setGender(SimpleStringProperty gender) {
		this.gender = gender;
	}

	public void setDob(SimpleStringProperty dob) {
		this.dob = dob;
	}

	public void setReligion(SimpleStringProperty religion) {
		this.religion = religion;
	}

	public void setLanguage(SimpleStringProperty language) {
		this.language = language;
	}

	public void setHomeAddress(SimpleStringProperty homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setHomeNumber(SimpleStringProperty homeNumber) {
		this.homeNumber = homeNumber;
	}

	public void setContactNumber(SimpleStringProperty contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

	public void setNationality(SimpleStringProperty nationality) {
		this.nationality = nationality;
	}

	public void setCitizenship(SimpleStringProperty citizenship) {
		this.citizenship = citizenship;
	}

	public void setEventRegistered(SimpleStringProperty eventRegistered) {
		this.eventRegistered = eventRegistered;
	}

	public void setEventDate(SimpleStringProperty eventDate) {
		this.eventDate = eventDate;
	}

	public void setTimeSlot(SimpleStringProperty timeSlot) {
		this.timeSlot = timeSlot;
	}

	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}
}

