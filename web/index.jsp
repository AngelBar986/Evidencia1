<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <title>Calcular IAC</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style> body{background:#f7f8fb;} </style>
</head>
<body>
<div class="container py-4">
  <h1 class="mb-3">Calcular IAC</h1>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <form action="${pageContext.request.contextPath}/calcular" method="post" class="card shadow-sm">
    <div class="card-body">
      <div class="row g-3">

        
        <div class="col-md-6">
          <label class="form-label">Nombre</label>
          <input name="nombre"
                 value="${fv_nombre}"
                 class="form-control ${errorField eq 'nombre' ? 'is-invalid' : ''}">
          <c:if test="${errorField eq 'nombre'}">
            <div class="invalid-feedback">Revisa este campo.</div>
          </c:if>
        </div>

        
        <div class="col-md-3">
          <label class="form-label">Edad</label>
          <input name="edad"
                 inputmode="numeric" pattern="[0-9]*" oninput="onlyInt(this)"
                 value="${fv_edad}"
                 class="form-control ${errorField eq 'edad' ? 'is-invalid' : ''}">
          <c:if test="${errorField eq 'edad'}">
            <div class="invalid-feedback">Debes ingresar tu edad.</div>
          </c:if>
        </div>

        
        <div class="col-md-3">
          <label class="form-label">Sexo</label>
          <select name="sexo" class="form-select">
            <option value="H" <c:if test="${empty fv_sexo || fv_sexo eq 'H'}">selected</c:if>>Hombre</option>
            <option value="M" <c:if test="${fv_sexo eq 'M'}">selected</c:if>>Mujer</option>
          </select>
        </div>

        
        <div class="col-md-4">
          <label class="form-label">Estatura (m)</label>
          <input name="estatura"
                 inputmode="decimal" pattern="[0-9]*[\\.,]?[0-9]*" oninput="onlyDec(this)"
                 value="${fv_est}"
                 class="form-control ${errorField eq 'estatura' ? 'is-invalid' : ''}">
          <c:if test="${errorField eq 'estatura'}">
            <div class="invalid-feedback">Ingresa un número válido (ej. 1.70).</div>
          </c:if>
        </div>

        
        <div class="col-md-4">
          <label class="form-label">Cadera (cm)</label>
          <input name="cadera"
                 inputmode="decimal" pattern="[0-9]*[\\.,]?[0-9]*" oninput="onlyDec(this)"
                 value="${fv_cad}"
                 class="form-control ${errorField eq 'cadera' ? 'is-invalid' : ''}">
          <c:if test="${errorField eq 'cadera'}">
            <div class="invalid-feedback">Ingresa un número válido (ej. 95.3).</div>
          </c:if>
        </div>

        <!-- Peso -->
        <div class="col-md-4">
          <label class="form-label">Peso (kg) <small class="text-muted">(opcional)</small></label>
          <input name="peso"
                 inputmode="decimal" pattern="[0-9]*[\\.,]?[0-9]*" oninput="onlyDec(this)"
                 value="${fv_peso}"
                 class="form-control ${errorField eq 'peso' ? 'is-invalid' : ''}">
          <c:if test="${errorField eq 'peso'}">
            <div class="invalid-feedback">Número inválido.</div>
          </c:if>
        </div>

      </div>

      <div class="mt-4 d-flex gap-2">
        <button class="btn btn-primary" type="submit">Calcular</button>
        <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/listar">
          Ver resultados guardados
        </a>
      </div>
    </div>
  </form>
</div>

<script>
  
  function onlyDec(el){ el.value = el.value.replace(/[^0-9.,-]/g,''); }
  function onlyInt(el){ el.value = el.value.replace(/[^0-9-]/g,''); }
</script>
</body>
</html>
