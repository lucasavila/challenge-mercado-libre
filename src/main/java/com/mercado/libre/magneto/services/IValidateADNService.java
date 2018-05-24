package com.mercado.libre.magneto.services;

public interface IValidateADNService {
        public Boolean isMutant(String[] dna);
        public Boolean adnIncorrecto(String[] array);
}