package com.stage.ecommerce.controller;

import com.stage.ecommerce.controller.api.ICommandeController;
import com.stage.ecommerce.dto.CommandeDto;
import com.stage.ecommerce.dto.CommandeProduitDto;
import com.stage.ecommerce.model.EtatCommande;
import com.stage.ecommerce.services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CommandeController implements ICommandeController {

    private ICommandeService commandeService;

    @Autowired
    public CommandeController(ICommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Override
    public ResponseEntity<CommandeDto> save(CommandeDto dto) {
        return ResponseEntity.ok(commandeService.save(dto));
    }

    @Override
    public ResponseEntity<CommandeDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return ResponseEntity.ok(commandeService.updateEtatCommande(idCommande, etatCommande));
    }

    @Override
    public ResponseEntity<CommandeDto> updateQuantiteCommande(Integer idCommande, Integer idCommandeProduit, Integer quantite) {
        return ResponseEntity.ok(commandeService.updateQuantiteCommande(idCommande, idCommandeProduit, quantite));
    }

    @Override
    public ResponseEntity<CommandeDto> updateClient(Integer idCommande, Integer idClient) {
        return ResponseEntity.ok(commandeService.updateClient(idCommande, idClient));
    }

    @Override
    public ResponseEntity<CommandeDto> updateProduit(Integer idCommande, Integer idCommandeProduit, Integer idProduit) {
        return ResponseEntity.ok(commandeService.updateProduit(idCommande, idCommandeProduit, idProduit));
    }

    @Override
    public ResponseEntity<CommandeDto> findById(Integer id) {
        return ResponseEntity.ok(commandeService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeDto> findByCode(String code) {
        return ResponseEntity.ok(commandeService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeProduitDto>> findAllCommandeProduitByCommandeId(Integer idCommande) {
        return ResponseEntity.ok(commandeService.findAllCommandeProduitByCommandeId(idCommande));
    }

    @Override
    public ResponseEntity<List<CommandeDto>> findAll() {
        return ResponseEntity.ok(commandeService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity deleteProduit(Integer idCommande, Integer idCommandeProduit) {
        return ResponseEntity.ok(commandeService.deleteProduit(idCommande, idCommandeProduit));
    }
}
