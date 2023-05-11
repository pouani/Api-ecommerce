package com.stage.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Panier")
public class Panier extends AbstractEntity {

    @Column(name = "produitajoute")
    private String produitajoute;

    @Column(name = "quantiteajoute")
    private String quantiteajoute;

    //@OneToMany(mappedBy = "produit")
    //private List<Produit> produit;

    @OneToMany(mappedBy = "panier")
    private List<Panierproduit> panierproduits;


}
