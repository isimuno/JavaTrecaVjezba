package main.hr.java.covidportal.main;

import main.hr.java.covidportal.iznimke.BolestIstihSimptomaException;
import main.hr.java.covidportal.iznimke.DuplikatKontaktiraneOsobeException;
import main.hr.java.covidportal.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Klasa Glavna predstavlja glavnu klasu programa
 *
 * @author isimun2
 */

public class Glavna {
    public static final Integer BROJ_ZUPANIJA = 3;
    public static final Integer BROJ_SIMPTOMA = 3;
    public static final Integer BROJ_BOLESTI = 4;
    public static final Integer BROJ_OSOBA = 3;
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);


    /**
     * @param args polje tipa String
     */
    public static void main(String[] args) {
        logger.info("Aplikacija pokrenuta! ");
        Scanner unos = new Scanner(System.in);
        Zupanija[] zupanije = new Zupanija[BROJ_ZUPANIJA];
        System.out.println("Unesite podatke o 3 županije: ");
        for (int i = 0; i < BROJ_ZUPANIJA; i++) {
            zupanije[i] = unesiZupaniju(i, unos);
        }

        Simptom[] simptomi = new Simptom[BROJ_SIMPTOMA];
        System.out.println("Unesite podatke o 3 simptoma: ");
        for (int i = 0; i < BROJ_SIMPTOMA; i++) {
            simptomi[i] = unesiSimptome(i, unos);
        }

        Bolest[] bolesti = new Bolest[BROJ_BOLESTI];
        System.out.println("Unesite podatke o 4 bolesti ili virusa: ");
        for (int i = 0; i < BROJ_BOLESTI; i++) {
            boolean ponoviUnos = false;
            do {
                ponoviUnos = false;
                try {
                    bolesti[i] = unesiBolesti(i, unos, simptomi, bolesti);
                } catch (BolestIstihSimptomaException e) {
                    logger.info(e.getMessage());
                    logger.warn("Aktivirana iznimka: " + e);
                    logger.error(e.getMessage(), e);
                    ponoviUnos = true;
                }
            } while (ponoviUnos);
        }

        Osoba[] osobe = new Osoba[BROJ_OSOBA];
        System.out.println("Unesite podatke o 3 osobe:");
        for (int i = 0; i < BROJ_OSOBA; i++) {
            try {
                osobe[i] = unesiOsobe(i, unos, bolesti, zupanije, osobe);
            } catch (DuplikatKontaktiraneOsobeException ex) {
                logger.info(ex.getMessage());
                logger.warn("Aktivirana iznimka" + ex.getMessage());
                System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu !");
            }
        }

        System.out.println("Unesene osobe: ");
        for (int i = 0; i < BROJ_OSOBA; i++) {
            System.out.println("================================================================");
            System.out.println("Ime i prezime: " + osobe[i].getIme() + "  " + osobe[i].getPrezime());
            System.out.println("Starost: " + osobe[i].getStarost());
            System.out.println("Županija prebivališta: " + osobe[i].getZupanija().getNaziv());
            System.out.println("Zaražen bolešću: " + osobe[i].getZarazenBolescu().getNaziv());
            if (osobe[i].getKontaktiraneOsobe() != null) {
                System.out.print("Kontakt osobe: ");
                for (int o = 0; o < osobe[i].getKontaktiraneOsobe().length; o++)
                    if (osobe[i].getKontaktiraneOsobe()[o] != null) {
                        System.out.print(osobe[i].getKontaktiraneOsobe()[o].getIme() + " " + osobe[i].getKontaktiraneOsobe()[o].getPrezime() + ", ");
                    }
                System.out.println("");
            } else {
                System.out.println("Kontakt osobe:  Nema kontaktiranih osoba!");
            }
        }

    }

    /**
     * Predstavlja metodu za unos Osoba u polje osobe
     * @param i predstavlja brojac Osoba tipa Integer
     * @param unos instanca klase za unos podataka
     * @param bolesti polje bolesti
     * @param zupanije polje zupanija
     * @param osobe polje osoba
     * @throws DuplikatKontaktiraneOsobeException iznimka
     * @return instanca Osobe
     */

    private static Osoba unesiOsobe(int i, Scanner unos, Bolest[] bolesti, Zupanija[] zupanije, Osoba[] osobe) throws DuplikatKontaktiraneOsobeException {
        String ime, prezime;
        Integer starost = 0;
        Zupanija zupanijaPrebivalista = new Zupanija("Naziv", 0);
        Bolest zarazenBolescu = new Bolest("Naziv", null);
        Osoba[] kontaktOsobe = new Osoba[0];
        System.out.print("Unesite ime " + (i + 1) + ". osobe: ");
        ime = unos.nextLine();
        System.out.print("Unesite prezime " + (i + 1) + ". osobe: ");
        prezime = unos.nextLine();
        boolean ponoviUnos = false;
        do {
            ponoviUnos = false;
            try {
                System.out.print("Unesite starost " + (i + 1) + ". osobe: ");
                starost = unos.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti za starost! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        do {
            try {
                System.out.println("Odaberite zupaniju prebivališta: ");
                for (int z = 0; z < BROJ_ZUPANIJA; z++) {
                    System.out.println((z + 1) + ". " + zupanije[z].getNaziv());
                }
                System.out.print("Odabir: ");
                Integer odabranaZupanija = unos.nextInt();
                if (odabranaZupanija > zupanije.length || odabranaZupanija < 1) {
                    System.out.println("Pograšan unos!");
                    ponoviUnos = true;
                } else {
                    ponoviUnos = false;
                    zupanijaPrebivalista = zupanije[odabranaZupanija - 1];
                }
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru zupanije! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        do {
            try {
                System.out.println("Odaberite bolest osobe: ");
                for (int b = 0; b < BROJ_BOLESTI; b++) {
                    System.out.println((b + 1) + ". " + bolesti[b].getNaziv());
                }
                System.out.print("Odabir: ");
                Integer odabranaBolest = unos.nextInt();
                if (odabranaBolest < 1 || odabranaBolest > bolesti.length) {
                    System.out.println("Pogrešan unos!");
                    ponoviUnos = true;
                } else {
                    zarazenBolescu = bolesti[odabranaBolest - 1];
                    ponoviUnos = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru bolesti! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        Integer brojKontaktOsoba = 0;
        if (i < 1) {
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .build();
            return novaOsoba;
        } else {
            do {
                try {
                    System.out.print("Unesite broj kontaktiranih osoba: ");
                    brojKontaktOsoba = unos.nextInt();
                    if (brojKontaktOsoba > i) {
                        System.out.println("Broj kontaktiranih osoba ne može biti veći od broja unesenih osoba ! ");
                        ponoviUnos = true;
                    } else {
                        ponoviUnos = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                    ponoviUnos = true;
                }
                unos.nextLine();
            } while (ponoviUnos);

            Osoba[] prosirenoPolje = Arrays.copyOf(osobe, brojKontaktOsoba);
            boolean dupliKorisnik = false;
            for (int o = 0; o < brojKontaktOsoba; o++) {
                do {
                    ponoviUnos = false;
                    try {
                        System.out.println("Odaberite " + (o + 1) + ". kontakt osobu: ");
                        for (int kontOsob = 0; kontOsob < BROJ_OSOBA; kontOsob++) {
                            if (osobe[kontOsob] != null) {
                                System.out.println((kontOsob + 1) + ". " + osobe[kontOsob].getIme() + " " + osobe[kontOsob].getPrezime());
                            }
                        }
                        System.out.print("Odabir: ");
                        Integer odabranaKontaktOsoba = unos.nextInt();
                        if (odabranaKontaktOsoba < 1 || odabranaKontaktOsoba > osobe.length) {
                            System.out.println("Pogrešan unos! ");
                            ponoviUnos = true;
                        } else {
                            dupliKorisnik = DuplikatKontaktOsoba(kontaktOsobe, osobe[odabranaKontaktOsoba - 1], o);
                            if (dupliKorisnik == false) {
                                kontaktOsobe = prosirenoPolje;
                                kontaktOsobe[o] = osobe[odabranaKontaktOsoba - 1];
                                ponoviUnos = false;
                            } else if (dupliKorisnik == true) {
                                System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu.");
                                ponoviUnos = true;
                            }
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                        ponoviUnos = true;
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti za odabir kontaktirane osobe! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                    } catch (DuplikatKontaktiraneOsobeException e) {
                        System.out.println("Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da odaberete neku drugu osobu.");
                        logger.info(e.getMessage());
                        logger.warn("Aktivirana iznimka: ", e);
                        logger.error(e.getMessage(), e);
                        ponoviUnos = true;
                    }
                    unos.nextLine();
                } while (ponoviUnos);


            }
            Osoba novaOsoba = new Osoba.Builder()
                    .seZove(ime)
                    .sePreziva(prezime)
                    .imaGodina(starost)
                    .pripadaZupaniji(zupanijaPrebivalista)
                    .imaBolest(zarazenBolescu)
                    .kontaktiraneOsobe(kontaktOsobe)
                    .build();
            return novaOsoba;
        }
    }

    /**
     * @param kontaktOsobe polje kontaktiranih osoba
     * @param osoba objekt Osoba
     * @param o brojač tipa Integer
     * @return boolean vrijednost
     * @throws DuplikatKontaktiraneOsobeException
     */
    private static boolean DuplikatKontaktOsoba(Osoba[] kontaktOsobe, Osoba osoba, int o) throws
            DuplikatKontaktiraneOsobeException {
        boolean postojiDuplikat = false;
        for (int i = 0; i < o; i++) {
            if (kontaktOsobe[i].equals(osoba)) {
                postojiDuplikat = true;
            }
        }
        if (postojiDuplikat == true) {
            throw new DuplikatKontaktiraneOsobeException("Odabrana osoba se već nalazi među kontaktiranim osobama. " +
                    "Molimo Vas da odaberete neku drugu osobu !");
        }
        return postojiDuplikat;
    }

    /**
     * @param i brojač bolesti tipa Integer
     * @param unos instanca klase za unos podataka
     * @param simptomi polje kreiranih simptoma
     * @param bolesti polje bolesti
     * @return instanca Bolest
     */
    private static Bolest unesiBolesti(int i, Scanner unos, Simptom[] simptomi, Bolest[] bolesti) {
        boolean ponoviUnos = false;
        Simptom[] simptomiBolesti;
        Integer vrstaBolesti = 0;
        do {
            ponoviUnos = false;
            try {
                System.out.println("Unosite li bolest ili virus ?");
                System.out.println("1. BOLEST");
                System.out.println("2. VIRUS");
                System.out.print("Odabir: ");
                vrstaBolesti = unos.nextInt();
                if (vrstaBolesti == 1 || vrstaBolesti == 2) {
                    ponoviUnos = false;
                } else {
                    System.out.println("Pogrešan unos! Pokušajte ponovno!");
                    ponoviUnos = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Molimo unesite brojčanu vrijednost! ");
                ponoviUnos = true;
                logger.info("Pokušaj unosa nedozvoljene vrijednosti odabiru vrste bolesti ! ");
                logger.warn("Aktivirana iznimka: ", ex);
            }
            unos.nextLine();
        } while (ponoviUnos);

        if (vrstaBolesti == 1) {
            String nazivBolesti;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv bolesti: ");
            nazivBolesti = unos.nextLine();
            do {
                ponoviUnos = false;
                try {
                    System.out.print("Unesite broj simptoma: ");
                    brojSimptoma = unos.nextInt();

                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrjednost!");
                    ponoviUnos = true;
                    logger.info("Pokušaj unosa nedozvoljene vrijednosti unosu broja simptoma! ");
                    logger.warn("Aktivirana iznimka: ", ex);
                    logger.error(ex.getMessage(), ex);
                }
                unos.nextLine();
            } while (ponoviUnos);

            simptomiBolesti = new Simptom[brojSimptoma];
            Integer odabraniSimptom;
            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    try {
                        System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                        for (int s = 0; s < BROJ_SIMPTOMA; s++) {
                            System.out.println((s + 1) + ". " + simptomi[s].getNaziv() + " " + simptomi[s].getVrijednost());
                        }
                        System.out.print("Odabir: ");
                        odabraniSimptom = unos.nextInt();
                        if (odabraniSimptom < 1 || odabraniSimptom > simptomi.length) {
                            System.out.println("Pogrešan unos!");
                            ponoviUnos = true;
                        } else {
                            ponoviUnos = false;
                            simptomiBolesti[j] = simptomi[odabraniSimptom - 1];
                        }

                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost !");
                        ponoviUnos = true;
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru simptoma bolesti ! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                        logger.error(ex.getMessage(), ex);
                    }
                    unos.nextLine();
                } while (ponoviUnos);
            }
            DuplikatBolesti(bolesti, simptomiBolesti, nazivBolesti, i);
            Bolest novaBolest = new Bolest(nazivBolesti, simptomiBolesti);
            return novaBolest;
        } else {
            String nazivVirusa;
            Integer brojSimptoma = 0;
            System.out.print("Unesite naziv virusa: ");
            nazivVirusa = unos.nextLine();
            do {
                ponoviUnos = false;
                try {
                    System.out.print("Unesite broj simptoma: ");
                    brojSimptoma = unos.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Pogreška, molimo unesite brojčanu vrjednost!");
                    logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru vrste bolesti! ");
                    logger.warn("Aktivirana iznimka: ", ex);
                    ponoviUnos = true;
                }
                unos.nextLine();
            } while (ponoviUnos);

            simptomiBolesti = new Simptom[brojSimptoma];
            Integer odabraniSimptom;

            for (int j = 0; j < brojSimptoma; j++) {
                do {
                    try {
                        System.out.println("Odaberite " + (j + 1) + ". simptom: ");
                        for (int s = 0; s < BROJ_SIMPTOMA; s++) {
                            System.out.println((s + 1) + ". " + simptomi[s].getNaziv() + " " + simptomi[s].getVrijednost());
                        }
                        System.out.print("Odabir: ");
                        odabraniSimptom = unos.nextInt();
                        if (odabraniSimptom < 1 || odabraniSimptom > simptomi.length) {
                            System.out.println("Pogrešan unos!");
                            ponoviUnos = true;
                        } else {
                            ponoviUnos = false;
                            simptomiBolesti[j] = simptomi[odabraniSimptom - 1];
                        }
                    } catch (InputMismatchException ex) {
                        System.out.println("Pogreška, molimo unesite brojčanu vrijednost!");
                        logger.info("Pokušaj unosa nedozvoljene vrijednosti pri odabiru simptoma virusa! ");
                        logger.warn("Aktivirana iznimka: ", ex);
                        logger.error(ex.getMessage(), ex);
                        ponoviUnos = true;
                    }
                    unos.nextLine();
                } while (ponoviUnos);
            }
            DuplikatBolesti(bolesti, simptomiBolesti, nazivVirusa, i);
            Bolest novaBolest = new Virus(nazivVirusa, simptomiBolesti);
            return novaBolest;
        }
    }

    /**
     * @param bolesti polje unesenih bolesti
     * @param simptomiBolesti polje unesenih simptoma bolesti
     * @param nazivBolesti varijabla koja sadrzi nazivBolesti
     * @param i brojac tipa Integer
     * @return boolean vrijednost postoji duplikat ili ne
     */
    private static boolean DuplikatBolesti(Bolest[] bolesti, Simptom[] simptomiBolesti, String nazivBolesti, int i) {
        boolean postojiDuplikat = false;
        for (int j = 0; j < i; j++) {
            if (bolesti[j] != null) {
                if (bolesti[j].getNaziv().equals(nazivBolesti)) {
                    for (int s = 0; s < bolesti[j].getSimptomi().length; s++) {
                        for (int s2 = 0; s2 < simptomiBolesti.length; s2++) {
                            if (bolesti[j].getSimptomi()[s].getVrijednost().equals(simptomiBolesti[s2].getVrijednost()) && s==s2) {
                                if(simptomiBolesti.length==bolesti[j].getSimptomi().length){
                                    postojiDuplikat = true; }

                            }
                        }
                    }
                }
            }
        }
        if (postojiDuplikat == true) {
            System.out.println("Pogrešan unos, već ste unijeli bolest ili virus s istim simptomima." +
                    " Molimo ponovite unos!");
            throw new BolestIstihSimptomaException("Unešena bolest/virus s istim simptomima.");
        }
        return postojiDuplikat;
    }

    /**
     * @param i brojac Simptoma tipa Integer
     * @param unos instanca klase za unos podataka
     * @return instanca Simptoma
     */
    private static Simptom unesiSimptome(int i, Scanner unos) {
        String naziv, vrijednost;
        System.out.print("Unesite naziv " + (i + 1) + ". simptoma: ");
        naziv = unos.nextLine();
        boolean ponoviUnos = false;
        do {
            System.out.print("Unesite vrijednost simptoma (RIJETKO, SREDNJE, ČESTO): ");
            vrijednost = unos.nextLine();

            if (vrijednost.equals("RIJETKO") || vrijednost.equals("SREDNJE") || vrijednost.equals("ČESTO")) {
                ponoviUnos = false;
            } else {
                System.out.println("Molimo koristite samo zadane vrijednosti (RIJETKO;SREDNJE;ČESTO)!");
                ponoviUnos = true;
            }
        } while (ponoviUnos);
        Simptom noviSimptom = new Simptom(naziv, vrijednost);
        return noviSimptom;
    }

    /**
     * @param i brojač Zupanija tipa Integer
     * @param unos instanca klase za unos podataka
     * @return instanca Zupanije
     */
    private static Zupanija unesiZupaniju(int i, Scanner unos) {
        String naziv;
        Integer brojStanovnika = 0;
        System.out.print("Unesite naziv " + (i + 1) + ". županije: ");
        naziv = unos.nextLine();
        boolean nastaviUnos = false;
        do {
            nastaviUnos = false;
            try {
                System.out.print("Unesite broj stanovnika: ");
                brojStanovnika = unos.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Pogreška, molimo unesite broj");
                logger.info("Pokušaj unosa nedozvoljene vrijednosti pri unosu broja stanovnika zupanije! ");
                logger.warn("Aktivirana iznimka: ", ex);
                logger.error(ex.getMessage(), ex);
                nastaviUnos = true;
            }
            unos.nextLine();
        } while (nastaviUnos == true);

        Zupanija novaZupanija = new Zupanija(naziv, brojStanovnika);
        return novaZupanija;
    }

}
