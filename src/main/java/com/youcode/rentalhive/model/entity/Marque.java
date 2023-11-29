package com.youcode.rentalhive.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
@Entity
public class Marque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMarque;

    @Column(unique = true)
    private String nomMarque;

    private String paysOrigine;

    @JsonIgnore
    @OneToMany(mappedBy = "marque")
    private List<Type> typeList;

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
