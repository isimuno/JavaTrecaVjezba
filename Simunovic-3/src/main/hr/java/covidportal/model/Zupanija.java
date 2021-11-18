package main.hr.java.covidportal.model;

/**
 * Klasa Zupanija koja nasljeđuje klasu ImenovniEntitet
 */
public class Zupanija extends  ImenovaniEntitet{

    private Integer brojStanovnika;

    /**
     * @param naziv  varijabla tipa String koja definira naziv zupanije
     * @param brojStanovnika varijabla tipa Integer koja definira broj stanovnika u zupaniji
     */
    public Zupanija(String naziv, Integer brojStanovnika) {
        super(naziv);
        this.brojStanovnika = brojStanovnika;
    }

    public Integer getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(Integer brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }
}
