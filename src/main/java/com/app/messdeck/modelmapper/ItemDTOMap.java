package com.app.messdeck.modelmapper;

import org.modelmapper.PropertyMap;

import com.app.messdeck.entity.Item;
import com.app.messdeck.model.dto.ItemDTO;

public class ItemDTOMap extends PropertyMap<ItemDTO, Item> {

	@Override
	protected void configure() {
		skip().setId(0);

	}

}
