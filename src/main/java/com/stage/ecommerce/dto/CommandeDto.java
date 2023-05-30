package com.stage.ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Commande;
import com.stage.ecommerce.model.EtatCommande;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class CommandeDto {

    private Integer id;
    private String nomcommande;
    private String codeCommande;
    private String datecommande;
    private String produitcommande;
    private EtatCommande statutcommande;

    private ClientDto client;

//    @JsonIgnore
//    private ProduitDto produit;

    @JsonIgnore
    private PanierDto panier;

    @JsonIgnore
    private List<CommandeProduitDto> commandeProduit;


    public static CommandeDto fromEntity(Commande commande){
        if (commande==null) {
            return null;
            //TODO throw an exception
        }

        return CommandeDto.builder()
                .id(commande.getId())
                .nomcommande(commande.getNomcommande())
                .codeCommande(commande.getCodeCommande())
                .datecommande(commande.getDatecommande())
                .statutcommande(commande.getStatutcommande())
                .client(ClientDto.fromEntity(commande.getClient()))
                .build();
    }

    public static Commande toEntity(CommandeDto commandeDto){
        if (commandeDto == null ){
            return null;
            //TODO throw an exception
        }

        Commande commande = new Commande();
        commande.setId(commandeDto.getId());
        commande.setNomcommande(commandeDto.getNomcommande());
        commande.setCodeCommande(commandeDto.getCodeCommande());
        commande.setDatecommande(commandeDto.getDatecommande());
        commande.setStatutcommande(commandeDto.getStatutcommande());

        return commande;
    }

    public boolean isCommandeLivre(){
        return EtatCommande.LIVREE.equals(this.statutcommande);
    }
}









