package com.app.messdeck.test.data;

import com.app.messdeck.model.dto.NameDTO;

public class NameDTODataSample {

	public static NameDTO getNameDTO_Owner() {
		NameDTO nameDTO = new NameDTO();
		nameDTO.setFirstName("Shailesh");
		nameDTO.setLastName("Kadam");

		return nameDTO;
	}

	public static NameDTO getNameDTO_Customer() {
		NameDTO nameDTO = new NameDTO();
		nameDTO.setFirstName("Sagar");
		nameDTO.setLastName("Bhosale");

		return nameDTO;
	}

}
