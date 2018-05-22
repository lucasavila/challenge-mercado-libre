package com.mercado.libre.Magneto.services;

import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import com.mercado.libre.Magneto.repositories.SecuenciaADNRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class ADNService{

    @Autowired
    private SecuenciaADNRepository secuenciaADNRepository;

    @Autowired
    private SecuenciaActorService secuenciaActorService;

    public void save(SecuenciaADN secuenciaADN, Boolean esMutante){
        secuenciaADN.setEsMutante(esMutante);
//        secuenciaADNRepository.save(secuenciaADN);
        secuenciaActorService.saveMessage(secuenciaADN, secuenciaADNRepository);

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