package com.stage.ecommerce.controller.api;

import com.stage.ecommerce.dto.CommandeDto;
import com.stage.ecommerce.dto.CommandeProduitDto;
import com.stage.ecommerce.model.EtatCommande;
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

    @PatchMapping(value = APP_ROOT + "/commandes/update/etat/{idCommande}/{etatCommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT + "/commandes/update/quantite/{idCommande}/{quantite}")
    ResponseEntity<CommandeDto> updateQuantiteCommande(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idCommandeProduit") Integer idCommandeProduit,
            @PathVariable("quantite") Integer quantite);

    @PatchMapping(value = APP_ROOT + "/commandes/update/client/{idClient}")
    ResponseEntity<CommandeDto> updateClient(@PathVariable("idCommande") Integer idCommande,@PathVariable("idClient") Integer idClient);

    @PatchMapping(value = APP_ROOT + "/commandes/update/product/{idCommande}/{idCommandeProduit}/{idProduit}")
    ResponseEntity<CommandeDto> updateProduit(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idCommandeProduit") Integer idCommandeProduit,
            @PathVariable("idProduit") Integer idProduit);

    @GetMapping(APP_ROOT + "/commandes/{idCommande}")
    ResponseEntity<CommandeDto> findById(Integer idCommande);

    @GetMapping(APP_ROOT + "/commandes/{codeCommande}")
    ResponseEntity<CommandeDto> findByCode(@PathVariable("codeCommande") String code);

    @GetMapping(APP_ROOT + "/commandes/commandeproduit/{idCommande}")
    ResponseEntity<List<CommandeProduitDto>> findAllCommandeProduitByCommandeId(@PathVariable("idCommande") Integer idCommande);

    @GetMapping(APP_ROOT + "/commandes/all")
    ResponseEntity<List<CommandeDto>> findAll();

    @DeleteMapping(APP_ROOT + "/commandes/delete/{idCommande}")
    ResponseEntity delete(@PathVariable("idCommande") Integer id);

    @DeleteMapping(APP_ROOT + "/commandes/delete/product/{idCommande}/{idCommandeProduit}")
    ResponseEntity deleteProduit(@PathVariable("idCommande") Integer idCommande, @PathVariable("idCommandeProduit") Integer idCommandeProduit);
}
