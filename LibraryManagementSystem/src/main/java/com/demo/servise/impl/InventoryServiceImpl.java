package com.demo.servise.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Inventory;
import com.demo.repository.InventoryRepository;
import com.demo.servise.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Override
	public Integer createInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
		return inventory.getInventoryId();
	}

}
