package com.utn.productos_api.service;



import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.exception.StockInsuficienteException;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    // Crear producto
    public Producto crearProducto(Producto producto) {
        return repository.save(producto);
    }

    // Obtener todos
    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    // Obtener por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    // Obtener por categoría
    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return repository.findByCategoria(categoria);
    }

    // Actualizar producto completo
    public Producto actualizarProducto(Long id, Producto productoActualizado) {

        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());

        return repository.save(productoExistente);
    }

    // Actualizar solo el stock
    public Producto actualizarStock(Long id, Integer nuevoStock) {

        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        if (nuevoStock < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo");

        }

        productoExistente.setStock(nuevoStock);
        return repository.save(productoExistente);
    }

    // Eliminar producto por ID
    public void eliminarProducto(Long id) {

        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        repository.delete(productoExistente);
    }
}
