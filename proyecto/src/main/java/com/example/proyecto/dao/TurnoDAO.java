package com.example.proyecto.dao;

import com.example.proyecto.interfaces.ITurnoDAO;
import com.example.proyecto.model.Turno;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class TurnoDAO implements ITurnoDAO {

    @Override
    public Turno crear(Turno turno) {
        String sql = "INSERT INTO turno (id_agenda, dni_paciente, fecha_turno, hora_turno, id_estado, motivo) " +
                     "VALUES (:idAgenda, :dniPaciente, :fechaTurno, :horaTurno, :idEstado, :motivo)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Integer id = (Integer) con.createQuery(sql, true)
                .addParameter("idAgenda", turno.getIdAgenda())
                .addParameter("dniPaciente", turno.getDniPaciente())
                .addParameter("fechaTurno", Timestamp.valueOf(turno.getFechaTurno()))
                .addParameter("horaTurno", turno.getHoraTurno())
                .addParameter("idEstado", turno.getIdEstado())
                .addParameter("motivo", turno.getMotivo())
                .executeUpdate()
                .getKey();
            turno.setIdTurno(id);
            return turno;
        }
    }

    @Override
    public List<Turno> obtenerTodos() {
        String sql = "SELECT id_turno AS idTurno, id_agenda AS idAgenda, dni_paciente AS dniPaciente, " +
                     "fecha_turno AS fechaTurno, hora_turno AS horaTurno, id_estado AS idEstado, motivo " +
                     "FROM turno";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .executeAndFetch(Turno.class); // mapeo automático por alias
        }
    }

    @Override
    public Turno obtenerPorId(Integer id) {
        String sql = "SELECT id_turno AS idTurno, id_agenda AS idAgenda, dni_paciente AS dniPaciente, " +
                     "fecha_turno AS fechaTurno, hora_turno AS horaTurno, id_estado AS idEstado, motivo " +
                     "FROM turno WHERE id_turno = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Turno.class); // mapeo automático
        }
    }

    @Override
    public Turno actualizar(Turno turno) {
        String sql = "UPDATE turno SET id_agenda=:idAgenda, dni_paciente=:dniPaciente, " +
                     "fecha_turno=:fechaTurno, hora_turno=:horaTurno, id_estado=:idEstado, motivo=:motivo " +
                     "WHERE id_turno=:idTurno";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("idAgenda", turno.getIdAgenda())
               .addParameter("dniPaciente", turno.getDniPaciente())
               .addParameter("fechaTurno", Timestamp.valueOf(turno.getFechaTurno()))
               .addParameter("horaTurno", turno.getHoraTurno())
               .addParameter("idEstado", turno.getIdEstado())
               .addParameter("motivo", turno.getMotivo())
               .addParameter("idTurno", turno.getIdTurno())
               .executeUpdate();
            return turno;
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM turno WHERE id_turno=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id", id)
               .executeUpdate();
        }
    }
}
