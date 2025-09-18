package com.evidencia1.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PingDbServlet2", urlPatterns = {"/pingdb2"})
public class PingDbServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");

        // Ajusta si tu base/usuario/clave son distintos:
        String url  = "jdbc:mariadb://localhost:3306/salud_iac?useSSL=false&serverTimezone=UTC";
        String user = "iac_user";
        String pass = "iac_pass";

        try (PrintWriter out = resp.getWriter()) {
            // Cargar el driver explícitamente (buena práctica con GF 5 + JDK 8)
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(url, user, pass)) {
                DatabaseMetaData md = con.getMetaData();
                out.println("OK: Conectado a MariaDB");
                out.println("DB: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
                out.println("Driver: " + md.getDriverName() + " " + md.getDriverVersion());
            }
        } catch (Exception e) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }
    }
}
