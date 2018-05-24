package com.mercado.libre.Magneto.services;

public interface IValidateADNService {
        public Boolean isMutant(String[] dna);
        public Boolean adnIncorrecto(String[] array);
}