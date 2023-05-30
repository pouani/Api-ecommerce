package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommandeProduitRepository extends JpaRepository<CommandeProduit, Integer> {
    List<CommandeProduit> findAllByCommandeId(Integer idCommande);
}
