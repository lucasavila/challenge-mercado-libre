package com.mercado.libre.Magneto.controllers;

import com.mercado.libre.Magneto.exceptions.NoMutanteException;
import com.mercado.libre.Magneto.services.ValidateADNService;
import org.json.JSONObject;
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
    private ValidateADNService validateADNService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String isMutant(@RequestBody String secuenciaADN) throws Exception {

        String[] array = validateADNService.getArray(validateADNService.getJSON(secuenciaADN));
        Boolean esMutante = validateADNService.isMutant(array);
        validateADNService.save(array, esMutante);

        if(esMutante){
            JSONObject jsonRespuesta = new JSONObject();
            jsonRespuesta.put("esMutante", esMutante);
            return jsonRespuesta.toString();
        }
        throw new NoMutanteException("No es Mutante");
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = "application/json")
    public String stats() {

       return validateADNService.getStats();
    }

}