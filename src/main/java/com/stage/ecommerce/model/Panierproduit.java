package com.stage.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "panierproduit")
public class Panierproduit extends AbstractEntity {


    @ManyToOne
    @JoinColumn(name = "Idproduit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "Idpanier")
    private Panier panier;

    @Column(name = "quantite")
    private Integer quantite;
}