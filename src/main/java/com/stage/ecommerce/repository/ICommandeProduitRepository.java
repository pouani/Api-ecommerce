package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandeProduitRepository extends JpaRepository<CommandeProduit, Integer> {
}
