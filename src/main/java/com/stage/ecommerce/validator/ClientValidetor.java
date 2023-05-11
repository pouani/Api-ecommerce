package com.stage.ecommerce.validator;

import com.stage.ecommerce.dto.CategorieDto;
import com.stage.ecommerce.dto.ClientDto;
import com.stage.ecommerce.model.Client;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidetor {

    public static List<String> validator(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();


        if (clientDto == null || !StringUtils.hasLength(clientDto.getNomclient())) {
            errors.add("Veuillez renseigner le nom du client");
        }

        if (clientDto == null || !StringUtils.hasLength(clientDto.getAdresseClient())) {
            errors.add("Veuillez renseigner l'adresse du client");
        }

        if (clientDto == null || !StringUtils.hasLength(clientDto.getPrenomclient())) {
                errors.add("Veuillez renseigner le prenom du client");

        }

        if (clientDto == null || !StringUtils.hasLength(clientDto.getTelephoneclient())) {
            errors.add("Veuillez renseigner le telephone du client");
        }

        return errors;

    }
}
