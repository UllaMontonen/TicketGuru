# Checking the Ticket

This API allows you to check ticket information in the TicketGuru system.

## Check Ticket

Check ticket information and if the ticket is valid

**URL**: `/api/tickets/check`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Scanner

### Request Body

Provide the following details to sell tickets:

```json
{
    "ticketcode": "EVT-2-1698595259570"
}
```

### Success Response

**Code**: `200 OK`

**Content examples**

```json
{
    "id": 1,
    "event": {
        "id": 1,
        "name": "TestiTapahtuma",
        "place": "Apollo",
        "city": "Helsinki",
        "ticketAmount": 100,
        "eventDate": "2023-11-07"
    },
    "ticketType": {
        "id": 1,
        "description": "Normaali",
        "price": 20.3,
        "event": {
            "id": 1,
            "name": "TestiTapahtuma",
            "place": "Apollo",
            "city": "Helsinki",
            "ticketAmount": 100,
            "eventDate": "2023-11-07"
        }
    },
    "transaction": {
        "id": 1,
        "date": "2023-11-07",
        "amount": 56.34,
        "customer": {
            "id": 1,
            "name": "Testi Pesti",
            "email": "email@mail.com"
        }
    },
    "verified": true,
    "code": "ABC-123"
}
```

### Error Responses


**Code** : `404 Not Found`

**Content** : `Ticket with Code EVT-2-1698595259570 not found`

