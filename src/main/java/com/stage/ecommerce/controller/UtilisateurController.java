package com.stage.ecommerce.controller;

import com.stage.ecommerce.controller.api.IUtilisateurController;
import com.stage.ecommerce.dto.UtilisateurDto;
import com.stage.ecommerce.services.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UtilisateurController implements IUtilisateurController {

    private IUtilisateurService utilisateurService;
    @Autowired
    public UtilisateurController(IUtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
