package com.EcoMarketSPA.Carrito.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "carrito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "usuario_id", unique = true, nullable = false)
    private Integer usuarioId;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private double total;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    // Relación 1 a muchos con CarritoItem
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CarritoItem> items;
}