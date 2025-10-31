package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IMedicoDAO;
import com.example.proyecto.model.Medico;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import com.example.proyecto.sql2o.Sql2oDAO;
import java.util.List;


@Repository
public class MedicoDAO implements IMedicoDAO {

    @Override
    public Medico crear(Medico medico) {
        String sql = "INSERT INTO medico (dni_medico, matricula, nombre, apellido, especialidad, telefono, email, dias_atencion, hora_inicio, hora_fin)"
            +"VALUES (:dniMedico, :matricula, :nombre, :apellido, :especialidad, :telefono, :email, :diasAtencion, :horaInicio, :horaFin)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniMedico", medico.getDniMedico())
               .addParameter("matricula", medico.getMatricula())
               .addParameter("nombre", medico.getNombre())
               .addParameter("apellido", medico.getApellido())
               .addParameter("especialidad", medico.getEspecialidad())
               .addParameter("telefono", medico.getTelefono())
               .addParameter("email", medico.getEmail())
               .addParameter("diasAtencion", medico.getDiasAtencion())
               .addParameter("horaInicio", medico.getHoraInicio())
               .addParameter("horaFin", medico.getHoraFin())
               .executeUpdate();
            return medico;
        }
    }

    @Override
    public List<Medico> obtenerTodos() {
        String sql = "SELECT dni_medico AS dniMedico, matricula, nombre, apellido, especialidad, telefono, email,dias_atencion AS diasAtencion,"
        +"hora_inicio AS horaInicio, hora_fin AS horaFin FROM medico";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Medico.class);
        }
    }

    @Override
    public Medico obtenerPorId(Integer dniMedico) {
        String sql = "SELECT dni_medico AS dniMedico, matricula, nombre, apellido, especialidad, telefono, email, dias_atencion AS diasAtencion,"
        +"hora_inicio AS horaInicio, hora_fin AS horaFin FROM medico WHERE dni_medico = :dniMedico ";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("dniMedico", dniMedico)
                      .executeAndFetchFirst(Medico.class);
        }
    }

    @Override
    public Medico actualizar(Medico medico) {
        String sql = " UPDATE medico SET matricula = :matricula,nombre = :nombre,apellido = :apellido,"
               + " especialidad = :especialidad, telefono = :telefono, email = :email, dias_atencion = :diasAtencion,"
                + "hora_inicio = :horaInicio, hora_fin = :horaFin WHERE dni_medico = :dniMedico";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniMedico", medico.getDniMedico())
               .addParameter("matricula", medico.getMatricula())
               .addParameter("nombre", medico.getNombre())
               .addParameter("apellido", medico.getApellido())
               .addParameter("especialidad", medico.getEspecialidad())
               .addParameter("telefono", medico.getTelefono())
               .addParameter("email", medico.getEmail())
               .addParameter("diasAtencion", medico.getDiasAtencion())
               .addParameter("horaInicio", medico.getHoraInicio())
               .addParameter("horaFin", medico.getHoraFin())
               .executeUpdate();
            return medico;
        }
    }

    @Override
    public void eliminar(Integer dniMedico) {
        String sql = "DELETE FROM medico WHERE dni_medico = :dniMedico";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dniMedico", dniMedico)
               .executeUpdate();
        }
    }
}
