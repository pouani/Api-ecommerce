package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.CommandeProduit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeProduitDto {

    private Integer id;

    private Integer quantite;

    @JsonIgnore
    private ProduitDto produit;

    @JsonIgnore
    private CommandeDto commande;

    public static CommandeProduitDto fromEntity(CommandeProduit commandeProduit){
        if (commandeProduit == null) {
            return null;
            //TODO throw an exception
        }

        return CommandeProduitDto.builder()
                .id(commandeProduit.getId())
                .produit(ProduitDto.fromEntity(commandeProduit.getProduit()))
                .quantite(commandeProduit.getQuantite())
                .build();
    }

    public static CommandeProduit toEntity(CommandeProduitDto commandeProduitDto){
        if (commandeProduitDto == null ){
            return null;
            //TODO throw an exception
        }
        CommandeProduit commandeProduit = new CommandeProduit();
        commandeProduit.setId(commandeProduitDto.getId());
        commandeProduit.setQuantite(commandeProduitDto.getQuantite());

        return commandeProduit;

    }

}
