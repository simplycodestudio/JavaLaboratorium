package zjazd3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WczytywaczDanych {

    private Scanner skaner;
    int liczbaCalkowita;
    double liczbaRzeczywista;
    String napis;

    public WczytywaczDanych(){
        skaner = new Scanner(System.in);
    }

    NaszeDane zKlawiatury() throws NaszWyjatek {

        try{
            liczbaCalkowita = skaner.nextInt();
            liczbaRzeczywista = skaner.nextDouble();
            napis = skaner.nextLine();
            if (napis.equals("")){
                throw new NoSuchElementException();
            }
            return new NaszeDane(liczbaCalkowita,liczbaRzeczywista,napis);
        }  catch (InputMismatchException e){
            String wiadomosc = "Niewłaściwe dane";
            NaszWyjatek w = new NaszWyjatek(wiadomosc, NaszWyjatek.Akcja.POWTORZ);
            throw w;
        } catch (NoSuchElementException e){
            String wiadomosc = "Za mało danych";
            NaszWyjatek w = new NaszWyjatek(wiadomosc, NaszWyjatek.Akcja.POWTORZ);
            throw w;
        }catch (IllegalStateException e){
            String wiadomosc = "Skaner nie jest otwarty";
            NaszWyjatek w = new NaszWyjatek(wiadomosc, NaszWyjatek.Akcja.ZREZYGNUJ);
            throw w;
        } finally{

        }
    }

    List<NaszeDane> zPliku(String nazwaPliku) throws IOException, NaszWyjatek{
        FileReader czytacz = new FileReader(nazwaPliku);
        BufferedReader bufor = new BufferedReader(czytacz);
        String linia;
        List<NaszeDane> listaDanych = new ArrayList<>();

        while ((linia = bufor.readLine()) != null) {
            Scanner skaner = new Scanner(linia);
            skaner.useLocale(Locale.US);
            try {
                skaner.skip("i=");
                liczbaCalkowita = skaner.nextInt();
                skaner.skip(" x=");
                liczbaRzeczywista = skaner.nextDouble();
                skaner.skip(" Opis: ");
                napis = skaner.nextLine();
                listaDanych.add(new NaszeDane(liczbaCalkowita,liczbaRzeczywista,napis));

            } catch (NoSuchElementException e) {
                continue;
            }
        }
        bufor.close();
        czytacz.close();
       return listaDanych;

    }
}
