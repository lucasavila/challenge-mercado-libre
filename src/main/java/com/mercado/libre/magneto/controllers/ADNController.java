package com.mercado.libre.magneto.controllers;

import com.mercado.libre.magneto.dominio.SecuenciaADN;
import com.mercado.libre.magneto.exceptions.ADNIncorrectoException;
import com.mercado.libre.magneto.exceptions.NoMutanteException;
import com.mercado.libre.magneto.services.IADNService;
import com.mercado.libre.magneto.services.IValidateADNService;
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

    /**
     * @param secuenciaADN: Array de String con el codigo de ADN
     * Se obtiene la entidad y se guarda en una Entidad para su manipulacion y persistencia
     * Método que se encarga de calcular si es o no mutante y persistirlo
     * En caso OK retorna 200 en caso error retorna 403
     * */

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

    /**
     * @return String: retoran un json con los parámetros:
     * count_mutant_dna: cuenta de ADN mutantes
     * count_human_dna: cuenta de ADN humanos
     * ratio: ratio de mutantes/humanos
     * */

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public String stats() {

       return adnService.getStats();
    }

}