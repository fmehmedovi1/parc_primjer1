package ba.unsa.etf.rpr;

public class Aerodrom {
    private String nazivAerodroma, grad, sifra;
    double sirina, duzina;

    public Aerodrom(String nazivAerodroma, String grad, String sifra, double sirina, double duzina) throws IlegalnaSifraAerodroma {
        this.nazivAerodroma = nazivAerodroma;
        this.grad = grad;
        setSifra(sifra);
        this.sirina = sirina;
        this.duzina = duzina;
    }

    public String getNazivAerodroma() {
        return nazivAerodroma;
    }

    public void setNazivAerodroma(String nazivAerodroma) {
        this.nazivAerodroma = nazivAerodroma;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) throws IlegalnaSifraAerodroma {
        if (sifra.length() != 3 || !sifra.matches("[a-zA-Z]+"))
            throw new IlegalnaSifraAerodroma("Ilegalna sifra " + sifra + ", probajte " +
                    grad.replaceAll("[^a-zA-Z0-9]", "").toUpperCase().substring(0, 3));
        if (sifra.toUpperCase() != sifra)
            throw new IlegalnaSifraAerodroma("Ilegalna sifra "+ sifra + ", probajte " + sifra.toUpperCase());
        this.sifra = sifra;
    }

    public double getSirina() {
        return sirina;
    }

    public void setSirina(double sirina) {
        this.sirina = sirina;
    }

    public double getDuzina() {
        return duzina;
    }

    public void setDuzina(double duzina) {
        this.duzina = duzina;
    }
}
