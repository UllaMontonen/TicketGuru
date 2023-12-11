# Event API

This API allows you to manage events in the TicketGuru system.
* [Get all events](#getall)
* [Get event with id](#getid)
* [Get list of sold tickets for certain event](#gettickets)
* [Get list of ticket types for certain event](#gettickettypes)
* [Create new event](#post)
* [Update event info](#put)
* [Delete event](#delete)

## <a name="getall"></a>List Events

Get a list of all events.

**URL**: `/api/events/`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User

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
        "ticketsSold": 21,
        "eventDate": "2023-10-15",
        "tickets": [],
        "ticketTypes": []
    },
    {
        "id": 2,
        "name": "Sports Event",
        "place": "Stadium X",
        "city": "City B",
        "ticketAmount": 200,
        "ticketsSold": 21,
        "eventDate": "2023-11-05",
        "tickets": [],
        "ticketTypes": []
    }
```

## <a name="getid"></a>Get Event by ID

Get details of a specific event by its ID.

**URL**: `/api/events/{id}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User

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
    "error": "Event with ID {id }not found"
}
```

## <a name="gettickets"></a>Get list of sold tickets for certain event

Get details of a specific event by its ID.

**URL**: `/api/events/{id}/tickets`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User

### Success Response

**Code**: `200 OK`

**Content examples**

For an event with ID 1:

```json
{
        "id": 21,
        "event": {
            "id": 1,
            "name": "Assembly Winter 24",
            "place": "Helsingin Messukeskus",
            "city": "Helsinki",
            "ticketAmount": 1500,
            "eventDate": "2024-02-22"
        },
        "ticketType": {
            "id": 4,
            "description": "4 vrk kävijälippu TO-SU",
            "price": 40.0,
            "event": {
                "id": 1,
                "name": "Assembly Winter 24",
                "place": "Helsingin Messukeskus",
                "city": "Helsinki",
                "ticketAmount": 1500,
                "eventDate": "2024-02-22"
            }
        },
        "transaction": {
            "id": 17,
            "date": "2023-11-20",
            "amount": 121.0,
            "customer": {
                "id": 1,
                "name": "DBTesti",
                "email": "dbmail@mail.com"
            }
        },
        "verified": false,
        "code": "EVT-1-1700485021557"
    }
```

**Code**: `404 Not Found`

For an event with a non-existent ID:

```json
{
    "error": "Event with ID {id }not found"
}
```
## <a name="gettickettypes"></a>Get list of ticket types for certain event

Get details of a specific event by its ID.

**URL**: `/api/events/{id}/tickettypes`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User

### Success Response

**Code**: `200 OK`

**Content examples**

For an event with ID 1:

```json
[
    {
        "id": 4,
        "description": "4 vrk kävijälippu TO-SU",
        "price": 40.0,
        "event": {
            "id": 1,
            "name": "Assembly Winter 24",
            "place": "Helsingin Messukeskus",
            "city": "Helsinki",
            "ticketAmount": 1500,
            "eventDate": "2024-02-22"
        }
    },
    {
        "id": 5,
        "description": "3 vrk kävijälippu TO-SU",
        "price": 35.0,
        "event": {
            "id": 1,
            "name": "Assembly Winter 24",
            "place": "Helsingin Messukeskus",
            "city": "Helsinki",
            "ticketAmount": 1500,
            "eventDate": "2024-02-22"
        }
    }
]
```

**Code**: `404 Not Found`

For an event with a non-existent ID:

```json
{
    "error": "Event with ID {id} not found"
}
```


## <a name="post"></a>Create Event
Create a new event.

**URL** : `/api/events/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Admin

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
    "ticketsSold": 0,
    "eventDate": "2023-12-31",
    "tickets": [],
    "ticketTypes": []
}
```

## <a name="put"></a>Update Event

Update an existing event.

**URL** : `/api/events/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin

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
    "ticketsSold": 0,
    "eventDate": "2024-01-15",
    "tickets": [],
    "ticketTypes": []
}
```

## <a name="delete"></a>Delete Event

Delete an event by providing its ID.

**URL** : `/api/events/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

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
        "ticketsSold": 0,
        "eventDate": "2023-11-15",
        "tickets": [],
        "ticketTypes": []
    }
]
```
### Error Responses

**Code** : `404 Not Found`

```json
{
    "error": "Event with ID {id} not found"
}
```
