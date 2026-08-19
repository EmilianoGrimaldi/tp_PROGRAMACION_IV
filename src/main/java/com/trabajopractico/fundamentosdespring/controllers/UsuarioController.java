package com.trabajopractico.fundamentosdespring.controllers;

import com.trabajopractico.fundamentosdespring.usuario.*;
import com.trabajopractico.fundamentosdespring.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public UsuarioResponseDTO crear(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.save(dto);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
//        return  ResponseEntity.ok(usuarioService.findById(id));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UsuarioDto>> findAll() {
//        return ResponseEntity.ok(usuarioService.findAll());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UsuarioDto> update(@RequestBody UsuarioEdit usuarioEdit, @PathVariable Long id) {
//        return ResponseEntity.ok(usuarioService.update(usuarioEdit, id));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UsuarioDto> delete(@PathVariable Long id) {
//        usuarioService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
