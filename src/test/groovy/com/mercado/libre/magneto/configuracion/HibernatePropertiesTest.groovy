package com.mercado.libre.magneto.configuracion

import spock.lang.Specification

public class HibernatePropertiesTest extends Specification {


    def "hibernate properties getHbm2ddlAuto"() {
        given:
            HibernateProperties hibernateProperties= new HibernateProperties();
        when:
            def resultado = hibernateProperties.getHbm2ddlAuto()
        then:
            resultado == "none"
    }

    def "hibernate properties isShowSql"() {
        given:
            HibernateProperties hibernateProperties= new HibernateProperties();
        when:
            def resultado = hibernateProperties.isShowSql()
        then:
            resultado == true
    }

    def "hibernate properties getShowSql"() {
        given:
            HibernateProperties hibernateProperties= new HibernateProperties();
        when:
            def resultado = hibernateProperties.getShowSql()
        then:
            resultado == true
    }

    def "hibernate properties setShowSql"() {
        given:
            HibernateProperties hibernateProperties = new HibernateProperties();
        when:

            hibernateProperties.setShowSql(false)
            def resultado = hibernateProperties.isShowSql()
        then:
            resultado == false
    }



    def "hibernate properties setHbm2ddlAuto"() {
        given:
        HibernateProperties hibernateProperties = new HibernateProperties();
        when:

        hibernateProperties.setHbm2ddlAuto("update")
        def resultado = hibernateProperties.getHbm2ddlAuto()
        then:
        resultado == "update"
    }
}