package com.evidencia1.dao;

import com.evidencia1.modelo.ResultadoIac;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultadosIacDAO {

    public void insertar(ResultadoIac r) throws Exception {
        String sql = "INSERT INTO resultados_iac(" +
                     "nombre, edad, sexo, estatura_m, peso_kg, cadera_cm, iac, categoria" +
                     ") VALUES (?,?,?,?,?,?,?,?)";
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setInt(2, r.getEdad());
            ps.setString(3, r.getSexo());
            ps.setBigDecimal(4, r.getEstaturaM());
            if (r.getPesoKg() == null) {
                ps.setNull(5, Types.DECIMAL);
            } else {
                ps.setBigDecimal(5, r.getPesoKg());
            }
            ps.setBigDecimal(6, r.getCaderaCm());
            ps.setBigDecimal(7, r.getIac());
            ps.setString(8, r.getCategoria());
            ps.executeUpdate();
        }
    }

    public List<ResultadoIac> listar() throws Exception {
        String sql = "SELECT id, nombre, edad, sexo, estatura_m, peso_kg, " +
                     "cadera_cm, iac, categoria, fecha " +
                     "FROM resultados_iac " +
                     "ORDER BY fecha DESC, id DESC";
        List<ResultadoIac> lista = new ArrayList<>();
        try (Connection cn = Conexion.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ResultadoIac r = new ResultadoIac();
                r.setId(rs.getInt("id"));
                r.setNombre(rs.getString("nombre"));
                r.setEdad(rs.getInt("edad"));
                r.setSexo(rs.getString("sexo"));
                r.setEstaturaM(rs.getBigDecimal("estatura_m"));
                r.setPesoKg(rs.getBigDecimal("peso_kg"));
                r.setCaderaCm(rs.getBigDecimal("cadera_cm"));
                r.setIac(rs.getBigDecimal("iac"));
                r.setCategoria(rs.getString("categoria"));
                Timestamp ts = rs.getTimestamp("fecha");
                if (ts != null) r.setFecha(ts.toInstant());
                lista.add(r);
            }
        }
        return lista;
    }
}
