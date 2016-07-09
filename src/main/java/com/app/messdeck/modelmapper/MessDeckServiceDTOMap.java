package com.app.messdeck.modelmapper;

import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;

public class MessDeckServiceDTOMap extends PropertyMap<MessDeckServiceInfoDTO, MessDeckServiceInfo> {

	@Override
	protected void configure() {
		map(source.getVendorId()).setVendor(null);

	}

}
