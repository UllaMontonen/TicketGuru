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

![](C:\Users\maiju\OneDrive\Työpöytä\TicketGuru\TicketGuru\kuvat\Kuva1.png "Kuva 1")

![](kuvat/Kuva2.png "Kuva 2")

![](kuvat/Kuva3.png "Kuva 3")