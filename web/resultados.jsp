<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.evidencia1.modelo.ResultadoIac"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Resultados IAC</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container py-4">
  <h1 class="mb-4">Resultados guardados</h1>

  <c:if test="${empty lista}">
    <div class="alert alert-warning">No hay registros.</div>
  </c:if>

  <c:if test="${not empty lista}">
  <div class="card shadow-sm">
    <div class="card-body p-0">
      <div class="table-responsive">
        <table class="table table-striped table-hover mb-0">
          <thead class="table-light">
          <tr>
            <th>#</th><th>Nombre</th><th>Edad</th><th>Sexo</th>
            <th>Est. (m)</th><th>Peso (kg)</th><th>Cadera (cm)</th>
            <th>IAC</th><th>Categoría</th><th>Fecha</th>
          </tr>
          </thead>
          <tbody>
          <%
            List<ResultadoIac> lista = (List<ResultadoIac>) request.getAttribute("lista");
            int i = 1;
            for (ResultadoIac r : lista) {
          %>
            <tr>
              <td><%= i++ %></td>
              <td><%= r.getNombre() %></td>
              <td><%= r.getEdad() %></td>
              <td><%= r.getSexo() %></td>
              <td><%= r.getEstaturaM() %></td>
              <td><%= r.getPesoKg() %></td>
              <td><%= r.getCaderaCm() %></td>
              <td><%= r.getIac() %></td>
              <td><%= r.getCategoria() %></td>
              <td><%= r.getFecha() %></td>
            </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  </c:if>

  <div class="mt-3">
    <a class="btn btn-secondary" href="index.jsp">← Nuevo cálculo</a>
  </div>
</div>
</body>
</html>
