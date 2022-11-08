<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Contratar Poliza</title>
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
                                        <button class="dropdown-item" formaction="perfil_usuario" formmethod="GET">
                                            Perfil de Usuario
                                        </button>
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
                        CONTRATACIÓN DE PÓLIZA
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
                    <li class="breadcrumb-item"><a href="no_funciona">Cotizaciones</a></li>
                    <li class="breadcrumb-item"><a href="no_funciona">Cotización del vehículo</a></li>
                    <li class="breadcrumb-item">Contratación de Póliza</a></li>
                </ol>
            </div>
        </nav>
        <!-- Final BreadCrumb (Navegacion) -->

        <style>
                .precio-col-header {
                    border: 2px solid black;
                    border-top-left-radius: 10px;
                    border-top-right-radius: 10px;
                }

                .precio-col {
                    border-radius: 2px;
                    width: 149px;
                    float: left;
                }

                .precio-col-piso {
                    border-left: 2px solid black;
                    border-right: 2px solid black;
                    border-bottom: 2px solid black;
                    padding: 5px;
                    border-bottom-left-radius: 10px;
                    border-bottom-right-radius: 10px;
                    background: #ddd;
                }

            .pagination {
                border-left: 1px solid #ccc;
                border-top: 1px solid #ccc;
                border-bottom: 1px solid #ccc;
                display: inline-block;
            }

            .pagination a {
                border-right: 1px solid #ccc;
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                background-color: white;
                border-right: 1px solid #ccc;
                color: black;
            }

            .pagination a.active {
                background-color: #ccc;
                color: black;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
        </style>

        <!-- Formulario vehiculo -->
        <!-- Botones de la izquiera -->
        <div class="container col-8">
            <h4>Información del Cliente</h4>
            <div class="row mb-2"></div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex;">
                    <label for="nombres" class="col-6 fw-bold">Nombre/s:</label>
                    <span id="nombres" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getNombres()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="apellidos" class="col-6 fw-bold">Apellido/s:</label>
                    <span id="apellidos" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getApellidos()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="sexo" class="col-5 fw-bold">Sexo:</label>
                    <span id="sexo" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().obtenerSexo()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="fecha" class="fw-bold">Fecha de Nacimiento:</label>
                    <span id="fecha" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().obtenerFNacimiento()}
                    </span>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="tipo_doc" class="col-6 fw-bold">Tipo de Documento:</label>
                    <span id="tipo_doc" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getTipoDocumento().getAbreviatura()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="documentos" class="col-6  fw-bold">Documento:</label>
                    <span id="documentos" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getDocumento()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="telefono" class="col-5  fw-bold">Telefono:</label>
                    <span id="telefono" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getTelefono()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="correo" class="fw-bold">Correo:</label>
                    <span id="correo" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().obtenerCorreo()}
                    </span>
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="provincia" class="col-6 fw-bold">Provincia:</label>
                    <span id="provincia" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getLocalidad().getProvincia().getDescripcionprovincia()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="localidad" class="col-6 fw-bold">Localidad:</label>
                    <span id="localidad" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getLocalidad().getDescripcionlocalidad()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="domicilio" class="col-5  fw-bold">Domicilio:</label>
                    <span id="domicilio" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getCliente().getDomicilio()}
                    </span>
                </div>
            </div>

            <div class="row mb-2"></div>
            <h4>Información del Vehiculo</h4>
            <div class="row mb-2"></div>
            <div class="row mb-3">
                <div class="col-md-3" style="display: flex;">
                    <label for="matricula" class="col-6 fw-bold">Matricula:</label>
                    <span id="matricula" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getMatricula()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="chasis" class="col-5 fw-bold">Chasis:</label>
                    <span id="chasis" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getChasis()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="n_motor" class="col-6 fw-bold">N° Motor:</label>
                    <span id="n_motor" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getNumeromotor()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="gnc" class="col-3  fw-bold">GNC:</label>
                    <span id="gnc" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().obtenerGNC()}
                    </span>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="marca" class="col-6 fw-bold">Marca:</label>
                    <span id="marca" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getVersion().getModelo().getMarca().getNombremarca()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="modelo" class="col-5  fw-bold">Modelo:</label>
                    <span id="modelo" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getVersion().getModelo().getNombremodelo()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="version" class="col-6  fw-bold">Versión:</label>
                    <span id="version" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getVersion().getNombreversion()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="año" class="col-3  fw-bold">Año:</label>
                    <span id="año" style="margin-left: 5px;">
                        ${linea.getCotizacion().getVehiculo().getAñofabricación()}
                    </span>
                </div>
            </div>

            <div class="row mb-2"></div>
            <h4>Documentación</h4>
            <div class="row mb-2"></div>
            <div class="row mb-2">

                <div class="precio-col">
                    <img id="foto_frontal" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Frontal
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_trasera" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Trasera
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_lateral_1" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Lateral 1
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_lateral_2" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Lateral 2
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_techo" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Techo
                    </div>
                </div>

                <div class="precio-col">
                    <img id="cedula_verde" class="precio-col-header" src="" width="125" height="125">
                    <div class="precio-col-piso text-center fw-bold">
                        Cedula Verde
                    </div>
                </div>
            </div>

            <div class="row mb-2"></div>
            <h4>Cobertura Contratada</h4>
            <div class="row mb-2"></div>

            <div class="row mb-3">
                <div class="col-md-3" style="display: flex;">
                    <label for="nombrecobertura" class="col-6 fw-bold">Nombre:</label>
                    <span id="nombrecobertura" style="margin-left: 5px;">
                        ${linea.getCobertura().getNombrecobertura()}
                    </span>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-3" style="display: flex;">
                    <label for="preciocobertura" class="col-6 fw-bold">Precio:</label>
                    <span id="preciocobertura" style="margin-left: 5px;">
                        <fmt:setLocale value = "es_AR"/>
                        <fmt:formatNumber value = "${linea.getMonto()}" type = "currency"/>
                    </span>
                </div>
            </div>
            <table id="dtBasicExample" class="table table-sm" width="100%">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Detalle</th>
                        <th>Descripción</th>
                        <th>Monto Asegurado</th>
                    </tr>
                </thead>
                <tbody class="table-light">
                    <c:forEach var="detalle" items="${cobertura_detalle.getDetalles()}">
                                                
                        <c:choose>
                            <c:when test='${detalle.getMontofijo() > 0}'>
                                <c:set var="varA" value="${detalle.getMontofijo()}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="varA" value="${monto_aseg}" />
                            </c:otherwise>
                        </c:choose>

                    <tr>
                        <td class="text-center">${detalle.getIddetalle()}</td>
                        <td>${detalle.getNombredetalle()}</td>
                        <td>${detalle.getDescripciondetalle()}</td>
                        <td>
                            <fmt:setLocale value = "es_AR"/>
                            <fmt:formatNumber value = "${varA}" type = "currency"/>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>

                <table>
                    <br>
                    <div class="row" style="display: flex; justify-content: space-between;">
                        <div class="col-md-6 form-dark mb-4">
                        </div>
                        <div class="col-md-3 form-dark mb-4">
                            <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                                value="Cancelar">
                                Cancelar
                            </button>
                        </div>
                        <div class="col-md-3 form-dark mb-4">
                            <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                                value="confirmarContrataciónPóliza">
                                Finalizar
                            </button>
                        </div>
                    </div>


<script>
    document.querySelector('#foto_frontal').setAttribute("src", localStorage.getItem("imagen_1"));
    document.querySelector('#foto_trasera').setAttribute("src", localStorage.getItem("imagen_2"));
    document.querySelector('#foto_lateral_1').setAttribute("src", localStorage.getItem("imagen_3"));
    document.querySelector('#foto_lateral_2').setAttribute("src", localStorage.getItem("imagen_4"));
    document.querySelector('#foto_techo').setAttribute("src", localStorage.getItem("imagen_5"));
    document.querySelector('#cedula_verde').setAttribute("src", localStorage.getItem("imagen_6"));
</script>

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