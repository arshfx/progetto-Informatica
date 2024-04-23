/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._progettoinfo;

import java.util.ArrayList;

/**
 *
 * @author AMMINISTRATORE
 */
public class Sezione {
    private ArrayList<Arbitro> arbitri;
    private ArrayList<Designatore> designatori;
    
    public Sezione(){
        arbitri=new ArrayList<>();
        designatori=new ArrayList<>();
    }
    
    public Sezione(Sezione sezione){
        arbitri=new ArrayList<>();
        for(Arbitro arbitro : sezione.arbitri){
            arbitri.add(new Arbitro(arbitro));
        }
        
        designatori=new ArrayList<>();
        for(Designatore designatore : sezione.designatori){
            designatori.add(new Designatore(designatore));
        }
    }
    
    public void aggiungiArbitro(Arbitro arbitro){
        arbitri.add(arbitro);
    }
    
    public void eliminaArbitro(String nome, String cognome, int codice){
        for(int i=0; i<arbitri.size(); i++){
            if(arbitri.get(i).getNome().equals(nome)){
                if(arbitri.get(i).getCognome().equals(cognome)){
                    if(arbitri.get(i).getCodice()==codice){
                        arbitri.remove(i);
                    }
                }
            }
        }
    }
    
    public void aggiungiDesignatore(Designatore designatore){
        designatori.add(designatore);
    }
    
    public void eliminaDesignatore(String nome, String cognome, int codice){
        for(int i=0; i<designatori.size(); i++){
            if(designatori.get(i).getNome().equals(nome)){
                if(designatori.get(i).getCognome().equals(cognome)){
                    if(designatori.get(i).getCodice()==codice){
                        designatori.remove(i);
                    }
                }
            }
        }
    }
    
    public void designaPartita(int codice, Partita partita){
        for(int i=0; i<arbitri.size(); i++){
            if(arbitri.get(i).getCodice()==codice){
                arbitri.get(i).setPartita(partita);
            }
        }
    }
    
    public void eliminaPartita(int codiceArbitro, int codicePartita){
        for(int i=0; i<arbitri.size(); i++){
            if(arbitri.get(i).getCodice()==codiceArbitro){
                ArrayList<Partita> elencoPartite=arbitri.get(i).visualizzaPartite();
                for(Partita partita : elencoPartite){
                    if(partita.getCodice()==codicePartita){
                        arbitri.get(i).eliminaPartita(codicePartita);
                        break;
                    }
                }
            }
        }
    }
    
    public Arbitro getArbitro(int codice){
        for(int i=0; i<arbitri.size(); i++){
            if(arbitri.get(i).getCodice()==codice){
                return arbitri.get(i);
            }
        }
        return null;
    }
    
    public Designatore getDesignatore(int codice){
        for(int i=0; i<designatori.size(); i++){
            if(designatori.get(i).getCodice()==codice){
                return designatori.get(i);
            }
        }
        
        return null;
    }
    
    public ArrayList<Arbitro> visualizzaArbitri(){
        return arbitri;
    }
    
    public ArrayList<Designatore> visualizzaDesignatori(){
        return designatori;
    }
}
