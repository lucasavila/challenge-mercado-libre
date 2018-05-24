package com.mercado.libre.Magneto.exceptions

import spock.lang.Specification

public class NoMutanteExceptionTest extends Specification{
    def "SecuenciaADN constructor por defecto"(){
        when:
            throw new NoMutanteException("mensaje")
        then:
            def ex = thrown(NoMutanteException)
            ex.message == "mensaje"
    }

}