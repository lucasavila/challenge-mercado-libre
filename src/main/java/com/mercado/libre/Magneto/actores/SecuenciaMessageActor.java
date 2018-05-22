package com.mercado.libre.Magneto.actores;

import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import com.mercado.libre.Magneto.repositories.SecuenciaADNRepository;
import groovyx.gpars.actor.DynamicDispatchActor;
import org.apache.log4j.Logger;

import java.util.Map;

public class SecuenciaMessageActor extends DynamicDispatchActor {

    public void onMessage(Map message) {

        try {

            SecuenciaADN secuenciaADN = (SecuenciaADN)message.get("secuenciaADN");
            SecuenciaADNRepository secuenciaADNRepository = (SecuenciaADNRepository)message.get("secuenciaADNRepository");
            secuenciaADNRepository.saveAndFlush(secuenciaADN);

        }
        catch (RuntimeException exception) {
            Logger.getLogger(SecuenciaMessageActor.class).error("Error al ejecutar insert");
        }
    }
}
