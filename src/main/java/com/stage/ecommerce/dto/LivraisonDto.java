package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Categorie;
import com.stage.ecommerce.model.Livraison;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder

public class LivraisonDto {

    private Integer id;
    private String adresselivraison;
    private String modelivraison;
    private String fraislivraison;

    @JsonIgnore
    private List<CommandeDto> commande;

    public LivraisonDto fromEntity(Livraison livraison){
        if (livraison == null) {
            return null;
            //TODO throw an exception
        }

        return LivraisonDto.builder()
                .id(livraison.getId())
                .adresselivraison(livraison.getAdresselivraison())
                .fraislivraison(livraison.getFraislivraison())
                .build();
    }

    public static Livraison toEntity(LivraisonDto livraisonDto){
        if (livraisonDto == null ){
            return null;
            //TODO throw an exception
        }

        Livraison livraison = new Livraison();
        livraison.setId(livraisonDto.getId());
        livraison.setAdresselivraison(livraisonDto.getAdresselivraison());
        livraison.setModelivraison(livraisonDto.getModelivraison());
        livraison.setFraislivraison(livraisonDto.getFraislivraison());

        return livraison;

    }
}
