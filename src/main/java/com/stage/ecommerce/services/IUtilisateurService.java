package com.stage.ecommerce.services;

import com.stage.ecommerce.dto.UtilisateurDto;

import java.util.List;

public interface IUtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    UtilisateurDto findByEmail(String email);

    void delete(Integer id);
}
