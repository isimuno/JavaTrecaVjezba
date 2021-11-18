package main.hr.java.covidportal.model;

/**
 * Klasa Virus koja nasljeduje klasu Bolest i implementira sucelje Zarazno
 */
public class Virus extends Bolest implements  Zarazno{

    /**
     * @param naziv varijabla tipa String koja definira naziv virusa
     * @param simptomi polje simptoma
     */
    public Virus(String naziv, Simptom[] simptomi) {
        super(naziv, simptomi);
    }

    @Override
    public void prelazakZarazeNaOsobu(Osoba osoba) {
        Virus nazivVirusa = new Virus(naziv,getSimptomi());
        osoba.setZarazenBolescu(nazivVirusa);
    }
}
