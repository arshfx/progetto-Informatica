/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._progettoinfo;

/**
 *
 * @author AMMINISTRATORE
 */
public class Designatore {
    private long codice;
    private static long nextCodice=1;
    private String nome;
    private String cognome;
    private String password;
    
    public Designatore(String nome, String cognome, String password){
        codice=nextCodice;
        this.nome=nome;
        this.cognome=cognome;
        this.password=password;
        nextCodice++;
    }
    
    public Designatore(Designatore designatore){
        this.nome=designatore.getNome();
        this.cognome=designatore.getCognome();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public long getCodice() {
        return codice;
    }

    @Override
    public String toString() {
        return "Designatore{" + "nome=" + nome + ", cognome=" + cognome + '}';
    }
    
}
