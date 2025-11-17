package com.utn.productos_api.controller;


import com.utn.productos_api.dto.*;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para gestión de productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por id")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Listar productos por categoría")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(service.obtenerPorCategoria(categoria));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo producto")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        ProductoResponseDTO created = service.crearProducto(dto);
        // Location header opcional
        return ResponseEntity.created(URI.create("/api/productos/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto completo")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        ProductoResponseDTO updated = service.actualizarProducto(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Actualizar solo el stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO dto) {
        ProductoResponseDTO updated = service.actualizarStock(id, dto.getStock());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
