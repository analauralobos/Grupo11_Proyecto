package com.example.proyecto.controller;

import com.example.proyecto.model.HistoriaClinica;
import com.example.proyecto.service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/historias-clinicas")
@RequiredArgsConstructor
public class HistoriaClinicaController {
    private final HistoriaClinicaService service;

    @GetMapping
    public List<HistoriaClinica> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoriaClinica> obtenerPorId(@PathVariable Integer id) {
        HistoriaClinica hc = service.obtenerPorId(id);
        return hc != null ? ResponseEntity.ok(hc) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public HistoriaClinica crear(@RequestBody HistoriaClinica h) {
        return service.crear(h);
    }

    @PutMapping
    public HistoriaClinica actualizar(@RequestBody HistoriaClinica h) {
        return service.actualizar(h);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
