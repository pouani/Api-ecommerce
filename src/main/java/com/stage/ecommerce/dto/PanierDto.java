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
    private String produitajoute;
    private String quantiteajoute;
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
                .produitajoute(panier.getProduitajoute())
                .quantiteajoute(panier.getQuantiteajoute())
                .build();
    }

    public static Panier toEntity(PanierDto panierDto){
        if (panierDto == null ){
            return null;
            //TODO throw an exception
        }
        Panier panier = new Panier();
        panier.setId(panierDto.getId());
        panier.setProduitajoute(panierDto.getProduitajoute());
        panier.setQuantiteajoute(panierDto.getQuantiteajoute());

        return panier;

    }
}


