package com.app.messdeck.model.dto;

import com.app.messdeck.entity.Gender;

import net.sf.oval.constraint.AssertValid;
import net.sf.oval.constraint.MatchPattern;

public class PersonDTO extends AbstractDTO {

	@AssertValid
	private NameDTO name;

	@MatchPattern(pattern = "^[789]\\d{9}$", message = "Invalid Mobile Number:Number should start with 7,8 or 9 & must be 10 digits")
	private String mobileNo;

	@AssertValid
	private EmailIDDTO emailID;
	private Gender gender;

	public NameDTO getName() {
		return name;
	}

	public void setName(NameDTO name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public EmailIDDTO getEmailID() {
		return emailID;
	}

	public void setEmailID(EmailIDDTO emailID) {
		this.emailID = emailID;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailID == null) ? 0 : emailID.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonDTO other = (PersonDTO) obj;
		if (emailID == null) {
			if (other.emailID != null)
				return false;
		} else if (!emailID.equals(other.emailID))
			return false;
		return true;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", mobileNo=" + mobileNo + ", emailID=" + emailID + ", gender=" + gender + "]";
	}
}
