package com.stage.ecommerce.controller;

import com.stage.ecommerce.controller.api.ICategorieController;
import com.stage.ecommerce.dto.CategorieDto;
import com.stage.ecommerce.services.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements ICategorieController {

    private ICategorieService categorieService;

    @Autowired
    public CategorieController(ICategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        return categorieService.save(dto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCodeCategorie(String codeCategorie) {
        return categorieService.findByCodeCategorie(codeCategorie);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorieService.delete(id);
    }
}
