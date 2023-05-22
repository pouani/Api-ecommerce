package com.stage.ecommerce.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stage.ecommerce.model.Categorie;
import lombok.*;

import java.util.List;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {
    private Integer id;
    private String nomCategorie;
    private String description;
    private String codeCategorie;

   @JsonIgnore
   private List<ProduitDto> produit;

   public static CategorieDto fromEntity(Categorie categorie){
       if (categorie==null) {
           return null;
           //TODO throw an exception
       }

       return CategorieDto.builder()
               .id(categorie.getId())
               .nomCategorie(categorie.getNomCategorie())
               .description(categorie.getDescription())
               .build();
   }

   public static Categorie toEntity(CategorieDto categorieDto){
       if (categorieDto == null ){
           return null;
           //TODO throw an exception
       }

       Categorie categorie = new Categorie();
       categorie.setId(categorieDto.getId());
       categorie.setNomCategorie(categorieDto.getNomCategorie());
       categorie.setDescription(categorieDto.getDescription());

       return categorie;
   }
}



