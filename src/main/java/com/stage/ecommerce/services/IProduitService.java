package com.stage.ecommerce.services;

import com.stage.ecommerce.dto.ProduitDto;

import java.util.List;

public interface IProduitService {

    ProduitDto save(ProduitDto dto);

    ProduitDto findById(Integer id);

    ProduitDto findByCodeProduit(String codeProduit);

    List<ProduitDto> findAll();

    void delete(Integer id);

}
