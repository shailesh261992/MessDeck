package com.app.messdeck.test.data;

import com.app.messdeck.model.dto.EmailIDDTO;

public class EmailIDDTODataSample {

	public static EmailIDDTO getEmailIDDTO_OwnerEmailID() {
		EmailIDDTO emailIDDTO = new EmailIDDTO();
		emailIDDTO.setEmailId("shailesh261992@gmail.com");
		return emailIDDTO;
	}

	public static EmailIDDTO getEmailIDDTO_CustomerEmailID() {
		EmailIDDTO emailIDDTO = new EmailIDDTO();
		emailIDDTO.setEmailId("sagar@gmail.com");
		return emailIDDTO;
	}

}
