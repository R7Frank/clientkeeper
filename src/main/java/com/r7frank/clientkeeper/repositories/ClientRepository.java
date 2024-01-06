package com.r7frank.clientkeeper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r7frank.clientkeeper.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
