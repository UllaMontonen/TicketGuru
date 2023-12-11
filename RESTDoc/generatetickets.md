# Generate Tickets API

This API allows you to generate unsold tickets for a certain event in the TicketGuru system.

## <a name="generatetickets"></a>Generate unsold tickets

Generate unsold tickets for a certain event.

**URL**: `/api/generatetickets`

**Method**: `POST`

**Auth required**: YES

**Content example**

```json
{
    "eventId": 1,
    "ticketTypeId": 1,
    "ticketAmount": 2
}
```

### Success Response

**Condition**: If the tickets are successfully generated

**Code**: `200 OK`

**Content example**

A list of generated tickets.

### Error Response

**Condition**: If no event or ticket type is found with the provided ID, or the event doesn't match the ticket type

**Code**: `400 BAD REQUEST`

**Content example**

```json
{
    "message": "No event or ticket type found with ID, or event doesn't match ticket type"
}
```

**Implementation**: The endpoint is implemented in the `generateUnsoldTickets` method. The method checks if the event and ticket type exist and match. If they do, it generates the specified amount of tickets, saves them to the `TicketRepository`, and responds with a 200 status code. If they don't, it responds with a 400 status code.