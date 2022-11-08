<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cargar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
</head>

<body>
    <!-- Nav -->
    <form action="">
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
                    <div class="navbar-nav ms-auto">
                        <button type="submit" class="nav-item btn-login" name="accion" formaction="login"
                            formmethod="POST" value="login">
                            Iniciar Sesión
                        </button>
                        <button type="submit" class="nav-item btn btn-primary" name="accion" formaction="register"
                            formmethod="POST" value="solicitarRegistro" style="background-color: black; border-color: white;">
                            Registrarse
                        </button>
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
                        <li class="breadcrumb-item"><a  href="inicio">Home</a></li>
                        <li class="breadcrumb-item">Cotización del vehículo</a></li>
                    </ol>
                </div>
            </nav>
            <!-- Final BreadCrumb (Navegacion) -->
            <!-- Formulario vehiculo -->
            <!-- Botones de la izquiera -->

            <div class="container col-8">
                <h4> Información del Cliente</h4>
                <div class="error-message mb-3" style="color:red">
                    ${mensajeError11}
                </div>

                <div class="row"">
                    <div class=" col-md-6">
                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="nombres">Nombre/s:</label>
                        <span style="color:red">${mensajeError1}</span>
                    </div>
                    <input type="text" name="nombres" placeholder="Nombre/s" class="form-control mb-2"
                        value="${nombres}" />

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="apellidos">Apellido/s:</label>
                        <span style="color:red">${mensajeError2}</span>
                    </div>
                    <input type="text" name="apellidos" placeholder="Apellido/s" class="form-control mb-2"
                        value="${apellidos}" />

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="tipo_documento">Tipo de Documento:</label>
                        <span style="color:red">${mensajeError3}</span>
                    </div>
                    <select id="tipo_documento" name="tipo_documento" class="form-control mb-2">
                        <option value="-1">Seleccionar</option>
                        <c:forEach var="tipodedoc" items="${tiposdedoc}">
                            <option value="${tipodedoc.getId()}" <c:if test="${tipodedoc.getId() == tipo_documento}">
                                selected </c:if>>
                                ${tipodedoc.getAbreviatura()}
                            </option>
                        </c:forEach>
                    </select>

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="documento">Documento:</label>
                        <span style="color:red">${mensajeError4}</span>
                    </div>
                    <input type="number" name="documento" placeholder="Documento" class="form-control mb-2"
                        value="${documento}" />

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="date">Fecha de Nacimiento:</label>
                        <span style="color:red">${mensajeError5}</span>
                    </div>
                    <input type="Date" name="date" class="form-control mb-2" value="${date}" />

                </div>
                <div class="col-md-6">

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="telefono">Telefono:</label>
                        <span style="color:red">${mensajeError6}</span>
                    </div>
                    <input type="number" name="telefono" placeholder="Telefono" class="form-control mb-2"
                        value="${telefono}" />

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="sexo">Sexo:</label>
                        <span style="color:red">${mensajeError7}</span>
                    </div>
                    <select id="sexo" name="sexo" class="form-control mb-2" placeholder="Sexo">
                        <option value="-1">Seleccionar</option>
                        <option value="0" <c:if test="${sex == 0}"> selected </c:if>>
                            Mujer
                        </option>
                        <option value="1" <c:if test="${sex == 1}"> selected </c:if>>
                            Hombre
                        </option>
                    </select>

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="provincia">Provincia:</label>
                        <span style="color:red">${mensajeError8}</span>
                    </div>
                    <select id="provincia" name="provincia" class="form-control mb-2" onchange="cargarLocalidades()">
                        <option value="-1">Seleccionar</option>
                        <c:forEach var="provincia" items="${provincias}">
                            <option value="${provincia.getIdprovincia()}" <c:if
                                test="${provincia.getIdprovincia() == prov_id}"> selected </c:if>>
                                ${provincia.getDescripcionprovincia()}
                            </option>
                        </c:forEach>
                    </select>

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="localidad">Localidad:</label>
                        <span style="color:red">${mensajeError9}</span>
                    </div>
                    <select id="localidad" name="localidad" class="form-control mb-2">
                        <option value="-1">Seleccionar</option>
                        <c:forEach var="localidad" items="${localidades}">
                            <c:if test="${localidad.getProvincia().getIdprovincia() == prov_id}">
                                <option value="${localidad.getIdlocalidad()}" <c:if
                                    test="${localidad.getIdlocalidad() == loc_id}"> selected
                            </c:if>>
                            ${localidad.getDescripcionlocalidad()}
                            </option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <div class="form-outline" style="display: flex; justify-content: space-between;">
                        <label for="domicilio">Domicilio:</label>
                        <span style="color:red">${mensajeError10}</span>
                    </div>
                    <input type="text" name="domicilio" placeholder="Domicilio" class="form-control mb-2"
                        value="${domicilio}" />

                </div>
            </div>

            <div class="col-md-6 mb-3">
            </div>

            <div class="row" style="display: flex; justify-content: space-between;">
                <div class="col-md-6 form-dark mb-4">
                </div>
                <div class="col-md-3 form-dark mb-4">
                    <button class="fw-bold form-control btn-action btn-lg" type="submit" 
                    name="accion" value="Cancelar" formaction="solicitar_poliza" formmethod="POST">
                        Cancelar
                    </button>
                </div>
                <div class="col-md-3 form-dark mb-4">
                    <button class="fw-bold form-control btn-action btn-lg" type="submit" 
                    name="accion" value="cargarDatosPersonales" formaction="solicitar_poliza" formmethod="POST">
                        Confirmar
                    </button>
                </div>
            </div>
            </div>
        </form>

        <!--FUNCIONES SCRIPT -->
        <script>
            function cargarLocalidades() {
                //OBTENER INDICE
                var indice = document.getElementById("provincia").value;

                //OBTENER LISTA
                const $select = document.querySelector("#localidad");

                //LIMPIAR LISTA
                for (let i = $select.options.length; i >= 0; i--) {
                    $select.remove(i);
                }
                //AGREGAR OPCION 1
                var option = document.createElement('option');
                option.value = -1;
                option.text = "Seleccionar";
                $select.appendChild(option);

                //AGREGAR ELEMENTOS
                <c:forEach var="localidad" items="${localidades}">
                    var num = "${localidad.getProvincia().getIdprovincia()}"
                    option = document.createElement('option');
                    option.value = "${localidad.getIdlocalidad()}";
                    option.text = "${localidad.getDescripcionlocalidad()}";
                    if(num == indice){
                        $select.appendChild(option)
                    }
                </c:forEach>

                //alert("Funciona");
            }
        </script>

    </section>
</body>

</html>