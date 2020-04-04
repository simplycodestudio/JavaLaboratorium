package zjazd3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Aplikacja {

  public static void main(String[] args) {
//    testMagazynuDanych();
      testWczytywaczDanych();
  }

  public static void testMagazynuDanych() {
    MagazynDanych magazynDanych = new MagazynDanych();
    magazynDanych.generujDane(10);
    magazynDanych.wyswietlDane();
    magazynDanych.zapiszDoPliku2("dane.txt");
    magazynDanych.wyswietlDane();

    try {
      magazynDanych.czytajZpliku("dane.txt");
    } catch (FileNotFoundException e) {
      System.out.println("cos poszło nie tak");
    } catch (IOException e) {
        e.printStackTrace();
    }
    magazynDanych.wyswietlDane();
  }

  /**
   * W metodzie testWczytywaczDanych dodana jest implementacja zgodna z wytycznymi z 1 zadania
   */
  public static void testWczytywaczDanych() {
    NaszeDane daneZKlawiatury = null;
    List<NaszeDane> daneZPliku = null;
    System.out.println("Wprowadź dane");
    while (true) {
      try {
        daneZKlawiatury = (new WczytywaczDanych().zKlawiatury());
        daneZPliku = (new WczytywaczDanych()).zPliku("dane.txt");
        break;
      } catch (NaszWyjatek w) {
        System.out.println(w.getMessage());
        if (w.akcja == NaszWyjatek.Akcja.ZREZYGNUJ) {
          break;
        }
        System.out.print(
            "Wprowadź dane we właściwy sposób:\n"
                + "liczba całkowita po niej odstęp, ale nie Enter\n"
                + "liczba rzeczywista"
                + "(separator dziesiętny zgodnie z ustawieniami systemu)"
                + "znowu odstęp, ale nie Enter\n"
                + "następnie tekst do wczytania i w końcu Enter\n"
                + "zrelaksuj się i próbuj: ");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Wprowadziłeś: " + daneZKlawiatury);
    System.out.println("\nZADANIE 1\n");
    daneZPliku.stream().forEach(System.out::println);
  }


}
