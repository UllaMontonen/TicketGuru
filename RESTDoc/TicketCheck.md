# Checking the Ticket information

This API allows you to check ticket information in the TicketGuru system.


## Get Ticket Information

Fetch the ticket information based on the ticket code.

**URL**: `/api/tickets/check/{ticketcode}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Scanner, user or admin

### Path Variables

`ticketcode`: The code of the ticket to be fetched.

Example: `81a37038-6d96-5ce7-ae6a-65e9f03d698f`

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
    "code": "81a37038-6d96-5ce7-ae6a-65e9f03d698f"
}
```


### Error Responses

**Condition:** If ticket with provided code does not exist.

**Code** : `404 Not Found`

**Content** : `Ticket with Code 81a37038-6d96-5ce7-ae6a-65e9f03d698f not found`

