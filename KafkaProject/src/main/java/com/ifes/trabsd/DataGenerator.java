/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.trabsd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Salzman
 */
public class DataGenerator{
    
    
//    public static void main(String[] args) throws FileNotFoundException {
//        
//        
//
//        
//        DataGenerator gerador = new DataGenerator();
//        
//        int cont = 0;
//        while(cont < 5){
//            System.out.println(gerador.nextTemp());
//            System.out.println(gerador.nextHumi());
//            System.out.println(gerador.nextPress());
//            System.out.println(gerador.nextDate());
//            System.out.println("###############################");
//            cont++;
//        }    
//    }
    
    LinkedList<Float> lstTemperature;
    LinkedList<Float> lstHumidity;
    LinkedList<Float> lstPressure;
    LinkedList<String> lstDate;
    
    public DataGenerator() throws FileNotFoundException{
        this.lstTemperature = new LinkedList<Float>();
        this.lstHumidity = new LinkedList<Float>();
        this.lstPressure = new LinkedList<Float>();
        this.lstDate = new LinkedList<String>();
        
        String nome_arq = "mundo.txt";
        String caminho_arq = "F:\\GitDesktop\\ifes-sd-kafka\\KafkaProject\\";
        
        this.carregamundo(nome_arq);
    }
    
    private void carregamundo(String nome_arq) throws FileNotFoundException{
        File arq = new File(nome_arq);
        
        Scanner s = new Scanner(arq);
        String line;
        
        while(s.hasNextLine()) {  
            line = s.nextLine(); 
            String[] vet = line.split(";"); 
            
            for(int i=0; i<=vet.length;i++){
                lstTemperature.add(Float.parseFloat(vet[3]));
                lstHumidity.add(Float.parseFloat(vet[4]));
                lstPressure.add(Float.parseFloat(vet[5]));
                lstDate.add(vet[1]);
            }
        }
        
    }
    
    
    private Float next(LinkedList<Float> lst){
        Random gerador = new Random();
        
        return lst.get(gerador.nextInt(lst.size()-1));
    }
    
    private String nextString(LinkedList<String> lst) {
        Random gerador = new Random();
        
        return lst.get(gerador.nextInt(lst.size()-1));
    }
    
    public Float nextTemp(){
        return next(this.lstTemperature);
    }
    
    public Float nextHumi(){
        return next(this.lstHumidity);
    }
    
    public Float nextPress(){
        return next(this.lstPressure);
    }
    
    public String nextDate(){
        return nextString(this.lstDate);
    }
 
}
