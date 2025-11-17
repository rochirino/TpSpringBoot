package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para crear o actualizar un producto")
public class ProductoDTO {
    @NotBlank
    @Size(min = 3, max = 100)
    @Schema(description = "Nombre del producto", example = "Auriculares inalámbricos")
    private String nombre;

    @Size(max = 500)
    @Schema(description = "Descripción del producto", example = "Descripción detallada")
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(description = "Precio del producto", example = "199.99")
    private Double precio;

    @NotNull
    @Min(0)
    @Schema(description = "Stock disponible", example = "10")
    private Integer stock;

    @NotNull
    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private Categoria categoria;
}