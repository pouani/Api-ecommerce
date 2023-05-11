package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILivraisonRepository extends JpaRepository<Livraison, Integer> {
}
