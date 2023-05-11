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
    private String prixproduit;

   @Column(name = "codeproduit")
    private String codeProduit;

   @ManyToOne
   @JoinColumn(name = "idCategorie")
    private Categorie categorie;

   @ManyToOne
   @JoinColumn(name = "idpanier")
   private Panier panier;

   @OneToMany(mappedBy = "panier")
   private List<Panierproduit> panierproduits;

    @OneToMany(mappedBy = "produit")
   private List<CommandeProduit> commandeProduits;

}
