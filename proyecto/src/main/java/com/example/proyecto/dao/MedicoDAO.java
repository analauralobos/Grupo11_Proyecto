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
        // Ahora incluimos el DNI manualmente
        String sql = "INSERT INTO medico (dni_medico, nombre, apellido, especialidad, telefono, email) " +
                     "VALUES (:dni, :nombre, :apellido, :especialidad, :telefono, :email)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni", medico.getDniMedico()) // ahora se usa el DNI que viene del objeto
               .addParameter("nombre", medico.getNombre())
               .addParameter("apellido", medico.getApellido())
               .addParameter("especialidad", medico.getEspecialidad())
               .addParameter("telefono", medico.getTelefono())
               .addParameter("email", medico.getEmail())
               .executeUpdate();
            return medico;
        }
    }

    @Override
    public List<Medico> obtenerTodos() {
        String sql = "SELECT dni_medico AS dniMedico, nombre, apellido, especialidad, telefono, email FROM medico";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Medico.class);
        }
    }
    

    @Override
    public Medico obtenerPorId(Integer dni) {
    String sql = "SELECT dni_medico AS dniMedico, nombre, apellido, especialidad, telefono, email FROM medico WHERE dni_medico = :dni";
    try (Connection con = Sql2oDAO.getSql2o().open()) {
        return con.createQuery(sql)
                  .addParameter("dni", dni)
                  .executeAndFetchFirst(Medico.class);
    }
}


    @Override
    public Medico actualizar(Medico medico) {
        String sql = "UPDATE medico SET nombre=:nombre, apellido=:apellido, especialidad=:especialidad, " +
                     "telefono=:telefono, email=:email WHERE dni_medico=:dni";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("nombre", medico.getNombre())
               .addParameter("apellido", medico.getApellido())
               .addParameter("especialidad", medico.getEspecialidad())
               .addParameter("telefono", medico.getTelefono())
               .addParameter("email", medico.getEmail())
               .addParameter("dni", medico.getDniMedico())
               .executeUpdate();
            return medico;
        }
    }

    @Override
    public void eliminar(Integer dni) {
        String sql = "DELETE FROM medico WHERE dni_medico=:dni";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni", dni)
               .executeUpdate();
        }
    }
}
