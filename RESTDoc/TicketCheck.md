# Checking the Ticket

This API allows you to check ticket information in the TicketGuru system.

## Check Ticket

Check ticket and mark it as used

**URL**: `/api/tickets/check/{ticketcode}`

**Method**: `PATCH`

**Auth required**: YES

**Permissions required**: Scanner

### Path Variables

Provide the following details to check and mark tickets as used:

`ticketcode`: The code of the ticket to be checked and marked as used.

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

**Condition:** If ticket with provided code does not exist.

**Code** : `404 Not Found`

**Content** : `Ticket with Code EVT-2-1698595259570 not found`

**Condition:** If ticket with provided code has already been used.

**Code** : `400 BAD REQUEST`

**Content** : `Ticket is already used`
