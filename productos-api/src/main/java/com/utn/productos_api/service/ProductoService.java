package com.utn.productos_api.service;



import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import com.utn.productos_api.exception.ProductoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Inyección por constructor (como pide la consigna)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Obtener por ID → retorna Optional (como pide la consigna)
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener por categoría
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // Actualizar producto completo
    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // Actualizar campos
        existente.setNombre(productoActualizado.getNombre());
        existente.setDescripcion(productoActualizado.getDescripcion());
        existente.setPrecio(productoActualizado.getPrecio());
        existente.setStock(productoActualizado.getStock());
        existente.setCategoria(productoActualizado.getCategoria());

        return productoRepository.save(existente);
    }

    // Actualizar solo el stock
    public Producto actualizarStock(Long id, Integer nuevoStock) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        existente.setStock(nuevoStock);
        return productoRepository.save(existente);
    }

    // Eliminar producto
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }
}