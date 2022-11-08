<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Static Navbar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body onload="prueba()">
    <style>
        .spinner{
	position: absolute;
	left: 46%;
	top: 40%;
	transform: translate(-50%, -50%);
	width: 100px;
	height: 100px;
	border-radius: 50%;
	border: 8px solid lightgray;
	border-top:  8px solid rgb(138, 166, 237);
	border-bottom:  8px solid rgb(138, 166, 237);
	animation: anime 1.4s ease infinite;
}

@keyframes anime{
	from{ transform: rotate(0deg); }
	to{ transform: rotate(360deg); }
}

.spinner::before{
	position: absolute;
	content: "";
	width: 200%;
	height: 200%;
	left: 50%;
	top: 50%;
	border-radius: inherit;
	opacity: 0.6;
	transform: translate(-50%, -50%);
	border: 10px solid lightgray ;
	border-left: 10px solid rgb(78, 109, 245) ;
	border-right: 10px solid rgb(78, 109, 245) ;
}

.spinner::after{
	position: absolute;
	content: "";
	width: 300%;
	height: 300%;
	left: 50%;
	top: 50%;
	border-radius: inherit;
	opacity: 0.6;
	transform: translate(-50%, -50%);
	border: 12px solid lightgray ;
	border-top: 12px solid blue ;
	border-bottom: 12px solid blue ;				
}



  div.textoAbajo{
    vertical-align: bottom;
    text-align: center;
  }


  .card {
	margin:  auto; /* Added */
	float: none; /* Added */
	margin-bottom: 10px; /* Added */
	margin-top: 20px;
}

.card-img-overlay{
	margin-top: 85px;
	margin-left: 10px;
}
    </style>
    <form name="formulario" action="polizas" method="POST">

        <h1 style="text-align: center; margin-top: 90px;"> Desplegando contrato en la BlockChain...</h1>
        <div class="contenedorcarga">
            <div class="spinner"></div>
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
        
        <script>
            function prueba(){
                let form = document.createElement('form');
                form.action = 'http://localhost:8080/intro-servlets/polizas';
                form.method = 'POST';

                form.innerHTML = '<input type="hidden" name="accion" value="guardarPoliza"><input type="hidden" name="status" value="approved">';

                // el formulario debe estar en el document para poder enviarlo
                document.body.append(form);

                form.submit();
            }
        </script>
</body>

</html>