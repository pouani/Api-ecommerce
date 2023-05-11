package com.stage.ecommerce.validator;

import com.stage.ecommerce.dto.PanierDto;
import com.stage.ecommerce.dto.ProduitDto;
import com.stage.ecommerce.model.Panier;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PanierValidetor {

    public static List<String> validator(PanierDto panierDto) {
        List<String> errors = new ArrayList<>();


        if (panierDto == null || !StringUtils.hasLength(panierDto.getProduitajoute())) {
            errors.add("Veuillez renseigner le produit ajoute au  panier");
        }

        if (panierDto == null || !StringUtils.hasLength(panierDto.getQuantiteajoute())) {
            errors.add("Veuillez renseigner la quantite ajoute au panier");
        }

        return errors;

    }
}
