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
@Table(name = "Paiement")
public class Paiement extends AbstractEntity {

    @Column(name = "modeldepaiement")
    private String modeldepaiement;

    @Column(name = "codedepaiement")
    private String codedepaiement;

}