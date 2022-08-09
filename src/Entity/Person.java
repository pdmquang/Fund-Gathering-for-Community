package Entity;

public class Person {
	private String nric;
    private String personName;
    private int homeNo;
    private int contactNo;
    private String email;
    private String dateOfBirth;
    private String address;
    private String language;
    private String occupation;
    private String gender;
    private String company;
    
	public Person(String nric) {
		super();
		this.nric = nric;
	}
	public Person(String nric, String personName) {
		super();
		this.nric = nric;
		this.personName = personName;
	}
	public Person(String nric, String personName, int homeNo, int contactNo, String email,
			String dateOfBirth, String address, String language, String occupation, 
			String gender, String company) {
		super();
		this.nric = nric;
		this.personName = personName;
		this.homeNo = homeNo;
		this.contactNo = contactNo;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.language = language;
		this.occupation = occupation;
		this.gender = gender;
		this.company = company;
	}

	
	
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getHomeNo() {
		return homeNo;
	}
	public void setHomeNo(int homeNo) {
		this.homeNo = homeNo;
	}
	public int getContactNo() {
		return contactNo;
	}
	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}