package com.stage.ecommerce.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @Column(name ="nomclient")
    private String nomclient;

    @Column(name = "prenomclient")
    private String prenomclient;

    @Column(name = "telephoneclient")
    private String telephoneclient;

    @Column(name = "adresseclient")
    private String adresseClient;

    @OneToMany(mappedBy = "client")
    private List<Commande> commande;


    Client(Integer id, Instant creationDate, Instant lastModifiedData) {
        super(id, creationDate, lastModifiedData);
    }
}

