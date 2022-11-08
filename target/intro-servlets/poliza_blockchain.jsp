<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Poliza Block-chain</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="CSS/Referencia.css">
</head>
<body>
    <style>
        .spinner {
            position: absolute;
            left: 46%;
            top: 40%;
            transform: translate(-50%, -50%);
            width: 100px;
            height: 100px;
            border-radius: 50%;
            border: 8px solid lightgray;
            border-top: 8px solid rgb(138, 166, 237);
            border-bottom: 8px solid rgb(138, 166, 237);
            animation: anime 1.4s ease infinite;
        }

        @keyframes anime {
            from {
                transform: rotate(0deg);
            }

            to {
                transform: rotate(360deg);
            }
        }

        .spinner::before {
            position: absolute;
            content: "";
            width: 200%;
            height: 200%;
            left: 50%;
            top: 50%;
            border-radius: inherit;
            opacity: 0.6;
            transform: translate(-50%, -50%);
            border: 10px solid lightgray;
            border-left: 10px solid rgb(78, 109, 245);
            border-right: 10px solid rgb(78, 109, 245);
        }

        .spinner::after {
            position: absolute;
            content: "";
            width: 300%;
            height: 300%;
            left: 50%;
            top: 50%;
            border-radius: inherit;
            opacity: 0.6;
            transform: translate(-50%, -50%);
            border: 12px solid lightgray;
            border-top: 12px solid blue;
            border-bottom: 12px solid blue;
        }



        div.textoAbajo {
            vertical-align: bottom;
            text-align: center;
        }


        .card {
            margin: auto;
            /* Added */
            float: none;
            /* Added */
            margin-bottom: 10px;
            /* Added */
            margin-top: 20px;
        }

        .card-img-overlay {
            margin-top: 85px;
            margin-left: 10px;
        }
    </style>

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
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-
                    target="#navbarCollapse">
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
                                    <button style="border: none" class="btn btn-outline-light dropdown-

toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${usuario.getApellido()} ${usuario.getNombres()}
                                        <img src="imagenes/Logo.png" class="rounded-circle" height="30" />
                                    </button>
                                    <div class="dropdown-menu text-center" aria- labelledby="dropdownMenuButton">
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
                                    <button style="border: none" class="btn btn-outline-light dropdown-

toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${cliente.getApellidos()} ${cliente.getNombres()}
                                        <img src="imagenes/Logo.png" class="rounded-circle" height="30" />
                                    </button>
                                    <div class="dropdown-menu text-center" aria- labelledby="dropdownMenuButton">
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

    <div class="contenedorcarga">
        <div class="card" style="width:500px">
            <img class="card-img-top" src="imagenes/contrato.png" alt="Card image">
            <div class="card-img-overlay">
                <div class="form-group" style="padding: 2%;">
                    <label for="nombre">
                        <b>
                            Nombre:
                        </b>
                    </label>
                    <label for="nom">${nombre_completo}</label>
                </div>

                <div class="form-group" style="padding: 2%;">
                    <label for="documentCliente">
                        <b>
                            Documento:
                        </b>
                    </label>
                    <label for="nom">${doc}</label>
                </div>

                <div class="form-group" style="padding: 2%;">
                    <label for="numeroPoliza">
                        <b>
                            Numero de Poliza:
                        </b>
                    </label>
                    <label for="nom">${num_pol}</label>
                </div>

                <div class="form-group" style="padding: 2%;">
                    <label for="fechaVencimiento">
                        <b>
                            Estado de Poliza:
                        </b>
                    </label>
                    <label for="nom">${estado}</label>
                </div>

                <div class="form-group" style="padding: 2%;">
                    <label for="fechaVencimiento">
                        <b>
                            Fecha de Vencimiento:
                        </b>
                    </label>
                    <label for="nom">${fecha}</label>
                </div>


                <div class="form-group" style="padding: 2%;">
                    <label for="matriculaVehiculo">
                        <b>
                            Matricula del Vehiculo:
                        </b>
                    </label>
                    <label for="nom">${matr}</label>
                </div>
                
                <div class="form-group" style="padding: 2%;">
                    <label for="direccionContrato">
                        <b>
                            Direccion de Contrato: 
                        </b>
                    </label>
                    <label for="nom">${contrato}</label>
                </div>

                <br>
                <br>
                <br>
                <br>
                <br>

                <form action="polizas" method="POST">
                    <div class="row" style="display: flex; justify-content: space-between;">
                        <div class="col-md-6 form-dark mb-4">
                        </div>
                        <div class="col-md-5 form-dark mb-4">
                            <button name="accion" class="fw-bold form-control btn-action btn-lg" type="submit"
                                value="iniciarPagoDePoliza">
                                Ir a Polizas
                            </button>
                        </div>
                        <div class="col-md-1 form-dark mb-4">
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</body>

</html>