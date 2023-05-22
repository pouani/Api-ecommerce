package com.stage.ecommerce.validator;

import com.stage.ecommerce.dto.ProduitDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProduitValidetor {

    public static List<String> validator(ProduitDto produitDto) {
        List<String> errors = new ArrayList<>();


        if (produitDto == null || !StringUtils.hasLength(produitDto.getNomproduit())) {
            errors.add("Veuillez renseigner le nom du produit");
        }

        if (produitDto == null ) {
            errors.add("Veuillez renseigner le prix du produit");
        }

        if (produitDto == null || !StringUtils.hasLength(produitDto.getCodeProduit())) {
            errors.add("Veuillez renseigner le code du produit");
        }

        return errors;

    }
}
