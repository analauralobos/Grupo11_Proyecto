package com.example.proyecto.controller;

import com.example.proyecto.model.Agenda;
import com.example.proyecto.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/agendas")
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService service;

    @GetMapping
    public List<Agenda> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agenda> obtenerPorId(@PathVariable Integer id) {
        Agenda agenda = service.obtenerPorId(id);
        return agenda != null ? ResponseEntity.ok(agenda) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Agenda crear(@RequestBody Agenda agenda) {
        return service.crear(agenda);
    }

    @PutMapping
    public Agenda actualizar(@RequestBody Agenda agenda) {
        return service.actualizar(agenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
