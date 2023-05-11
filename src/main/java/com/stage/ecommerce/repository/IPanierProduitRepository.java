package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Panierproduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPanierProduitRepository extends JpaRepository<Panierproduit, Integer> {
}
