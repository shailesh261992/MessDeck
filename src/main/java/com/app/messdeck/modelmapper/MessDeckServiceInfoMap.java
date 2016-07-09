package com.app.messdeck.modelmapper;

import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.MessDeckServiceInfo;
import com.app.messdeck.model.dto.MessDeckServiceInfoDTO;

public class MessDeckServiceInfoMap extends PropertyMap<MessDeckServiceInfo, MessDeckServiceInfoDTO> {

	@Override
	protected void configure() {
		map(source.getVendor().getId()).setVendor(0);
	}

}
