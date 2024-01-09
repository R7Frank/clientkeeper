package com.r7frank.clientkeeper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r7frank.clientkeeper.dto.ClientDTO;
import com.r7frank.clientkeeper.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping
	public List<ClientDTO> findAll() {
		return clientService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ClientDTO findById(@PathVariable Long id) {
		ClientDTO client = clientService.findById(id);
		return client;
	}

}
