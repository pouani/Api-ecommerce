package com.stage.ecommerce.controller.api;

import com.stage.ecommerce.dto.CommandeDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.ecommerce.utils.IConstants.APP_ROOT;

@Api(APP_ROOT + "/commandes")
public interface ICommandeController {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeDto> save(@RequestBody CommandeDto dto);

    @GetMapping(APP_ROOT + "/commandes/{idCommande}")
    ResponseEntity<CommandeDto> findById(Integer idCommande);

    @GetMapping(APP_ROOT + "/commandes/{codeCommande}")
    ResponseEntity<CommandeDto> findByCode(@PathVariable("codeCommande") String code);

    @GetMapping(APP_ROOT + "/commandes/all")
    ResponseEntity<List<CommandeDto>> findAll();

    @DeleteMapping(APP_ROOT + "/commandes/delete/{idCommande}")
    ResponseEntity delete(@PathVariable("idCommande") Integer id);
}
