package com.mercado.libre.magneto

import spock.lang.Specification
public class MagnetoApplicationTest extends Specification {


    def "Magneto main"() {
        given:
            MagnetoApplication magnetoApplication = new MagnetoApplication()
        when:
            magnetoApplication.main()
        then:
            true == true
    }
}