package com.mercado.libre.Magneto.repositories;

import com.mercado.libre.Magneto.controllers.ADNController;
import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class SecuenciaADNRepositoryTest {

//    @Autowired
//    ADNController adnController;

    @Autowired
    SecuenciaADNRepository secuenciaADNRepository;

    @Before
    public void cuentaMutantes() throws Exception {
            SecuenciaADN secuenciaADN = new SecuenciaADN();
            String[] dna = new String[4];
            dna[0] ="aaaa";
            dna[0] ="bbbb";
            dna[0] ="cccc";
            dna[0] ="dddd";
            secuenciaADN.setDna(dna);
            secuenciaADN.setEsMutante(true);
            secuenciaADNRepository.save(secuenciaADN);

            SecuenciaADN secuenciaADN2 = new SecuenciaADN();
            String[] dna2 = new String[4];
            dna2[0] ="aaaa";
            dna2[0] ="bbbb";
            dna2[0] ="cccc";
            dna2[0] ="dddd";
            secuenciaADN2.setEsMutante(false);
            secuenciaADNRepository.save(secuenciaADN2);

    }

    @Test
    public void testSave(){
        SecuenciaADN secuenciaADN3 = new SecuenciaADN();
        String[] dna3 = new String[4];
        dna3[0] ="aaaa";
        dna3[0] ="bbbb";
        dna3[0] ="cccc";
        dna3[0] ="dddd";
        secuenciaADN3.setEsMutante(false);
        SecuenciaADN secuenciaADN = secuenciaADNRepository.save(secuenciaADN3);
        assertThat(secuenciaADN.getDna()==dna3);
    }

    @Test
    public void testCuentaMutantes(){
            Long resultado = secuenciaADNRepository.cuentaMutantes();
            assertThat(resultado==1L).isTrue();
    }

    @Test
    public void testCuentaHumanos(){
        Long resultado = secuenciaADNRepository.cuentaHumanos();
        assertThat(resultado==1L).isTrue();
    }
}