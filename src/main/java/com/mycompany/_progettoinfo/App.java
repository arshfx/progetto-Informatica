/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._progettoinfo;

import eccezioni.EmptyStringException;
import eccezioni.*;
import java.io.EOFException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import utility.ConsoleInput;
import utility.Menu;
import utility.TextFile;

/**
 *
 * @author AMMINISTRATORE
 */
public class App {
        
    public static void main(String[] args) {
        
        Sezione sezione=new Sezione();
       
        ArrayList<Designatore> elencoDesignatori=sezione.visualizzaDesignatori();
        int numeroVoci=4;
        String[] vociMenu=new String[numeroVoci];
        Menu login;
        int voceScelta;
        String nome, cognome, categoria = null;
        int eta;
        String password = null, confermaPassword = null;
        ConsoleInput input=new ConsoleInput();
        int codice;
        int sceltaMenuArbitro;
        boolean accesso = false;
        
        ArrayList<Arbitro> elencoArbitri=sezione.visualizzaArbitri();
        Presidente presidente=new Presidente("nome", "cognome", "password");
        
        //IMPORTA DA FILE CSV
        importReferee(elencoArbitri, sezione);
        importMatch(elencoArbitri, sezione);
        
        vociMenu[0]="0 -->\tEsci";
        vociMenu[1]="1 -->\taccedi come arbitro";
        vociMenu[2]="2 -->\taccedi come designatore";
        vociMenu[3]="3 -->\taccedi come presidente della sezione";
        login=new Menu(vociMenu);
        
        do{
            voceScelta=login.sceltaMenu();
            switch(voceScelta){
                case 0 -> System.out.println("Arrivederci");
                //accedi come arbitro
                case 1 -> {
                    do{
                        
                        try{
                            System.out.println("inserisci il tuo nome");
                            nome=input.readString();
                            System.out.println("inserisci il tuo cognome");
                            cognome=input.readString();
                            
                            for(Arbitro arbitro : elencoArbitri){
                                if(arbitro.getPassoword().equals(arbitro.getNome()+"_"+arbitro.getCognome())){
                                    System.out.println("inserisci la vecchia password");
                                    String vecchiPassword=input.readString();
                                    if(vecchiPassword.equals(arbitro.getPassoword())){
                                        System.out.println("imposta la tua nuova password");
                                        password=input.readString();
                                        Arbitro a=new Arbitro(arbitro.getNome(), arbitro.getCognome(), arbitro.getCategoria(), password, arbitro.getEta());
                                        elencoArbitri.remove(arbitro);
                                        elencoArbitri.add(a);
                                        password=a.getPassoword();
                                    }
                                    else{
                                        throw new InvalidPasswordException();
                                    }
                                    
                                }
                            }
                            
                            
                            
                            for(int i=0; i<elencoArbitri.size(); i++){
                                if(elencoArbitri.get(i).getNome().equals(nome)){
                                    if(elencoArbitri.get(i).getCognome().equals(cognome)){
                                        if(elencoArbitri.get(i).getPassoword().equals(password)){
                                            accesso=true; //accesso effettuato
                                            System.out.println("accesso effettuato");
                                            System.out.println("");
                                            System.out.println("Profilo {");
                                            System.out.println(elencoArbitri.get(i).toString());
                                            System.out.println("}");
                                            do{
                                                
                                                
                                                System.out.println("");
                                                Menu menuArbitro;
                                                int numeroVociArbitro=5;
                                                String[] vociMenuArbitro=new String[numeroVociArbitro];
                                                vociMenuArbitro[0]="0 -->\tEsci";
                                                vociMenuArbitro[1]="1 -->\tVisualizza profilo";
                                                vociMenuArbitro[2]="2 -->\tVisualizza le tue partite";
                                                vociMenuArbitro[3]="3 -->\tModifica profilo";
                                                vociMenuArbitro[4]="4 -->\tLogout";
                                                menuArbitro=new Menu(vociMenuArbitro);
                                                sceltaMenuArbitro=menuArbitro.sceltaMenu();
                                                switch(sceltaMenuArbitro){
                                                    case 0 -> {
                                                        exportRefereeAndMatch(elencoArbitri);
                                                        exportDesignator(elencoDesignatori);
                                                        System.out.println("Arrivederci");
                                                        System.exit(0);
                                                    }      
                                                    
                                                    case 1 -> {
                                                                System.out.println("Profilo {");
                                                                System.out.println(elencoArbitri.get(i).toString());
                                                                System.out.println("}");
                                                                break;
                                                            }
                                                    
                                                    case 2 -> {
                                                        ArrayList<Partita> elencoPartite=elencoArbitri.get(i).visualizzaPartite();
                                                        for(Partita partita : elencoPartite){
                                                            System.out.println("");
                                                            System.out.println("{");
                                                            System.out.println(partita.toString());
                                                            System.out.println("}");
                                                        }
                                                        break;
                                                    }
                                                    case 3 -> {
                                                        System.out.println(elencoArbitri.get(i).toString());
                                                        int vociModificaProfilo=6;
                                                        int sceltaMenuModifica;
                                                        String[] vociMenuModifica=new String[vociModificaProfilo];
                                                        vociMenuModifica[0]="0 -->\tEsci";
                                                        vociMenuModifica[1]="1 -->\tModifica nome";
                                                        vociMenuModifica[2]="2 -->\tModifica cognome";
                                                        vociMenuModifica[3]="3 -->\tModifica eta";
                                                        vociMenuModifica[4]="4 -->\tModifica password";
                                                        vociMenuModifica[5]="5 -->\tTorna indietro";
                                                        Menu modificaProfilo = new Menu(vociMenuModifica);
                                                        sceltaMenuModifica=modificaProfilo.sceltaMenu();
                                                        switch(sceltaMenuModifica){
                                                            case 0 -> {
                                                                System.exit(0);
                                                            }
                                                           
                                                            case 1 -> { 
                                                                try{
                                                                    System.out.println("inserisci il nome corrente");
                                                                    nome=input.readString();
                                                                    System.out.println("inserisci il nuovo nome");
                                                                    String nuovoNome=input.readString();
                                                                    if(nuovoNome.isEmpty()){
                                                                        throw new EmptyStringException();
                                                                    }
                                                                    if(elencoArbitri.get(i).getNome().equals(nome)){
                                                                        elencoArbitri.get(i).setNome(nuovoNome);
                                                                        System.out.println("modifica avvenuta con successo");
                                                                    }
                                                                    else{
                                                                        throw new InvalidNameException();
                                                                    }
                                                                }
                                                                catch(InvalidNameException e){
                                                                    System.out.println("ERROR InvalidNameException::il nome corrente non corrisponde");
                                                                }
                                                                catch(EmptyStringException e){
                                                                    System.out.println("ERROR EmptyStringException::nuovo nome vuoto");
                                                                }
                                                            }
                                                            case 2 -> {
                                                                try{
                                                                    System.out.println("inserisci il cognome corrente");
                                                                    cognome=input.readString();
                                                                    System.out.println("inserisci il nuovo cognome ");
                                                                    String nuovoCognome=input.readString();
                                                                    if(nuovoCognome.isEmpty()){
                                                                        throw new EmptyStringException();
                                                                    }
                                                                    if(elencoArbitri.get(i).getCognome().equals(cognome)){
                                                                        elencoArbitri.get(i).setCognome(nuovoCognome);
                                                                        System.out.println("modifica avvenuta con successo");
                                                                    }
                                                                    else{
                                                                        throw new InvalidSurnameException();
                                                                    }
                                                                }
                                                                catch(InvalidSurnameException e){
                                                                    System.out.println("ERROR InvalidNameException::il cognome corrente non corrisponde");
                                                                }
                                                                catch(EmptyStringException e){
                                                                    System.out.println("ERROR EmptyStringException::nuovo cognome vuoto");
                                                                }
                                                                break;
                                                            }
                                                            
                                                            case 3 -> {
                                                                System.out.println("inserisci l'eta da inserire");
                                                                eta=input.readInt();
                                                                elencoArbitri.get(i).setEta(eta);
                                                                System.out.println("modifica avvenuta con successo");
                                                                break;
                                                            }
                                                            
                                                            case 4 -> { 
                                                                try{
                                                                    System.out.println("inserisci la password corrente");
                                                                    password=input.readString();
                                                                    System.out.println("inserisci la nuova password");
                                                                    String nuovaPassword=input.readString();
                                                                    if(nuovaPassword.isEmpty()){
                                                                        throw new EmptyStringException();
                                                                    }
                                                                    if(elencoArbitri.get(i).getPassoword().equals(password)){
                                                                        elencoArbitri.get(i).setPassoword(nuovaPassword);
                                                                    }
                                                                    else{
                                                                        throw new InvalidPasswordException();
                                                                    }
                                                                }
                                                                catch(InvalidPasswordException e){
                                                                    System.out.println("ERROR InvalidPasswordException::password corrente non valida");
                                                                }
                                                                catch(EmptyStringException e){
                                                                    System.out.println("ERROR EmptyStringException::nuova password vuoto");
                                                                }
                                                                break;
                                                            }
                                                            
                                                            case 5 -> {
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    case 4 -> {
                                                        break;
                                                    }
                                                }
                                            }while(sceltaMenuArbitro!=4); 
                                        }
                                    }
                                }
                            }
                            if(accesso==false){
                                throw new AuthorizedAccessException();
                            }
                        }
                        catch(IOException e){
                            System.out.println("ERROR IOException::accesso negato");
                        } 
                        catch (AuthorizedAccessException e){
                            System.out.println("ERROR AuthorizedAccessException::accesso negato");
                        }
                        catch(InvalidPasswordException e){
                            System.out.println("ERROR InvalidPasswordException::vecchia password non valida");
                        }
                        
                        break;
                    }while(true);
                }
                    
                //accedi come designatore
                case 2 -> { 
                    try{
                        System.out.println("inserisci il tuo nome");
                        nome=input.readString();
                        System.out.println("inserisci la tua password");
                        password=input.readString();

                        for(int i=0; i<elencoDesignatori.size(); i++){
                            if(elencoDesignatori.get(i).getNome().equals(nome)){
                                if((elencoDesignatori.get(i).getPassword()).equals(password)){
                                    System.out.println("accesso effettuato");
                                    accesso=true;
                                    int sceltaMenuDesignatore;
                                    
                                    do{
                                        int vociDesignatore=5;
                                        String[] vociMenuDesignatore=new String[vociDesignatore];
                                        vociMenuDesignatore[0]="0 -->\tEsci";
                                        vociMenuDesignatore[1]="1 -->\tDesigna partita";
                                        vociMenuDesignatore[2]="2 -->\tElimina partita";
                                        vociMenuDesignatore[3]="3 -->\tModifica password";
                                        vociMenuDesignatore[4]="4 -->\tLogout";
                                        Menu designatore=new Menu(vociMenuDesignatore);
                                        sceltaMenuDesignatore=designatore.sceltaMenu();
                                        System.out.println("");
                                        switch(sceltaMenuDesignatore){
                                            case 0: 
                                                exportRefereeAndMatch(elencoArbitri);
                                                exportDesignator(elencoDesignatori);
                                                System.exit(0);
                                            case 1: 
                                                for(Arbitro arbitro : elencoArbitri){
                                                    System.out.println(arbitro);
                                                    System.out.println("");
                                                }
                                                System.out.println("inserisci il nome dell'arbitro a cui vuoi assegnare la partita");
                                                nome=input.readString();
                                                System.out.println("inserisci il codice dell'arbitro a cui vuoi assegnare la partita");
                                                codice=input.readInt();
                                                boolean arbitroTrovato=false;
                                                LocalDate giorno=null;
                                                try{
                                                    for(Arbitro arbitro : elencoArbitri){
                                                        if(arbitro.getNome().equals(nome)){
                                                            if(arbitro.getCodice()==codice){
                                                                System.out.println("");
                                                                System.out.println("Partita: ");
                                                                System.out.println("inserisci la squadra ospitante");
                                                                String squadraOspitante=input.readString();
                                                                System.out.println("inserisci la squadra ospite");
                                                                String squadraOspite=input.readString();
                                                                do{
                                                                    try{
                                                                        System.out.println("inserisci il giorno (YYYY-MM-GG)");
                                                                        String day=input.readString();
                                                                        giorno=LocalDate.parse(day);
                                                                        break;
                                                                    }
                                                                    catch(IOException e){
                                                                        
                                                                    }
                                                                }while(true);
                                                                System.out.println("inserisci la citta");
                                                                String citta=input.readString();
                                                                System.out.println("inserisci la distanza");
                                                                int distanza=input.readInt();
                                                                Partita partita=new Partita(squadraOspitante, squadraOspite, citta, giorno, distanza);
                                                                sezione.designaPartita(codice, partita);
                                                                System.out.println("partita designata con successo");
                                                                arbitroTrovato=true;
                                                            }else{
                                                                throw new InvalidCodeExcepion();
                                                            }
                                                        }
                                                    }
                                                    if(!arbitroTrovato)
                                                        throw new InvalidNameException();
                                                }
                                                catch(InvalidNameException e){
                                                    System.out.println("ERROR InvalidNameException::nome non valido");
                                                }
                                                catch(InvalidCodeExcepion e){
                                                    System.out.println("ERROR InvalidCodeExcepion::codice non valido");
                                                }
                                                
                                                break;
                                            case 2:
                                                System.out.println("inserisci il nome dell'arbitro a cui vuoi eliminare la partita");
                                                nome=input.readString();
                                                System.out.println("inserisci il codice dell'arbitro a cui vuoi eliminare la partita");
                                                codice=input.readInt();
                                                for(Arbitro arbitro : elencoArbitri){
                                                    if(arbitro.getNome().equals(nome)){
                                                        if(arbitro.getCodice()==codice){
                                                            ArrayList<Partita> elencoPartite=arbitro.visualizzaPartite();
                                                            for(Partita partita : elencoPartite){
                                                                System.out.println(partita);
                                                            }
                                                            System.out.println("inserisci il codice della partita che vuoi eliminare");
                                                            int codicePartita=input.readInt();
                                                            for(Partita partita : elencoPartite){
                                                                if(partita.getCodice()==codicePartita){
                                                                    sezione.eliminaPartita(codice, codicePartita);
                                                                    System.out.println("eliminazione avvenuta con successo");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3: 
                                                System.out.println(elencoArbitri.get(i).toString());
                                                int vociModificaProfilo=4;
                                                int sceltaMenuModificaDesignatore;
                                                String[] vociMenuModificaDesignatore=new String[vociModificaProfilo];
                                                vociMenuModificaDesignatore[0]="0 -->\tEsci";
                                                vociMenuModificaDesignatore[1]="1 -->\tModifica nome";
                                                vociMenuModificaDesignatore[2]="2 -->\tModifica cognome";
                                                vociMenuModificaDesignatore[3]="3 -->\tModifica password";
                                                Menu modificaProfiloDesignatore = new Menu(vociMenuModificaDesignatore);
                                                sceltaMenuModificaDesignatore=modificaProfiloDesignatore.sceltaMenu();
                                                
                                                switch(sceltaMenuModificaDesignatore){
                                                    case 0 -> { 
                                                        System.exit(0);
                                                    }
                                                    case 1 -> { 
                                                        try{
                                                            System.out.println("inserisci il nome corrente");
                                                            nome=input.readString();
                                                            System.out.println("inserisci il nuovo nome");
                                                            String nuovoNome=input.readString();
                                                            if(nuovoNome.isEmpty()){
                                                                throw new EmptyStringException();
                                                            }
                                                            if(elencoDesignatori.get(i).getNome().equals(nome)){
                                                                elencoDesignatori.get(i).setNome(nuovoNome);
                                                                System.out.println("modifica avvenuta con successo");
                                                            }
                                                            else{
                                                                throw new InvalidNameException();
                                                            }
                                                        }
                                                        catch(InvalidNameException e){
                                                            System.out.println("ERROR InvalidNameException::il nome corrente non corrisponde");
                                                        }
                                                        catch(EmptyStringException e){
                                                            System.out.println("ERROR EmptyStringException::nuovo nome vuoto");
                                                        }
                                                    }
                                                    
                                                    case 2 -> {
                                                        try{
                                                            System.out.println("inserisci il cognome corrente");
                                                            cognome=input.readString();
                                                            System.out.println("inserisci il nuovo cognome");
                                                            String nuovoCognome=input.readString();
                                                            if(nuovoCognome.isEmpty()){
                                                                throw new EmptyStringException();
                                                            }
                                                            if(elencoDesignatori.get(i).getCognome().equals(cognome)){
                                                                elencoDesignatori.get(i).setCognome(nuovoCognome);
                                                                System.out.println("modifica avvenuta con successo");
                                                            }
                                                            else{
                                                                throw new InvalidSurnameException();
                                                            }
                                                        }
                                                        catch(InvalidSurnameException e){
                                                            System.out.println("ERROR InvalidNameException::il cognome corrente non corrisponde");
                                                        }
                                                        catch(EmptyStringException e){
                                                            System.out.println("ERROR EmptyStringException::nuovo cognome vuoto");
                                                        }
                                                        break;
                                                    }
                                                    
                                                    case 3 -> {
                                                        try{
                                                            System.out.println("inserisci la password corrente");
                                                            password=input.readString();
                                                            System.out.println("inserisci il nuovo cognome");
                                                            String nuovaPassword=input.readString();
                                                            if(nuovaPassword.isEmpty()){
                                                                throw new EmptyStringException();
                                                            }
                                                            if(elencoDesignatori.get(i).getPassword().equals(password)){
                                                                elencoDesignatori.get(i).setPassword(nuovaPassword);
                                                                System.out.println("modifica avvenuta con successo");
                                                            }
                                                            else{
                                                                throw new InvalidSurnameException();
                                                            }
                                                        }
                                                        catch(InvalidSurnameException e){
                                                            System.out.println("ERROR InvalidNameException::password corrente non corrisponde");
                                                        }
                                                        catch(EmptyStringException e){
                                                            System.out.println("ERROR EmptyStringException::nuova password vuota");
                                                        }
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 4: 
                                                break;
                                        }
                                    }while(sceltaMenuDesignatore!=4);
                                    
                                }
                                else{
                                    throw new AuthorizedAccessException();
                                }
                            }
                        }
                        if(accesso==false){
                            throw new AuthorizedAccessException(); 
                        }
                    }
                    catch(IOException e){
                        System.out.println("ERROR IOException::codice non valido");
                    }
                    catch(NumberFormatException e){
                        System.out.println("ERROR NumberFormatException::formato non valido");
                    }
                    catch(AuthorizedAccessException e){
                        System.out.println("ERROR AuthorizedAccessException::accesso negato");
                    }
                    break;
                }
                    
                //accedi come presidente
                case 3 -> {
                    try{
                        System.out.println("inserisci il nome");
                        nome=input.readString();
                        System.out.println("inserisci il cognome");
                        cognome=input.readString();
                        System.out.println("inserisci la password");
                        password=input.readString();
                        
                        if(presidente.getNome().equals(nome)){
                            if(presidente.getCognome().equals(cognome)){
                                if(presidente.getPassword().equals(password)){
                                    //accesso effettuato
                                    int sceltaMenuPresidente;
                                    Menu menuPresidente;
                                    do{
                                        int numeroVociPresidente=6;
                                        
                                        String[] vociMenuPresidente=new String[numeroVociPresidente];
                                        vociMenuPresidente[0]="0 -->\tEsci";
                                        vociMenuPresidente[1]="1 -->\tRegistra nuovo arbitro";
                                        vociMenuPresidente[2]="2 -->\tRegistra nuovo designatore";
                                        vociMenuPresidente[3]="3 -->\tElimina arbitro";
                                        vociMenuPresidente[4]="4 -->\tElimina designatore";
                                        vociMenuPresidente[5]="5 -->\tLogout";
                                        menuPresidente=new Menu(vociMenuPresidente);
                                        sceltaMenuPresidente=menuPresidente.sceltaMenu();

                                        switch(sceltaMenuPresidente){
                                            case 0 -> {
                                                exportRefereeAndMatch(elencoArbitri);
                                                exportDesignator(elencoDesignatori);
                                                System.exit(0);
                                            }
                                            case 1 -> {
                                                System.out.println("nuovo arbitro: ");
                                                System.out.println("Inserisci il nome");
                                                nome=input.readString();
                                                System.out.println("Inserisci il cognome");
                                                cognome=input.readString();
                                                try{
                                                    for(Arbitro arbitro : elencoArbitri){
                                                        if(arbitro.getNome().equals(nome) && arbitro.getCognome().equals(cognome)){
                                                            throw new ExistingRefereeException();
                                                        }
                                                    }
                                                    System.out.println("Inserisci la categoria");
                                                    categoria=input.readString();
                                                    System.out.println("Inserisci l'eta");
                                                    eta=input.readInt();
                                                    password=nome+"_"+cognome;
                                                    System.out.println("");
                                                    System.out.println("arbitro aggiunto con successo");
                                                    Arbitro arbitro=new Arbitro(nome, cognome, categoria, password, eta);
                                                    sezione.aggiungiArbitro(arbitro);
                                                }
                                                catch(ExistingRefereeException e){
                                                    System.out.println("ERROR ExistingRefereeException::arbitro gia esistente");
                                                }
                                                break;
                                            }
                                            case 2 -> {
                                                System.out.println("nuovo designatore: ");
                                                System.out.println("Inserisci il nome");
                                                nome=input.readString();
                                                System.out.println("Inserisci il cognome");
                                                cognome=input.readString();
                                                password="password";
                                                try{
                                                    for(Designatore designatore : elencoDesignatori){
                                                        if(designatore.getNome().equals(nome) && designatore.getCognome().equals(cognome)){
                                                            throw new ExistingDesignatorException();
                                                        }
                                                    }
                                                    System.out.println("designatore aggiunto correttamente");
                                                    Designatore designatore=new Designatore(nome, cognome, password);
                                                    sezione.aggiungiDesignatore(designatore);
                                                }
                                                catch(ExistingDesignatorException e){
                                                    System.out.println("ERROR ExistingDesignatorException::designatore gia esistente");
                                                }
                                                break;
                                            }
                                            case 3 -> {
                                                for(Arbitro arbitro : elencoArbitri){
                                                    System.out.println(arbitro);
                                                }
                                                System.out.println("elimina arbitro");
                                                System.out.println("Inserisci il nome");
                                                nome=input.readString();
                                                System.out.println("Inserisci il cognome");
                                                cognome=input.readString();
                                                System.out.println("Inserisci il codice");
                                                codice=input.readInt();

                                                for(Arbitro arbitro : elencoArbitri){
                                                    if(arbitro.getNome().equals(nome)){
                                                        if(arbitro.getCognome().equals(cognome)){
                                                            if(arbitro.getCodice()==codice){
                                                                sezione.eliminaArbitro(nome, cognome, codice);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                            case 4 -> {
                                                for(Designatore designatore : elencoDesignatori){
                                                    System.out.println(designatore);
                                                }
                                                System.out.println("elimina designatore");
                                                System.out.println("Inserisci il nome");
                                                nome=input.readString();
                                                System.out.println("Inserisci il cognome");
                                                cognome=input.readString();
                                                System.out.println("Inserisci il codice");
                                                codice=input.readInt();

                                                for(Designatore designatore : elencoDesignatori){
                                                    if(designatore.getNome().equals(nome)){
                                                        if(designatore.getCognome().equals(cognome)){
                                                            if(designatore.getCodice()==codice){
                                                                sezione.eliminaDesignatore(nome, cognome, codice);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            }
                                            case 5 -> {
                                                break;
                                            }
                                            case 6 -> {
                                                System.out.println("visualizza tutti gli arbitri");
                                                for(Arbitro arbitro : elencoArbitri){
                                                    
                                                }
                                                
                                                
                                            }
                                        }
                                    }while(sceltaMenuPresidente!=5);
                                }
                                else{
                                    throw new InvalidPasswordException();
                                }
                            }
                            else{
                                throw new InvalidSurnameException();
                            }
                        }
                        else{
                            throw new InvalidNameException();
                        }
                    }
                    catch(IOException e){
                        
                    }
                    catch(InvalidNameException e){
                        System.out.println("ERROR InvalidNameException::accesso negato-nome non valido");
                    }
                    catch(InvalidSurnameException e){
                        System.out.println("ERROR InvalidNameException::accesso negato-cognome non valido");
                    }
                    catch(InvalidPasswordException e){
                        System.out.println("ERROR InvalidNameException::accesso negato-password non valida");
                    }
                    
                    break;
                }
            }
        }while(voceScelta!=0);
        
        //ESPORTA SU FILE CSV
        exportRefereeAndMatch(elencoArbitri);
        exportDesignator(elencoDesignatori);
    }
    
    public static void exportRefereeAndMatch(ArrayList<Arbitro> elencoArbitri) {
        String nomeFile = "elencoArbitri.csv";
        String datiArbitro;
        TextFile fileArbitro = null;
        TextFile filePartite = null;
        
        try {
            fileArbitro = new TextFile(nomeFile, 'W');
            
            for (Arbitro arbitro : elencoArbitri) {
                datiArbitro = arbitro.getNome() + ";" + arbitro.getCognome() + ";" + arbitro.getCategoria() + ";" + arbitro.getEta() + ";" + arbitro.getPassoword();
                fileArbitro.toFile(datiArbitro);
                
                ArrayList<Partita> elencoPartite = arbitro.visualizzaPartite();
                
                for (Partita partita : elencoPartite) {
                    String nomeFileArbitro = "partite " + arbitro.getCognome() + "_" + arbitro.getNome().charAt(0) + ".csv";
                    filePartite = new TextFile(nomeFileArbitro, 'W', true);
                    
                    String datiPartita = partita.getSquadraOspitante() + ";" + partita.getSquadraOspite() + ";" + partita.getCitta() + ";" + partita.getGiorno() + ";" + partita.getDistanza() + ";"  + partita.getRimborso() + "€";
                    filePartite.toFile(datiPartita);
                    filePartite.closeFile(); 
                }
            }
            fileArbitro.closeFile();
            System.out.println("Esportazione avvenuta correttamente.");
        } catch (IOException e) {
            System.out.println("ERROR IOException: Impossibile accedere al file");
        } catch (FileException e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (fileArbitro != null) {
                    fileArbitro.closeFile();
                }
                if (filePartite != null) {
                    filePartite.closeFile();
                }
            } catch (IOException e) {
                System.out.println("Errore durante la chiusura del file: " + e.getMessage());
            }
        }
    }
    
    public static void importReferee(ArrayList<Arbitro> elencoArbitri, Sezione sezione){
        String rigaLetta;
        String[] datiArbitro;
        String nomeFile = "elencoArbitri.csv";
        TextFile fileArbitro = null;
        Arbitro arbitro;
        try{
            fileArbitro=new TextFile(nomeFile, 'R');
            do{
                rigaLetta=fileArbitro.fromFile();
                datiArbitro=rigaLetta.split(";");
                arbitro=new Arbitro(datiArbitro[0], datiArbitro[1], datiArbitro[2], datiArbitro[4], Integer.parseInt(datiArbitro[3]));
                sezione.aggiungiArbitro(arbitro);
            }while(true);
        }
        catch(EOFException e){
            try{
                fileArbitro.closeFile();
            } 
            catch (IOException ex){
                
            }
        }
        catch(FileException | IOException e){
            
        }
    }
    
    public static void importMatch(ArrayList<Arbitro> elencoArbitri, Sezione sezione) {
        String rigaLettaPartite;
        String nomeFilePartite;
        String[] datiPartita;
        TextFile filePartite=null;
        String[] day;
        LocalDate giorno;

        for (Arbitro arbitro : elencoArbitri) {
            try {
                nomeFilePartite = "partite " + arbitro.getCognome() + "_" + arbitro.getNome().charAt(0) + ".csv";
                filePartite = new TextFile(nomeFilePartite, 'R');
                do{ 
                    rigaLettaPartite = filePartite.fromFile();
                    if(rigaLettaPartite==null)
                        throw new EOFException();
                    datiPartita = rigaLettaPartite.split(";");
                    day = datiPartita[3].split("-");
                    giorno = LocalDate.of(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]));
                    Partita partita = new Partita(datiPartita[0], datiPartita[1], datiPartita[2], giorno, Integer.parseInt(datiPartita[4]));
                    sezione.designaPartita((int) arbitro.getCodice(), partita);
                }while(true);
                
            } 
            catch (FileException | IOException | NumberFormatException e) {
                try{
                    filePartite.closeFile();
                }
                catch(IOException ex){
                    
                }
            }
        }
    }
    
    public static void exportDesignator(ArrayList<Designatore> elencoDesignatori){
        String nomeFileDesignatori="designatori.csv";
        try{
            TextFile fileDesignatori=new TextFile(nomeFileDesignatori, 'W', true);
            String datiDesignatore;
            for(Designatore designatore : elencoDesignatori){
                datiDesignatore=designatore.getNome()+";"+designatore.getCognome()+";"+designatore.getPassword();
                fileDesignatori.toFile(datiDesignatore);
            }
            fileDesignatori.closeFile();
            System.out.println("Esportazione avvenuta correttamente.");
        }
        catch(IOException e){
            System.out.println("ERROR IOException::Impossibile accedere al file");
        }
        catch(FileException e){
            System.out.println("ERROR FileException::Impossibile accedere al file");
        }
    }

}