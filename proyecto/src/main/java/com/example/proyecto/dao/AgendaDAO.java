package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IAgendaDAO;
import com.example.proyecto.model.Agenda;
import org.springframework.stereotype.Repository;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import java.util.List;

@Repository
public class AgendaDAO implements IAgendaDAO {

    @Override
    public Agenda crear(Agenda agenda) {
        String sql = "INSERT INTO agenda (dni_medico, fecha_atencion, hora_inicio, hora_fin, estado_agenda, descripcion, ubicacion) " +
                     "VALUES (:dni_medico, :fecha_atencion, :hora_inicio, :hora_fin, :estado_agenda, :descripcion, :ubicacion)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Integer id = (Integer) con.createQuery(sql, true)
                    .addParameter("dni_medico", agenda.getDniMedico())
                    .addParameter("fecha_atencion", agenda.getFechaAtencion())
                    .addParameter("hora_inicio", agenda.getHoraInicio())
                    .addParameter("hora_fin", agenda.getHoraFin())
                    .addParameter("estado_agenda", agenda.getEstadoAgenda())
                    .addParameter("descripcion", agenda.getDescripcion())
                    .addParameter("ubicacion", agenda.getUbicacion())
                    .executeUpdate()
                    .getKey();
            agenda.setIdAgenda(id);
            return agenda;
        }
    }

    @Override
    public List<Agenda> obtenerTodos() {
        String sql = "SELECT * FROM agenda";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Agenda.class);
        }
    }

    @Override
    public Agenda obtenerPorId(Integer id) {
        String sql = "SELECT * FROM agenda WHERE id_agenda=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Agenda.class);
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
               .addParameter("fecha_atencion", agenda.getFechaAtencion())
               .addParameter("hora_inicio", agenda.getHoraInicio())
               .addParameter("hora_fin", agenda.getHoraFin())
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
}
