package com.mercado.libre.Magneto.services

import com.mercado.libre.Magneto.services.impl.ValidateADNServiceImpl
import spock.lang.Specification

public class ValidateADNServiceImplImplTest extends Specification{


    def "test getMatriz"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String [] arrayStrings = ['aaa', 'bbb', 'ccc']

            String[][] matrizResultante = [['a','a', 'a'], ['b','b','b'], ['c' ,'c', 'c'] ];

        when:
            def matriz = validateADNService.getMatriz(arrayStrings)
        then:
            matrizResultante == matriz
    }

    def "test calculo columna"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [[fila,'a','a','a'], [fila,'b','b', 'b'], [fila ,'c', 'c', 'c'], ['d' ,'d', 'd', 'd'] ];
            Integer fil = 0
            Integer col = 0

        when:
            Long resultadoCalculo = validateADNService.calculoColumna(matrizString, fil, col)
        then:
            resultado == resultadoCalculo
        where:
            fila   | resultado
            'a'    | 0
            'd'    | 1
    }

    def "test calculo diagonal descendente"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [[fila,'a','a','a'], ['b',fila,'b', 'b'], ['c', 'c',fila, 'c'], ['d' ,'d', 'd', 'd'] ];
            Integer fil = 0
            Integer col = 0

        when:
            Long resultadoCalculo = validateADNService.calculoDiagonalDesc(matrizString, fil, col)
        then:
            resultado == resultadoCalculo
        where:
            fila   | resultado
            'a'    | 0
            'd'    | 1
    }

    def "test calculo diagonal ascendente"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [['a','a','a', fila], ['b','b',fila, 'b'], ['c',fila, 'c', 'c'], ['d' ,'d', 'd', 'd'] ];
            Integer fil = 0
            Integer col = 3

        when:
            Long resultadoCalculo = validateADNService.calculoDiagonalAsc(matrizString, fil, col)
        then:
            resultado == resultadoCalculo
        where:
            fila   | resultado
            'a'    | 0
            'd'    | 1
    }

    def "test calculo fila"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [['a','a','a', fila], ['b','b','a', 'b'], ['c','x', 'c', 'c'], ['d' ,'a', 'd', 'd'] ];
            Integer fil = 0
            Integer col = 0

        when:
            Long resultadoCalculo = validateADNService.calculoFila(matrizString, fil, col)
        then:
            resultado == resultadoCalculo
        where:
            fila   | resultado
            'd'    | 0
            'a'    | 1
    }


    def "test calculo is mutant"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()

            String [] arrayStrings = ['aaa'+fila, 'basa', 'cfsa', 'ddd'+columna]

        when:
            Boolean resultadoCalculo = validateADNService.isMutant(arrayStrings)
        then:
            resultado == resultadoCalculo
        where:
            fila   | columna | resultado
            'a'    | 'a'     | true
            'a'    | 'b'     | false
    }

    def "test calculo calcular Mutante"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [['a','a','a', fila], ['b','b','a', 'a'], ['c','x', 'c', 'a'], ['d' ,'a', 'd', columna] ];
        when:
            Boolean resultadoCalculo = validateADNService.calcularMutante(matrizString)
        then:
            resultado == resultadoCalculo
        where:
            fila   | columna | resultado
            'a'    | 'a'     | true
            'a'    | 'b'     | false
    }

    def "test calculo calcular matriz"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String[][] matrizString = [['a','a','a', fila], ['b','b','a', 'a'], ['c','x', 'c', 'a'], ['d' ,'a', 'd', columna] ];
        when:
            Boolean resultadoCalculo = validateADNService.calculaADN(matrizString)
        then:
            resultado == resultadoCalculo
        where:
            fila   | columna | resultado
            'a'    | 'a'     | true
            'a'    | 'b'     | true
    }

    def "test calculo ADN incorrecto"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
        when:
            Boolean resultadoCalculo = validateADNService.lineaInvalida(linea)
        then:
            resultado == resultadoCalculo
        where:
            linea       | resultado
            'aaaatcga'  | false
            'aaaa'      | false
            'tttt'      | false
            'cccc'      | false
            'gggg'      | false
            'xxxx'      | true
            '!"#!'      | true
    }

    def "test calculo linea invalida"(){
        given:
            ValidateADNServiceImpl validateADNService = new ValidateADNServiceImpl()
            String [] array = [
                    linea,
                    'tacgtacg',
                    'tacgtacg',
                    'tacgtacg',
                    'tacgtacg',
                    'tacgtacg',
                    'tacgtacg',
                    'tacgtacg'
            ]
        when:
            Boolean resultadoCalculo = validateADNService.adnIncorrecto(array)
        then:
            resultado == resultadoCalculo
        where:
            linea       | resultado
            'aaaatacg'  | false
            'aaaatxxx'  | true
    }

}
