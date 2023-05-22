package com.stage.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Produit")
public class Produit extends AbstractEntity {

   @Column(name = "nomproduit")
    private String nomproduit;

   @Column(name = "prixproduit")
    private Integer prixproduit;

   @Column(name = "codeproduit")
    private String codeProduit;

   @Column(name = "description")
   private String description;

   @Column(name = "photo")
   private String photo;

   @ManyToOne
   @JoinColumn(name = "idCategorie")
   private Categorie categorie;

//   @ManyToOne
//   @JoinColumn(name = "idpanier")
//   private Panier panier;

   @OneToMany(mappedBy = "panier")
   private List<Panierproduit> panierproduits;

   @OneToMany(mappedBy = "produit")
   private List<CommandeProduit> commandeProduits;

}
