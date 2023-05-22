package com.stage.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    //@CreatedDate
    @Column(name = "creationDate")
    private Instant creationDate;

    //@LastModifiedDate
    @Column (name = "lastModifiedData")
    private Instant lastModifiedData;

    @PrePersist
    void prePersist(){
        creationDate = Instant.now();
        lastModifiedData = Instant.now();
    }

    @PreUpdate
    void preUpdate(){
        lastModifiedData = Instant.now();
    }
}



