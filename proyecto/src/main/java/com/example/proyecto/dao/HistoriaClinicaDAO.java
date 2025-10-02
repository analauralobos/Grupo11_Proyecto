package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IHistoriaClinicaDAO;
import com.example.proyecto.model.HistoriaClinica;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import com.example.proyecto.sql2o.Sql2oDAO;
import java.util.List;

@Repository
public class HistoriaClinicaDAO implements IHistoriaClinicaDAO {

    @Override
    public HistoriaClinica crear(HistoriaClinica hc) {
        String sql = "INSERT INTO historia_clinica (fecha_creacion, alergia, antecedente, medicacion_actual, observacion, ultima_actualizacion, dni_paciente) " +
                     "VALUES (:fecha_creacion, :alergia, :antecedente, :medicacion_actual, :observacion, :ultima_actualizacion, :dni_paciente)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Integer id = (Integer) con.createQuery(sql, true)
                    .addParameter("fecha_creacion", hc.getFechaCreacion())
                    .addParameter("alergia", hc.getAlergia())
                    .addParameter("antecedente", hc.getAntecedente())
                    .addParameter("medicacion_actual", hc.getMedicacionActual())
                    .addParameter("observacion", hc.getObservacion())
                    .addParameter("ultima_actualizacion", hc.getUltimaActualizacion())
                    .addParameter("dni_paciente", hc.getDniPaciente())
                    .executeUpdate()
                    .getKey();
            hc.setIdHistoriaClinica(id);
            return hc;
        }
    }

    @Override
    public List<HistoriaClinica> obtenerTodos() {
        String sql = "SELECT * FROM historia_clinica";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(HistoriaClinica.class);
        }
    }

    @Override
    public HistoriaClinica obtenerPorId(Integer id) {
        String sql = "SELECT * FROM historia_clinica WHERE id_historiaclinica=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(HistoriaClinica.class);
        }
    }

    @Override
    public HistoriaClinica actualizar(HistoriaClinica hc) {
        String sql = "UPDATE historia_clinica SET fecha_creacion=:fecha_creacion, alergia=:alergia, antecedente=:antecedente, " +
                     "medicacion_actual=:medicacion_actual, observacion=:observacion, ultima_actualizacion=:ultima_actualizacion, " +
                     "dni_paciente=:dni_paciente WHERE id_historiaclinica=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("fecha_creacion", hc.getFechaCreacion())
               .addParameter("alergia", hc.getAlergia())
               .addParameter("antecedente", hc.getAntecedente())
               .addParameter("medicacion_actual", hc.getMedicacionActual())
               .addParameter("observacion", hc.getObservacion())
               .addParameter("ultima_actualizacion", hc.getUltimaActualizacion())
               .addParameter("dni_paciente", hc.getDniPaciente())
               .addParameter("id", hc.getIdHistoriaClinica())
               .executeUpdate();
            return hc;
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM historia_clinica WHERE id_historiaclinica=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id", id)
               .executeUpdate();
        }
    }
}
