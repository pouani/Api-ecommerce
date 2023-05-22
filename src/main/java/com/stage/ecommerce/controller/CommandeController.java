package com.stage.ecommerce.controller;

import com.stage.ecommerce.controller.api.ICommandeController;
import com.stage.ecommerce.dto.CommandeDto;
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
    public ResponseEntity<CommandeDto> findById(Integer id) {
        return ResponseEntity.ok(commandeService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeDto> findByCode(String code) {
        return ResponseEntity.ok(commandeService.findByCode(code));
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
}
