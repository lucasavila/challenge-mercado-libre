package com.mercado.libre.magneto.services;

import com.mercado.libre.magneto.dominio.SecuenciaADN;
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository;

public interface ISecuenciaActorService {

    public String saveMessage(SecuenciaADN secuenciaADN, SecuenciaADNRepository secuenciaADNRepository);

}