package com.mycompany.web.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

public class MercadoPago {

    public Preference crearPreferencia(String titulo, String descripcion, int cantidad, double precio) throws MPException, MPApiException{
        String PROD_ACCESS_TOKEN = "TEST-818817132235323-100220-89e9fbeb7bff5586eb7aaa08f9ac36b3-1209412579";
        MercadoPagoConfig.setAccessToken(PROD_ACCESS_TOKEN);
        PreferenceClient client = new PreferenceClient();

        ArrayList<PreferenceItemRequest> items = new ArrayList<PreferenceItemRequest>();
        PreferenceItemRequest item = PreferenceItemRequest.builder()
            .title(titulo +"")
            .description(descripcion)
            .quantity(cantidad)
            .unitPrice(new BigDecimal(precio))
            .build();
        items.add(item);
                    
        PreferenceBackUrlsRequest urls = PreferenceBackUrlsRequest.builder()
        .success("http://localhost:8080/intro-servlets/webhooks?accion=guardarPoliza")
        .pending("http://localhost:8080/intro-servlets/fallo.jsp")
        .failure("http://localhost:8080/intro-servlets/fallo.jsp")
        .build();

        PreferenceRequest req = PreferenceRequest.builder()
        .backUrls(urls)
        .items(items).build();

        Preference pref = client.create(req);
        
        return pref;
    }
}


        