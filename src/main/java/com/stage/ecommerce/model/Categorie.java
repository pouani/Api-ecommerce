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
@Table(name = "Categorie")
public class Categorie extends AbstractEntity{

    @Column(name = "nomCategorie")
    private String nomCategorie;

    @Column(name = "designation")
    private String description;

    @Column(name = "codeCategorie")
    private String codeCategorie;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produit;

}

