package com.example.proyecto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;

@Component
public class TestConexion implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try (Connection con = com.example.proyecto.sql2o.Sql2oDAO.getSql2o().open()) {
            Integer result = con.createQuery("SELECT 1").executeScalar(Integer.class);
            System.out.println("Conexión a la DB OK, resultado: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
