# TicketGuru projekti

SK(R)UM tiimi: Ulla Montonen, Santeri Sajari, Maiju Rekola, Kristjan Savolainen

## Johdanto

* Asiakkaana lipputoimisto.
* Lipunmyyntijärjestelmä nimeltä TicketGuru. 
* Lipputoimisto syöttää myytävät liput, tapahtuman kuvauksen ja hinnat ja asiakas pystyy ostamaan ne.
* Asiakas pystyy tulostamaan ostamansa liput.
* Sovellus tuottaa lipuille helposti tarkastettavan koodin, millä pystyy todentamaan lipun aitouden mobiilipäätteellä.

Toteutus- ja toimintaympäristö lyhyesti:
* Maven projekti 3.1.3
* Java 17

Riippuvuudet/dependencies
* Spring Boot DevTools
* Spring Web
* MySQL driver

Käytetään tietokoneella ja liput luetaan mobiilipäätteellä.

## Järjestelmän määrittely
Tässä projektissa tunnistettuja käyttäjärooleja:
* Admin
* Lipunmyyjä
* Lipuntarkastaja
* Ostaja

Lisää: [Käyttäjäryhmät ja tarinat](https://github.com/UllaMontonen/TicketGuru/blob/dev/KayttajaroolitJaTarinat.md)

## Käyttöliittymä

![](Kuva1.png "Kuva 1")

![](Kuva2.png "Kuva 2")

![](Kuva3.png "Kuva 3")

## Tietokanta

![](Tietokantakaavio.png "Tietokantakaavio")

 ### Tapahtuma
Tapahtuma-taulu sisältää tapahtuman tiedot. Tapahtumaan voidaan myydä monta lippua. Lippu käy vain tiettyyn tapahtumaan. 

| Attribuutti     | Tyyppi                  | Kuvaus                       |
| --------------- |:-----------------------:| ----------------------------:|
| Tapahtuma_id PK | Integer (autoincrement) | Tapahtuman id                |
| Aika            | Date                    | Tapahtuman ajankohta         |
| Paikka          | Varchar(100)            | Tapahtuman paikka/tila       |
| Nimi            | Varchar(200)            | Tapahtuman nimi              |
| Kaupunki        | Varchar(100)            | Tapahtuman järjestyskaupunki |
| Lippumäärä      | Integer                 | Myytävien lippujen määrä     |

### Lippu
Lippu-taulu sisältää lipun tiedot. Lippu käy tiettyyn tapahtumaan. Yhdellä lipulla voi olla yksi lipputyyppi. Lipputyyppi voi olla useammalla lipulla. 

| Attribuutti      | Tyyppi                  | Kuvaus                                      |
| ---------------- |:-----------------------:| -------------------------------------------:|
| Lippu_id PK      | Integer (autoincrement) | Lipun id                                    |
| Tapahtuma_id  FK | Integer                 | Viittaus tapahtumaan tapahtuma-taulussa     |
| Tyyppi_id FK     | Varchar(100)            | Viittaus lipun tyyppiin lipputyyli-taulussa |

### Lipputyyppi
Lipputyyppi-taulu sisältää lippujen eri tyypit. Sama lipputyyppi voi olla eri lipuilla. Yhdellä lipulla voi olla vain yksi lipputyyppi.

| Attribuutti   | Tyyppi      | Kuvaus                                      |
| ------------- |:-----------:| -------------------------------------------:|
| Tyyppi_id PK  | C/100       | Lipputyypin id                              |
| Kuvaus        | C/200       | Lipputyypin kuvaus (esim. aikuinen, lapsi)  |
| Hinta         | Integer     | Lipputyypin hinta                           |