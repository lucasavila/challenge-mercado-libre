package com.mercado.libre.magneto.dominio

import spock.lang.Specification;

public class SecuenciaADNDominioTest extends Specification{
    def "SecuenciaADN constructor por defecto"(){
        when:
            def secuencia = new SecuenciaADN()
        then:
            secuencia != null
            secuencia.dna == null
            secuencia.esMutante==null
            secuencia.id==null
    }

    def "SecuenciaADN constructor parametrizado"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            Boolean esMutante = true
        when:
            def secuencia = new SecuenciaADN(dna, esMutante)
        then:
            secuencia.dna == dna
            secuencia.esMutante== true
    }

    def "SecuenciaADN get DNA"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            Boolean esMutante = true
            def secuencia = new SecuenciaADN(dna, esMutante)
        when:
            def dnaResultado = secuencia.getDna()
        then:
            dnaResultado == dna
    }

    def "SecuenciaADN get Es Mutante"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            Boolean esMutante = true
            def secuencia = new SecuenciaADN(dna, esMutante)
        when:
            def esMutanteResultado = secuencia.getEsMutante()
        then:
            esMutanteResultado == esMutante
    }

    def "SecuenciaADN get id"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            Boolean esMutante = true
            def secuencia = new SecuenciaADN(dna, esMutante)
        when:
            def esMutanteResultado = secuencia.getId()
        then:
            esMutanteResultado == null
    }


    def "SecuenciaADN set id"(){
        given:
            def secuencia = new SecuenciaADN()
            Long id = 1L
        when:
            secuencia.setId(id)
            def idResultado = secuencia.getId()
        then:
            idResultado == id
    }

    def "SecuenciaADN set esMutante"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            Boolean esMutante = true
            def secuencia = new SecuenciaADN()
        when:
            secuencia.setEsMutante(esMutante)
            def esMutanteResultado = secuencia.getEsMutante()
        then:
            esMutanteResultado == esMutante
    }

    def "SecuenciaADN set dna"(){
        given:
            String[] dna = ['aaa', 'bbb', 'ccc']
            def secuencia = new SecuenciaADN()
        when:
            secuencia.setDna(dna)
            def dnaResultado = secuencia.getDna()
        then:
        dnaResultado == dna
    }


}