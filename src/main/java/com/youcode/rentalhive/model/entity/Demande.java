package com.youcode.rentalhive.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youcode.rentalhive.model.enums.demande.StatutDemande;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemande;

    @Column(unique = true)
    private String reference;

    private StatutDemande statutDemande;

    @OneToMany(mappedBy = "demande")
    private List<MaterielDemande> materielDemandeList;

    @ManyToOne
    private Utilisateur utilisateur;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
