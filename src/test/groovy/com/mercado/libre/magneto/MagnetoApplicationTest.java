package com.mercado.libre.magneto;

import com.mercado.libre.magneto.dominio.SecuenciaADN;
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@RunWith(SpringRunner.class)
public class MagnetoApplicationTest {

    @Test
    public void testMagnetoApplication(){}

}