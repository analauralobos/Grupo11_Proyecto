package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IRegistroMedicoDAO;
import com.example.proyecto.model.RegistroMedico;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegistroMedicoDAO implements IRegistroMedicoDAO {

    @Override
    public RegistroMedico crear(RegistroMedico rm) {
        String sql = " INSERT INTO registro_medico (id_turno, id_medico, id_historiaclinica, diagnostico, tratamiento, estudios)"
            +"VALUES (:id_turno, :id_medico, :id_historiaclinica, :diagnostico, :tratamiento, :estudios)";

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

            if (keyObj instanceof Number) {
                rm.setIdRegistroMedico(((Number) keyObj).intValue());
            }

            System.out.println("Registro médico creado con ID: " + rm.getIdRegistroMedico());
            return rm;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el registro médico: " + e.getMessage(), e);
        }
    }

    @Override
    public List<RegistroMedico> obtenerTodos() {
        String sql = "SELECT * FROM registro_medico";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<RegistroMedico> lista = con.createQuery(sql)
                    .executeAndFetch(RegistroMedico.class);

            System.out.println("Total registros médicos obtenidos: " + lista.size());
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener registros médicos: " + e.getMessage(), e);
        }
    }

    @Override
    public RegistroMedico obtenerPorId(Integer id) {
        String sql = "SELECT * FROM registro_medico WHERE id_registromedico = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            RegistroMedico rm = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(RegistroMedico.class);

            if (rm != null)
                System.out.println("Registro médico encontrado: " + rm.getIdRegistroMedico());
            else
                System.out.println("No se encontró el registro médico con ID: " + id);

            return rm;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el registro médico: " + e.getMessage(), e);
        }
    }

    @Override
    public RegistroMedico actualizar(RegistroMedico rm) {
        String sql = " UPDATE registro_medico SET id_turno = :id_turno,id_medico = :id_medico, id_historiaclinica = :id_historiaclinica,"
                + "diagnostico = :diagnostico, tratamiento = :tratamiento, estudios = :estudios WHERE id_registromedico = :id ";

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
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el registro médico: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM registro_medico WHERE id_registromedico = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

            System.out.println("Registro médico eliminado: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el registro médico: " + e.getMessage(), e);
        }
    }
}
