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
public class Presidente {
    private String nome;
    private String cognome;
    private String password;

    public Presidente(String nome, String cognome, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
    }
    
    public Presidente(Presidente presidente) {
        this.nome = presidente.getNome();
        this.cognome = presidente.getCognome();
        this.password = presidente.getPassword();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Presidente{" + "nome=" + nome + ", cognome=" + cognome + ", password=" + password + '}';
    }    
    
}
