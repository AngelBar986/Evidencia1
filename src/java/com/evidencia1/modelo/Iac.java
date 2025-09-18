package com.evidencia1.modelo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/** Utilidad para calcular IAC y su categoría. */
public class Iac {

    private static final MathContext MC = new MathContext(12, RoundingMode.HALF_UP);

    /**
     * IAC = (cadera_cm / (est_m * sqrt(est_m))) - 18
     */
    public BigDecimal calcular(BigDecimal estaturaM, BigDecimal caderaCm) {
        if (estaturaM == null || caderaCm == null)
            throw new IllegalArgumentException("Estatura y cadera no pueden ser nulas");
        if (estaturaM.compareTo(BigDecimal.ZERO) <= 0 || caderaCm.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Estatura/Cadera inválidas");

        BigDecimal sqrt = sqrt(estaturaM);
        BigDecimal denom = estaturaM.multiply(sqrt, MC);
        BigDecimal iac = caderaCm.divide(denom, MC).subtract(new BigDecimal("18"), MC);
        return iac.setScale(2, RoundingMode.HALF_UP);
    }

    /** Uma categorización simple; ajusta si tu profe pide otros rangos. */
    public String categoria(String sexo, BigDecimal iac) {
        if (iac == null) return "N/D";
        // normalizamos sexo a 'H' o 'M'
        String s = (sexo == null) ? "" : sexo.trim().toUpperCase();
        if (s.startsWith("H")) s = "H"; // Hombre
        if (s.startsWith("M")) s = "M"; // Mujer

        double v = iac.doubleValue();
        // ejemplo idéntico para ambos sexos (puedes diferenciar si quieres):
        if (v < 8)  return "Bajo";
        if (v < 21) return "Normal";
        if (v < 26) return "Alto";
        return "Muy alto";
    }

    // ---- sqrt BigDecimal: Newton-Raphson
    private static BigDecimal sqrt(BigDecimal x) {
        BigDecimal g = x.divide(new BigDecimal("2"), MC);
        for (int i = 0; i < 20; i++) {
            g = g.add(x.divide(g, MC), MC).divide(new BigDecimal("2"), MC);
        }
        return g;
    }
}
