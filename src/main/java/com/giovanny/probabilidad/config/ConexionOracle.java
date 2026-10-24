package com.giovanny.probabilidad.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionOracle {

    // cambia "ORCL" por el service_name configurado en tu instalacion.
    // ejemplo alternativo= jdbc:oracle:thin:@//localhost:1521/FREEPDB1
    private static final String URL =
            "jdbc:oracle:thin:@//localhost:1521/orcl";

    private static final String USUARIO = "system";
    private static final String CLAVE = "Tapiero123";

    private ConexionOracle() {

    }

    public static Connection obtenerConexion() throws SQLException
    {
       return DriverManager.getConnection(URL, USUARIO, CLAVE); 
    }
}
