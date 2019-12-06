package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.function.Function;


// Pomoćna klasa koja je u suštini deque letova implementiran pomoću ArrayList
// Mogla se direktno koristiti klasa Deque ili ArrayList, ali na ovaj način dobijamo ljepši i čitljiviji kod u klasi Aviokompanija

public class Put implements Comparable<Put> {
    private ArrayList<Let> letovi;

    public Put() {
        letovi = new ArrayList<>();
    }

    public Put(Let l) {
        letovi = new ArrayList<Let>();
        letovi.add(l);
    }

    public ArrayList<Let> getLetovi() {
        return letovi;
    }

    public void setLetovi(ArrayList<Let> letovi) {
        this.letovi = letovi;
    }

    public void push(Let l) { letovi.add(l); }
    public void pushFront(Let l) { letovi.add(0, l); }

    public void pop() { letovi.remove(letovi.size()-1); }

    public Let first() { return letovi.get(0); }
    public Let last() { return letovi.get(letovi.size()-1); }

    // Sumiraj letove po funkciji f, koristi se zbog zadatka sa lambdom
    public Double suma(Function<Let, Double> f) {
        Double rezultat = 0.0;
        for(Let l : letovi) rezultat += f.apply(l);
        return rezultat;
    }

    // Dodajemo funkciju ukupnaDuzina (suma dužina svih letova)
    // kako bismo mogli klasu Put porediti po toj sumi, što omogućuje da u metodi
    public double ukupnaDuzina() {
        /*double rezultat = 0;
        for(Let l : letovi) rezultat += l.duzinaLeta();
        return rezultat;*/
        return suma((Let l) -> l.duzinaLeta());
    }

    @Override
    public int compareTo(Put sl) {
        double d = this.ukupnaDuzina();
        double d2 = sl.ukupnaDuzina();
        if (d < d2) return -1;
        if (d > d2) return 1;
        return 0;
    }
}
