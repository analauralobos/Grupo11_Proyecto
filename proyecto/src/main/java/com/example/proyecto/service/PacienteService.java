package com.example.proyecto.service;

import com.example.proyecto.dao.PacienteDAO;
import com.example.proyecto.dao.HistoriaClinicaDAO;
import com.example.proyecto.model.Paciente;
import com.example.proyecto.model.HistoriaClinica;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteDAO pacienteDAO;
    private final HistoriaClinicaDAO historiaClinicaDAO;

    public List<Paciente> obtenerTodos() {
        return pacienteDAO.obtenerTodos();
    }

    public Paciente obtenerPorId(Integer dni) {
        return pacienteDAO.obtenerPorId(dni);
    }

    public Paciente crear(Paciente paciente) {
        
        //Crear paciente
        /*Paciente pacienteCreado = pacienteDAO.crear(paciente);*/

        //crear paciente con crudDAO.. 
        boolean resultado = pacienteDAO.insert(paciente);        
        if (!resultado) throw new RuntimeException("Error al crear paciente.");
        //obtengo el paciente creado
        Paciente pacienteCreado = pacienteDAO.obtenerPorId(paciente.getDni_paciente());

        //Crear historia clínica inicial      
        HistoriaClinica hc = new HistoriaClinica();        
        hc.setDniPaciente(pacienteCreado.getDni_paciente());        
        hc.setAntecedentes("No registrados");
        hc.setAlergias("No registradas");

        historiaClinicaDAO.crear(hc);

        return pacienteCreado;
    }

    public Paciente actualizar(Paciente paciente) {
        return pacienteDAO.actualizar(paciente);
    }

    public void eliminar(Integer dni) {
        pacienteDAO.eliminar(dni);
    }
}
