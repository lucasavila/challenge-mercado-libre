package com.mercado.libre.magneto.services.impl;

import com.mercado.libre.magneto.actores.ActorService;
import com.mercado.libre.magneto.actores.SecuenciaMessageActor;
import com.mercado.libre.magneto.dominio.SecuenciaADN;
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository;
import com.mercado.libre.magneto.services.ISecuenciaActorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecuenciaActorServiceImpl extends ActorService<SecuenciaMessageActor> implements ISecuenciaActorService {

    SecuenciaActorServiceImpl() {
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
