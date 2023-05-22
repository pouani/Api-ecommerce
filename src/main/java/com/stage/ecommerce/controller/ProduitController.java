package com.stage.ecommerce.controller;

import com.stage.ecommerce.controller.api.IProduitController;
import com.stage.ecommerce.dto.ProduitDto;
import com.stage.ecommerce.services.IProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProduitController implements IProduitController {

    private IProduitService produitService;

    @Autowired //Autowired défini l'injection de dépendance (ici nous utilisons injection constructor pour injection par constructeur)
    public ProduitController(IProduitService produitService){
        this.produitService = produitService;
    }
    @Override
    public ProduitDto save(ProduitDto dto) {
        return produitService.save(dto);
    }

    @Override
    public ProduitDto findById(Integer id) {
        return produitService.findById(id);
    }

    @Override
    public ProduitDto findByCodeProduit(String codeProduit) {
        return produitService.findByCodeProduit(codeProduit);
    }

    @Override
    public List<ProduitDto> findAll() {
        return produitService.findAll();
    }

    @Override
    public void delete(Integer id) {
        produitService.delete(id);
    }
}
