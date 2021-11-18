package main.hr.java.covidportal.model;

/**
 * Klasa Bolest nasljeđuje klasu imenovani entitet
 * služi za kreiranje instance Bolesti
 */

public class Bolest extends ImenovaniEntitet{
    private Simptom[] simptomi;

    /**
     * @param naziv varijabla naziva bolesti
     * @param simptomi polje simptoma bolesti
     */
    public Bolest(String naziv, Simptom[] simptomi) {
        super(naziv);
        this.simptomi = simptomi;
    }

    public Simptom[] getSimptomi() {
        return simptomi;
    }

    public void setSimptomi(Simptom[] simptomi) {
        this.simptomi = simptomi;
    }
}
