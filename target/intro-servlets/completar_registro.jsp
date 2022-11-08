<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Completar Registro</title>
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
                            formmethod="POST" value="Login">
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

    <!--Iniciar Sesión -->
    <section class="vh-85 gradient-custom">
        <form action="solicitar_poliza" method="POST">
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
            <!-- Inicio del Registro del cliente -->
            <div class="container d-flex justify-content-center py-4 h-85">
                <div class="col-12 col-lg-10 col-xl-5">
                    <div class="card bg-light text-dark" style="border-radius: 10px;">
                        <div class="card-body p-4 text-center"> 
                            <h3 class="fw-bold mb-3 text-uppercase">Completar Registro</h3>
                            <div class="error-message mb-1" style="color:red">
                                ${mensajeError4}
                            </div>
                            <div class="row mb-2">
                                <div class="col-md-12">

                                    <div class="form-outline form-dark mb-1"
                                        style="display: flex; justify-content: space-between;">
                                        <label for="correo">Correo: </label>
                                        <span style="color:red">${mensajeError1}</span>
                                    </div>
                                    <input type="text" name="correo" placeholder="Correo"
                                        class="form-control form-control-lg mb-2" value="${correo}" />

                                    <div class="form-outline form-dark mb-1"
                                        style="display: flex; justify-content: space-between;">
                                        <label for="contraseña">Contraseña:</label>
                                        <span style="color:red">${mensajeError2}</span>
                                    </div>
                                    <input type="password" name="contraseña" placeholder="Contraseña"
                                        class="form-control form-control-lg mb-2" value="${contraseña}" />

                                    <div class="form-outline" 
                                    style="display: flex; justify-content: space-between;">
                                        <label for="confirmar_contraseña">Confirmar Contraseña:</label>
                                        <span style="color:red">${mensajeError3}</span>
                                    </div>
                                    <input type="password" name="confirmar_contraseña" placeholder="Confirmar Contraseña"
                                        class="form-control form-control-lg mb-2" value="${confirmar_contraseña}"/>

                                </div>
                            </div>

                            <div class="row" style="display: flex; justify-content: space-between;">
                                <div class="col-md-6 form-dark mb-1">
                                    <button name="accion" class="fw-bold form-control btn-action b bi-google btn-lg" 
                                    type="submit" value="">
                                        Google
                                    </button>
                                </div>
                                <div class="col-md-6 form-dark mb-1">
                                    <button name="accion" class="fw-bold form-control btn-action btn-lg"
                                     type="submit" value="crearCuentaCliente">
                                        Registrarse
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </section>
</body>

</html>