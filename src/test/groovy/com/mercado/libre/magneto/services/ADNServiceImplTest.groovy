package com.mercado.libre.magneto.services

import com.mercado.libre.magneto.dominio.SecuenciaADN
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository
import com.mercado.libre.magneto.services.impl.ADNServiceImpl
import org.json.JSONObject
import spock.lang.Specification

public class ADNServiceImplTest extends Specification{


    def "validateADNtest GetStats"(){
        given:
            ADNServiceImpl adnService = new ADNServiceImpl()
            def secuenciaADNRepository = Mock(SecuenciaADNRepository)
            adnService.secuenciaADNRepository = secuenciaADNRepository
            JSONObject response;
        when:
            def json = adnService.getStats()
            response= new JSONObject(json);
        then:
            1 * adnService.secuenciaADNRepository.cuentaMutantes() >> 1L
            1 * adnService.secuenciaADNRepository.cuentaHumanos() >> 1L
            response.count_human_dna ==1L
            response.count_mutant_dna==1L
            response.ratio=="1"

    }

    def "test ES Mutante"() {
        given:
        JSONObject response;
        ADNServiceImpl adnService = new ADNServiceImpl()
        Boolean esMutante = true;
        when:
        def json = adnService.getRetornoResponse(esMutante)
        response = new JSONObject(json)
        then:
        response.esMutante == true
    }

    def "test NO es Mutante"(){
        given:
            JSONObject response;
            ADNServiceImpl adnService = new ADNServiceImpl()
            Boolean esMutante = false;
        when:
            def json = adnService.getRetornoResponse(esMutante)
            response = new JSONObject(json)
        then:
            response.esMutante==false

    }

    def "save"(){
        given:
            ADNServiceImpl adnService = new ADNServiceImpl()
            def secuenciaADNRepository = Mock(SecuenciaADNRepository)
            adnService.secuenciaADNRepository = secuenciaADNRepository
            SecuenciaADN secuenciaADN = new SecuenciaADN();
            def secuenciaActorService = Mock(ISecuenciaActorService)
            adnService.secuenciaActorService = secuenciaActorService
            Boolean esMutante = true
        when:
            adnService.save(secuenciaADN, esMutante)
        then:
            1 * adnService.secuenciaActorService.saveMessage(_, _) >> "OK"
            true == true

    }

}
