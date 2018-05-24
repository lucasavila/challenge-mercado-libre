package com.mercado.libre.Magneto.exceptions

import spock.lang.Specification

public class NoMutanteExceptionTest extends Specification{
    def "se ejecuta No Mutante Exception"(){
        when:
            throw new NoMutanteException("mensaje")
        then:
            def ex = thrown(NoMutanteException)
            ex.message == "mensaje"
    }

}