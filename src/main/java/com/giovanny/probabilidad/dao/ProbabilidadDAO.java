package com.giovanny.probabilidad.dao;

import com.giovanny.probabilidad.config.ConexionOracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProbabilidadDAO {

    public void guardar(
            long favorables,
            long posibles,
            double probabilidad,
            String clasificacion,
            double pendiente,
            double intercepto
    ) throws SQLException {

        String sql = """
                INSERT INTO calculos_probabilidad
                (casos_favorables, casos_posibles, probabilidad, clasificacion,
                pendiente, intercepto)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection cn = ConexionOracle.obtenerConexion();
             PreparedStatement ps = cn.prepareStatement(sql))
        {
            ps.setLong(1, favorables);
            ps.setLong(2, posibles);
            ps.setDouble(3, probabilidad);
            ps.setString(4, clasificacion);
            ps.setDouble(5, pendiente);
            ps.setDouble(6, intercepto);

            ps.executeUpdate();
        }
    }
}   
