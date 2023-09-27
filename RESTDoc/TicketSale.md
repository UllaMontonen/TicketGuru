# Ticket Sales API

This API allows you to sell tickets for events in the TicketGuru system.

## Sale Event Tickets

Sell tickets for a specific event and ticket type.

**URL**: `/api/events/{eventId}/tickettypes/{ticketTypeId}`

**Method**: `POST`

**Auth required**: NO

**Permissions required**: None

### Parameters

- `{eventId}`: The ID of the event for which you want to sell tickets.
- `{ticketTypeId}`: The ID of the ticket type you want to sell.

### Request Body

Provide the following details to sell tickets:

```json
{
    "customerId": 1, // Optional, provide customer ID if the customer exists
    "ticketAmount": 1
}
```

### Success Response

**Code**: `200 OK`

**Content examples**

```json
[
    {
        "id": 3,
        "event": {
            "id": 2,
            "name": "TestiTapahtuma2",
            "place": "Maxine",
            "city": "Helsinki",
            "ticketAmount": 100,
            "eventDate": "2023-09-27"
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
                "eventDate": "2023-09-27"
            }
        },
        "transaction": {
            "id": 3,
            "date": "2023-09-27T15:52:26.715+00:00",
            "amount": 20.3,
            "customer": {
                "id": 1,
                "name": "Testi Pesti",
                "email": "email@mail.com"
            }
        },
        "verified": true,
        "code": "EVT-2-1695829946717"
    }
]
```

### Error Responses

**Code** : `400 Bad Request`

**Content** : `Invalid request body`

**Code** : `404 Not Found`

**Content** : `Event or ticket not found`

**Code** : `409 Conflict`

**Content** : `Customer not found, please provide valid customer information`

**Code** : `403 Not Found`

**Content** : `Customer not allowed to make the purchase`


