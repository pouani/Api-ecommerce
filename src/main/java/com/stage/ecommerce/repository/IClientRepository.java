package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {
}
