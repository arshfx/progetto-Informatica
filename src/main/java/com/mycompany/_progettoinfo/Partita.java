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
        int rimborso=distanza;
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
