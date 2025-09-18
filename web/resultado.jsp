<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Resultados IAC</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
  <div class="card shadow-sm p-4">
    <h2 class="mb-4 fw-bold">Resultados IAC</h2>

    <c:if test="${empty resultado}">
      <div class="alert alert-warning">No hay datos para mostrar.</div>
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/">Nuevo cálculo</a>
    </c:if>

    <c:if test="${not empty resultado}">
      <div class="row g-3">
        <div class="col-md-6"><strong>Nombre:</strong> ${resultado.nombre}</div>
        <div class="col-md-6"><strong>Edad:</strong> ${resultado.edad}</div>

        <div class="col-md-6"><strong>Sexo:</strong> ${resultado.sexo}</div>
        <div class="col-md-6"><strong>Estatura:</strong> <fmt:formatNumber value="${resultado.estaturaM}" minFractionDigits="2" /> m</div>

        <div class="col-md-6"><strong>Cadera:</strong> <fmt:formatNumber value="${resultado.caderaCm}" minFractionDigits="1"/> cm</div>
        <div class="col-md-6"><strong>Peso:</strong>
          <c:choose>
            <c:when test="${not empty resultado.pesoKg}">
              <fmt:formatNumber value="${resultado.pesoKg}" minFractionDigits="1"/> kg
            </c:when>
            <c:otherwise>—</c:otherwise>
          </c:choose>
        </div>

        <div class="col-md-6"><strong>IAC:</strong> <fmt:formatNumber value="${resultado.iac}" minFractionDigits="2" /></div>
        <div class="col-md-6"><strong>Categoría:</strong> ${resultado.categoria}</div>
      </div>

      <div class="mt-4 d-flex gap-2">
        <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/">← Nuevo cálculo</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/listar">Ver registros en BD</a>
      </div>
    </c:if>
  </div>
</div>
</body>
</html>
