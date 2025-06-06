package com.EcoMarketSPA.Carrito.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "producto_id", nullable = false)
    private Integer productoId;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    // Relación muchos a 1 con Carrito
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    // Subtotal calculado dinámicamente
    public Double getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }
}