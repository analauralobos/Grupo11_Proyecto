package com.example.proyecto.controller;

import com.example.proyecto.model.RegistroMedico;
import com.example.proyecto.service.RegistroMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/registros-medicos")
@RequiredArgsConstructor
public class RegistroMedicoController {
    private final RegistroMedicoService service;

    @GetMapping
    public List<RegistroMedico> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroMedico> obtenerPorId(@PathVariable Integer id) {
        RegistroMedico rm = service.obtenerPorId(id);
        return rm != null ? ResponseEntity.ok(rm) : ResponseEntity.notFound().build();
    }
    
    @PostMapping("/registrar")
    public RegistroMedico registrarAtencion(@RequestBody RegistroMedico rm) {
        return service.registrarAtencion(rm);
    }

    @PostMapping
    public RegistroMedico crear(@RequestBody RegistroMedico rm) {
        return service.crear(rm);
    }

    @PutMapping
    public RegistroMedico actualizar(@RequestBody RegistroMedico rm) {
        return service.actualizar(rm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
