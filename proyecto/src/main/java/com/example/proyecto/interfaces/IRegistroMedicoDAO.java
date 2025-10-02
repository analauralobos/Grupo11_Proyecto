package com.example.proyecto.interfaces;

import com.example.proyecto.model.RegistroMedico;
import java.util.List;

public interface IRegistroMedicoDAO {
    List<RegistroMedico> obtenerTodos();
    RegistroMedico obtenerPorId(Integer id);
    RegistroMedico crear(RegistroMedico rm);
    RegistroMedico actualizar(RegistroMedico rm);
    void eliminar(Integer id);
}
