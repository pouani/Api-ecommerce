package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
}
