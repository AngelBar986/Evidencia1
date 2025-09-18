package com.evidencia1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
  // Ajusta user/pass a los tuyos
  private static final String URL =
      "jdbc:mariadb://localhost:3306/salud_iac"
      + "?useUnicode=true&characterEncoding=utf8"
      + "&useSSL=false&serverTimezone=UTC";

  private static final String USER = "iac_user";
  private static final String PASS = "iac_pass";

  static {
    try { Class.forName("org.mariadb.jdbc.Driver"); }
    catch (ClassNotFoundException ignored) {}
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
  }
}
