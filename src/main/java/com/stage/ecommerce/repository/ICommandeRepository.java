package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICommandeRepository extends JpaRepository<Commande, Integer> {
    Optional<Commande> findByCodeCommande(String code);
}
