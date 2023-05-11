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
@Table(name = "Commande")
public class Commande extends AbstractEntity {
    @Column(name = "nomcommande")
    private String nomcommande;

    @Column(name = "codeCommande")
    private String codeCommande;

    @Column(name = "datecommande")
    private String datecommande;

    @Column(name = "produitcommande")
    private String produitcommande;

    @Column(name = "statutcommande")
    private String statutcommande;

    @ManyToOne
    @JoinColumn (name = "idclient")
    private Client client;

//    @ManyToOne
//    @JoinColumn (name = "produit")
//    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idlivraison")
    private Livraison livraison;

    @ManyToOne
    @JoinColumn(name = "idpaiement")
    private Paiement paiement;

    @OneToMany(mappedBy = "commande")
    private List<CommandeProduit> commandeProduits;

}
