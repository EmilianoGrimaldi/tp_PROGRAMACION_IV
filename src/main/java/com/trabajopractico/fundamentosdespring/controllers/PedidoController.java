package com.trabajopractico.fundamentosdespring.controllers;

import com.trabajopractico.fundamentosdespring.models.Pedido;
import com.trabajopractico.fundamentosdespring.pedido.PedidoDto;
import com.trabajopractico.fundamentosdespring.pedido.PedidoEdit;
import com.trabajopractico.fundamentosdespring.service.PedidoService;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioCreate;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioDto;
import com.trabajopractico.fundamentosdespring.usuario.UsuarioEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDto> save(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> findById(@PathVariable Long id) {
        return  ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoDto>> findAll() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> update(@RequestBody PedidoEdit pedidoEdit, @PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.update(pedidoEdit, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDto> delete(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
