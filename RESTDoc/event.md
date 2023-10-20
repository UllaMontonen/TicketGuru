# Event API

This API allows you to manage events in the TicketGuru system.

## List Events

Get a list of all events.

**URL**: `/api/events/`

**Method**: `GET`

**Auth required**: NO

**Permissions required**: None

### Success Response

**Code**: `200 OK`

**Content examples**

```json

    {
        "id": 1,
        "name": "Concert",
        "place": "Venue A",
        "city": "City A",
        "ticketAmount": 100,
        "eventDate": "2023-10-15"
    },
    {
        "id": 2,
        "name": "Sports Event",
        "place": "Stadium X",
        "city": "City B",
        "ticketAmount": 200,
        "eventDate": "2023-11-05"
    }
```

## Get Event by ID

Get details of a specific event by its ID.

**URL**: `/api/events/{id}`

**Method**: `GET`

**Auth required**: NO

**Permissions required**: None

### Success Response

**Code**: `200 OK`

**Content examples**

For an event with ID 1:

```json
{
    "id": 1,
    "name": "Concert",
    "place": "Venue A",
    "city": "City A",
    "ticketAmount": 100,
    "eventDate": "2023-10-15"
}
```

**Code**: `404 Not Found`

For an event with a non-existent ID:

```json
{
    "error": "Event not found"
}
```

## Create Event
Create a new event.

**URL** : `/api/events/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Request Body**
Provide the following details to create a new event:

```json
{
    "name": "New Event",
    "place": "New Location",
    "city": "New City",
    "ticketAmount": 200,
    "eventDate": "2023-12-31"
}
```
### Success Response
**Code** : `201 Created`

**Content examples**

For a newly created event:

```json
{
    "id": 3,
    "name": "New Event",
    "place": "New Location",
    "city": "New City",
    "ticketAmount": 200,
    "eventDate": "2023-12-31",
    "tickets": [],
    "ticketTypes": []
}
```

## Update Event

Update an existing event.

**URL** : `/api/events/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : None

### Parameters

- `{id}`: The ID of the event to update.

### Request Body

Provide the updated details for the event.

```json
{
    "name": "Updated Event",
    "place": "Updated Location",
    "city": "Updated City",
    "ticketAmount": 250,
    "eventDate": "2024-01-15"
}
```
### Success Response

**Code** : `200 OK`

**Content examples**

For an updated event with ID 1:

```json
{
    "id": 1,
    "name": "Updated Event",
    "place": "Updated Location",
    "city": "Updated City",
    "ticketAmount": 250,
    "eventDate": "2024-01-15",
    "tickets": [],
    "ticketTypes": []
}
```

## Delete Event

Delete an event by providing its ID.

**URL** : `/api/events/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

### Parameters

- `{id}`: The ID of the event to delete.

### Success Response

**Code** : `200 OK`

**Content examples**

For a list of remaining events after deletion:

```json
[
    {
        "id": 2,
        "name": "Event 2",
        "place": "Location 2",
        "city": "City 2",
        "ticketAmount": 150,
        "eventDate": "2023-11-15",
        "tickets": [],
        "ticketTypes": []
    }
]
```
### Error Responses

**Code** : `404 Not Found`

**Content** : `Event not found`
