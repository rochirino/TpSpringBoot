package com.utn.productos_api.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(length = 500)
    private String descripcion;

    private Double precio;

    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}





