package com.app.messdeck.test.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.app.messdeck.entity.ServiceType;
import com.app.messdeck.model.dto.ItemDTO;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;

public class MessDeckServiceInfoDataSample {
	public static MessDeckServiceInfoDTO getMessDeckServiceInfoDTO() {
		MessDeckServiceInfoDTO dto = new MessDeckServiceInfoDTO();
		dto.setCapacityOfMembers(50);
		dto.setCost(100);
		dto.setDate(LocalDate.now());

		dto.setStartTime(LocalTime.now().minus(2, ChronoUnit.MINUTES));
		dto.setEndTime(LocalTime.now());
		dto.setServiceType(ServiceType.DINNER);
		dto.setVendor(1);

		ArrayList<ItemDTO> items = new ArrayList<>();
		ItemDTO itemDTO1 = new ItemDTO();
		itemDTO1.setId(1);
		itemDTO1.setName("Egg curry");
		itemDTO1.setDescription("Eggs cury description");

		ItemDTO itemDTO2 = new ItemDTO();
		// itemDTO2.setId(2);
		itemDTO2.setName("Chapatai");
		itemDTO2.setDescription("Wheat Chapati Description");

		ItemDTO itemDTO3 = new ItemDTO();
		// itemDTO3.setId(3);
		itemDTO3.setName("Rice");
		itemDTO3.setDescription("Rice Description");

		items.add(itemDTO1);
		items.add(itemDTO2);
		items.add(itemDTO3);

		dto.setMeal(items);

		return dto;

	}

}
