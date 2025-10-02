package com.example.proyecto.dao;

import com.example.proyecto.interfaces.IEstadoDAO;
import com.example.proyecto.model.Estado;
import org.sql2o.Connection;
import org.springframework.stereotype.Repository;
import com.example.proyecto.sql2o.Sql2oDAO;
import java.util.List;

@Repository
public class EstadoDAO implements IEstadoDAO {

    @Override
    public Estado crear(Estado estado) {
        String sql = "INSERT INTO estados (nombre_estado) VALUES (:nombre_estado)";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            Integer id = (Integer) con.createQuery(sql, true)
                    .addParameter("nombre_estado", estado.getNombreEstado())
                    .executeUpdate()
                    .getKey();
            estado.setIdEstado(id);
            return estado;
        }
    }

    @Override
    public List<Estado> obtenerTodos() {
        String sql = "SELECT * FROM estados";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql).executeAndFetch(Estado.class);
        }
    }

    @Override
    public Estado obtenerPorId(Integer id) {
        String sql = "SELECT * FROM estados WHERE id_estado=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            return con.createQuery(sql)
                      .addParameter("id", id)
                      .executeAndFetchFirst(Estado.class);
        }
    }

    @Override
    public Estado actualizar(Estado estado) {
        String sql = "UPDATE estados SET nombre_estado=:nombre_estado WHERE id_estado=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("nombre_estado", estado.getNombreEstado())
               .addParameter("id", estado.getIdEstado())
               .executeUpdate();
            return estado;
        }
    }

    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM estados WHERE id_estado=:id";
        try (Connection con = Sql2oDAO.getSql2o().open()) {
            con.createQuery(sql)
               .addParameter("id", id)
               .executeUpdate();
        }
    }
}
