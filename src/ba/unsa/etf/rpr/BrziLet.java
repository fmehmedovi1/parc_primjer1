package ba.unsa.etf.rpr;

import java.time.LocalTime;

public class BrziLet extends Let {
    public BrziLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime polazak, LocalTime dolazak) {
        super(polazniAerodrom, dolazniAerodrom, polazak, dolazak);
    }

    public double duzinaLeta() {
        return super.duzinaLeta() / 2;
    }
}
