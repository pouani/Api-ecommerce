package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<Paiement, Integer> {
}
