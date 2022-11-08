<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagar Poliza</title>
    <script src="https://sdk.mercadopago.com/js/v2"></script>
</head>
<body>
    <form action="polizas" method="POST">
        <input type="hidden" name="accion" value="guardarPoliza">
        <input type="hidden" id="pref_id" value="${pref_id}">
        <script>
            var pref_id = document.getElementById("pref_id").value;
            const mp = new MercadoPago('TEST-cf94f7fa-6241-460b-86dd-bd6bd2ef750f');

            const checkout = mp.checkout({
                preference: {
                    id: pref_id,
                },
                autoOpen: true, // Habilita la apertura automática del Checkout Pro
            });
        </script>
        
    </form>
</body>

</html>