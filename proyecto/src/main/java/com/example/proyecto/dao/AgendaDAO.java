package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IAgendaDAO;
import com.example.proyecto.model.Agenda;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AgendaDAO implements IAgendaDAO {

    @Override
    public Agenda crear(Agenda agenda) {
        String sql = "INSERT INTO agenda (dni_medico, fecha_atencion, hora_inicio, hora_fin, estado_agenda, descripcion, ubicacion) " +
                     "VALUES (:dni_medico, :fecha_atencion, :hora_inicio, :hora_fin, :estado_agenda, :descripcion, :ubicacion)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Object keyObj = con.createQuery(sql, true)
                    .addParameter("dni_medico", agenda.getDniMedico())
                    .addParameter("fecha_atencion", Timestamp.valueOf(agenda.getFechaAtencion().replace("T", " ")))
                    .addParameter("hora_inicio", Time.valueOf(agenda.getHoraInicio()))
                    .addParameter("hora_fin", Time.valueOf(agenda.getHoraFin()))
                    .addParameter("estado_agenda", agenda.getEstadoAgenda())
                    .addParameter("descripcion", agenda.getDescripcion())
                    .addParameter("ubicacion", agenda.getUbicacion())
                    .executeUpdate()
                    .getKey();

            agenda.setIdAgenda((keyObj instanceof Number) ? ((Number) keyObj).intValue() : null);
            return agenda;
        }
    }

    @Override
    public List<Agenda> obtenerTodos() {
        String sql = "SELECT * FROM agenda";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Map<String,Object>> rows = con.createQuery(sql).executeAndFetchTable().asList();
            List<Agenda> lista = new ArrayList<>();
            for(Map<String,Object> row : rows){
                lista.add(mapRowToAgenda(row));
            }
            return lista;
        }
    }

    @Override
    public Agenda obtenerPorId(Integer id) {
        String sql = "SELECT * FROM agenda WHERE id_agenda=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Map<String,Object> row = con.createQuery(sql)
                                        .addParameter("id", id)
                                        .executeAndFetchTable()
                                        .asList()
                                        .stream()
                                        .findFirst()
                                        .orElse(null);
            if(row == null) return null;
            return mapRowToAgenda(row);
        }
    }

    @Override
    public Agenda actualizar(Agenda agenda) {
        String sql = "UPDATE agenda SET dni_medico=:dni_medico, fecha_atencion=:fecha_atencion, hora_inicio=:hora_inicio, " +
                     "hora_fin=:hora_fin, estado_agenda=:estado_agenda, descripcion=:descripcion, ubicacion=:ubicacion " +
                     "WHERE id_agenda=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("dni_medico", agenda.getDniMedico())
               .addParameter("fecha_atencion", Timestamp.valueOf(agenda.getFechaAtencion().replace("T", " ")))
                .addParameter("hora_inicio", Time.valueOf(agenda.getHoraInicio()))
                .addParameter("hora_fin", Time.valueOf(agenda.getHoraFin()))

               .addParameter("estado_agenda", agenda.getEstadoAgenda())
               .addParameter("descripcion", agenda.getDescripcion())
               .addParameter("ubicacion", agenda.getUbicacion())
               .addParameter("id", agenda.getIdAgenda())
               .executeUpdate();
            return agenda;
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM agenda WHERE id_agenda=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id", id)
               .executeUpdate();
        }
    }

    // Método auxiliar para mapear una fila a Agenda
    private Agenda mapRowToAgenda(Map<String,Object> row){
        Agenda a = new Agenda();

        // Convertir Integer seguro desde Number
        a.setIdAgenda(row.get("id_agenda") != null ? ((Number) row.get("id_agenda")).intValue() : null);
        a.setDniMedico(row.get("dni_medico") != null ? ((Number) row.get("dni_medico")).intValue() : null);

        a.setFechaAtencion(row.get("fecha_atencion").toString().replace(" ", "T"));
        a.setHoraInicio(row.get("hora_inicio").toString());
        a.setHoraFin(row.get("hora_fin").toString());

        a.setEstadoAgenda((String) row.get("estado_agenda"));
        a.setDescripcion((String) row.get("descripcion"));
        a.setUbicacion((String) row.get("ubicacion"));
        return a;
    }
}
