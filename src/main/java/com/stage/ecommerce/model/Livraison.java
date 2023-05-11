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
@Table(name = "livraison")
public class Livraison extends AbstractEntity {

    @Column(name = "adresselivraison")
    private String adresselivraison;

    @Column(name = "modelivraison")
    private String modelivraison;

    @Column(name = "fraislivraison")
    private String fraislivraison;

    @OneToMany (mappedBy = "livraison")
    private List<Commande> commande;

    

}
