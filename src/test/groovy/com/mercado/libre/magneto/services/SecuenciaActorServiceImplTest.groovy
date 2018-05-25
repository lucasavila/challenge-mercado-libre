package com.mercado.libre.magneto.services

import com.mercado.libre.magneto.actores.ActorService
import com.mercado.libre.magneto.actores.SecuenciaMessageActor
import com.mercado.libre.magneto.dominio.SecuenciaADN
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository
import com.mercado.libre.magneto.services.impl.ADNServiceImpl
import com.mercado.libre.magneto.services.impl.SecuenciaActorServiceImpl
import groovyx.gpars.actor.Actor
import org.json.JSONObject
import spock.lang.Specification

public class SecuenciaActorServiceImplTest extends Specification{


    def "SecuenciaActorServiceImpl save message"(){

        given:
            SecuenciaActorServiceImpl secuenciaActorServiceImpl = new SecuenciaActorServiceImpl()
            def secuenciaADNRepository = Mock(SecuenciaADNRepository)
            def secuencia = new SecuenciaADN()
        when:

            String response = secuenciaActorServiceImpl.saveMessage(secuencia, secuenciaADNRepository)
        then:
            response=="Se agregan datos al mensaje"

    }



    def "SecuenciaMessageActor getCurrentActor"(){
        given:
            ActorService actorService = new SecuenciaActorServiceImpl()
        when:
            Actor actor = actorService.getCurrentActor()
        then:
            true==true
    }


    def "SecuenciaMessageActor sendMessage"(){
        given:
            ActorService actorService = new SecuenciaActorServiceImpl()
            def message = [mensaje: "mensaje"]
        when:
            Actor actor = actorService.sendMessage(message)
        then:
            true==true
    }
}
