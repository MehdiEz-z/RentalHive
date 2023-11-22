package com.youcode.rentalhive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    private String image;

    @NotNull(message = "Init quantity cannot be null")
    @Min(value = 0, message = "Init quantity must be a positive number")
    private Integer initQuantity;

    private Integer availableQuantity;

    @NotNull(message = "Availability cannot be null")
    private Boolean available;

    @NotNull(message = "Daily rental cost cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Daily rental cost must be greater than 0")
    private Double dailyRentalCost;
}
