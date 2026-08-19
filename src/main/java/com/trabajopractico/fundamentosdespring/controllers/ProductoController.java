package com.trabajopractico.fundamentosdespring.controllers;

import com.trabajopractico.fundamentosdespring.producto.ProductoCreate;
import com.trabajopractico.fundamentosdespring.producto.ProductoDto;
import com.trabajopractico.fundamentosdespring.producto.ProductoEdit;
import com.trabajopractico.fundamentosdespring.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;
    
    @PostMapping
    public ResponseEntity<ProductoDto> save (@RequestBody ProductoCreate productoCreate){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productoCreate));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(productoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> findAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> update(@RequestBody ProductoEdit productoEdit, @PathVariable Long id) {
        return ResponseEntity.ok(productoService.update(productoEdit, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
