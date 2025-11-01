package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IPacienteDAO;
import com.example.proyecto.model.Paciente;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PacienteDAO extends CrudDAO<Paciente> implements IPacienteDAO {

    String tablePK = "dni_paciente";
    String tableName = "paciente";

    @Override
    public Class<Paciente> getTClass() {
        return Paciente.class;
    }

    @Override
    public String getTablePK() {
        return tablePK;
    }

    @Override
    public String getTableName() {
        return tableName;
    }
    @Override
    public boolean isPKAutoIncrement() {
        return false; // porque dni_paciente no se genera automáticamente
    }

/*
    @Override
    public Paciente crear(Paciente paciente) {        
        String sql = " INSERT INTO paciente (dni_paciente, nombre, apellido, obra_social, fecha_nacimiento, telefono, email)"
           +" VALUES (:dni_paciente, :nombre, :apellido, :obra_social, :fecha_nacimiento, :telefono, :email)";
           Timestamp fecha = Timestamp.valueOf(paciente.getFecha_nacimiento());

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni_paciente", paciente.getDni_paciente())
               .addParameter("nombre", paciente.getNombre())
               .addParameter("apellido", paciente.getApellido())
               .addParameter("obra_social", paciente.getObra_social())
               .addParameter("fecha_nacimiento", fecha)
               .addParameter("telefono", paciente.getTelefono())
               .addParameter("email", paciente.getEmail())
               .executeUpdate();
            return paciente;
        }
    }
*/
    @Override
    public List<Paciente> obtenerTodos() {
        String sql = " SELECT dni_paciente AS dni_paciente, nombre, apellido, obra_social AS obra_social,"
          +"fecha_nacimiento AS fecha_nacimiento, telefono, email FROM paciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Paciente.class);
        }
    }

    @Override
    public Paciente obtenerPorId(Integer dni_paciente) {
        String sql = "SELECT dni_paciente AS dni_paciente, nombre, apellido, obra_social AS obra_social,"
            + "fecha_nacimiento AS fecha_nacimiento, telefono, email FROM paciente WHERE dni_paciente = :dni_paciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("dni_paciente", dni_paciente)
                      .executeAndFetchFirst(Paciente.class);
        }
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nombre = :nombre, apellido = :apellido, obra_social = :obra_social,"
         +"fecha_nacimiento = :fecha_nacimiento, telefono = :telefono, email = :email WHERE dni_paciente = :dni_paciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni_paciente", paciente.getDni_paciente())
               .addParameter("nombre", paciente.getNombre())
               .addParameter("apellido", paciente.getApellido())
               .addParameter("obra_social", paciente.getObra_social())
               .addParameter("fecha_nacimiento", paciente.getFecha_nacimiento())
               .addParameter("telefono", paciente.getTelefono())
               .addParameter("email", paciente.getEmail())
               .executeUpdate();
            return paciente;
        }
    }

    @Override
    public void eliminar(Integer dni_paciente) {
        String sql = "DELETE FROM paciente WHERE dni_paciente = :dni_paciente";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni_paciente", dni_paciente)
               .executeUpdate();
        }
    }
}
