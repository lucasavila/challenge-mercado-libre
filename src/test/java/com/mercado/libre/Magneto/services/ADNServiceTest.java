package com.mercado.libre.Magneto.services;

import com.mercado.libre.Magneto.repositories.SecuenciaADNRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@DataJpaTest
public class ADNServiceTest{

    ADNService adnService;

    @Autowired
    private SecuenciaADNRepository secuenciaADNRepository;


    @Before
    public void setUp() {
        adnService = new ADNService();
//        adnService.secuenciaADNRepository = secuenciaADNRepository;
    }

    @Test
    public void testGetRetornoResponseEsMutante() {
        Boolean esMutante = true;
        adnService.getRetornoResponse(esMutante);
        assertEquals(adnService.getRetornoResponse(esMutante), "{\"esMutante\":true}");
    }

    @Test
    public void testGetRetornoResponseNOEsMutante() {
        Boolean esMutante = false;
        adnService.getRetornoResponse(esMutante);
        assertEquals(adnService.getRetornoResponse(esMutante), "{\"esMutante\":false}");
    }

//    @Test
//    public void testGetStats(){
//
//        adnService.getStats();
//    }
}
