package com.utn.productos_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO para actualizar solo el stock")
public class ActualizarStockDTO {
    @NotNull
    @Min(0)
    @Schema(description = "Nuevo stock", example = "5")
    private Integer stock;
}

