<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cargar Vehiculo</title>
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
            <div class="container col-8">
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
            <h4> Información del Vehículo</h4>
            <div class="error-message mb-3" style="color:red">
                ${mensajeError8}
            </div>

            <div class="row"">

                <div class="col-md-6">

                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="matricula">Matricula: </label>
                    <span style="color:red">${mensajeError1}</span>
                </div>
                <input type="text" name="matricula" placeholder="Matricula" class="form-control mb-1"
                    value="${matricula}" />


                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="chasis">Chasis: </label>
                    <span style="color:red">${mensajeError2}</span>
                </div>
                <input type="text" name="chasis" placeholder="Chasis" class="form-control mb-1"
                    value="${chasis}" />


                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="numero_motor">Numero de Motor: </label>
                    <span style="color:red">${mensajeError3}</span>
                </div>
                <input type="number" name="numero_motor" placeholder="Numero de Motor" class="form-control mb-1"
                    value="${numero_motor}" />

                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="gnc">Tipo de vehiculo</label>
                </div>
                <div class="form-check form-switch" id="gnc">
                    <input class="form-check-input mb-1" type="checkbox" name="gnc" 
                    <c:if test="${valor_gnc}">checked</c:if>/>
                    <label class="form-check-label">Posee GNC</label>
                </div>

            </div>

            <div class="col-md-6">


                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="marca">Marca:</label>
                    <span style="color:red">${mensajeError4}</span>
                </div>
                <select id="marca" name="marca" class="form-select mb-1" onchange="cargarModelos()">
                    <option value="-1">Seleccionar</option>
                    <c:forEach var="marca" items="${marcas}">
                        <option value="${marca.getIdmarca()}" <c:if test="${marca.getIdmarca() == marca_id}">
                            selected </c:if>>
                            ${marca.getNombremarca()}
                        </option>
                    </c:forEach>
                </select>


                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="modelo"> Modelo:</label>
                    <span style="color:red">${mensajeError5}</span>
                </div>
                <select id="modelo" name="modelo" class="form-select mb-1" onchange="cargarVersiones()">
                    <option value="-1">Seleccionar</option>
                    <c:forEach var="modelo" items="${modelos}">
                        <c:if test="${modelo.getMarca().getIdmarca() == marca_id}">
                            <option value="${modelo.getIdmodelo()}" <c:if test="${modelo.getIdmodelo() == modelo_id}">
                                selected
                        </c:if>>
                        ${modelo.getNombremodelo()}
                        </option>
                        </c:if>
                    </c:forEach>
                </select>

                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="version">Version:</label>
                    <span style="color:red">${mensajeError6}</span>
                </div>
                <select id="version" name="version" class="form-select mb-1">
                    <option value="-1">Seleccionar</option>
                    <c:forEach var="version" items="${versiones}">
                        <c:if test="${version.getModelo().getIdmodelo() == modelo_id}">
                            <option value="${version.getIdversion()}" <c:if
                                test="${version.getIdversion() == version_id}"> selected
                        </c:if>>
                        ${version.getNombreversion()}
                        </option>
                        </c:if>
                    </c:forEach>
                </select>

                <div class="form-outline" style="display: flex; justify-content: space-between;">
                    <label for="año">Año:</label>
                    <span style="color:red">${mensajeError7}</span>
                </div>
                <select id="año" name="año" class="form-select mb-1">
                    <option value="-1">Seleccionar</option>
                    <c:forEach var="año" items="${años}">
                        <option value="${año}" <c:if test="${año==año_valor}"> selected </c:if>>
                            ${año}
                        </option>
                    </c:forEach>
                </select>

            </div>

            <div class="col-md-6 mb-3">
            </div>
        </div>
        <div class="row" style="display: flex; justify-content: space-between;">
            <div class="col-md-6 form-dark mb-4">
            </div>
            <div class="col-md-3 form-dark mb-4">
                <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion" value="Cancelar"
                    formaction="solicitar_poliza" formmethod="POST">
                    Cancelar
                </button>
            </div>
            <div class="col-md-3 form-dark mb-4">
                <button class="fw-bold form-control btn-action btn-lg" type="submit" name="accion" value="cargarVehiculo"
                    formaction="solicitar_poliza" formmethod="POST">
                    Siguiente
                </button>
            </div>
        </div>
        </div>
    </form>

    <!-- AQUI VAN LOS SCRIPTS -->

    <script>
        function cargarModelos() {
            //OBTENER INDICE
            var indice = document.getElementById("marca").value;

            //OBTENER LISTA
            const $select = document.querySelector("#modelo");

            //LIMPIAR LISTA
            for (let i = $select.options.length; i >= 1; i--) {
                $select.remove(i);
            }

            //AGREGAR ELEMENTOS
            <c:forEach var="modelo" items="${modelos}">
                var num = "${modelo.getMarca().getIdmarca()}"
                option = document.createElement('option');
                option.value = "${modelo.getIdmodelo()}";
                option.text = "${modelo.getNombremodelo()}";
                if(num == indice){
                    $select.appendChild(option)
                }
            </c:forEach>

            cargarVersiones();

            //alert("Funciona");
        }

        function cargarVersiones() {
            //OBTENER INDICE
            var indice = document.getElementById("modelo").value;

            //OBTENER LISTA
            const $select = document.querySelector("#version");

            //LIMPIAR LISTA
            for (let i = $select.options.length; i >= 1; i--) {
                $select.remove(i);
            }

            //AGREGAR ELEMENTOS
            <c:forEach var="version" items="${versiones}">
                var num = "${version.getModelo().getIdmodelo()}"
                option = document.createElement('option');
                option.value = "${version.getIdversion()}";
                option.text = "${version.getNombreversion()}";
                if(num == indice){
                    $select.appendChild(option)
                }
            </c:forEach>
            //alert("Funciona");
        }
    </script>

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