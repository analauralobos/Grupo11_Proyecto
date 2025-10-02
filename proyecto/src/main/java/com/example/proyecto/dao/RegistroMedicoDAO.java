package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IRegistroMedicoDAO;
import com.example.proyecto.model.RegistroMedico;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import com.example.proyecto.sql2o.Sql2oDAO;
import java.util.List;

@Repository
public class RegistroMedicoDAO implements IRegistroMedicoDAO {

    @Override
    public RegistroMedico crear(RegistroMedico rm) {
        String sql = "INSERT INTO registro_medico (id_turno, id_medico, id_historiaclinica, diagnostico, tratamiento, estudios) " +
                     "VALUES (:id_turno, :id_medico, :id_historiaclinica, :diagnostico, :tratamiento, :estudios)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Integer id = (Integer) con.createQuery(sql, true)
                    .addParameter("id_turno", rm.getIdTurno())
                    .addParameter("id_medico", rm.getIdMedico())
                    .addParameter("id_historiaclinica", rm.getIdHistoriaClinica())
                    .addParameter("diagnostico", rm.getDiagnostico())
                    .addParameter("tratamiento", rm.getTratamiento())
                    .addParameter("estudios", rm.getEstudios())
                    .executeUpdate()
                    .getKey();
            rm.setIdRegistroMedico(id);
            return rm;
        }
    }

    @Override
    public List<RegistroMedico> obtenerTodos() {
        String sql = "SELECT * FROM registro_medico";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(RegistroMedico.class);
        }
    }

    @Override
    public RegistroMedico obtenerPorId(Integer id) {
        String sql = "SELECT * FROM registro_medico WHERE id_registromedico=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(RegistroMedico.class);
        }
    }

    @Override
    public RegistroMedico actualizar(RegistroMedico rm) {
        String sql = "UPDATE registro_medico SET id_turno=:id_turno, id_medico=:id_medico, id_historiaclinica=:id_historiaclinica, " +
                     "diagnostico=:diagnostico, tratamiento=:tratamiento, estudios=:estudios WHERE id_registromedico=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id_turno", rm.getIdTurno())
               .addParameter("id_medico", rm.getIdMedico())
               .addParameter("id_historiaclinica", rm.getIdHistoriaClinica())
               .addParameter("diagnostico", rm.getDiagnostico())
               .addParameter("tratamiento", rm.getTratamiento())
               .addParameter("estudios", rm.getEstudios())
               .addParameter("id", rm.getIdRegistroMedico())
               .executeUpdate();
            return rm;
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM registro_medico WHERE id_registromedico=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id", id)
               .executeUpdate();
        }
    }
}
