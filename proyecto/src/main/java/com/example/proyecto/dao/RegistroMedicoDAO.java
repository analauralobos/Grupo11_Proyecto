package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IRegistroMedicoDAO;
import com.example.proyecto.model.RegistroMedico;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RegistroMedicoDAO implements IRegistroMedicoDAO {

    @Override
    public RegistroMedico crear(RegistroMedico rm) {
        String sql = "INSERT INTO registro_medico (id_turno, id_medico, id_historiaclinica, diagnostico, tratamiento, estudios) " +
                     "VALUES (:id_turno, :id_medico, :id_historiaclinica, :diagnostico, :tratamiento, :estudios)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {

            Object keyObj = con.createQuery(sql, true)
                    .addParameter("id_turno", rm.getIdTurno())
                    .addParameter("id_medico", rm.getIdMedico())
                    .addParameter("id_historiaclinica", rm.getIdHistoriaClinica())
                    .addParameter("diagnostico", rm.getDiagnostico())
                    .addParameter("tratamiento", rm.getTratamiento())
                    .addParameter("estudios", rm.getEstudios())
                    .executeUpdate()
                    .getKey();

            // Convertir BigInteger a Integer
            rm.setIdRegistroMedico((keyObj instanceof Number) ? ((Number) keyObj).intValue() : null);

            System.out.println("Registro médico creado con ID: " + rm.getIdRegistroMedico());
            return rm;
        }
    }

    @Override
    public List<RegistroMedico> obtenerTodos() {
        String sql = "SELECT * FROM registro_medico";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Map<String, Object>> rows = con.createQuery(sql).executeAndFetchTable().asList();
            List<RegistroMedico> lista = new ArrayList<>();

            for (Map<String, Object> row : rows) {
                RegistroMedico rm = new RegistroMedico();
                rm.setIdRegistroMedico(((Number) row.get("id_registromedico")).intValue());
                rm.setIdTurno((Integer) row.get("id_turno"));
                rm.setIdMedico((Integer) row.get("id_medico"));
                rm.setIdHistoriaClinica((Integer) row.get("id_historiaclinica"));
                rm.setDiagnostico((String) row.get("diagnostico"));
                rm.setTratamiento((String) row.get("tratamiento"));
                rm.setEstudios((String) row.get("estudios"));
                lista.add(rm);
            }

            System.out.println("Total registros médicos obtenidos: " + lista.size());
            return lista;
        }
    }

    @Override
    public RegistroMedico obtenerPorId(Integer id) {
        String sql = "SELECT * FROM registro_medico WHERE id_registromedico = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Map<String, Object>> rows = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchTable()
                    .asList();
            if (rows.isEmpty()) return null;

            Map<String, Object> row = rows.get(0);
            RegistroMedico rm = new RegistroMedico();
            rm.setIdRegistroMedico(((Number) row.get("id_registromedico")).intValue());
            rm.setIdTurno((Integer) row.get("id_turno"));
            rm.setIdMedico((Integer) row.get("id_medico"));
            rm.setIdHistoriaClinica((Integer) row.get("id_historiaclinica"));
            rm.setDiagnostico((String) row.get("diagnostico"));
            rm.setTratamiento((String) row.get("tratamiento"));
            rm.setEstudios((String) row.get("estudios"));

            System.out.println("Registro médico encontrado: " + rm.getIdRegistroMedico());
            return rm;
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

            System.out.println("Registro médico actualizado: " + rm.getIdRegistroMedico());
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
            System.out.println("Registro médico eliminado: " + id);
        }
    }
}
