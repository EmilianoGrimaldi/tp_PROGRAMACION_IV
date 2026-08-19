package com.trabajopractico.fundamentosdespring.controllers;

import com.trabajopractico.fundamentosdespring.usuario.*;
import com.trabajopractico.fundamentosdespring.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioCreate usuarioCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioCreate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioEdit usuarioEdit, @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.update(usuarioEdit, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDto> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
