# Marking ticket as used

This API allows you to mark ticket as used in the TicketGuru system.
If the verified field is true, then the ticket has been marked as used. If it is false, then it has not been marked as used.

## Mark ticket as used

Mark the ticket as used based on the ticket code.

**URL**: `/api/tickets/markused/{ticketcode}`

**Method**: `PATCH`

**Auth required**: YES

**Permissions required**: Scanner, user or admin

### Path Variables

`ticketcode`: The code of the ticket to be marked as used.

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
    "verified": true,
    "code": "81a37038-6d96-5ce7-ae6a-65e9f03d698f"
}
```

### Error Responses

**Condition:** If ticket with provided code does not exist.

**Code** : `404 Not Found`

**Content** : `Ticket with Code 81a37038-6d96-5ce7-ae6a-65e9f03d698f not found`

**Condition:** If ticket with provided code has already been used.

**Code** : `400 BAD REQUEST`

**Content** : `Ticket is already used`
