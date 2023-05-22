package com.stage.ecommerce.model;

import lombok.*;

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

    private String nom;
    //@OneToMany(mappedBy = "produit")
    //private List<Produit> produit;

    @OneToMany(mappedBy = "panier")
    private List<Panierproduit> panierproduits;


}
