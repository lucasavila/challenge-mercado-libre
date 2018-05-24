package com.mercado.libre.magneto.controllers
import com.mercado.libre.magneto.dominio.SecuenciaADN
import com.mercado.libre.magneto.services.IADNService
import com.mercado.libre.magneto.services.IValidateADNService
import spock.lang.Specification

public class ADNControllerTest extends Specification {


    def "validate controller is mutant"() {
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