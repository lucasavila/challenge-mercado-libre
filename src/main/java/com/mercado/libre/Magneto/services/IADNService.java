package com.mercado.libre.Magneto.services;

import com.mercado.libre.Magneto.dominio.SecuenciaADN;

public interface IADNService {

    public void save(SecuenciaADN secuenciaADN, Boolean esMutante);
    public String getStats();
    public String getRetornoResponse(Boolean esMutante);

}