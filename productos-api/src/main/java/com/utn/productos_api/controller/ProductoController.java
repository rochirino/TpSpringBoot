package com.utn.productos_api.controller;


import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Productos",
        description = "Endpoints para la creación, consulta y modificación de productos de e-commerce.")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }


    private ProductoResponseDTO convertToDto(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    private Producto convertToEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    @Operation(summary = "Crear producto",
            description = "Crea un nuevo producto válido y retorna el producto creado.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos enviados",
                            content = @Content)
            })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto nuevoProducto = convertToEntity(productoDTO);
        Producto productoGuardado = service.crearProducto(nuevoProducto);

        return new ResponseEntity<>(convertToDto(productoGuardado), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar productos",
            description = "Lista todos los productos registrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada con éxito")
            })
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<Producto> productos = service.obtenerTodos();
        List<ProductoResponseDTO> response = productos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener producto por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto encontrado",
                            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(producto -> ResponseEntity.ok(convertToDto(producto)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Obtener productos por categoría",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Productos filtrados correctamente")
            })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(
            @PathVariable Categoria categoria) {

        List<Producto> productos = service.obtenerPorCategoria(categoria);
        List<ProductoResponseDTO> response = productos.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar producto completamente",
            description = "Actualiza todos los datos de un producto existente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {

        Producto productoActualizado = service.actualizarProducto(id, convertToEntity(productoDTO));
        return ResponseEntity.ok(convertToDto(productoActualizado));
    }

    @Operation(summary = "Actualizar stock",
            description = "Actualiza únicamente el stock de un producto.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock actualizado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
                    @ApiResponse(responseCode = "400", description = "Stock inválido")
            })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarStockDTO stockDTO) {

        Producto productoActualizado = service.actualizarStock(id, stockDTO.getStock());
        return ResponseEntity.ok(convertToDto(productoActualizado));
    }

    @Operation(summary = "Eliminar producto",
            description = "Elimina un producto por ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
