package com.example.proyecto.controller;

import com.example.proyecto.model.Turno;
import com.example.proyecto.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService service;

    @GetMapping
    public List<Turno> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerPorId(@PathVariable Integer id) {
        Turno turno = service.obtenerPorId(id);
        return turno != null ? ResponseEntity.ok(turno) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearTurno(@RequestBody Turno turno) {
        try {
            Turno nuevoTurno = service.crear(turno);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTurno);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public Turno actualizar(@RequestBody Turno turno) {
        return service.actualizar(turno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
