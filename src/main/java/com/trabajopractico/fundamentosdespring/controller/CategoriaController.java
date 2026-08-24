package com.trabajopractico.fundamentosdespring.controller;

import com.trabajopractico.fundamentosdespring.categoria.CategoriaCreate;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaDto;
import com.trabajopractico.fundamentosdespring.categoria.CategoriaEdit;
import com.trabajopractico.fundamentosdespring.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaCreate categoriaCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaCreate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@Valid @RequestBody CategoriaEdit categoriaEdit, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.update(categoriaEdit, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
