package zjazd3;

import java.io.*;
import java.util.*;

public class MagazynDanych {

  protected Deque<NaszeDane> kontenerKolejka = new ArrayDeque<>();

  public void generujDane(int n) {
    kontenerKolejka.clear();
    Random generator = new Random(System.currentTimeMillis());
    for (int k = 0; k < n; k++) {
      int i = generator.nextInt(100);
      double x = generator.nextDouble();
      kontenerKolejka.offerLast(new NaszeDane(i, x, "dane losowe"));
    }
  }

  public void wyswietlDane() {
    System.out.println("\n \n");
    if (kontenerKolejka.isEmpty()) {
      System.out.println("Brak danych");
    } else {
      for (NaszeDane d : kontenerKolejka) {
        System.out.println(d);
      }
    }
  }

  public void zapiszDoPliku(String nazwaPliku) {
    try {
      FileWriter zapisywacz = new FileWriter(nazwaPliku);
      while (!kontenerKolejka.isEmpty()) {
        NaszeDane d = kontenerKolejka.removeFirst();
        zapisywacz.write(d.toString() + "\r\n");
      }
      zapisywacz.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }


  /**
   * @zadanie 2
   * @opis Metoda podobnie jak pierwotna implementacja (zapiszDoPliku) zapisuje zawartość kolejki do pliku, lecz bez usuwania jej zawartości
   * @param nazwaPliku
   */
  public void zapiszDoPliku2(String nazwaPliku) {
    try {
      FileWriter zapisywacz = new FileWriter(nazwaPliku);
      for (Iterator<NaszeDane> iterator = kontenerKolejka.iterator(); iterator.hasNext(); ) {
        String value = String.valueOf(iterator.next());
        zapisywacz.write(value + "\r\n");
      }
      zapisywacz.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void czytajZpliku(String nazwaPliku) throws IOException {
    FileReader czytacz = new FileReader(nazwaPliku);
    BufferedReader bufor = new BufferedReader(czytacz);
    String linia;


    while ((linia = bufor.readLine()) != null) {

      Scanner skaner = new Scanner(linia);
      skaner.useLocale(Locale.US);
      skaner.skip("i=");
      int i = skaner.nextInt();
      skaner.skip(" x=");
      double x = skaner.nextDouble();
      skaner.skip(" Opis: ");
      String s = skaner.nextLine();

      kontenerKolejka.offerLast(new NaszeDane(i, x, s));
    }
    bufor.close();
    czytacz.close();
  }

  /**
   * @zadanie 3
   * @opis Metoda czyta dane z pliku i umieszcza je w kolejce. Pozostaje przy tym odporna na błędy pojawiające się w liniach pliku wejściowego pomijając je.
   * @param nazwaPliku
   * @throws IOException
   */

  public void czytajZpliku2(String nazwaPliku) throws IOException {
    FileReader czytacz = new FileReader(nazwaPliku);
    BufferedReader bufor = new BufferedReader(czytacz);
    String linia;

    while ((linia = bufor.readLine()) != null) {
      Scanner skaner = new Scanner(linia);
      skaner.useLocale(Locale.US);
      try {
        skaner.skip("i=");
        int i = skaner.nextInt();
        skaner.skip(" x=");
        double x = skaner.nextDouble();
        skaner.skip(" Opis: ");
        String s = skaner.nextLine();

        kontenerKolejka.offerLast(new NaszeDane(i, x, s));
      } catch (NoSuchElementException e) {
        continue;
      }
    }
    bufor.close();
    czytacz.close();
  }
}
