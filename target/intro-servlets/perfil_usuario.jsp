<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perfil de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
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
                                        ${usuario.getApellido()} ${usuario.getNombres()}
                                        <img src="imagenes/Logo.png" class="rounded-circle" height="30" />
                                    </button>
                                    <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                                        <div class="dropdown-divider"></div>
                                        <button name="accion" value="CerrarSesion" class="dropdown-item"
                                            formaction="login" formmethod="POST">
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
                                        <button name="accion" value="CerrarSesion" class="dropdown-item"
                                            formaction="login" formmethod="POST">
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

    <form action="solicitar_poliza" method="post">
        <!-- Linea gris abajo del nav -->
        <nav class="d-flex flex-column bd-highlight navbar-light bg-secondary">
            <div style="text-align: center;">
                <strong>
                    <div class="p-2 bd-highlight" style="color: white; font-size: x-large;">
                        DATOS
                    </div>
                </strong>
            </div>
        </nav>
        <!-- FINAL DEL Linea gris abajo del nav -->

        <!-- BreadCrumb (Navegacion) -->
        <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="navbar-light bg-light">
            <div class="container col-8">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="no_funciona">Cuenta</a></li>
                    <li class="breadcrumb-item">Datos</a></li>
                </ol>
            </div>
        </nav>
        <!-- Final BreadCrumb (Navegacion) -->

        <div class="container col-8">
            <div class="row mb-2" style="border: 1px solid black;">
                <div class="col-md-3" style="border-right: 1px solid black;">
                    <br>
                    <div class="col-md-12">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                            value="" formaction="perfil_usuario" formmethod="GET">
                            Datos
                        </button>
                    </div>
                    <br>
                    <div class="col-md-12">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                            value="iniciarPagoDePoliza" formaction="polizas" formmethod="POST">
                            Polizas
                        </button>
                    </div>
                    <br>
                    <div class="col-md-12">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                            value="" formaction="no_funciona" formmethod="GET">
                            Cotizaciones
                        </button>
                    </div>
                    <br>
                </div>
                <div class="col-md-9">

                    <h4>Información Personal</h4>
                    <div class="row mb-2">
                        <div class="col-md-4" style="display: flex;">
                            <label for="nombres" class="col-6 fw-bold">Nombre/s:</label>
                            <span id="nombres" style="margin-left: 5px;">
                                ${cliente.getNombres()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="apellidos" class="col-6 fw-bold">Apellido/s:</label>
                            <span id="apellidos" style="margin-left: 5px;">
                                ${cliente.getApellidos()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="sexo" class="col-6 fw-bold">Sexo:</label>
                            <span id="sexo" style="margin-left: 5px;">
                                ${cliente.obtenerSexo()}
                            </span>
                        </div>
                    </div>

                    <div class="row mb-2 mb-4">
                        <div class="col-md-4" style="display: flex;">
                            <label for="tipo_doc" class="col-6 fw-bold">Tipo de Documento:</label>
                            <span id="tipo_doc" style="margin-left: 5px;">
                                ${cliente.getTipoDocumento().getAbreviatura()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="documento" class="col-6 fw-bold">Documento:</label>
                            <span id="documento" style="margin-left: 5px;">
                                ${cliente.getDocumento()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="fecha" class="col-6 fw-bold">Fecha de Nacimiento:</label>
                            <span id="fecha" style="margin-left: 5px;">
                                ${cliente.obtenerFNacimiento()}
                            </span>
                        </div>
                    </div>

                    <h4>Seguridad y Privacidad</h4>
                    <div class="row mb-2 mb-4">
                        <div class="col-md-4" style="display: flex;">
                            <label for="correo" class="col-4 fw-bold">Correo:</label>
                            <span id="correo" style="margin-left: 5px;">
                                ${cliente.obtenerCorreo()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="contraseña" class="col-6 fw-bold">Contraseña:</label>
                            <span id="contraseña" style="margin-left: 5px;">
                                ${cliente.getContraseña()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="telefono" class="col-6 fw-bold">Telefono:</label>
                            <span id="telefono" style="margin-left: 5px;">
                                ${cliente.getTelefono()}
                            </span>
                        </div>
                    </div>

                    <h4>Ubicación</h4>
                    <div class="row mb-2 mb-4">
                        <div class="col-md-4" style="display: flex;">
                            <label for="provincia" class="col-6 fw-bold">Provincia:</label>
                            <span id="provincia" style="margin-left: 5px;">
                                ${cliente.getLocalidad().getProvincia().getDescripcionprovincia()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="localidad" class="col-6 fw-bold">Localidad:</label>
                            <span id="localidad" style="margin-left: 5px;">
                                ${cliente.getLocalidad().getDescripcionlocalidad()}
                            </span>
                        </div>
                        <div class="col-md-4" style="display: flex;">
                            <label for="domicilio" class="col-6 fw-bold">Domicilio:</label>
                            <span id="domicilio" style="margin-left: 5px;">
                                ${cliente.getDomicilio()}
                            </span>
                        </div>
                    </div>

                </div>
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

</body>

</html>