package com.stage.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "CommandeProduit")
public class CommandeProduit extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "idcommande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "idproduit")
    private Produit produit;

    @Column(name = "quantite")
    private Integer quantite;
}
