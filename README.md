# Treca-laboratorijska-vjezba


3.1. TEMA VJEŽBE
Svrha laboratorijske vježbe je obrađivanje različitih vrsta iznimaka te
kreiranje i bacanje iznimaka kojima se signalizira nastanak specifičnih
situacija prilikom izvođenja Java aplikacije. Osim toga je opisan način
korištenje vanjske biblioteke za upravljane log datotekama – Logback.
Također, potrebno je aplikaciju dokumentirati Javadoc dokumentacijom.
3.2. ZADATAK ZA PRIPREMU
Proširiti rješenje druge laboratorijske vježbe na način da se kopira
rješenje te preimenuje u naziv koji sadrži indeks „3“, umjesto „2“. Osim
same mape s projektom, potrebno je promijeniti i naziv projekta unutar
IntelliJ-a korištenjem opcije „Refactor->Rename“. Program je potrebno
proširiti na sljedeći način:
1. Na svim mjestima u aplikaciji gdje se unose brojčane vrijednosti
implementirati obradu iznimke „InputMismatchException“ na način
da se korisniku ispiše poruka o pogrešci te se od njega traži ponovni
unos vrijednosti.
2. Kreirati novi paket „hr.java.covidportal.iznimke“.
3. U paket „hr.java.covidportal.iznimke“ dodati novu označenu iznimku
„DuplikatKontaktiraneOsobeException“ koja se baca u slučaju kad se
više puta odabere ista osoba među kontaktirane osobe. Klasa
„DuplikatKontaktiraneOsobeException“ mora sadržavati tri
konstruktora: jedan prima „String message“, drugi „Throwable cause“,
a treći oba ta parametra. Ako korisnik unese neispravnu kontaktiranu
osobu, potrebno je ispisati poruku o pogrešci i zatražiti unos nove
osobe. Provjera je li odabran duplikat kontaktirane osobe mora se
provjeravati u zasebnoj metodi u klasi „Glavna“, a iznimka hvatati i
obrađivati u „main“ metodi.
4. Implementirati ograničenje koje ne dopušta unos veći broj
kontaktiranih osoba od broja prethodno unesenih osoba. Na primjer,
tijekom unose druge osobe je potrebno ograničiti broj kontaktiranih 
osoba na samo jednu osobu, a tijekom unosa treće osobe je potrebno
ograničiti broj kontaktiranih osoba na dvije osobe.
5. U paket „hr.java.covidportal.iznimke“ dodati novu neoznačenu
iznimku „BolestIstihSimptomaException“ koja se baca u slučaju
unosa nove bolesti ili virusa sa simptomima koje već ima jedna od
unesenih bolesti ili virusa. Klasa „BolestIstihSimptomaException“
mora sadržavati tri konstruktora: jedan prima „String message“,
drugi „Throwable cause“, a treći oba ta parametra. Ako korisnik unese
neispravnu bolest ili virus, potrebno je ispisati poruku o pogrešci i
zatražiti unos nove bolesti ili virusa. Provjera je li unesena bolest ili
virus s istim simptomima kao jedna od prethodnih bolesti ili virusa
mora se provjeravati u zasebnoj metodi u klasi „Glavna“, a iznimka
hvatati i obrađivati u „main“ metodi.
6. Provjeriti nalazi li se unutar projekta datoteka „pom.xml“ (ako nije,
ponoviti postupak iz koraka 12. prve laboratorijske vježbe). U projekt
dodati „dependency“ pod nazivom „logback-classic“ verzije „1.2.3“. U
mapu „resources“ IntelliJ projekta dodati datoteku „logback.xml“.
Nakon toga odabrati opciju „Maven-Reload project“.
7. Sve klase proširiti na način da se svi kritični događaji i pogreške
zajedno s informacijama o iznimkama zapisuju u „log“ datoteku.
Potrebno je koristiti svaku od metoda koja određuje razinu „logiranja“
barem jednom.
8. Na kraju vježbe je potrebno korištenjem Javadoc dokumentacije
dokumentirati sve klase, konstruktore i metode (osim „getter“ i
„setter“ metoda) te generirati Javadoc dokumentaciju korištenjem
uputa sa sljedećeg linka:
https://www.jetbrains.com/help/idea/working-with-codedocumentation.html#generate-javadoc.

