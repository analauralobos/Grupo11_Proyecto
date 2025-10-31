package com.example.proyecto.dao;

import com.example.proyecto.interfaces.ITurnoDAO;
import com.example.proyecto.model.Turno;
import com.example.proyecto.sql2o.Sql2oDAO;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDAO implements ITurnoDAO {

    @Override
    public Turno crear(Turno turno) {
        String sql = "INSERT INTO turno (dni_medico, dni_paciente, fecha_turno, hora_turno, id_estado, motivo) " +
                     "VALUES (:dniMedico, :dniPaciente, :fechaTurno, :horaTurno, :idEstado, :motivo)";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            // Si el turno tiene fecha en formato texto tipo "2025-10-30T08:00"
            Timestamp fecha = Timestamp.valueOf(turno.getFechaTurno());


            Object keyObj = con.createQuery(sql, true)
                    .addParameter("dniMedico", turno.getDniMedico())
                    .addParameter("dniPaciente", turno.getDniPaciente())
                    .addParameter("fechaTurno", fecha)
                    .addParameter("horaTurno", Time.valueOf(turno.getHoraTurno()))
                    .addParameter("idEstado", turno.getIdEstado())
                    .addParameter("motivo", turno.getMotivo())
                    .executeUpdate()
                    .getKey();

            turno.setIdTurno((keyObj instanceof Number) ? ((Number) keyObj).intValue() : null);
            return turno;
        }
    }

    @Override
    public List<Turno> obtenerTodos() {
        String sql = "SELECT id_turno AS idTurno, dni_medico AS dniMedico, dni_paciente AS dniPaciente, " +
                     "fecha_turno AS fechaTurno, hora_turno AS horaTurno, id_estado AS idEstado, motivo " +
                     "FROM turno";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Turno.class);
        }
    }

    @Override
    public Turno obtenerPorId(Integer id) {
        String sql = "SELECT id_turno AS idTurno, dni_medico AS dniMedico, dni_paciente AS dniPaciente, " +
                     "fecha_turno AS fechaTurno, hora_turno AS horaTurno, id_estado AS idEstado, motivo " +
                     "FROM turno WHERE id_turno = :id";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Turno.class);
        }
    }

    @Override
    public Turno actualizar(Turno turno) {
        String sql = "UPDATE turno SET dni_medico = :dniMedico, dni_paciente = :dniPaciente, fecha_turno = :fechaTurno, " +
                     "hora_turno = :horaTurno, id_estado = :idEstado, motivo = :motivo WHERE id_turno = :idTurno";

        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Timestamp fechaStr = Timestamp.valueOf(turno.getFechaTurno());

            
            con.createQuery(sql)
                    .addParameter("dniMedico", turno.getDniMedico())
                    .addParameter("dniPaciente", turno.getDniPaciente())
                    .addParameter("fechaTurno", fechaStr)
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
        String sql = "DELETE FROM turno WHERE id_turno = :id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

    // Turnos por Médico y Fecha de atención
    public List<Turno> obtenerPorMedicoYFecha(Integer dniMedico, String fecha) {        
            String sqlTurnos = "SELECT " +
                "id_turno AS idTurno, " +
                "dni_paciente AS dniPaciente, " +
                "dni_medico AS dniMedico, " +
                "fecha_turno AS fechaTurno, " +
                "hora_turno AS horaTurno, " +
                "id_estado AS idEstado, " +
                "motivo AS motivo " +
                "FROM turno " +
                "WHERE dni_medico = :dniMedico AND DATE(fecha_turno) = DATE(:fecha)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sqlTurnos)
                .addParameter("dniMedico", dniMedico)
                .addParameter("fecha", fecha)
                .executeAndFetch(Turno.class);
        }
    }
    

}
