package com.app.messdeck.model.dto;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotNull;

public class EmailIDDTO {

    @Email(message="Invalid EmailID")
    @NotNull(message="EmailID  can not be null")
	private String emailId;

	public EmailIDDTO() {
		super();
	}

	public EmailIDDTO(String emailID) {
		this.emailId = emailID;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailIDDTO other = (EmailIDDTO) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmailID [emailId=" + emailId + "]";
	}

}
