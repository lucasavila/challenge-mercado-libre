package com.mercado.libre.magneto.services.impl;

import com.mercado.libre.magneto.dominio.SecuenciaADN;
import com.mercado.libre.magneto.repositories.SecuenciaADNRepository;
import com.mercado.libre.magneto.services.IADNService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ADNServiceImpl implements IADNService {

    @Autowired
    private SecuenciaADNRepository secuenciaADNRepository;

    public void save(SecuenciaADN secuenciaADN, Boolean esMutante){
        secuenciaADN.setEsMutante(esMutante);
        secuenciaADNRepository.save(secuenciaADN);
    }

    public String getStats(){
        JSONObject json = new JSONObject();
        Long cuentaMutantes = secuenciaADNRepository.cuentaMutantes();
        Long cuentaHumanos = secuenciaADNRepository.cuentaHumanos();
        json.put("count_mutant_dna", cuentaMutantes);
        json.put("count_human_dna", cuentaHumanos);
        if(cuentaHumanos>0){
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);
            Float ratio = cuentaMutantes.floatValue()/cuentaHumanos.floatValue();
            json.put("ratio",df.format(ratio));
        }
        return json.toString();

    }

    public String getRetornoResponse(Boolean esMutante){
        JSONObject jsonRespuesta = new JSONObject();
        jsonRespuesta.put("esMutante", esMutante);
        return jsonRespuesta.toString();
    }


}