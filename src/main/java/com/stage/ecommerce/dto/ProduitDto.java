package com.stage.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Produit;
import lombok.*;

import java.util.List;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDto {

    private Integer id;
    private String nomproduit;
    private Integer prixproduit;

    private String codeProduit;

    private String description;

    private String photo;

    private CategorieDto categorie;

    @JsonIgnore
    private PanierDto panier;
    @JsonIgnore
    private List<PanierproduitDto> panierproduits;

    @JsonIgnore
    private CommandeDto commandes;

    @JsonIgnore
    private List<CommandeProduitDto> commandeProduits;



    public static ProduitDto fromEntity(Produit produit){
        if (produit == null) {
            return null;
            //TODO throw an exception
        }

        return ProduitDto.builder()
                .id(produit.getId())
                .prixproduit(produit.getPrixproduit())
                .codeProduit(produit.getCodeProduit())
                .nomproduit(produit.getNomproduit())
                .description(produit.getDescription())
                .photo(produit.getPhoto())
                .categorie(CategorieDto.fromEntity(produit.getCategorie()))
                .build();
    }

    public static Produit toEntity(ProduitDto produitDto){
        if (produitDto == null ){
            return null;
            //TODO throw an exception
        }
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setPrixproduit(produitDto.getPrixproduit());
        produit.setCodeProduit(produitDto.getCodeProduit());
        produit.setNomproduit(produitDto.getNomproduit());
        produit.setDescription(produitDto.getDescription());
        produit.setPhoto(produitDto.getPhoto());
        produit.setCategorie(CategorieDto.toEntity(produitDto.getCategorie()));

        return produit;

    }
}



