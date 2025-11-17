package com.utn.productos_api.dto;


import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO de respuesta de producto")
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Categoria categoria;
}
