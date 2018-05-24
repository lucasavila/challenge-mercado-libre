package com.mercado.libre.magneto.repositories;

import com.mercado.libre.magneto.dominio.SecuenciaADN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecuenciaADNRepository extends JpaRepository<SecuenciaADN, Long> {

    @Query("select count(1) from SecuenciaADN where esMutante=true")
    Long cuentaMutantes();

    @Query("select count(1) from SecuenciaADN where esMutante=false")
    Long cuentaHumanos();
}