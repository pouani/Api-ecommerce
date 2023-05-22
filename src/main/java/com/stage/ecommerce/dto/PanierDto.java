package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Panier;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder

public class PanierDto {

    private Integer id;
    private String nom;

    @JsonIgnore
    private List<ProduitDto> produit;

    private List<PanierproduitDto> panierproduits;


    public PanierDto fromEntity(Panier panier){
        if (panier == null) {
            return null;
            //TODO throw an exception
        }

        return PanierDto.builder()
                .id(panier.getId())
                .nom(panier.getNom())
                .build();
    }

    public static Panier toEntity(PanierDto panierDto){
        if (panierDto == null ){
            return null;
            //TODO throw an exception
        }
        Panier panier = new Panier();
        panier.setId(panierDto.getId());
        panier.setNom(panierDto.getNom());

        return panier;

    }
}


