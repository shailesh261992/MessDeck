package com.app.messdeck.test.data;

import com.app.messdeck.model.dto.PersonDTO;

public class PersonDTODataSample {

	public static PersonDTO getPersonDTO() {
		PersonDTO personDTO = new PersonDTO();
		personDTO.setMobileNo("7276248187");
		personDTO.setEmailID(EmailIDDTODataSample.getEmailIDDTO_OwnerEmailID());
		personDTO.setName(NameDTODataSample.getNameDTO_Owner());
		return personDTO;
	}

}
