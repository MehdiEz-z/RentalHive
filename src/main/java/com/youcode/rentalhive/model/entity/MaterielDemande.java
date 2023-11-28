package com.youcode.rentalhive.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
public class MaterielDemande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMaterielDemande;

    @ManyToOne
    private Materiel materiel;

    @JsonIgnore
    @ManyToOne
    private Demande demande;

    private LocalDate debutLocation;

    private LocalDate finLocation;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
