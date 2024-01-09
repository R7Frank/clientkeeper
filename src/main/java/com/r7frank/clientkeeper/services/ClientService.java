package com.r7frank.clientkeeper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.r7frank.clientkeeper.entities.Client;
import com.r7frank.clientkeeper.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		Client client = result.get();
		return client;
	}

}
