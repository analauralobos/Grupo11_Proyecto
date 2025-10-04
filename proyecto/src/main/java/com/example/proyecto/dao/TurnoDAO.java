package com.example.proyecto.dao;

import com.example.proyecto.interfaces.ITurnoDAO;
import com.example.proyecto.model.Turno;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TurnoDAO implements ITurnoDAO {

    @Override
    public Turno crear(Turno turno) {
        String sql = "INSERT INTO turno (id_agenda, dni_paciente, fecha_turno, hora_turno, id_estado, motivo) " +
                     "VALUES (:idAgenda, :dniPaciente, :fechaTurno, :horaTurno, :idEstado, :motivo)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {

            Object keyObj = con.createQuery(sql, true)
                    .addParameter("idAgenda", turno.getIdAgenda())
                    .addParameter("dniPaciente", Integer.parseInt(turno.getDniPaciente()))
                    .addParameter("fechaTurno", Timestamp.valueOf(turno.getFechaTurno().replace("T", " ")))
                    .addParameter("horaTurno", Time.valueOf(turno.getHoraTurno()))
                    .addParameter("idEstado", turno.getIdEstado())
                    .addParameter("motivo", turno.getMotivo())
                    .executeUpdate()
                    .getKey();

            turno.setIdTurno((keyObj instanceof Number) ? ((Number) keyObj).intValue() : null);
            return turno;
        }
    }

    //validacion distinto paciente + misma agenda pero distinto horario

   
    public boolean existeTurnoEnHorario(Integer idAgenda, String fechaTurno, String horaTurno) {
    String sql = "SELECT COUNT(*) FROM turno " +
                 "WHERE id_agenda = :idAgenda AND DATE(fecha_turno) = DATE(:fechaTurno) " +
                 "AND hora_turno = :horaTurno";
    try (Connection con = Sql2oDAO.getSql2o().open()) {
        long count = con.createQuery(sql)
                .addParameter("idAgenda", idAgenda)
                .addParameter("fechaTurno", fechaTurno.replace("T", " "))
                .addParameter("horaTurno", horaTurno)
                .executeScalar(Long.class);
        return count > 0;
    }
}
















    @Override
    public List<Turno> obtenerTodos() {
        String sql = "SELECT * FROM turno";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Map<String, Object>> rows = con.createQuery(sql).executeAndFetchTable().asList();
            List<Turno> lista = new ArrayList<>();

            for (Map<String, Object> row : rows) {
                Turno t = new Turno();
                t.setIdTurno(((Number) row.get("id_turno")).intValue());
                t.setIdAgenda((Integer) row.get("id_agenda"));
                t.setDniPaciente(String.valueOf(row.get("dni_paciente")));
                t.setFechaTurno(row.get("fecha_turno").toString());
                t.setHoraTurno(row.get("hora_turno").toString());
                t.setIdEstado((Integer) row.get("id_estado"));
                t.setMotivo((String) row.get("motivo"));
                lista.add(t);
            }
            return lista;
        }
    }

    @Override
    public Turno obtenerPorId(Integer id) {
        String sql = "SELECT * FROM turno WHERE id_turno = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            List<Map<String, Object>> rows = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchTable()
                    .asList();
            if (rows.isEmpty()) return null;

            Map<String, Object> row = rows.get(0);
            Turno t = new Turno();
            t.setIdTurno(((Number) row.get("id_turno")).intValue());
            t.setIdAgenda((Integer) row.get("id_agenda"));
            t.setDniPaciente(String.valueOf(row.get("dni_paciente")));
            t.setFechaTurno(row.get("fecha_turno").toString());
            t.setHoraTurno(row.get("hora_turno").toString());
            t.setIdEstado((Integer) row.get("id_estado"));
            t.setMotivo((String) row.get("motivo"));
            return t;
        }
    }

    
    @Override
    public Turno actualizar(Turno turno) {
        String sql = "UPDATE turno SET id_agenda=:idAgenda, dni_paciente=:dniPaciente, " +
                 "fecha_turno=:fechaTurno, hora_turno=:horaTurno, id_estado=:idEstado, motivo=:motivo " +
                 "WHERE id_turno=:idTurno";
        try (Connection con = Sql2oDAO.getSql2o().open()) {

        
        String fechaHora = turno.getFechaTurno().replace("T", " ");
        if (fechaHora.length() == 16) { 
            fechaHora += ":00";
        }

        con.createQuery(sql)
                .addParameter("idAgenda", turno.getIdAgenda())
                .addParameter("dniPaciente", Integer.parseInt(turno.getDniPaciente()))
                .addParameter("fechaTurno", Timestamp.valueOf(fechaHora))
                .addParameter("horaTurno", Time.valueOf(turno.getHoraTurno()))
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
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }
}
