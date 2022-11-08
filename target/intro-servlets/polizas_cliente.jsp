<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Polizas</title>
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

    <form action="" method="post">
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

<script>
    function guardarDatos(pol_num) {
        document.getElementById(pol_num).value = pol_num;
    }
</script>

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
		
                <table id="tablax">
                <thead class="encabezado">
                    <th class="text-center">ID</th>
                    <th class="text-center">Cobertura</th>
                    <th class="text-center">Fecha</th>
                    <th class="text-center">Hora</th>
                    <th class="text-center">Estado</th>
                    <th class="text-center"></th>
                </thead>
                <tbody>
                        <c:forEach var="poliza" items="${polizas}">
                            <tr>
                                <form action="polizas" method="POST">
                                <td class="text-center">
                                    ${poliza.getNumeropoliza()}
                                </td>
                                <td>${poliza.getLinea_cotizacion().getCobertura().getNombrecobertura()}</td>
                                <td>${poliza.obtenerFechaCont()}</td>
                                <td>${poliza.obtenerHoraCont()}</td>
                                <td>${poliza.getEstadopoliza().getAbreviatura()}</td>
                                <td>
                                    <button class="btn" name="pol_num" onclick="guardarDatos('${poliza.getNumeropoliza()}')">
                                        <img src="imagenes/ojo.png" width="25">
                                        <input type="hidden" id="${poliza.getNumeropoliza()}" name="poliza_numero" value="0">
                                        <input type="hidden" name="accion" value="visualizarPolizaAPagar">
                                    </button>
                                </td>
                                </form>
                            </tr>
                        </c:forEach>
                </tbody>
            </table>


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
                scrollY: 400,
                lengthMenu: [ [10, 25, -1], [10, 25, "All"] ],
            });
        });
    </script>

</body>

</html>