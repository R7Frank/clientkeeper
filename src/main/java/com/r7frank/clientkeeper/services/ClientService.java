package com.r7frank.clientkeeper.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> result = clientRepository.findAll(pageable);
		return result.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		Client client = result.get();
		ClientDTO dto = new ClientDTO(client);
		return dto;
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client(dto);
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client client = clientRepository.getReferenceById(id);
		copyDtoToEntity(dto, client);
		client = clientRepository.save(client);		
		return new ClientDTO(client);
	}

	@Transactional
	public void delete(Long id) {
	clientRepository.deleteById(id);
	}
	
	
	private void copyDtoToEntity(ClientDTO dto, Client client) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}	
	
}
