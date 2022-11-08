<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cotizaciones de Vehiculo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
<style>
    .iluminado {
        background-color: none;
      }
    .iluminado:hover {
        background-color: #ECECEC;
    }
    table {
        border-collapse: collapse;
      }
      th, td {
        border: 2px solid #ECECEC;
        align-items: center;
        padding-top: 15px;
        padding-bottom: 15px;
        padding-left: 15px;
        padding-right: 15px;
      }
</style>
</head>
<body>
<!-- Nav -->
<form action="" method="POST">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <!-- Car-Chain Logo -->
            <a class="navbar-brand me-2" href="inicio">
                <img src="imagenes/Carchain.png" height="30" alt="Car-Chain" loading="lazy"
                    style="margin-top: -1px;" />
            </a>
            <!-- Botones al costado del logo -->
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <button type="submit" class="nav-item btn-login">¿Quienes somos?</button>
                    <button type="submit" class="nav-item btn-login">Contactanos</button>
                    <button type="submit" class="nav-item btn-login">Ayuda</button>
                </div>
                <!-- Boton login -->
                <div class="navbar-nav ms-auto">
                    <c:choose>
                        <c:when test='${usuario.getIdPersona() > 0}'>
                            <div class="dropdown">
                                <button style="border: none" class="btn btn-outline-light dropdown-toggle"
                                    type="button" id="dropdownMenuButton" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                    ${usuario.getApellidos()} ${usuario.getNombres()}
                                    <img src="imagenes/Logo.png" class="rounded-circle" height="30" />
                                </button>
                                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                                    <div class="dropdown-divider"></div>
                                    <button name="accion" value="CerrarSesion" class="dropdown-item" formaction="login"
                                        formmethod="POST">
                                        Salir
                                    </button>
                                </div>
                            </div>
                        </c:when>
                        <c:when test='${cliente.getIdPersona() > 0}'>
                            <div class="dropdown">
                                <button style="border: none" class="btn btn-outline-light dropdown-toggle"
                                    type="button" id="dropdownMenuButton" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false">
                                    ${cliente.getApellidos()} ${cliente.getNombres()}
                                    <img src="imagenes/Logo.png" class="rounded-circle" height="30" />
                                </button>
                                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                                    <p class="dropdown-item">Perfil de Usuario</p>
                                    <div class="dropdown-divider"></div>
                                    <button name="accion" value="CerrarSesion" class="dropdown-item" formaction="login"
                                        formmethod="POST">
                                        Salir
                                    </button>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" class="btn-login" name="accion" value="Login" formaction="login"
                                formmethod="POST">
                                Iniciar Sesión
                            </button>
                            <button type="submit" class="btn btn-primary" name="accion" value="solicitarRegistro"
                                formaction="register" formmethod="POST"
                                style="background-color: black; border-color: white;">
                                Registrarse
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>
</form>
<!-- Final del Nav -->
    <!--REGISTRO -->
    <section class="vh-85 gradient-custom">
        <form action="" method="post">
            <!-- Linea gris abajo del nav -->
            <nav class="d-flex flex-column bd-highlight navbar-light bg-secondary">
                <div style="text-align: center;">
                    <strong>
                        <div class="p-2 bd-highlight" style="color: white; font-size: x-large;">
                            COTIZACIÓN DEL VEHÍCULO
                        </div>
                    </strong>
                </div>
            </nav>
            <!-- FINAL DEL Linea gris abajo del nav -->


            <!-- BreadCrumb (Navegacion) -->
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="navbar-light bg-light">
                <div class="container col-8 mb-3">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="inicio">Home</a></li>
                        <li class="breadcrumb-item">Cotización del vehículo</a></li>
                    </ol>
                </div>
            </nav>
            <!-- Final BreadCrumb (Navegacion) -->
            <!-- Formulario vehiculo -->
            <!-- Botones de la izquiera -->

            <div class="container col-8">         
                <center><h2>¡Te ofrecemos estas cotizaciones!</h2></center>
                        <table>
                            <thead>
                                <tr>
                                    <th style="border:none"></th>
                    		        <c:forEach var="lineacotizacion" items="${lineascotizaciones}">
                                        <th style="border:none" class="text-center"><h4>${lineacotizacion.getCobertura().getNombrecobertura()}</h4></th>
                    		        </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="border:none"></td>
                    		    	<c:forEach var="lineacotizacion" items="${lineascotizaciones}">
                                    	<td class="text-center">
                                            <h3>
                                                <fmt:setLocale value = "es_AR"/>
                                                <fmt:formatNumber value = "${lineacotizacion.getMonto()}" type = "currency"/>
                                            </h3>
                                        </td>
                    		    	</c:forEach>
                                </tr>

                                <c:forEach var="lineas" items="${lineasTotales}">
                                <tr class="iluminado">
                                    <c:forEach var="linea" items="${lineas}">
                                        <c:choose>
                                            <c:when test='${linea.equals("1")}'>
                                                <td class="text-center"><img src="imagenes/simb_tilde.png" height="30" width="30"></td>
                                            </c:when>
                                            <c:when test='${linea.equals("0")}'>
                                                <td class="text-center"><img src="imagenes/simb_cruz.png" height="30" width="30"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${linea}</td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tr>
                                </c:forEach>
                                <tr>
                                    <td style="border:none"></td>
                                    <c:forEach var="lineacotizacion" items="${lineascotizaciones}">
                                        <form action="solicitar_poliza" method="POST">
                                        <input type="hidden" name="accion" value="solicitarContratacionDeCobertura"> 
                                            <td class="text-center">
                                                <button class="fw-bold form-control btn-action btn-lg" type="submit" 
                                                name="${lineacotizacion.getCobertura().getIdcobertura()}"
                                                value="${lineacotizacion.getCobertura().getIdcobertura()}">
                                                    Contratar
                                                </button>
                                            </td>
                                        </form>
                                    </c:forEach>
                                </tr>
                            </tbody>
                        </table>
                        <br>
                        <div class="row" style="display: flex; justify-content: space-between;">
                            <div class="col-md-5 form-dark mb-4"></div>
                            <div class="col-md-4 form-dark mb-4">
                                <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion" 
                                value="solicitarContratacionDeCobertura" formaction="solicitar_poliza" formmethod="POST">
                                    Guardar Cotizacion
                                </button>
                            </div>
                            <div class="col-md-3 form-dark mb-4"></div>
                        </div>
            </div>
        </form>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    </section>
</body>

</html>