Primjer izvođenja programa:
Unesite podatke o 3 županije:
Unesite naziv županije: GRAD ZAGREB
Unesite broj stanovnika: asb
Pogreška u formatu podataka, molimo ponovite unos!
Unesite broj stanovnika: 1000000
Unesite naziv županije: MEĐIMURSKA
Unesite broj stanovnika: 100000
Unesite naziv županije: VARAŽDINSKA
Unesite broj stanovnika: 200000
Unesite podatke o 3 simptoma:
Unesite naziv simptoma: VISOKA TEMPERATURA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): ČESTO
Unesite naziv simptoma: GLAVOBOLJA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): SREDNJE
Unesite naziv simptoma: GRLOBOLJA
Unesite vrijednost simptoma (RIJETKO, SREDNJE ILI ČESTO): RIJETKO
Unesite podatke o 4 bolesti ili virusa:
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 1
Unesite naziv bolesti ili virusa: HERPES
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 3
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: Covid
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 3
Pogrešan unos, već ste unijeli bolest ili virus s istim simptomima. Molimo
ponovite unos.
Unesite naziv bolesti ili virusa: Covid
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 1
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: Gripa
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 2
Unosite li bolest ili virus?
1) BOLEST
2) VIRUS
ODABIR >> 2
Unesite naziv bolesti ili virusa: KONJUKTIVITIS
Unesite broj simptoma: 1
Odaberite 1. simptom:
1. VISOKA TEMPERATURA ČESTO
2. GLAVOBOLJA SREDNJE
3. GRLOBOLJA RIJETKO
Odabir: 2
Unesite 1. ime osobe: Pero
Unesite prezime osobe: Perić
Unesite starost osobe: 88
Unesite županiju prebivališta osobe: 
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 1
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 4
Unesite 2. ime osobe: Janko
Unesite prezime osobe: Janić
Unesite starost osobe: 77
Unesite županiju prebivališta osobe:
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 2
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 2
Unesite broj osoba koje su bile u kontaktu s tom osobom: 2
Neispravan unos, možete odabrani maksimalno 1 osobu.
Unesite broj osoba koje su bile u kontaktu s tom osobom: 1
Unesite osobe koje su bile u kontaktu s tom osobom:
Odaberite 1. osobu:
1. Pero Perić
Odabir: 1
Unesite 3. ime osobe: Marica
Unesite prezime osobe: Ždrerić
Unesite starost osobe: 44
Unesite županiju prebivališta osobe: 
1. GRAD ZAGREB
2. MEĐIMURSKA
3. VARAŽDINSKA
Odabir: 3
Unesite bolest ili virus osobe:
1. HERPES
2. Covid
3. Gripa
4. KONJUKTIVITIS
Odabir: 3
Unesite broj osoba koje su bile u kontaktu s tom osobom: 2
Unesite osobe koje su bile u kontaktu s tom osobom:
Odaberite 1. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 2
Odaberite 2. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 2
Odabrana osoba se već nalazi među kontaktiranim osobama. Molimo Vas da
odaberete neku drugu osobu.
Odaberite 2. osobu:
1. Pero Perić
2. Janko Janić
Odabir: 1
Popis osoba:
Ime i prezime: Pero Perić
Starost: 88
Županija prebivališta: GRAD ZAGREB
Zaražen bolešću: Gripa
Kontaktirane osobe:
Nema kontaktiranih osoba.
Ime i prezime: Janko Janić
Starost: 77
Županija prebivališta: MEĐIMURSKA
Zaražen bolešću: Gripa
Kontaktirane osobe:
Pero Perić
Ime i prezime: Marica Ždrerić
Starost: 44
Županija prebivališta: VARAŽDINSKA
Zaražen bolešću: Gripa
Kontaktirane osobe:
Pero Perić
Janko Janić
NAPOMENE:
1. Osim implementacija vježbe prema uputama, dozvoljeno je uvoditi i
promjene ako su opravdane i ne narušavaju koncepte objektnoorijentiranog programiranja.
2. Nije dozvoljeno korištenje elemenata gradiva koje još nije obrađeno,
kao što su liste i slično.
