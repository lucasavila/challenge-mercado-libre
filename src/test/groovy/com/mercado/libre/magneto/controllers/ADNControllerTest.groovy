package com.mercado.libre.magneto.controllers
import com.mercado.libre.magneto.dominio.SecuenciaADN
import com.mercado.libre.magneto.exceptions.ADNIncorrectoException
import com.mercado.libre.magneto.exceptions.NoMutanteException
import com.mercado.libre.magneto.services.IADNService
import com.mercado.libre.magneto.services.IValidateADNService
import spock.lang.Specification

public class ADNControllerTest extends Specification {


    def "validate controller es mutante"() {
        given:
            ADNController adnController = new ADNController()
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            secuenciaADN.dna = ['aaaa', 'bbbb', 'cccc', 'dddd']
            def adnService = Mock(IADNService)
            adnController.adnService = adnService
            def validateADNService = Mock(IValidateADNService)
            adnController.validateADNService = validateADNService
        when:
            def resultado = adnController.isMutant(secuenciaADN)
        then:
            1 * validateADNService.adnIncorrecto(_) >> false
            1 * validateADNService.isMutant(_) >> true
            1 * adnService.getRetornoResponse(_) >> true
            1 * adnService.save(_, _)
            resultado == "true"

    }

    def "validate controller no es mutante"() {
        given:
            ADNController adnController = new ADNController()
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            secuenciaADN.dna = ['aaaa', 'bbbb', 'cccc', 'dddd']
            def adnService = Mock(IADNService)
            adnController.adnService = adnService
            def validateADNService = Mock(IValidateADNService)
            adnController.validateADNService = validateADNService
        when:
            adnController.isMutant(secuenciaADN)
        then:
            1 * validateADNService.adnIncorrecto(_) >> false
            1 * validateADNService.isMutant(_) >> false
            1 * adnService.save(_, _)
            def ex = thrown(NoMutanteException)
            ex.message == "No es Mutante"

    }


    def "validate controller adn incorrecto"() {
        given:
            ADNController adnController = new ADNController()
            SecuenciaADN secuenciaADN = new SecuenciaADN()
            secuenciaADN.dna = ['aaaa', 'bbbb', 'cccc', 'dddd']
            def adnService = Mock(IADNService)
            adnController.adnService = adnService
            def validateADNService = Mock(IValidateADNService)
            adnController.validateADNService = validateADNService
        when:
            adnController.isMutant(secuenciaADN)
        then:
            1 * validateADNService.adnIncorrecto(_) >> true
            def ex = thrown(ADNIncorrectoException)
            ex.message == "ADN Incorrecto"

    }

    def "validate controller stats"() {
        given:
            ADNController adnController = new ADNController()
            def adnService = Mock(IADNService)
            adnController.adnService = adnService
        when:
            def resultado = adnController.stats()
        then:
            1 * adnService.getStats() >> "stats"
            resultado == "stats"
    }


}