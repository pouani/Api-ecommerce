package com.stage.ecommerce.controller.api;

import com.stage.ecommerce.dto.ProduitDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.ecommerce.utils.IConstants.APP_ROOT;

@Api(APP_ROOT + "/products")
public interface IProduitController {
    @PostMapping(value = APP_ROOT + "/products/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un produit", notes = "Cette methode permet d'enregistrer ou modifier un produit", response = ProduitDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet produit cree / modifie"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "L'objet produit n'est pas valide")
    })
    ProduitDto save(@RequestBody ProduitDto dto);

    @GetMapping(value = APP_ROOT + "/products/{idProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un produit par ID", notes = "Cette methode permet de chercher un produit par son ID", response = ProduitDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet produit a ete trouve dans la BDD"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Aucun produit n'existe dans la BDD avec l'ID fourni")
    })
    ProduitDto findById(@PathVariable("idProduit") Integer id);
    @GetMapping(value = APP_ROOT + "/products/byCode/{codeProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un produit par CODE", notes = "Cette methode permet de chercher un produit par son CODE",
            responseContainer = "List<ProduitDto>")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet produit a ete trouve dans la BDD"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Aucun produit n'existe dans la BDD avec le CODE fourni")
    })
    ProduitDto findByCodeProduit(@PathVariable("codeProduit") String codeProduit);

    @GetMapping(value = APP_ROOT + "/products/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des produits", notes = "Cette methode permet de chercher et renvoyer la liste des produits qui existent dans la BDD", response = ProduitDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "La liste des produits / Une liste vide")
    })
    List<ProduitDto> findAll();
    @DeleteMapping(value = APP_ROOT + "/products/delete/{idProduit}")
    @ApiOperation(value = "Supprimer un produit", notes = "Cette methode permet de supprimer un produit par ID")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet produit a ete supprime")
    })
    void delete(@PathVariable("idProduit") Integer id);
}
