package com.example.proyecto.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger registraLog = LoggerFactory.getLogger(MedicoController.class);

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
        registraLog.info("Solicitud recibida para ver agenda del médico con DNI {} en la fecha {}", dni, fechaStr);

        try {            
            LocalDate fecha = LocalDate.parse(fechaStr);
            registraLog.debug("Fecha parseada correctamente: {}", fecha);

            List<Turno> agenda = service.verAgenda(dni, fecha);
            registraLog.info("Agenda obtenida correctamente. Cantidad de turnos: {}", agenda.size());

            return ResponseEntity.ok(agenda); // 200 OK con lista

        } catch (RuntimeException e) {
            registraLog.warn("Error de negocio al obtener agenda del médico {}: {}", dni, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            registraLog.error("Error inesperado al procesar la solicitud de agenda del médico {}: {}", dni, e.getMessage());
            return ResponseEntity.badRequest().body("Formato de fecha inválido. Use yyyy-mm-dd");
        }
    }


}
