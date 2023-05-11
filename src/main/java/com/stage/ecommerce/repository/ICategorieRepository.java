package com.stage.ecommerce.repository;

import com.stage.ecommerce.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategorieRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findByCodeCategorie(String codeCategorie);
}
