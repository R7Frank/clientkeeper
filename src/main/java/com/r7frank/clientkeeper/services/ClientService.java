package com.r7frank.clientkeeper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.r7frank.clientkeeper.dto.ClientDTO;
import com.r7frank.clientkeeper.entities.Client;
import com.r7frank.clientkeeper.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<Client> result = clientRepository.findAll();
		return result.stream().map(x -> new ClientDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		Client client = result.get();
		ClientDTO dto = new ClientDTO(client);
		return dto;
	}

}
