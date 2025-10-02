package com.example.proyecto.controller;

import com.example.proyecto.model.Estado;
import com.example.proyecto.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService service;

    @GetMapping
    public List<Estado> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerPorId(@PathVariable Integer id) {
        Estado estado = service.obtenerPorId(id);
        return estado != null ? ResponseEntity.ok(estado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Estado crear(@RequestBody Estado e) {
        return service.crear(e);
    }

    @PutMapping
    public Estado actualizar(@RequestBody Estado e) {
        return service.actualizar(e);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
