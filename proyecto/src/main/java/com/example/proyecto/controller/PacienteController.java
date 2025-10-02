package com.example.proyecto.controller;

import com.example.proyecto.model.Paciente;
import com.example.proyecto.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService service;

    @GetMapping
    public List<Paciente> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Paciente> obtenerPorId(@PathVariable Integer dni) {
        Paciente paciente = service.obtenerPorId(dni);
        return paciente != null ? ResponseEntity.ok(paciente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Paciente crear(@RequestBody Paciente paciente) {
        return service.crear(paciente);
    }

    @PutMapping
    public Paciente actualizar(@RequestBody Paciente paciente) {
        return service.actualizar(paciente);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer dni) {
        service.eliminar(dni);
        return ResponseEntity.noContent().build();
    }
}
