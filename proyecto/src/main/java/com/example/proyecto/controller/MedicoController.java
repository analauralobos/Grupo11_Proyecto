package com.example.proyecto.controller;

import com.example.proyecto.model.Medico;
import com.example.proyecto.model.Turno;
import com.example.proyecto.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService service;

    @GetMapping
    public List<Medico> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Integer dni) {
        Medico medico = service.obtenerPorId(dni);
        return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Medico crear(@RequestBody Medico medico) {
        return service.crear(medico);
    }

    @PutMapping
    public Medico actualizar(@RequestBody Medico medico) {
        return service.actualizar(medico);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer dni) {
        service.eliminar(dni);
        return ResponseEntity.noContent().build();
    }

    // ----- VER AGENDA -----
    @GetMapping("/{dni}/agenda")
    public ResponseEntity<?> verAgenda(@PathVariable Integer dni, @RequestParam("fecha") String fechaStr) 
    {
        try {
            // 1. Parsear la fecha
            LocalDate fecha = LocalDate.parse(fechaStr);            
            List<Turno> agenda = service.verAgenda(dni, fecha);
            return ResponseEntity.ok(agenda); // 200 OK con lista

        } catch (RuntimeException e) {
            // Si el service lanza una excepción (médico no existe o no trabaja)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Error de formato o cualquier otro
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-mm-dd");
        }
    }


}
