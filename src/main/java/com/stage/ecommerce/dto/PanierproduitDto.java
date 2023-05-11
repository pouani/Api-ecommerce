package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Panierproduit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PanierproduitDto {

    private Integer id;

    private Integer quantite;

    @JsonIgnore
    private ProduitDto produit;

    @JsonIgnore
    private PanierDto panier;


    public PanierproduitDto fromEntity(Panierproduit panierproduit){
        if (panierproduit == null) {
            return null;
            //TODO throw an exception
        }

        return PanierproduitDto.builder()
                .id(panierproduit.getId())
                .quantite(panierproduit.getQuantite())
                .produit(ProduitDto.fromEntity(panierproduit.getProduit()))
                .build();
    }

    public static Panierproduit toEntity(PanierproduitDto panierproduitDto){
        if (panierproduitDto == null ){
            return null;
            //TODO throw an exception
        }
        Panierproduit panierproduit = new Panierproduit();
        panierproduit.setQuantite(panierproduitDto.getQuantite());
        panierproduit.setId(panierproduitDto.getId());

        return panierproduit;

    }
}
