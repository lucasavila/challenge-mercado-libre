package com.mercado.libre.magneto.services.impl;

import com.mercado.libre.magneto.services.IValidateADNService;
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
     * calcula en diagonal la linea de abajo e izquierda hacia arriba a la derecha
     * */
    private Integer calculoDiagonalAsc(String[][] matriz, int i, int j){
        if(0<=(j-3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j-1][i+1])
                && matriz[j][i].equals(matriz [j-2][i+2])
                && matriz[j][i].equals(matriz [j-3][i+3])){
            return 1;
        }
        return 0;
    }
    /**
     * @param matriz: Matriz de Strings
     * @param i columna
     * @param j fila
     * @return Integer
     * calcula la linea de izquierda a derecha
     * */
    private Integer calculoFila(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[j][i].equals(matriz [j][i+1])
                && matriz[j][i].equals(matriz [j][i+2])
                && matriz[j][i].equals(matriz [j][i+3])){
            return 1;
        }
        return 0;
    }

    /**
     * @param matriz: Matriz de Strings
     * @param i columna
     * @param j fila
     * @return Integer
     * calcula en diagonal la linea de arriba e izquierda hacia abajo a la derecha
     * */
    private Integer calculoDiagonalDesc(String[][] matriz, int i, int j){
        if(matriz.length>(j+3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j+1][i+1])
                && matriz[j][i].equals(matriz [j+2][i+2])
                && matriz[j][i].equals(matriz [j+3][i+3])){
            return 1;
        }
        return 0;
    }
    /**
     * @param matriz: Matriz de Strings
     * @param i fila
     * @param j columna
     * @return Integer
     * calcula la linea de arriba hacia abajo
     * */
    private Integer calculoColumna(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[i][j].equals(matriz [i+1][j])
                && matriz[i][j].equals(matriz [i+2][j])
                && matriz[i][j].equals(matriz [i+3][j])){
            return 1;
        }
        return 0;
    }
    /**
     * @param dna: Array de Strings
     * @return String[][]: matriz de Strings
     * se recorre el array y se genera una matriz para facilitar su manipulación
     * */
    private String [][] getMatriz(String[] dna){
        String [][] matriz= new String[dna.length][dna.length];
        for(int i = 0; i< dna.length;i++){
            String [] linea = dna[i].split("");
            matriz[i] = linea;
        }
        return matriz;
    }

    /**
     * @param array: Array de Strings
     * @return Boolean
     * itera las lineas del array y llama al metodo lineaInvalida
     * */
    public Boolean adnIncorrecto(String[] array){
        int i = 0;
        Boolean invalido = false;
        while(i < array.length && invalido == false){
            invalido = lineaInvalida(array[i++].toLowerCase());
        }
        return invalido;
    }
    /**
     * @param linea: String
     * @return Boolean
     * Regex que determina si el valor del ADN es correcto o incorrecto
     * */
    private Boolean lineaInvalida(String linea){
        return !Pattern.compile("[a|t|c|g]+").matcher(linea).matches();
    }
}