package com.stage.ecommerce.controller.api;

import com.stage.ecommerce.dto.ProduitDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.stage.ecommerce.utils.IConstants.APP_ROOT;

public interface IProduitController {
    @PostMapping(value = APP_ROOT + "/produit/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ProduitDto save(@RequestBody ProduitDto dto);

    @GetMapping(value = APP_ROOT + "/produit/{idProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProduitDto findById(@PathVariable("idProduit") Integer id);
    @GetMapping(value = APP_ROOT + "/produit/{codeProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
    ProduitDto findByCodeProduit(@PathVariable("codeProduit") String codeProduit);

    @GetMapping(value = APP_ROOT + "/produit/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ProduitDto> findAll();
    @DeleteMapping(value = APP_ROOT + "/produit/delete/{idProduit}")
    void delete(@PathVariable("idProduit") Integer id);
}
