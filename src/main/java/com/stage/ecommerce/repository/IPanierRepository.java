package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPanierRepository extends JpaRepository<Panier, Integer> {
}
