# TicketGuru projekti

SK(R)UM tiimi: Ulla Montonen, Santeri Sajari, Maiju Rekola, Kristjan Savolainen

## Johdanto

* Asiakkaana Tickettoimisto.
* Lipunmyyntijärjestelmä nimeltä TicketGuru. 
* Tickettoimisto syöttää myytävät liput, Tapahtuman kuvauksen ja hinnat ja asiakas pystyy ostamaan ne.
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

Lisää: [Käyttäjäryhmät ja tarinat](KayttajaroolitJaTarinat.md)

## Käyttöliittymä

![](Kuva1.png "Kuva 1")

![](Kuva2.png "Kuva 2")

![](Kuva3.png "Kuva 3")

## Tietokanta

 ### Event
Event-taulu sisältää tapahtuman tiedot. Tapahtumaan voidaan myydä monta lippua. Lippu käy vain tiettyyn tapahtumaan. 

| Attribuutti | Tyyppi | Kuvaus |
| --- |:---:| ---:|
| id PK           | Integer (autoincrement) | Tapahtuman id |
| eventDate        | Date | Tapahtuman ajankohta |
| place           | Varchar(100) | Tapahtuman paikka/tila |
| name            | Varchar(200) | Tapahtuman nimi |
| city            | Varchar(100) | Tapahtuman järjestyskaupunki |
| ticketAmount    | Integer | Myytävien Ticketjen määrä |

### Ticket
Ticket-taulu sisältää lipun tiedot. Ticket käy tiettyyn tapahtumaan. Yhdellä lipulla voi olla yksi Ticket tyyppi. Ticket tyyppi voi olla useammalla lipulla.

| Attribuutti | Tyyppi | Kuvaus |
| --- |:---:| ---:|
| id PK      | Integer (autoincrement) | Lipun id |
| event_id  FK | Integer | Viittaus Tapahtumaan Event-taulussa |
| ticketType_id FK     | Integer | Viittaus lipun tyyppiin TicketType-taulussa |
| transaction_id FK     | Integer | Viittaus myynti tapahtumaan transaction-taulussa |
| code     | Varchar(100) | Lipun koodi |
| transaction_id FK     | Varchar(200) | Viittaus myynti tapahtumaan transaction-taulussa |
| verified     | Boolean | Maksutapahtuman tila |

### TicketType
TicketType-taulu sisältää Ticketjen eri tyypit. Sama TicketType voi olla eri lipuilla. Yhdellä lipulla voi olla vain yksi TicketType.

| Attribuutti | Tyyppi | Kuvaus |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket tyypin id |
| description          | Varchar(200) | Ticket tyypin kuvaus (esim. aikuinen, lapsi) |
| price           | FLOAT(53) | Ticket tyypin hinta |
| Event_id  FK | Integer | Viittaus Tapahtumaan Event-taulussa |

### Customer
Customer-taulu sisältää asiakkaan tiedot. Asiakkaat voivat omilla tiedoilla ostaa lippuja. 

| Attribuutti | Tyyppi | Kuvaus |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket tyypin id |
| name           | Varchar(200) | Asiakkaan nimi |
| email           | Varchar(200) | Sähköposti osoite |

### transaction
transaction-taulu sisältää myyntitapahtuman tiedot. Taulu sisältää myös sen asiakkaan jonka lipun osti tiedot.

| Attribuutti | Tyyppi | Kuvaus |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket tyypin id |
| customer_id FK          | Integer | Asiakkaan taulu viittaus |
| amount           | FLOAT(53) | määrä |
| date          | Date | Myyntitapahtuman aikaleima |

# Ticket Selling API

This is the documentation for the Ticket Selling API, which allows you to manage customers, events, transactions, ticket types and tickets in a ticket-selling application.

## Endpoints

### Customers

- **GET /api/customers**: Get a list of customers.
- **POST /api/customers**: Create a new customer.
- **PUT /api/customers/{id}**: Update an existing customer.
- **DELETE /api/customers/{id}**: Delete a customer.

### [Events](REST Doc/event.md)

- **GET /api/events**: Get a list of events.
- **POST /api/events**: Create a new event.
- **PUT /api/events/{id}**: Update an existing event.
- **DELETE /api/events/{id}**: Delete an event.

### [Tickets](REST doc/ticket.md)

- **GET /api/tickets**: Get a list of tickets.
- **POST /api/tickets**: Create a new ticket.
- **PUT /api/tickets/{id}**: Update an existing ticket.
- **DELETE /api/tickets/{id}**: Delete a ticket.

### Transactions

- **GET /api/transactions**: Get a list of transactions.
- **POST /api/transactions**: Create a new transaction.
- **PUT /api/transactions/{id}**: Update an existing transaction.
- **DELETE /api/transactions/{id}**: Delete a transaction.

### Ticket Types

- **GET /api/ticketTypes**: Get a list of ticketTypes.
- **POST /api/ticketTypes**: Create a new ticketType.
- **PUT /api/ticketTypes/{id}**: Update an existing ticketType.
- **DELETE /api/ticketTypes/{id}**: Delete a ticketType.