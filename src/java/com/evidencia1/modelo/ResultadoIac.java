package com.evidencia1.modelo;

import java.math.BigDecimal;
import java.time.Instant;

/** POJO para almacenar y transportar resultados de IAC. */
public class ResultadoIac {

    private Integer id;            // auto-increment en BD
    private String  nombre;
    private int     edad;
    private String  sexo;          // 'H' o 'M' (o "Hombre"/"Mujer")
    private BigDecimal estaturaM;  // m
    private BigDecimal caderaCm;   // cm
    private BigDecimal pesoKg;     // opcional
    private BigDecimal iac;        // resultado calculado
    private String  categoria;
    private Instant fecha;         // timestamp de BD

    public ResultadoIac() {}

    // ---------- getters / setters ----------
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public BigDecimal getEstaturaM() { return estaturaM; }
    public void setEstaturaM(BigDecimal estaturaM) { this.estaturaM = estaturaM; }

    public BigDecimal getCaderaCm() { return caderaCm; }
    public void setCaderaCm(BigDecimal caderaCm) { this.caderaCm = caderaCm; }

    public BigDecimal getPesoKg() { return pesoKg; }
    public void setPesoKg(BigDecimal pesoKg) { this.pesoKg = pesoKg; }

    public BigDecimal getIac() { return iac; }
    public void setIac(BigDecimal iac) { this.iac = iac; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Instant getFecha() { return fecha; }
    public void setFecha(Instant fecha) { this.fecha = fecha; }
}
