package com.example.proyecto.dao;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.sql2o.Connection;
import org.sql2o.Query;

import com.example.proyecto.sql2o.Sql2oDAO;

public abstract class CrudDAO<T> {

    public abstract Class<T> getTClass();
    public abstract String getTableName();
    public abstract String getTablePK();

    // Indica si el PK es autoincremental
    public abstract boolean isPKAutoIncrement();

    public boolean insert(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();

            StringBuilder columnas = new StringBuilder("(");
            StringBuilder valores = new StringBuilder("(");

            int count = 0;
            for (Field field : fields) {
                String name = field.getName();

                // Solo saltar PK si es autoincremental
                if (name.equalsIgnoreCase(getTablePK()) && isPKAutoIncrement()) continue;

                if (count > 0) {
                    columnas.append(", ");
                    valores.append(", ");
                }

                columnas.append(name);
                valores.append(":").append(name);
                count++;
            }

            columnas.append(")");
            valores.append(")");

            String sql = "INSERT INTO " + getTableName() + " " + columnas + " VALUES " + valores;

            try (Connection con = Sql2oDAO.getSql2o().open()) {
                Query query = con.createQuery(sql);

                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(t);

                    if (value != null) {
                        String lowerName = field.getName().toLowerCase();

                        // Convertir fechas
                        if (lowerName.contains("fecha")) {
                            if (value instanceof LocalDate) {
                                value = Date.valueOf((LocalDate) value);
                            } else if (value instanceof LocalDateTime) {
                                value = Timestamp.valueOf((LocalDateTime) value);
                            }
                        }
                        // Convertir horas
                        else if (lowerName.contains("hora")) {
                            if (value instanceof String) {
                                String strVal = (String) value;
                                if (strVal.length() == 5) strVal += ":00";
                                value = Time.valueOf(strVal);
                            }
                        }
                    }

                    // Solo agregar parámetros que existen en SQL
                    if (!isPKAutoIncrement() || !field.getName().equalsIgnoreCase(getTablePK())) {
                        query.addParameter(field.getName(), value);
                    } else if (!isPKAutoIncrement() && field.getName().equalsIgnoreCase(getTablePK())) {
                        // Agregar PK si no es autoincremental
                        query.addParameter(field.getName(), value);
                    }
                }

                query.executeUpdate();
                return true;
            }

        } catch (IllegalAccessException e) {
            System.err.println("Error al acceder a los campos de la entidad: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Error al ejecutar el insert: " + e.getMessage());
            return false;
        }
    }
}
