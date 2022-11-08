<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Poliza</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">

    <!-- BOOTSTRAP -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">

    <style>
        th,
        td {
            border: 1px solid #ECECEC;
        }

        .page-item.active .page-link {
            z-index: 1;
            background-color: black;
            color: white;
            border-color: black;
        }

        .page-link {
            background-color: white;
            border-right: 1px solid #ccc;
            color: black;
        }

        .page-link:hover {
            background-color: black;
            border-right: 1px solid #ccc;
            color: white;
        }

        .encabezado {
            background-color: black;
            color: white;
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
                                        ${usuario.obtenerNombre()}
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

    <form action="">
        <!-- Linea gris abajo del nav -->
        <nav class="d-flex flex-column bd-highlight navbar-light bg-secondary">
            <div style="text-align: center;">
                <strong>
                    <div class="p-2 bd-highlight" style="color: white; font-size: x-large;">
                        PÓLIZA
                    </div>
                </strong>
            </div>
        </nav>
        <!-- FINAL DEL Linea gris abajo del nav -->


        <!-- BreadCrumb (Navegacion) -->
        <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="navbar-light bg-light">
            <div class="container col-8">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/vendedor">Pólizas Pendientes</a></li>
                    <li class="breadcrumb-item">Póliza</a></li>
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
        </style>

        <!-- Formulario vehiculo -->
        <!-- Botones de la izquiera -->
        <div class="container col-8">

            <!-- Botones del inicio -->
            <h4>Información de la Poliza</h4>
            <div class="row mb-2"></div>
            <div class="row mb-3">
                <div class="col-md-3" style="display: flex;">
                    <label for="n_poliza" class="col-6 fw-bold">N° de Poliza:</label>
                    <span id="n_poliza" style="margin-left: 5px;">
                        ${poliza.getNumeropoliza()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="fec_contrato" class="col-6 fw-bold">Fecha de Contratación:</label>
                    <span id="fec_contrato" style="margin-left: 5px;">
                        ${poliza.obtenerFechaCont()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="hora_contrato" class="col-6 fw-bold">Hora de Contratación:</label>
                    <span id="hora_contrato" style="margin-left: 5px;">
                        ${poliza.obtenerHoraCont()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="estado" class="col-6 fw-bold">Estado:</label>
                    <span id="estado" style="margin-left: 5px;">
                        ${poliza.getEstadopoliza().getAbreviatura()}
                    </span>
                </div>
            </div>

            <h4>Información del Cliente</h4>
            <div class="row mb-2"></div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex;">
                    <label for="nombres" class="col-6 fw-bold">Nombre/s:</label>
                    <span id="nombres" style="margin-left: 5px;">
                        ${cliente.getNombres()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="apellidos" class="col-6 fw-bold">Apellido/s:</label>
                    <span id="apellidos" style="margin-left: 5px;">
                        ${cliente.getApellidos()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="sexo" class="col-5 fw-bold">Sexo:</label>
                    <span id="sexo" style="margin-left: 5px;">
                        ${cliente.obtenerSexo()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="fecha" class="fw-bold">Fecha de Nacimiento:</label>
                    <span id="fecha" style="margin-left: 5px;">
                        ${cliente.obtenerFNacimiento()}
                    </span>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="tipo_doc" class="col-6 fw-bold">Tipo de Documento:</label>
                    <span id="tipo_doc" style="margin-left: 5px;">
                        ${cliente.getTipoDocumento().getAbreviatura()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="documentos" class="col-6  fw-bold">Documento:</label>
                    <span id="documentos" style="margin-left: 5px;">
                        ${cliente.getDocumento()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="telefono" class="col-5  fw-bold">Telefono:</label>
                    <span id="telefono" style="margin-left: 5px;">
                        ${cliente.getTelefono()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="correo" class="fw-bold">Correo:</label>
                    <span id="correo" style="margin-left: 5px;">
                        ${cliente.getCorreo()}
                    </span>
                </div>
            </div>

            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="provincia" class="col-6 fw-bold">Provincia:</label>
                    <span id="provincia" style="margin-left: 5px;">
                        ${cliente.getLocalidad().getProvincia().getDescripcionprovincia()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="localidad" class="col-6 fw-bold">Localidad:</label>
                    <span id="localidad" style="margin-left: 5px;">
                        ${cliente.getLocalidad().getDescripcionlocalidad()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="domicilio" class="col-5  fw-bold">Domicilio:</label>
                    <span id="domicilio" style="margin-left: 5px;">
                        ${cliente.getDomicilio()}
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
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getMatricula()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="chasis" class="col-5 fw-bold">Chasis:</label>
                    <span id="chasis" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getChasis()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="n_motor" class="col-6 fw-bold">N° Motor:</label>
                    <span id="n_motor" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getNumeromotor()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="gnc" class="col-3  fw-bold">GNC:</label>
                    <span id="gnc" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().obtenerGNC()}
                    </span>
                </div>
            </div>
            <div class="row mb-2">
                <div class="col-md-3" style="display: flex">
                    <label for="marca" class="col-6 fw-bold">Marca:</label>
                    <span id="marca" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getVersion().getModelo().getMarca().getNombremarca()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="modelo" class="col-5  fw-bold">Modelo:</label>
                    <span id="modelo" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getVersion().getModelo().getNombremodelo()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="version" class="col-6  fw-bold">Versión:</label>
                    <span id="version" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getVersion().getNombreversion()}
                    </span>
                </div>

                <div class="col-md-3" style="display: flex">
                    <label for="año" class="col-3  fw-bold">Año:</label>
                    <span id="año" style="margin-left: 5px;">
                        ${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getAñofabricación()}
                    </span>
                </div>
            </div>

            <div class="row mb-2"></div>
            <h4>Documentación</h4>
            <div class="row mb-2"></div>
            <div class="row mb-2">

                <div class="precio-col">
                    <img id="foto_frontal" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=frente">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Frontal
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_trasera" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=trasera">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Trasera
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_lateral_1" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=lateral1">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Lateral 1
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_lateral_2" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=lateral2">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Lateral 2
                    </div>
                </div>

                <div class="precio-col">
                    <img id="foto_techo" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=techo">
                    <div class="precio-col-piso text-center fw-bold">
                        Foto Techo
                    </div>
                </div>

                <div class="precio-col">
                    <img id="cedula_verde" class="precio-col-header" width="125" height="125"
                        src="Controlimg?id=${poliza.getDocumentacion().getIddocumentacion()}&p=cedula">
                    <div class="precio-col-piso text-center fw-bold">
                        Cedula Verde
                    </div>
                </div>
            </div>

            <div class="row mb-2"></div>

            <h4> Cobertura Contratada</h4>
            <div class="row" style="display: flex; justify-content: space-between;">

                <div class="row col-6">
                    <div class="col-md-6 mb-3" style="display: flex">
                        <label for="nombrecobertura" class="col-6 fw-bold">Nombre:</label>
                        <span id="nombrecobertura" style="margin-left: 5px;">
                            ${poliza.getLinea_cotizacion().getCobertura().getNombrecobertura()}
                        </span>
                    </div>
                    <div class="col-md-6 mb-3" style="display: flex">
                        <label for="preciocobertura" class="col-6 fw-bold">Precio:</label>
                        <span id="preciocobertura" style="margin-left: 5px;">
                            <fmt:setLocale value="es_AR" />
                            <fmt:formatNumber value="${poliza.getPreciopolizaactual()}" type="currency" />
                        </span>
                    </div>
                    <div class="col-md-6 mb-3" style="display: flex">
                        <label for="tipo_contratacion" class="col-6 fw-bold">Tipo de Contratación:</label>
                        <span id="tipo_contratacion" style="margin-left: 5px;">
                            ${poliza.getTipo_contratacion().getNombrecontratacion()}
                        </span>
                    </div>
                    <div class="col-md-6 mb-3" style="display: flex">
                        <label for="periodopago" class="col-6 fw-bold">Periodo de Pago:</label>
                        <span id="periodopago" style="margin-left: 5px;">
                            ${poliza.getPeriodo_pago().getNombreperiodopago()}
                        </span>
                    </div>
                </div>

                <div class="col-md-6 mb-3">
                    <div class="row" style="background: black; color: white; width: 100%">
                        <label class="text-center fw-bold">
                            Fecha de Pago
                        </label>
                    </div>
                    <div class="row" style="width: 100%;">
                        <table class="table">
                            <thead class="table table-borderless">
                                <th class="text-center">Inicio</th>
                                <th class="text-center">Vencimiento</th>
                            </thead>
                            <tbody>
                                <td class="text-center">${poliza.obtenerFechaInicio()}</td>
                                <td class="text-center">${poliza.obtenerFechaVenc()}</td>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            


            <table id="tablax">
                <thead class="encabezado">
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Detalle</th>
                        <th class="text-center">Descripción</th>
                        <th class="text-center">Monto Asegurado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detalle" items="${cobertura_detalle.getDetalles()}">
                                                
                        <c:choose>
                            <c:when test='${detalle.getMontofijo() > 0}'>
                                <c:set var="varA" value="${detalle.getMontofijo()}" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="varA" value="${poliza.getMontoasegurado()}" />
                            </c:otherwise>
                        </c:choose>

                        <tr>
                            <td class="text-center">${detalle.getIddetalle()}</td>
                            <td>${detalle.getNombredetalle()}</td>
                            <td>${detalle.getDescripciondetalle()}</td>
                            <td>
                                <fmt:setLocale value="es_AR" />
                                <fmt:formatNumber value="${varA}" type="currency" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <table>
                <br>

                <div class="row" style="display: flex; justify-content: space-between;">
                    <div class="col-md-4 form-dark mb-4"></div>
                    <div class="col-md-2 form-dark mb-4">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                        value="iniciarAprobacionDePoliza" formaction="vendedor" formmethod="POST">
                            Cancelar
                        </button>
                    </div>
                    <div class="col-md-2 form-dark mb-4">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                        value="enviarARevisarPoliza" formaction="poliza_cliente" formmethod="POST">
                            Revisar
                        </button>
                    </div>
                    <div class="col-md-2 form-dark mb-4">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                        value="rechazarPoliza" formaction="poliza_cliente" formmethod="POST">
                            Rechazar
                        </button>
                    </div>
                    <div class="col-md-2 form-dark mb-4">
                        <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion"
                        value="aprobarPoliza" formaction="poliza_cliente" formmethod="POST">
                            Aprobar
                        </button>
                    </div>
                </div>
        </div>
    </form>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

    <!-- JQUERY -->
    <script src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous">
        </script>
    <!-- DATATABLES -->
    <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js">
    </script>
    <!-- BOOTSTRAP -->
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js">
    </script>
    <script>
        $(document).ready(function () {
            $('#tablax').DataTable({
                language: {
                    processing: "Tratamiento en curso...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Agrupar de _MENU_ items",
                    info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
                    infoEmpty: "No existen datos.",
                    infoPostFix: "",
                    loadingRecords: "Cargando...",
                    zeroRecords: "No se encontraron datos con tu busqueda",
                    emptyTable: "No hay datos disponibles en la tabla.",
                    paginate: {
                        first: "Primero",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": active para ordenar la columna en orden ascendente",
                        sortDescending: ": active para ordenar la columna en orden descendente"
                    }
                },
                scrollY: 200,
                lengthMenu: [[5, 10, -1], [5, 10, "All"]],
            });
        });
    </script>

</body>

</html>