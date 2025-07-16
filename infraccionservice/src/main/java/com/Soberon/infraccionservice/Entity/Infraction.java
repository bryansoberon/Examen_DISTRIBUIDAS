package com.Soberon.infraccionservice.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "infractions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Infraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false)
    private String dni;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 20, nullable = false)
    private String tipoInfraccion;

    @Column(length = 200, nullable = false)
    private String ubicacion;

    @Column(length = 255)
    private String descripcion;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal montoMulta;

    @Column(length = 20, nullable = false)
    private String estado; // Ej: "Pagado", "No pagado", "Anulada"
}
