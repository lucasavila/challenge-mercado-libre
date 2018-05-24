package com.mercado.libre.Magneto.services.impl;

import com.mercado.libre.Magneto.services.IValidateADNService;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidateADNServiceImpl implements IValidateADNService {

    private static Integer NUMERO_PARA_SER_MUTANTE=2;

    /**
     * @param dna: Array de Strings en el que se reciben las lineas de ADN
     * @return Boolean
     * Método que se encarga de obtener una matriz en base al Array de Strings de parámetro
     * y calcula si es mutante en base a dicha matriz
     * */
    public Boolean isMutant(String[] dna){

        String [][] matriz = getMatriz(dna);

        return calcularMutante(matriz);
    }
    /**
     * @param matriz: Matriz de Strings
     * @return Boolean
     * Calcula si tiene 2 o mas lineas de ADN retorna verdadero sino es falso
     * */
    public Boolean calcularMutante(String [][] matriz){
        return calculaADN(matriz)>=NUMERO_PARA_SER_MUTANTE ? true : false;
    }

    public Integer calculaADN(String [][] matriz){
        Integer cuenta = 0;

        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                cuenta+=calculoDiagonalAsc(matriz, i, j);
                if(cuenta>=NUMERO_PARA_SER_MUTANTE)
                    return cuenta;
                cuenta+=calculoFila(matriz, i, j);
                if(cuenta>=NUMERO_PARA_SER_MUTANTE)
                    return cuenta;
                cuenta+=calculoDiagonalDesc(matriz, i, j);
                if(cuenta>=NUMERO_PARA_SER_MUTANTE)
                    return cuenta;
                cuenta+=calculoColumna(matriz, i, j);
                if(cuenta>=NUMERO_PARA_SER_MUTANTE)
                    return cuenta;
            }
        }
        return cuenta;
    }
    /**
     * @param matriz: Matriz de Strings
     * @param i columna
     * @param j fila
     * @return Integer
     * Calcula si tiene 2 o mas lineas de ADN retorna verdadero sino es falso
     * */
    private Integer calculoDiagonalAsc(String[][] matriz, int i, int j){
        if(0<=(j-3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j-1][i+1])
                && matriz[j][i].equals(matriz [j-2][i+2])
                && matriz[j][i].equals(matriz [j-3][i+3])){
            return 1;
        }
        return 0;
    }

    private Integer calculoFila(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[j][i].equals(matriz [j][i+1])
                && matriz[j][i].equals(matriz [j][i+2])
                && matriz[j][i].equals(matriz [j][i+3])){
            return 1;
        }
        return 0;
    }

    private Integer calculoDiagonalDesc(String[][] matriz, int i, int j){
        if(matriz.length>(j+3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j+1][i+1])
                && matriz[j][i].equals(matriz [j+2][i+2])
                && matriz[j][i].equals(matriz [j+3][i+3])){
            return 1;
        }
        return 0;
    }

    private Integer calculoColumna(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[i][j].equals(matriz [i+1][j])
                && matriz[i][j].equals(matriz [i+2][j])
                && matriz[i][j].equals(matriz [i+3][j])){
            return 1;
        }
        return 0;
    }

    private String [][] getMatriz(String[] dna){
        String [][] matriz= new String[dna.length][dna.length];
        for(int i = 0; i< dna.length;i++){
            String [] linea = dna[i].split("");
            matriz[i] = linea;
        }
        return matriz;
    }

    public Boolean adnIncorrecto(String[] array){
        int i = 0;
        Boolean invalido = false;
        while(i < array.length && invalido == false){
            invalido = lineaInvalida(array[i++].toLowerCase());
        }
        return invalido;
    }

    private Boolean lineaInvalida(String line){
        return !Pattern.compile("[a|t|c|g]+").matcher(line).matches();
    }
}