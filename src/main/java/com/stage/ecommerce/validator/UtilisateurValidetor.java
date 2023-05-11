package com.stage.ecommerce.validator;

import com.stage.ecommerce.dto.CategorieDto;
import com.stage.ecommerce.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidetor {
    public static List<String> validator(UtilisateurDto utilisateurDto) {
        List<String> errors = new ArrayList<>();


        if (utilisateurDto == null || !StringUtils.hasLength(utilisateurDto.getPrenomutilisateur())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }
        if (utilisateurDto == null || !StringUtils.hasLength(utilisateurDto.getNomutilisateur())) {
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }
        if (utilisateurDto == null || !StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner l'email de l'utilisateur");
        }
        if (utilisateurDto == null || !StringUtils.hasLength(utilisateurDto.getMotsdepasse())) {
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }

        return errors;

    }
}
