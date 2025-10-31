package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IPacienteDAO;
import com.example.proyecto.model.Paciente;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PacienteDAO implements IPacienteDAO {

    @Override
    public Paciente crear(Paciente paciente) {        
        String sql = " INSERT INTO paciente (dni_paciente, nombre, apellido, obra_social, fecha_nacimiento, telefono, email)"
           +" VALUES (:dniPaciente, :nombre, :apellido, :obraSocial, :fechaNacimiento, :telefono, :email)";
           Timestamp fecha = Timestamp.valueOf(paciente.getFechaNacimiento());

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniPaciente", paciente.getDniPaciente())
               .addParameter("nombre", paciente.getNombre())
               .addParameter("apellido", paciente.getApellido())
               .addParameter("obraSocial", paciente.getObraSocial())
               .addParameter("fechaNacimiento", fecha)
               .addParameter("telefono", paciente.getTelefono())
               .addParameter("email", paciente.getEmail())
               .executeUpdate();
            return paciente;
        }
    }

    @Override
    public List<Paciente> obtenerTodos() {
        String sql = " SELECT dni_paciente AS dniPaciente, nombre, apellido, obra_social AS obraSocial,"
          +"fecha_nacimiento AS fechaNacimiento, telefono, email FROM paciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Paciente.class);
        }
    }

    @Override
    public Paciente obtenerPorId(Integer dniPaciente) {
        String sql = "SELECT dni_paciente AS dniPaciente, nombre, apellido, obra_social AS obraSocial,"
            + "fecha_nacimiento AS fechaNacimiento, telefono, email FROM paciente WHERE dni_paciente = :dniPaciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("dniPaciente", dniPaciente)
                      .executeAndFetchFirst(Paciente.class);
        }
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nombre = :nombre, apellido = :apellido, obra_social = :obraSocial,"
         +"fecha_nacimiento = :fechaNacimiento, telefono = :telefono, email = :email WHERE dni_paciente = :dniPaciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniPaciente", paciente.getDniPaciente())
               .addParameter("nombre", paciente.getNombre())
               .addParameter("apellido", paciente.getApellido())
               .addParameter("obraSocial", paciente.getObraSocial())
               .addParameter("fechaNacimiento", paciente.getFechaNacimiento())
               .addParameter("telefono", paciente.getTelefono())
               .addParameter("email", paciente.getEmail())
               .executeUpdate();
            return paciente;
        }
    }

    @Override
    public void eliminar(Integer dniPaciente) {
        String sql = "DELETE FROM paciente WHERE dni_paciente = :dniPaciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniPaciente", dniPaciente)
               .executeUpdate();
        }
    }
}
