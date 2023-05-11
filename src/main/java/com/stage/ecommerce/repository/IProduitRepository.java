package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProduitRepository extends JpaRepository<Produit, Integer> {

    Optional<Produit> findProduitByCodeProduit(String codeProduit);
}
