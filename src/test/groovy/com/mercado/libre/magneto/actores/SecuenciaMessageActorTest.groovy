package com.mercado.libre.magneto.actores

import com.mercado.libre.magneto.dominio.SecuenciaADN
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository
import com.mercado.libre.magneto.services.impl.SecuenciaActorServiceImpl
import spock.lang.Specification

public class SecuenciaMessageActorTest extends Specification{

    def "SecuenciaMessageActor onMessage"(){
        given:
            SecuenciaMessageActor secuenciaMessageActor = new SecuenciaMessageActor()
            def secuenciaADNRepository = Mock(SecuenciaADNRepository)
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            def message = [secuenciaADN: secuenciaADN, secuenciaADNRepository: secuenciaADNRepository]
        when:
            secuenciaMessageActor.onMessage(message)
        then:
            1 * secuenciaADNRepository.saveAndFlush(_) >> "OK"
            true==true
    }

    def "SecuenciaMessageActor onMessage RuntimeExceptions"(){
        given:
            SecuenciaMessageActor secuenciaMessageActor = new SecuenciaMessageActor()
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            def message = [secuenciaADN: secuenciaADN]
        when:
            secuenciaMessageActor.onMessage(message)
        then:
            true == true
    }
}
