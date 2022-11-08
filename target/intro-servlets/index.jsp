<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Car-Chain</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
    <!-- BOOTSTRAP -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">

<style>

    th,td {
        border: 1px solid #ECECEC;
        padding-top: 6px;
        padding-bottom: 6px;
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
            <img src="imagenes/Carchain.png" height="30" alt="Car-Chain" loading="lazy" style="margin-top: -1px;" />
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
                            <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button"
                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                                ${usuario.getApellidos()} ${usuario.getNombres()}
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
                            <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button"
                                id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
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
                            <button type="submit" class="btn-login" name="accion" value="Login"
                            formaction="login" formmethod="POST">
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
<form action="" method="POST">
<c:choose>
    <c:when test='${usuario.getTipoUsuario().toString().equals("ADMINISTRADOR")}'>
        <br><br>
        <h1 align="center">[INSERTE PANTALLA DE ADMINISTRADOR AQUI]</h1>
    </c:when>
    <c:when test='${usuario.getTipoUsuario().toString().equals("VENDEDOR")}'>

    
        <form action="" method="post">
            <!-- Linea gris abajo del nav -->
            <nav class="d-flex flex-column bd-highlight navbar-light bg-secondary">
                <div style="text-align: center;">
                    <strong>
                        <div class="p-2 bd-highlight" style="color: white; font-size: x-large;">
                            PÓLIZAS PENDIENTES
                        </div>
                    </strong>
                </div>
            </nav>
            <!-- FINAL DEL Linea gris abajo del nav -->
    
            <!-- BreadCrumb (Navegacion) -->
            <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="navbar-light bg-light">
                <div class="container col-8">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">Pólizas Pendientes</li>
                    </ol>
                </div>
            </nav>
            <!-- Final BreadCrumb (Navegacion) -->

            <script>
                function guardarDatos(pol_num) {
                    document.getElementById(pol_num).value = pol_num;
                }
            </script>

        <div class="container col-8">
        <table id="tablax">
            <thead class="encabezado">
                <th class="text-center">ID</th>
                <th class="text-center">Cobertura</th>
                <th class="text-center">Titular</th>
                <th class="text-center">Vehiculo</th>
                <th class="text-center">Fecha Creacion</th>
                <th class="text-center">Estado</th>
                <th class="text-center"></th>
            </thead>
            <tbody>
                    <c:forEach var="poliza" items="${polizas}">
                        <tr>
                            <form action="poliza_cliente" method="POST">
                            <td class="text-center">
                                ${poliza.getNumeropoliza()}
                            </td>
                            <td>${poliza.getLinea_cotizacion().getCobertura().getNombrecobertura()}</td>
                            <td>${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getCliente().obtenerTitular()}</td>
                            <td>${poliza.getLinea_cotizacion().getCotizacion().getVehiculo().getMatricula()}</td>
                            <td>${poliza.getLinea_cotizacion().getCotizacion().obtenerFechaCreacion()}</td>
                            <td>${poliza.getEstadopoliza().getAbreviatura()}</td>
                            <td>
                                <button class="btn" name="pol_num" onclick="guardarDatos('${poliza.getNumeropoliza()}')">
                                    <img src="imagenes/ojo.png" width="25">
                                    <input type="hidden" id="${poliza.getNumeropoliza()}" name="poliza_numero" value="0">
                                    <input type="hidden" name="accion" value="solicitarVerPoliza">
                                </button>
                            </td>
                            </form>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        </div>

    </form>

    </c:when>
    <c:when test='${usuario.getTipoUsuario().toString().equals("PERITO")}'>
        <br><br>
        <h1 align="center">[INSERTE PANTALLA DE PERITO AQUI]</h1>
    </c:when>
    <c:when test='${usuario.getTipoUsuario().toString().equals("GESTOR_DE_SINIESTROS")}'>
        <br><br>
        <h1 align="center">[INSERTE PANTALLA DE GESTOR_DE_SINIESTROS AQUI]</h1>
    </c:when>
    <c:otherwise>
        <!-- Carrousel -->
        <div id="carruselCarChain" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="imagenes/Carrusel D1.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1>¡Bienvenido a Car-Chain!</h1>
                        <br>
                        <div class="container d-flex justify-content-center">
                            <div class="col-8">
                                Con tan solo seguir unos cuantos pasos, poderemos decirle el
                                valor exacto que tiene su vehiculo en el mercado, y las 
                                coberturas que podemos ofrecerle en base a este.
                            </div>
                            <div class="col-4">
                                <button type="submit" class="fw-bold btn btn-action btn-lg" name="accion"
                                value="solicitarCotizacion" formaction="solicitar_poliza" formmethod="POST">
                                    Solicitar Cotización
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="imagenes/Carrusel D2.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1>¡Bienvenido a Car-Chain!</h1>
                        <br>
                        <div class="container d-flex justify-content-center">
                            <div class="col-8">
                                Con tan solo seguir unos cuantos pasos, poderemos decirle el
                                valor exacto que tiene su vehiculo en el mercado, y las 
                                coberturas que podemos ofrecerle en base a este.
                            </div>
                            <div class="col-4">
                                <button type="submit" class="fw-bold btn btn-action btn-lg" name="accion"
                                value="solicitarCotizacion" formaction="solicitar_poliza" formmethod="POST">
                                    Solicitar Cotización
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="imagenes/Carrusel D3.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h1>Bienvenido a Car-Chain</h1>
                        <br>
                        <div class="container d-flex justify-content-center">
                            <div class="col-8">
                                Con tan solo seguir unos cuantos pasos, poderemos decirle el
                                valor exacto que tiene su vehiculo en el mercado, y las 
                                coberturas que podemos ofrecerle en base a este.
                            </div>
                            <div class="col-4">
                                <button type="submit" class="fw-bold btn btn-action btn-lg" name="accion"
                                value="solicitarCotizacion" formaction="solicitar_poliza" formmethod="POST">
                                    Solicitar Cotización
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carruselCarChain" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carruselCarChain" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!-- FINAL DEL Carrousel -->
    </c:otherwise>
</c:choose>
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
                lengthMenu: [ [10, 25, -1], [10, 25, "All"] ],
            });
        });
    </script>

</body>

</html>