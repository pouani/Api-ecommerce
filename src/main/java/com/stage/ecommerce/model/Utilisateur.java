package com.stage.ecommerce.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Utilisateur" )
public class Utilisateur extends AbstractEntity {

    @Column(name = "nomutilisateur")
    private String nomutilisateur;

    @Column(name = "penomutilisateur")
    private String prenomutilisateur;

    @Column(name = "email")
    private String email;

    @Column(name = "motsdepasse")
    private String motsdepasse;





}
