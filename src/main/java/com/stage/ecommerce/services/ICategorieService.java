package com.stage.ecommerce.services;

import com.stage.ecommerce.dto.CategorieDto;

import java.util.List;

public interface ICategorieService {
    CategorieDto save(CategorieDto dto);

    CategorieDto findById(Integer id);

    CategorieDto findByCodeCategorie(String codeCategorie);

    List<CategorieDto> findAll();

    void delete(Integer id);
}
