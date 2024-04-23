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
public class Arbitro {
    private ArrayList<Partita> partite;
    private String nome;
    private String cognome;
    private String categoria;
    private String passoword;
    private int eta;
    private long codice;
    private static long nextCodice=1;
    
    public Arbitro(String nome, String cognome, String categoria, String passoword, int eta){  
        codice=nextCodice;
        this.nome=nome;
        this.cognome=cognome;
        this.passoword=passoword;
        this.categoria=categoria;
        this.eta=eta;
        nextCodice++;
        partite = new ArrayList<>();
    }
    
    public Arbitro(Arbitro arbitro){
        this.nome = arbitro.getNome();
        this.cognome = arbitro.getCognome();
        this.categoria = arbitro.getCategoria();
        this.passoword=arbitro.getPassoword();
        this.eta = arbitro.getEta(); 
        this.partite = new ArrayList<>();
        for(int i = 0; i < arbitro.partite.size(); i++){
            partite.add(new Partita(partite.get(i)));
        }
    }

    
    public void setPartita(Partita partita){
        partite.add(partita);
    }
    
    public void eliminaPartita(int codice){
        for(int i=0; i<partite.size(); i++){
            if(partite.get(i).getCodice()==codice){
                partite.remove(i);
            }
        }
    }
    
    public ArrayList<Partita> visualizzaPartite(){
        return partite;
    }
    
    public int getnPartite(){
        int nPartite=partite.size();
        return nPartite;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassoword() {
        return passoword;
    }

    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getEta() {
        return eta;
    }

    public long getCodice() {
        return codice;
    }
    
    @Override
    public String toString(){
        String s;
        s=  "\tcodice: "+codice
            +"\n\tnome: "+nome
            +"\n\tcognome: "+cognome
            +"\n\tetà: "+eta
            +"\n\tnPartite: "+getnPartite();
        
        return s;
    }
}
