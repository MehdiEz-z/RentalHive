package com.youcode.rentalhive.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.enums.materiel.EtatMateriel;
import com.youcode.rentalhive.model.enums.materiel.TypeCarburant;
import com.youcode.rentalhive.model.enums.materiel.TypePneu;
import com.youcode.rentalhive.model.enums.materiel.TypeTransmission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;

    private String matricule;

    private String description;

    private Double prixLocation;

    private int kilometrage;

    private int capaciteChargeMax;

    private EtatMateriel etatMateriel;

    private TypeCarburant typeCarburant;

    private TypePneu typePneu;

    private TypeTransmission typeTransmission;

    @ManyToOne
    private Type type;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
