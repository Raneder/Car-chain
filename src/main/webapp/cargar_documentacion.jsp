<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cargar Documentacion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">

<script>
    function guardarDatos() {
        var llave = true;
        var mensaje1 = "";
        var mensaje2 = "";
        var mensaje3 = "";
        var mensaje4 = "";
        var mensaje5 = "";
        var mensaje6 = "";
        if(document.getElementById("input_foto_frontal").value==""){ 
            mensaje1 = "Archivo No Subido";
            llave = false;
        }
        if(document.getElementById("input_foto_trasera").value==""){ 
            mensaje2 = "Archivo No Subido";
            llave = false;
        }
        if(document.getElementById("input_foto_lateral_1").value==""){ 
            mensaje3 = "Archivo No Subido";
            llave = false;
        }
        if(document.getElementById("input_foto_lateral_2").value==""){ 
            mensaje4 = "Archivo No Subido";
            llave = false;
        }
        if(document.getElementById("input_foto_techo").value==""){ 
            mensaje5 = "Archivo No Subido";
            llave = false;
        }
        if(document.getElementById("input_cedula_verde").value==""){ 
            mensaje6 = "Archivo No Subido";
            llave = false;
        }
        document.getElementById("mensajeError1").textContent = mensaje1;
        document.getElementById("mensajeError2").textContent = mensaje2;
        document.getElementById("mensajeError3").textContent = mensaje3;
        document.getElementById("mensajeError4").textContent = mensaje4;
        document.getElementById("mensajeError5").textContent = mensaje5;
        document.getElementById("mensajeError6").textContent = mensaje6;
        return llave;
    }

    function validateFileType(numero, input_texto, mensaje){
        var fileName = document.getElementById(input_texto).value;
        var idxDot = fileName.lastIndexOf(".") + 1;
        var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
        if (extFile=="jpg"){
            document.getElementById(mensaje).textContent = "";
        }else{
            document.getElementById(input_texto).value= null;
            document.getElementById(mensaje).textContent = "Archivo Invalido";
        }

        var file    = document.querySelector('#'+ input_texto).files[0];
        const reader  = new FileReader();

        reader.addEventListener("load", () => {
            localStorage.setItem("imagen_" + numero, reader.result);
	    });

	    reader.readAsDataURL(file);
    }
</script>

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
<form action="solicitar_poliza" method="post" onsubmit="return guardarDatos()" enctype="multipart/form-data">
    
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
        <!-- Formulario vehiculo -->
        <!-- Botones de la izquiera -->

        <div class="container col-8">
            <h4>Documentación del Vehículo</h4>
            <div class="error-message mb-3" style="color:red">
                ${mensajeError7}
            </div>

            <div class="row"">

                <div class=" col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="foto_frontal" class="fw-bold">Foto Frontal: </label>
                    <span id="mensajeError1" style="color:red">${mensajeError1}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="foto_frontal" id="input_foto_frontal" 
                    accept=".jpg" onchange="validateFileType(1, 'input_foto_frontal', 'mensajeError1')"/>
                </div>

            </div>

            <div class="col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="foto_trasera" class="fw-bold">Foto Trasera: </label>
                    <span id="mensajeError2" style="color:red">${mensajeError2}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="foto_trasera" id="input_foto_trasera"
                    accept=".jpg" onchange="validateFileType(2, 'input_foto_trasera', 'mensajeError2')"/>
                </div>

            </div>

            <div class="col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="foto_lateral_1" class="fw-bold">Foto Lateral 1: </label>
                    <span id="mensajeError3" style="color:red">${mensajeError3}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="foto_lateral_1" id="input_foto_lateral_1"
                    accept=".jpg" onchange="validateFileType(3, 'input_foto_lateral_1', 'mensajeError3')"/>
                </div>

            </div>

            <div class="col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="foto_lateral_2" class="fw-bold">Foto Lateral 2: </label>
                    <span id="mensajeError4" style="color:red">${mensajeError4}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="foto_lateral_2" id="input_foto_lateral_2"
                    accept=".jpg" onchange="validateFileType(4, 'input_foto_lateral_2', 'mensajeError4')"/>
                </div>

            </div>

            <div class="col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="foto_techo" class="fw-bold">Foto Techo: </label>
                    <span id="mensajeError5" style="color:red">${mensajeError5}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="foto_techo" id="input_foto_techo"
                    accept=".jpg" onchange="validateFileType(5, 'input_foto_techo', 'mensajeError5')"/>
                </div>
            </div>

            <div class="col-md-6 mb-2">
                <div class="form-outline mb-1" style="display: flex; justify-content: space-between;">
                    <label for="cedula_verde" class="fw-bold">Cedula Verde: </label>
                    <span id="mensajeError6" style="color:red">${mensajeError6}</span>
                </div>
                <div class="file-select col-md-12">
                    <input type="file" class="custom-file-input col-12" name="cedula_verde" id="input_cedula_verde"
                    accept=".jpg" onchange="validateFileType(6, 'input_cedula_verde', 'mensajeError6')"/>
                </div>

            </div>
            <div class="col-md-6 mb-3">
            </div>
        </div>
        <div class="row" style="display: flex; justify-content: space-between;">
            <div class="col-md-6 form-dark mb-4">
            </div>
            <div class="col-md-3 form-dark mb-4">
                <button class="fw-bold form-control btn-action btn-lg" type="submit"
                name="accion" value="Cancelar">
                    Cancelar
                </button>
            </div>
            <div class="col-md-3 form-dark mb-4">
                <button class="fw-bold form-control btn-action btn-lg" type="submit" 
                name="accion" value="cargarDocumentacion">
                    Siguiente
                </button>
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