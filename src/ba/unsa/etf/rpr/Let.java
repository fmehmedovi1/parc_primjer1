package ba.unsa.etf.rpr;

import java.time.LocalTime;

public class Let implements Comparable<Let> {
    private Aerodrom polazniAerodrom, dolazniAerodrom;
    private LocalTime vrijemePolaska, vrijemeDolaska;

    public Let(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime polazak, LocalTime dolazak) {
        this.polazniAerodrom = polazniAerodrom;
        this.dolazniAerodrom = dolazniAerodrom;
        this.vrijemePolaska = polazak;
        this.vrijemeDolaska = dolazak;
    }

    public Aerodrom getPolazniAerodrom() {
        return polazniAerodrom;
    }

    public void setPolazniAerodrom(Aerodrom polazniAerodrom) {
        this.polazniAerodrom = polazniAerodrom;
    }

    public Aerodrom getDolazniAerodrom() {
        return dolazniAerodrom;
    }

    public void setDolazniAerodrom(Aerodrom dolazniAerodrom) {
        this.dolazniAerodrom = dolazniAerodrom;
    }

    public LocalTime getVrijemePolaska() {
        return vrijemePolaska;
    }

    public void setVrijemePolaska(LocalTime polazak) {
        this.vrijemePolaska = polazak;
    }

    public LocalTime getVrijemeDolaska() {
        return vrijemeDolaska;
    }

    public void setVrijemeDolaska(LocalTime dolazak) {
        this.vrijemeDolaska = dolazak;
    }

    public int trajanje() {
        return (vrijemeDolaska.getHour()-vrijemePolaska.getHour()) * 60 + vrijemeDolaska.getMinute() - vrijemePolaska.getMinute();
    }

    public double duzinaLeta() {
        return Math.sqrt((dolazniAerodrom.getDuzina() - polazniAerodrom.getDuzina()) * (dolazniAerodrom.getDuzina() - polazniAerodrom.getDuzina()) +
                (dolazniAerodrom.getSirina() - polazniAerodrom.getSirina()) *  (dolazniAerodrom.getSirina() - polazniAerodrom.getSirina()));
    }

    @Override
    public int compareTo(Let let) {
        if (vrijemePolaska.isBefore(let.vrijemePolaska)) return -1;
        if (vrijemePolaska.isAfter(let.vrijemePolaska)) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return polazniAerodrom.getSifra() + "-" + dolazniAerodrom.getSifra();
    }
}
