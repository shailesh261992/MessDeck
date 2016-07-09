package com.app.messdeck.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailID {
	@Column(unique = true,nullable=false,name="email_id")
	private String emailId;

	public EmailID() {
		super();
	}

	public EmailID(String emailID) {
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
		EmailID other = (EmailID) obj;
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
