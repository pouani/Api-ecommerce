package com.stage.ecommerce.services;

import com.stage.ecommerce.dto.CommandeDto;

import java.util.List;

public interface ICommandeService {

    CommandeDto save(CommandeDto dto);

    CommandeDto findById(Integer id);
    CommandeDto findByCode(String code);
    List<CommandeDto> findAll();

    void delete(Integer id);
}
