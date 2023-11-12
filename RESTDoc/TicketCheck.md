# Checking the Ticket

This API allows you to check ticket information in the TicketGuru system.

## Get Ticket Information

Fetch the ticket information based on the ticket code.

**URL**: `/api/tickets/check/{ticketcode}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Scanner

### Path Variables

`ticketcode`: The code of the ticket to be fetched.
Example: `EVT-1-1699806364295`

### Success Response

**Code**: `200 OK`

**Content examples**

```json
{
    "id": 4,
    "event": {
        "id": 1,
        "name": "DBtapahtuma",
        "place": "kantapaikka",
        "city": "vaikka kanta",
        "ticketAmount": 1000,
        "eventDate": "2023-12-24"
    },
    "ticketType": {
        "id": 1,
        "description": "Testi",
        "price": 20.3,
        "event": {
            "id": 1,
            "name": "DBtapahtuma",
            "place": "kantapaikka",
            "city": "vaikka kanta",
            "ticketAmount": 1000,
            "eventDate": "2023-12-24"
        }
    },
    "transaction": {
        "id": 2,
        "date": "2023-11-12",
        "amount": 60.6,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
    "verified": false,
    "code": "EVT-1-1699806364295"
}
```

## Mark ticket as used

Mark the ticket as used based on the ticket code.

**URL**: `/api/tickets/markused/{ticketcode}`

**Method**: `PATCH`

**Auth required**: YES

**Permissions required**: Scanner

### Path Variables

`ticketcode`: The code of the ticket to be marked as used.
Example: `EVT-1-1699806364295`

### Success Response

**Code**: `200 OK`

**Content examples**

```json
{
    "id": 4,
    "event": {
        "id": 1,
        "name": "DBtapahtuma",
        "place": "kantapaikka",
        "city": "vaikka kanta",
        "ticketAmount": 1000,
        "eventDate": "2023-12-24"
    },
    "ticketType": {
        "id": 1,
        "description": "Testi",
        "price": 20.3,
        "event": {
            "id": 1,
            "name": "DBtapahtuma",
            "place": "kantapaikka",
            "city": "vaikka kanta",
            "ticketAmount": 1000,
            "eventDate": "2023-12-24"
        }
    },
    "transaction": {
        "id": 2,
        "date": "2023-11-12",
        "amount": 60.6,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
    "verified": true,
    "code": "EVT-1-1699806364295"
}
```

### Error Responses

**Condition:** If ticket with provided code does not exist.

**Code** : `404 Not Found`

**Content** : `Ticket with Code EVT-1-1699806364295 not found`

**Condition:** If ticket with provided code has already been used.

**Code** : `400 BAD REQUEST`

**Content** : `Ticket is already used`
