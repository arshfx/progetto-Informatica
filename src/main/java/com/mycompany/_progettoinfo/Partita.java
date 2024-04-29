/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._progettoinfo;

import java.time.LocalDate;

/**
 *
 * @author AMMINISTRATORE
 */
public class Partita {
    private long codice;
    private static long nextCodice=1;
    private String squadraOspitante;
    private String squadraOspite;
    private String citta;
    private LocalDate giorno;
    private int distanza;
    private int rimborso;
    
    public Partita(String squadraOspitante, String squadraOspite, String citta, LocalDate giorno, int distanza){
        codice=nextCodice;
        this.squadraOspitante=squadraOspitante;
        this.squadraOspite=squadraOspite;
        this.citta=citta;
        this.giorno = giorno;
        this.distanza=distanza;
        nextCodice++;
    }
    
    public Partita(Partita partita){
        this.squadraOspitante=partita.getSquadraOspitante();
        this.squadraOspite=partita.getSquadraOspite();
        this.citta=partita.getCitta();
        this.giorno=partita.getGiorno();
        this.distanza=partita.getDistanza();
    }

    public void setSquadraOspitante(String squadraOspitante) {
        this.squadraOspitante = squadraOspitante;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public void setGiorno(LocalDate giorno) {
        this.giorno = giorno;
    }
    
    public long getCodice() {
        return codice;
    }

    public String getSquadraOspitante() {
        return squadraOspitante;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public String getCitta() {
        return citta;
    }

    public int getDistanza() {
        return distanza;
    }

    public LocalDate getGiorno() {
        return giorno;
    }
    
    public int getRimborso(){
        String categoria = null;
        int rimborso = 0;
        switch(categoria){
            case "giovanissimi", "allievi" -> rimborso=36;
            case "juniores" -> rimborso=46;
            case "terza categoria" -> rimborso=56;
            case "seconda categoria" -> rimborso=66;
            case "prima categoria" -> rimborso=76;
            case "promozione" -> rimborso=86;
            case "eccellenza" -> rimborso=96;
            case "serie D" -> rimborso=106;
            case "serie C" -> rimborso=116;
            case "serie B" -> rimborso=126;
            case "serie A" -> rimborso=136;
        }
        return rimborso;
    }
    
    @Override
    public String toString(){
        String s;
        s=  "\tcodice: "+codice
            +"\n\tcitta: "+citta
            +"\n\t"+squadraOspitante+"-"+squadraOspite
            +"\n\tdistanza: "+distanza
            +"\n\trimborso: "+getRimborso()+"€";
        return s;
    }
            
}
