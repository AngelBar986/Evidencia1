package com.evidencia1.controlador;

import com.evidencia1.dao.ResultadosIacDAO;
import com.evidencia1.modelo.ResultadoIac;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet(name="CalcularIacServlet", urlPatterns={"/calcular"})
public class CalcularIacServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // Asegura UTF-8 aquí también por si el filtro no corre en algún entorno
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    List<String> errores = new ArrayList<>();

    // Obtén y preserva SIEMPRE los valores del formulario
    String vNombre = safe(req.getParameter("nombre"));
    String vEdad   = safe(req.getParameter("edad"));
    String vSexo   = safe(req.getParameter("sexo"));
    String vEst    = safe(req.getParameter("estatura"));
    String vCad    = safe(req.getParameter("cadera"));
    String vPeso   = safe(req.getParameter("peso")); // opcional

    req.setAttribute("fv_nombre", vNombre);
    req.setAttribute("fv_edad",   vEdad);
    req.setAttribute("fv_sexo",   vSexo);
    req.setAttribute("fv_est",    vEst);
    req.setAttribute("fv_cad",    vCad);
    req.setAttribute("fv_peso",   vPeso);

    try {
      // Validaciones suaves: nunca lances NPE
      if (vNombre.isEmpty()) errores.add("El nombre es obligatorio.");
      Integer edad = parseEntero(vEdad, "Edad", errores);

      BigDecimal est = parseDecimal(vEst,  "Estatura (m)", errores);
      BigDecimal cad = parseDecimal(vCad,  "Cadera (cm)",  errores);
      BigDecimal peso = vPeso.isEmpty() ? null : parseDecimal(vPeso, "Peso (kg)", errores);

      String sexo = vSexo == null ? "" : vSexo.trim();
      if (!(sexo.equalsIgnoreCase("H") || sexo.equalsIgnoreCase("M")
            || sexo.equalsIgnoreCase("Hombre") || sexo.equalsIgnoreCase("Mujer"))) {
        errores.add("Selecciona un sexo válido.");
      }

      // Si hubo errores, vuelve al formulario mostrando mensajes
      if (!errores.isEmpty()) {
        req.setAttribute("error", String.join("  •  ", errores));
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        return;
      }

      // Cálculo IAC (ejemplo; usa la fórmula de tu actividad)
      BigDecimal iac = calcularIac(cad, est);
      String categoria = categorizar(sexo, iac);

      // Guarda
      ResultadoIac r = new ResultadoIac();
      r.setNombre(vNombre);
      r.setEdad(edad);
      r.setSexo(sexo.substring(0,1).toUpperCase(Locale.ROOT)); // H / M
      r.setEstaturaM(est);
      r.setCaderaCm(cad);
      r.setPesoKg(peso);
      r.setIac(iac);
      r.setCategoria(categoria);

      new ResultadosIacDAO().insertar(r);

      // Muestra resultado
      req.setAttribute("resultado", r);
      req.getRequestDispatcher("/resultado.jsp").forward(req, resp);

    } catch (Exception ex) {
      // Cualquier otra excepción (por seguridad, y preservando los datos)
      req.setAttribute("error", ex.toString());
      req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
  }

  private static String safe(String s) { return s == null ? "" : s.trim(); }

  private static Integer parseEntero(String v, String label, List<String> errs) {
    try { return Integer.valueOf(v); }
    catch (Exception e) { errs.add(label + " inválida."); return null; }
  }

  private static BigDecimal parseDecimal(String v, String label, List<String> errs) {
    try {
      String clean = v.replaceAll("[^\\d,.-]", "").replace(',', '.');
      return new BigDecimal(clean);
    } catch (Exception e) {
      errs.add(label + " inválida.");
      return null;
    }
  }

  private static BigDecimal calcularIac(BigDecimal caderaCm, BigDecimal estM) {
    double est = estM.doubleValue();
    double cad = caderaCm.doubleValue();
    double iac = cad / (est * Math.sqrt(est)) - 18.0; // ej. fórmula
    return new BigDecimal(String.format(Locale.US, "%.2f", iac));
  }

  private static String categorizar(String sexo, BigDecimal iac) {
    double v = iac.doubleValue();
    // Ejemplo simple; adapta a tu tabla real
    if (v < 18) return "Bajo";
    if (v < 25) return "Normal";
    if (v < 30) return "Alto";
    return "Obesidad";
  }
}
