package com.mercado.libre.Magneto.controllers;

import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import com.mercado.libre.Magneto.exceptions.ADNIncorrectoException;
import com.mercado.libre.Magneto.exceptions.NoMutanteException;
import com.mercado.libre.Magneto.services.IADNService;
import com.mercado.libre.Magneto.services.IValidateADNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ADNController{

    @Autowired
    private IValidateADNService validateADNService;

    @Autowired
    private IADNService adnService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String isMutant(@RequestBody SecuenciaADN secuenciaADN) throws Exception {

        String[] array = secuenciaADN.getDna();
        if(validateADNService.adnIncorrecto(array)){
            throw new ADNIncorrectoException("ADN Incorrecto");
        }
        Boolean esMutante = validateADNService.isMutant(array);
        adnService.save(secuenciaADN, esMutante);

        if(esMutante)
            return adnService.getRetornoResponse(esMutante);

        throw new NoMutanteException("No es Mutante");
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public String stats() {

       return adnService.getStats();
    }

}