package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Livraison;
import com.stage.ecommerce.model.Paiement;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PaiementDto {

    private Integer id;
    private String modeldepaiement;
    private String codedepaiement;


    public PaiementDto fromEntity(Paiement paiement){
        if (paiement == null) {
            return null;
            //TODO throw an exception
        }

        return PaiementDto.builder()
                .id(paiement.getId())
                .modeldepaiement(paiement.getModeldepaiement())
                .codedepaiement(paiement.getCodedepaiement())
                .build();
    }

    public static Paiement toEntity(PaiementDto paiementDto){
        if (paiementDto == null ){
            return null;
            //TODO throw an exception
        }

        Paiement paiement = new Paiement();
        paiement.setId(paiementDto.getId());
        paiement.setModeldepaiement(paiementDto.getModeldepaiement());
        paiement.setCodedepaiement(paiementDto.getCodedepaiement());

        return paiement;

    }
}





