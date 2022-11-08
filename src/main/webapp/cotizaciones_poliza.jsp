<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Proceso Completado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
</head>
<body>
<center>
    <c:choose>
        <c:when test='${mensaje.toString().equals("1")}'>
            <h2>¡Cotizacion y Poliza Creados con exito!</h2>
        </c:when>
        <c:when test='${mensaje.toString().equals("2")}'>
            <h2>¡Cotizacion Guardada con Exito!</h2>
        </c:when>
    </c:choose>
    
    <div class="row" style="display: flex; justify-content: space-between;">
        <div class="col-md-5 form-dark mb-4">
        </div>
        <div class="col-md-2 form-dark mb-4">
            <form action="solicitar_poliza" method="post">
            <button class="fw-bold form-control btn-action btn-lg" type="submit"
            name="accion" value="Cancelar">
                Volver a Inicio
            </button>
            </form>
        </div>
        <div class="col-md-5 form-dark mb-4">
        </div>
    </div>
</center>
</body>
</html>