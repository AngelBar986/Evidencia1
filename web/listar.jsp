<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Resultados guardados</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
  <h1 class="mb-4 fw-bold">Resultados guardados</h1>

  <c:choose>
    <c:when test="${empty lista}">
      <div class="alert alert-warning">No hay registros.</div>
    </c:when>
    <c:otherwise>
      <div class="table-responsive">
        <table class="table table-hover align-middle bg-white shadow-sm">
          <thead class="table-light">
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Sexo</th>
            <th>Est. (m)</th>
            <th>Peso (kg)</th>
            <th>Cadera (cm)</th>
            <th>IAC</th>
            <th>Categoría</th>
            <th>Fecha</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="r" items="${lista}" varStatus="st">
            <tr>
              <td>${st.count}</td>
              <td>${r.nombre}</td>
              <td>${r.edad}</td>
              <td>${r.sexo}</td>
              <td>${r.estaturaM}</td>
              <td><c:out value="${r.pesoKg}" default="—"/></td>
              <td>${r.caderaCm}</td>
              <td>${r.iac}</td>
              <td>${r.categoria}</td>
              <td>${r.fecha}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </c:otherwise>
  </c:choose>

  <a class="btn btn-outline-secondary mt-3" href="${pageContext.request.contextPath}/">
    ← Nuevo cálculo
  </a>
</div>
</body>
</html>
