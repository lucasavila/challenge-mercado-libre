package com.mercado.libre.Magneto.services;

import com.mercado.libre.Magneto.actores.ActorService;
import com.mercado.libre.Magneto.actores.SecuenciaMessageActor;
import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import com.mercado.libre.Magneto.repositories.SecuenciaADNRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
class SecuenciaActorService extends ActorService<SecuenciaMessageActor> {

    SecuenciaActorService() {
        super(SecuenciaMessageActor.class);
    }

    public String saveMessage(SecuenciaADN secuenciaADN, SecuenciaADNRepository secuenciaADNRepository) {

        Map message = new HashMap<String, String>();
        message.put("secuenciaADN", secuenciaADN);
        message.put("secuenciaADNRepository", secuenciaADNRepository);
        sendMessage(message);

        return "Se agregan datos al mensaje";

    }

}
