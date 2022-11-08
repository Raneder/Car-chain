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
    <script>
        function guardarDatos() {
            var llave = true;
            var mensaje1 = "";
            var mensaje2 = "";
            if(document.getElementById("tipo_contratacion").value < 0){ 
                mensaje1 = "Seleccione Uno *";
                llave = false;
            }
            if(document.getElementById("periodo_pago").value < 0){ 
                mensaje2 = "Seleccione Uno *";
                llave = false;
            }
            document.getElementById("mensajeError1").textContent = mensaje1;
            document.getElementById("mensajeError2").textContent = mensaje2;

            return llave;
        }

        function calcularMonto(){
            const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', });

            var per_id = document.getElementById("periodo_pago").value;
            var monto_or = document.getElementById("monto_original").value;
            <c:forEach var="periodo_pago" items="${periodos_pago}">
                var periodo_id = "${periodo_pago.getIdperiodopago()}";
                var descuento = "${periodo_pago.getDescuentoperiodopago()}";
                var cantidad = "${periodo_pago.getCantidadmesespago()}";

                montoFinal(periodo_id, per_id, monto_or, descuento, cantidad);
            </c:forEach>
            if(per_id == -1){
                document.getElementById("monto_final").textContent = formatter.format(monto_or);
            }
        }

        function montoFinal(periodo_id, per_id, monto_or, descuento, cantidad){
            const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', });
            if(periodo_id==per_id){
                monto_or = (monto_or * (1 - descuento/100))*cantidad;
                document.getElementById("monto_final").textContent = formatter.format(monto_or);
            }
        }
    </script>
    <form action="polizas" method="post" onsubmit="return guardarDatos()">
        <!-- Linea gris abajo del nav -->
        <nav class="d-flex flex-column bd-highlight navbar-light bg-secondary">
            <div style="text-align: center;">
                <strong>
                    <div class="p-2 bd-highlight" style="color: white; font-size: x-large;">
                        PAGAR PÓLIZA
                    </div>
                </strong>
            </div>
        </nav>
        <!-- FINAL DEL Linea gris abajo del nav -->

        <!-- BreadCrumb (Navegacion) -->
        <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb" class="navbar-light bg-light">
            <div class="container col-8">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="perfil_usuario">Cuenta</a></li>
                    <li class="breadcrumb-item"><a href="polizas">Mis Polizas</a></li>
                    <li class="breadcrumb-item"><a href="polizas">Póliza</a></li>
                    <li class="breadcrumb-item">Pagar Póliza</a></li>
                </ol>
            </div>
        </nav>
        <!-- Final BreadCrumb (Navegacion) -->
        <!-- Formulario del Pago -->
        <!-- Botones de la izquierda -->
        <div class="container col-8">
            <br>
            <div class="row" style="display: flex; justify-content: space-between;">
                <div class="col-8">

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="tipo_contratacion">Tipo de Contratación:</label>
                        <span id="mensajeError1" style="color:red">${mensajeError1}</span>
                    </div>
                    <select id="tipo_contratacion" name="tipo_contratacion" class="form-control mb-1" onchange="cargarPeriodoPago()">
                        <option value="-1">Seleccionar</option>
                        <c:forEach var="tipo_contratacion" items="${tipos_contratacion}">
                            <option value="${tipo_contratacion.getIdtipocontratacion()}" 
                            <c:if test="${tipo_contratacion.getIdtipocontratacion() == tipo_id}"> selected </c:if>>
                                ${tipo_contratacion.getNombrecontratacion()}
                            </option>
                        </c:forEach>
                    </select>
                    <div class="mb-3"></div>

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="periodo_pago">Periodo de Pago:</label>
                        <span id="mensajeError2" style="color:red">${mensajeError2}</span>
                    </div>
                    <select id="periodo_pago" name="periodo_pago" class="form-control mb-1" onchange="calcularMonto()">
                        <option value="-1">Seleccionar</option>
                    </select>
                    
                </div>
                <div class="col-4">
                    <center>
                        <h2 class="fw-bold">TOTAL A PAGAR</h2>
                        <br>
                        <h1 id="monto_final" class="fw-bold">${monto_final}</h1>
                        <input type="hidden" id="monto_original" value="${poliza.getLinea_cotizacion().getMonto()}">
                    </center>
                </div>

            </div>
            <div class="row mb-3">
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                </div>
                <div class="col-md-3 form-dark mb-4">
                    <button class="fw-bold form-control btn-action btn-lg"
                    type="submit" name="accion" value="">
                        Cancelar
                    </button>
                </div>
                <div class="col-md-3 form-dark mb-4">
                    <button class="fw-bold form-control btn-action btn-lg"
                    type="submit" name="accion" value="pagarPolizaPorPrimeraVez">
                        Pagar
                    </button>
                </div>
            </div>
        </div>
    </form>

    <script>
        const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD', });
        var monto_original = document.getElementById("monto_original").value;
        document.getElementById("monto_final").textContent = formatter.format(monto_original);

        function cargarPeriodoPago() {
            //OBTENER INDICE
            var indice = document.getElementById("tipo_contratacion").value;

            //AGREGAR ELEMENTOS
            var mes_cont = 0;
            <c:forEach var="tipo_contratacion" items="${tipos_contratacion}">
                var idtipo = "${tipo_contratacion.getIdtipocontratacion()}";
                if(indice == idtipo){
                    mes_cont = "${tipo_contratacion.getCantidadmesescontratacion()}"
                }
            </c:forEach>

            //OBTENER LISTA
            const $select = document.querySelector("#periodo_pago");

            //LIMPIAR LISTA
            for (let i = $select.options.length; i >= 1; i--) {
                $select.remove(i);
            }

            //AGREGAR ELEMENTOS
            <c:forEach var="periodo_pago" items="${periodos_pago}">
                var mes_per = 0;
                mes_per = "${periodo_pago.getCantidadmesespago()}";
                option = document.createElement('option');
                option.value = "${periodo_pago.getIdperiodopago()}";
                option.text = "${periodo_pago.getNombreperiodopago()}";
                comparar(mes_per, mes_cont, $select, option);
            </c:forEach>
        }

        function comparar(mes_per, mes_cont, $select, option){
            var cond = mes_cont - mes_per;
            if(cond >= 0){
                $select.appendChild(option);
            }
        }

    </script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>

</html>