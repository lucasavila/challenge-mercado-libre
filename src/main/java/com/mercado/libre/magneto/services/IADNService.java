package com.mercado.libre.magneto.services;

import com.mercado.libre.magneto.dominio.SecuenciaADN;

public interface IADNService {

    public void save(SecuenciaADN secuenciaADN, Boolean esMutante);
    public String getStats();
    public String getRetornoResponse(Boolean esMutante);

}