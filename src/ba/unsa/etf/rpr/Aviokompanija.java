package ba.unsa.etf.rpr;

import javax.naming.SizeLimitExceededException;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;

public class Aviokompanija {
    private int maxLetova;
    private ArrayList<Let> letovi;

    public Aviokompanija(int maxLetova) {
        this.maxLetova = maxLetova;
        letovi = new ArrayList<>();
    }

    public void registrujLet(Aerodrom polazniAerodrom, Aerodrom dolazniAerodrom, LocalTime polazak, LocalTime dolazak, boolean brzi) throws SizeLimitExceededException {
        if (letovi.size() >= maxLetova)
            throw new SizeLimitExceededException("");
        if (brzi)
            letovi.add(new BrziLet(polazniAerodrom, dolazniAerodrom, polazak, dolazak));
        else
            letovi.add(new BrziLet(polazniAerodrom, dolazniAerodrom, polazak, dolazak));
    }

    public int brojLetova() { return letovi.size(); }

    public Map<String, List<Let>> dolazniLetovi() {
        HashMap<String, List<Let>> mapa = new HashMap<>();
        for(Let l: letovi) {
            String grad = l.getDolazniAerodrom().getGrad();
            if (!mapa.containsKey(grad))
                mapa.put(grad, new ArrayList<Let>());
            mapa.get(grad).add(l);
        }
        return mapa;
    }

    public Set<Let> uZraku(LocalTime sada) {
        Set<Let> rezultat = new TreeSet<>();
        for(Let l : letovi)
            if (l.getVrijemeDolaska().isAfter(sada) && l.getVrijemePolaska().isBefore(sada))
                rezultat.add(l);
        return rezultat;
    }


    // Pomoćna funkcija za metode nadjiNajkraci i nadjiNajbolji

    // NAPOMENA: Potpuno korektno rješenje ovog zadatka bi moralo koristiti DFS i to rješenje je dato ispod
    // No na ispitu svi testovi su tražili najviše dva koraka, pa je takvo rješenje priznato
    private ArrayList<Put> dajSvePuteve(String polazniGrad, String odredisniGrad, Put trenutniPut) {
        ArrayList<Put> sve = new ArrayList<>();

        for(Let l : letovi) {
            // Da li je dolazni grad već posjećen
            boolean posjecen = false;
            for(Let dl : trenutniPut.getLetovi()) {
                if (l.getDolazniAerodrom().getSifra().equals( dl.getPolazniAerodrom().getSifra()) )
                    posjecen = true;
            }
            if (posjecen) continue;

            // Dodajemo nove letove
            if (l.getPolazniAerodrom().getGrad().equals(polazniGrad))
                // Da li je ovo direktan let od polaznog do odredišnog grada?
                if (l.getDolazniAerodrom().getGrad().equals(odredisniGrad))
                    sve.add(new Put(l));

                // Ako nije, pokušavamo konstruisati put
                else {
                    // Generišemo sve moguće puteve preko ovog grada i stavljamo ih u pomoćnu listu
                    trenutniPut.push(l);
                    ArrayList<Put> kandidati = dajSvePuteve( l.getDolazniAerodrom().getGrad(), odredisniGrad, trenutniPut );

                    // Vraćamo trenutni put u polazno stanje
                    trenutniPut.pop();

                    // Sada iz liste izbacujemo one kojima se preklapa vrijeme polaska
                    for (Put p : kandidati) {
                        if (p.first().getVrijemePolaska().isAfter( l.getVrijemeDolaska()) ) {
                            p.pushFront(l);
                            sve.add(p);
                        }
                    }
                }
        }
        return sve;
    }

    public ArrayList<Let> nadjiNajkraci(String polazniGrad, String odredisniGrad) {
        return Collections.min(dajSvePuteve(polazniGrad, odredisniGrad, new Put())).getLetovi();
    }

    public ArrayList<Let> nadjiNajbolji(String polazniGrad, String odredisniGrad, Function<Let, Double> f) {
        return Collections.min(dajSvePuteve(polazniGrad, odredisniGrad, new Put()), (Put sl1, Put sl2)->sl1.suma(f).compareTo(sl2.suma(f))).getLetovi();
    }
}
