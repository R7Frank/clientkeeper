package com.r7frank.clientkeeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.r7frank.clientkeeper.dto.ClientDTO;
import com.r7frank.clientkeeper.entities.Client;
import com.r7frank.clientkeeper.repositories.ClientRepository;
import com.r7frank.clientkeeper.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso Não Localizado!"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client(dto);
		client = clientRepository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = clientRepository.getReferenceById(id);
			copyDtoToEntity(dto, client);
			client = clientRepository.save(client);
			return new ClientDTO(client);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não localizado!");
		}
	}

	@Transactional
	public void delete(Long id) {
		if (!clientRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não localizado!");
		}
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
