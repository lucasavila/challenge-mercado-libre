package com.mercado.libre.magneto.configuracion

import spock.lang.Specification

import javax.persistence.EntityManagerFactory

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


    def "hibernate configuration transactionManager"() {
        given:
            HibernateConfiguration hibernateConfiguration= new HibernateConfiguration()
            EntityManagerFactory entityManagerFactory = null
        when:
            def transactionManager = hibernateConfiguration.transactionManager(entityManagerFactory)
        then:
            transactionManager !=null

    }


}