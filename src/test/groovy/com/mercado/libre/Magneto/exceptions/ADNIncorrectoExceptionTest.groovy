package com.mercado.libre.Magneto.exceptions

import spock.lang.Specification

public class ADNIncorrectoExceptionTest extends Specification{
    def "se ejecuta ADN Incorrecto Exception"(){
        when:
            throw new ADNIncorrectoException("mensaje")
        then:
            def ex = thrown(ADNIncorrectoException)
            ex.message == "mensaje"
    }

}