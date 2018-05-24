package com.mercado.libre.Magneto.repositories

import com.mercado.libre.Magneto.controllers.ADNController
import com.mercado.libre.Magneto.dominio.SecuenciaADN
import com.mercado.libre.Magneto.services.IADNService
import com.mercado.libre.Magneto.services.IValidateADNService
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@DataJpaTest
@RunWith(SpringRunner.class)
public class SecuenciaADNRepositoryTest extends Specification {

    @Autowired
    ADNController adnController

    @Autowired
    SecuenciaADNRepository secuenciaADNRepository

    def "validate controller is mutant"() {
        given:
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            secuenciaADN.dna = ['aaaa', 'bbbb', 'cccc', 'dddd']
            adnController.isMutant(secuenciaADN)
        when:
            def resultado = secuenciaADNRepository.cuentaMutantes()
        then:
            resultado == 1L

    }

}