package com.mercado.libre.Magneto.services;

import com.mercado.libre.Magneto.dominio.SecuenciaADN;
import com.mercado.libre.Magneto.repositories.SecuenciaADNRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

@Service
public class ValidateADNService {

    @Autowired
    private SecuenciaADNRepository secuenciaADNRepository;

    public void save(String[] array, Boolean esMutante){
        secuenciaADNRepository.save(new SecuenciaADN(array, esMutante));
    }

    public Boolean isMutant(String[] dna){

        String [][] matriz = getMatriz(dna);

        return calcularMutante(matriz);
    }

    public Boolean calcularMutante(String [][] matriz){
        return calculaADN(matriz)>=2 ? true : false;
    }

    public Long calculaADN(String [][] matriz){
        Long cuenta = 0L;

        //cortar si encuentra 2
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                cuenta+=calculoDiagonalAsc(matriz, i, j);
                if(cuenta>=2)
                    return cuenta;
                cuenta+=calculoFila(matriz, i, j);
                if(cuenta>=2)
                    return cuenta;
                cuenta+=calculoDiagonalDesc(matriz, i, j);
                if(cuenta>=2)
                    return cuenta;
                cuenta+=calculoColumna(matriz, i, j);
                if(cuenta>=2)
                    return cuenta;
            }
        }
        return cuenta;
    }

    private Long calculoDiagonalAsc(String[][] matriz, int i, int j){
        if(0<=(j-3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j-1][i+1])
                && matriz[j][i].equals(matriz [j-2][i+2])
                && matriz[j][i].equals(matriz [j-3][i+3])){
            return 1L;
        }
        return 0L;
    }

    private Long calculoFila(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[j][i].equals(matriz [j][i+1])
                && matriz[j][i].equals(matriz [j][i+2])
                && matriz[j][i].equals(matriz [j][i+3])){
            return 1L;
        }
        return 0L;
    }

    private Long calculoDiagonalDesc(String[][] matriz, int i, int j){
        if(matriz.length>(j+3) && matriz.length>(i+3) && matriz[j][i].equals(matriz [j+1][i+1])
                && matriz[j][i].equals(matriz [j+2][i+2])
                && matriz[j][i].equals(matriz [j+3][i+3])){
            return 1L;
        }
        return 0L;
    }

    private Long calculoColumna(String[][] matriz, int i, int j){
        if(matriz.length>(i+3) && matriz[i][j].equals(matriz [i+1][j])
                && matriz[i][j].equals(matriz [i+2][j])
                && matriz[i][j].equals(matriz [i+3][j])){
            return 1L;
        }
        return 0L;
    }




    private String [][] getMatriz(String[] dna){
        String [][] matriz= new String[dna.length][dna.length];
        for(int i = 0; i< dna.length;i++){
            String [] linea = dna[i].split("");
            matriz[i] = linea;
        }
        return matriz;
    }

    public String [] getArray(JSONArray jsonArray){

        String [] result = new String[jsonArray.length()];

        for(int i=0;i<jsonArray.length();i++){
            result[i] = jsonArray.getString(i);
        }
        return result;
    }

    public JSONArray getJSON(String json){
        JSONArray arrayADN = null;
        try {
            arrayADN = new JSONObject(json).getJSONArray("dna");
        }
        catch (Exception e){
            e.getMessage();
        }
        return arrayADN;
    }

    public Boolean adnIncorrecto(JSONArray array){
        int i = 0;
        Boolean invalido = false;
        while(i < array.length() && invalido == false){
            invalido = lineaInvalida(array.getString(i++).toLowerCase());
            i++;
        }
        return invalido;
    }

    private Boolean lineaInvalida(String line){
        return !Pattern.compile("[a|t|c|g]+").matcher(line).matches();
    }

    public String getStats(){
        JSONObject json = new JSONObject();
        Long cuentaMutantes = secuenciaADNRepository.cuentaMutantes();
        Long cuentaHumanos = secuenciaADNRepository.cuentaHumanos();
        json.put("count_mutant_dna", secuenciaADNRepository.cuentaMutantes());
        json.put("count_human_dna", secuenciaADNRepository.cuentaHumanos());
        if(cuentaHumanos>0){
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(1);
            Float ratio = cuentaMutantes.floatValue()/cuentaHumanos.floatValue();
            json.put("ratio",df.format(ratio));
        }
        return json.toString();

    }
}