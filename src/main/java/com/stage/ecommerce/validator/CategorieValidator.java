package com.stage.ecommerce.validator;

import com.stage.ecommerce.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {

    public static List<String> validator(CategorieDto categorieDto) {
        List<String> errors = new ArrayList<>();


        if (categorieDto == null || !StringUtils.hasLength(categorieDto.getNomCategorie())) {
            errors.add("Veuillez renseigner le nom de la categorie");
        }

        if (categorieDto == null || !StringUtils.hasLength(categorieDto.getDescription())) {
            errors.add("Veuillez renseigner la designation de la categorie");
        }

        return errors;

    }
}
