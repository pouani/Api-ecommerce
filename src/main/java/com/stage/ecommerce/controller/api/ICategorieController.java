package com.stage.ecommerce.controller.api;

import com.stage.ecommerce.dto.CategorieDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.ecommerce.utils.IConstants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface ICategorieController {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou modifier une categorie", response = CategorieDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet categorie cree / modifie"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    CategorieDto save(@RequestBody  CategorieDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategorie}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par ID", notes = "Cette methode permet de chercher une categorie par son ID", response = CategorieDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategorieDto findById(@PathVariable("idCategorie") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/byCode/{codeCategorie}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Cette methode permet de chercher une categorie par son CODE",
            response = CategorieDto.class)
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet categorie a ete trouve dans la BDD"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec le CODE fourni")
    })
    CategorieDto findByCodeCategorie(@ApiParam(value = "Accepted [CAT1, CAT2, CAT3, ..]") @PathVariable("codeCategorie") String codeCategorie);

    @GetMapping(value = APP_ROOT + "/categories/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent "
            + "dans la BDD", responseContainer = "List<CategorieDto>")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategorieDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    @ApiOperation(value = "Supprimer une categorie", notes = "Cette methode permet de supprimer une categorie par ID")
    @ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "L'objet categorie a ete supprime")
    })
    void delete(@PathVariable("idCategorie") Integer id);
}
