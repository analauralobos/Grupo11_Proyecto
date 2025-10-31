package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IHistoriaClinicaDAO;
import com.example.proyecto.model.HistoriaClinica;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoriaClinicaDAO implements IHistoriaClinicaDAO {

    @Override
    public HistoriaClinica crear(HistoriaClinica hc) {
        String sql = "INSERT INTO historia_clinica (dni_paciente, antecedentes, alergias)"
            +"VALUES ( :dni_paciente, :antecedentes, :alergias)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Object keyObj = con.createQuery(sql, true)                    
                    .addParameter("dni_paciente", hc.getDniPaciente())
                    .addParameter("antecedentes", hc.getAntecedentes())
                    .addParameter("alergias", hc.getAlergias())
                    .executeUpdate()
                    .getKey();

            if (keyObj instanceof Number) {
                hc.setIdHistoriaClinica(((Number) keyObj).intValue());
            }

            System.out.println("Historia clínica creada con ID: " + hc.getIdHistoriaClinica());
            return hc;
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la historia clínica: " + e.getMessage(), e);
        }
    }

    @Override
    public List<HistoriaClinica> obtenerTodos() {
        
        String sql = " SELECT id_historiaclinica AS idHistoriaClinica, dni_paciente AS dniPaciente, antecedentes, alergias FROM historia_clinica";
     
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<HistoriaClinica> lista = con.createQuery(sql)
                    .executeAndFetch(HistoriaClinica.class);

            System.out.println("Total historias clínicas obtenidas: " + lista.size());
            return lista;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener las historias clínicas: " + e.getMessage(), e);
        }
    }

    @Override
    public HistoriaClinica obtenerPorId(Integer id) {
        String sql = "SELECT * FROM historia_clinica WHERE id_historiaclinica = :id";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            HistoriaClinica hc = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(HistoriaClinica.class);

            if (hc != null)
                System.out.println("Historia clínica encontrada: " + hc.getIdHistoriaClinica());
            else
                System.out.println("No se encontró la historia clínica con ID: " + id);

            return hc;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la historia clínica: " + e.getMessage(), e);
        }
    }

    @Override
    public HistoriaClinica actualizar(HistoriaClinica hc) {
        String sql = "UPDATE historia_clinica SET  dni_paciente = :dni_paciente, antecedentes = :antecedentes, "
                + "alergias = :alergias WHERE id_historiaclinica = :id ";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)                    
                    .addParameter("dni_paciente", hc.getDniPaciente())
                    .addParameter("antecedentes", hc.getAntecedentes())
                    .addParameter("alergias", hc.getAlergias())
                    .addParameter("id", hc.getIdHistoriaClinica())
                    .executeUpdate();

            System.out.println("Historia clínica actualizada: " + hc.getIdHistoriaClinica());
            return hc;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la historia clínica: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM historia_clinica WHERE id_historiaclinica = :id";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();

            System.out.println("Historia clínica eliminada: " + id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la historia clínica: " + e.getMessage(), e);
        }
    }
}
