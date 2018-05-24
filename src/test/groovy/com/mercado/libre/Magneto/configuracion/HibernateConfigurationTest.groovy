package com.mercado.libre.Magneto.configuracion

import spock.lang.Specification

public class HibernateConfigurationTest extends Specification {


    def "hibernate configuration getHibernateProperties"() {
        given:
            HibernateProperties hibernateProperties= new HibernateProperties();
            HibernateConfiguration hibernateConfiguration= new HibernateConfiguration()
            hibernateConfiguration.hibernateProperties= hibernateProperties
        when:
            def resultado = hibernateConfiguration.getHibernateProperties()
        then:
            resultado == hibernateProperties

    }

    def "hibernate configuration setHibernateProperties"() {
        given:
            HibernateProperties hibernateProperties= new HibernateProperties();
            HibernateConfiguration hibernateConfiguration= new HibernateConfiguration()
        when:
            hibernateConfiguration.setHibernateProperties(hibernateProperties)
        then:
            hibernateProperties == hibernateConfiguration.hibernateProperties

    }
}