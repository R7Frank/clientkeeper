package com.r7frank.clientkeeper.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r7frank.clientkeeper.entities.Client;
import com.r7frank.clientkeeper.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@GetMapping
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		clients = clientService.findAll();
		return clients;
	}
	
	@GetMapping(value = "/{id}")
	public Client findById(@PathVariable Long id) {
		Client client = clientService.findById(id);
		return client;
	}

}
