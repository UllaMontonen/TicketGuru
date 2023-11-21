# TicketGuru project

SK(R)UM team: Ulla Montonen, Santeri Sajari and Kristjan Savolainen. Additionally, Maiju Rekola participated in the creation of the project in its initial phase.

## Introduction

This project has been produced as part of the Software Project 1 course at Haaga-Helia University of Applied Sciences.

* The client for the project is the Ticket Office.
* We developed a ticket sales system named TicketGuru.
* The ticket office has the capability to create new events, specify the quantity of tickets available for each event, define ticket types for the event, and set the prices for these ticket types.
* Tickets can be printed after purchase. Additionally, all unsold tickets can be printed for on-site sales at the door.
* The application generates easily verifiable QR codes for the tickets, facilitating authenticity verification with a mobile terminal.

Implementation and Operating Environment Briefly:
* Maven project version 3.1.3
* Java 17

Dependencies
* Spring Boot DevTools
* Spring Web
* MySQL driver

The application is used on a computer or mobile device, and tickets are scanned with a mobile terminal.

In addition, a client-side has been created for the project, which is available as a published GitHub Pages: [TicketGuru client](https://kridesav.github.io/TicketGuru_client/).

## System Definition
Identified user roles in this project:
* Admin (admin)
* Ticket Seller (user)
* Ticket Inspector (scanner)
* Buyer (will be implemented in the future)

User roles and stories are presented in more detail here: [User Roles and Stories](UserRolesAndStories.md)

## User interface (will be updated!!!)

![](Kuva1.png "Kuva 1")

![](Kuva2.png "Kuva 2")

![](Kuva3.png "Kuva 3")

## Database

 ### Event
The "Event" table contains information about an event. Multiple tickets can be sold for an event, and each ticket is valid only for a specific event.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK           | Integer (autoincrement) | Event id |
| eventDate        | Date | Date of the event |
| place           | Varchar(100) | Place/space of the event |
| name            | Varchar(200) | Name of the event |
| city            | Varchar(100) | The city of the event |
| ticketAmount    | Integer | Number of tickets to be sold |

### Ticket
The "Ticket" table contains information about a ticket. A ticket is valid for a specific event. Each ticket can have one ticket type, and a ticket type may be associated with multiple tickets.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK      | Integer (autoincrement) | Ticket id |
| event_id  FK | Integer | Reference to the Event in the Event table. |
| ticketType_id FK     | Integer | Reference to the ticket type in the TicketType table. |
| transaction_id FK     | Integer | Reference to the sales event in the Transaction table. |
| code     | Varchar(100) | Ticket code |
| transaction_id FK     | Varchar(200) | Reference to the sales event in the Transaction table. |
| verified     | Boolean | Ticket verification status |

### TicketType
The "TicketType" table contains different types of tickets. The same TicketType can be associated with different tickets. Each ticket can have only one TicketType.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket Type id |
| description          | Varchar(200) | Description of the ticket type (e.g., adult, child). |
| price           | FLOAT(53) | Price of the ticket type |
| Event_id  FK | Integer | Reference to the Event in the Event table. |

### User (this need to be checked!!!)
A user can have only one active role. Each role always has specific permissions.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | User id |
| username           | Varchar(200) | username of the user |
| password           | Varchar(200) | password of the user |
| role           | Varchar(200) | User role |


### Customer
The "Customer" table contains customer information. Customers can purchase tickets using their own information.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket Type id |
| name           | Varchar(200) | Customer name |
| email           | Varchar(200) | email address |

### transaction
The "Transaction" table contains information about sales transactions. The table also includes details about the customer who purchased the ticket in that transaction.

| Attribute | Type | Description |
| --- |:---:| ---:|
| id PK     | Integer (autoincrement) | Ticket Type id |
| customer_id FK          | Integer | Reference to the Customer table. |
| amount           | FLOAT(53) | amount |
| date          | Date | Timestamp of the sales transaction. |

# Ticket Selling API

This is the documentation for the Ticket Selling API, which allows you to manage customers, events, transactions, ticket types and tickets in a ticket-selling application.

## Endpoints

### [Customers](RESTDoc/customer.md)

- **GET /api/customers**: Get a list of customers.
- **POST /api/customers**: Create a new customer.
- **PUT /api/customers/{id}**: Update an existing customer.
- **DELETE /api/customers/{id}**: Delete a customer.

### [Events](RESTDoc/event.md)

- **GET /api/events**: Get a list of events.
- **POST /api/events**: Create a new event.
- **PUT /api/events/{id}**: Update an existing event.
- **DELETE /api/events/{id}**: Delete an event.

### [Tickets](RESTDoc/ticket.md)

- **GET /api/tickets**: Get a list of tickets.
- **POST /api/tickets**: Create a new ticket.
- **PUT /api/tickets/{id}**: Update an existing ticket.
- **DELETE /api/tickets/{id}**: Delete a ticket.

### [Transactions](RESTDoc/transaction.md)

- **GET /api/transactions**: Get a list of transactions.
- **POST /api/transactions**: Create a new transaction.
- **PUT /api/transactions/{id}**: Update an existing transaction.
- **DELETE /api/transactions/{id}**: Delete a transaction.

### [Ticket Types](RESTDoc/TicketType.md)

- **GET /api/ticketTypes**: Get a list of ticketTypes.
- **POST /api/ticketTypes**: Create a new ticketType.
- **PUT /api/ticketTypes/{id}**: Update an existing ticketType.
- **DELETE /api/ticketTypes/{id}**: Delete a ticketType.

### [Ticket Sales](RESTDoc/TicketSale.md)

- **POST /api/event/{eventId}/ticketTypes/{ticketTypeId}**: Create a new ticket sale.

### [Ticket Check](RESTDoc/TicketCheck.md)

- **GET /api/tickets/check**: Checking the ticket

## Authentication

Currently all endpoints require a valid Token to be included in the request. A Token can be acquired from the Login view. More information on login can be found here: [login info](RESTDoc/login.md)

