package Entity;

import java.util.ArrayList;

public class Sponsorship {
	
	private String sponsorshipNric;
	private String donationType;
	private double donationAmt;
	
	public Sponsorship() {
	
	}

	public Sponsorship(String sponsorshipNric, String donationType,double donationAmt){
		this.sponsorshipNric = sponsorshipNric;
		this.donationType = donationType;
		this.donationAmt = donationAmt;
	}
	
	public double getDonationAmt() {
		return donationAmt;
	}
	
	public void setDonationAmt(double donationAmt) {
		this.donationAmt = donationAmt;
	}
	
	public String getDonationType() {
		return this.donationType;
	}
	
	public void setDonationType(String donationType) {
		this.donationType = donationType;
	}
	
	public String getSponsorshipNric() {
		return sponsorshipNric;
	}
	
	public void setSponsorshipID(String sponsorshipNric) {
		this.sponsorshipNric = sponsorshipNric;
	}
	
}
