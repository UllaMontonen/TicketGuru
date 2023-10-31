# TicketType API

This API allows you to manage TicketTypes in the TicketGuru system.

## List TicketTypes

Get a list of all TicketTypes.

**URL**: `/api/tickettypes/`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User

### Success Response

**Code**: `200 OK`

**Content examples**

```json
    [
    {
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
    {
        "id": 2,
        "description": "Opiskelija",
        "price": 21.32,
        "event": {
            "id": 2,
            "name": "TestiTapahtuma2",
            "place": "Maxine",
            "city": "Helsinki",
            "ticketAmount": 100,
            "eventDate": "2023-09-27"
        }
    }
]
```

## Get TicketType by ID

Get details of a specific TicketType by its ID.

**URL**: `/api/tickettypes/{id}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin

### Success Response

**Code**: `200 OK`

**Content examples**

For an TicketType with ID 1:

```json
    {
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
}
```

**Code**: `404 Not Found`

For an TicketType with a non-existent ID:

```json
{
    "error": "TicketType not found"
}
```

## Create TicketType
Create a new TicketType.

**URL** : `/api/tickettypes/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Admin

**Request Body**
Provide the following details to create a new TicketType:

```json
    {
        "description": "Aikuinen",
        "price": 40,
        "event": {
            "id": 2
        }
    }
```
### Success Response
**Code** : `201 Created`

**Content examples**

For a newly created TicketType:

```json
    {
        "id": 3,
        "description": "Aikuinen",
        "price": 40.0,
        "event": {
            "id": 2,
            "name": "TestiTapahtuma2",
            "place": "Maxine",
            "city": "Helsinki",
            "ticketAmount": 100,
            "eventDate": "2023-09-27"
        }
    }
```

## Update TicketType

Update an existing TicketType.

**URL** : `/api/tickettypes/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin

### Parameters

- `{id}`: The ID of the TicketType to update.

### Request Body

Provide the updated details for the TicketType.

```json
    {
        "description": "Aikuinen VIP",
        "price": 55,
        "event": {
            "id": 2
        }
    }
```
### Success Response

**Code** : `200 OK`

**Content examples**

For an updated TicketType with ID 1:

```json
{
    "id": 3,
    "description": "Aikuinen VIP",
    "price": 55.0,
    "event": {
        "id": 2,
        "name": "TestiTapahtuma2",
        "place": "Maxine",
        "city": "Helsinki",
        "ticketAmount": 100,
        "eventDate": "2023-09-27"
    }
}
```

## Delete TicketType

Delete an TicketType by providing its ID.

**URL** : `/api/tickettypes/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin

### Parameters

- `{id}`: The ID of the TicketType to delete.

### Success Response

**Code** : `200 OK`

**Content examples**

For a list of remaining TicketTypes after deletion:

```json
    [
    {
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
    {
        "id": 2,
        "description": "Opiskelija",
        "price": 21.32,
        "event": {
            "id": 2,
            "name": "TestiTapahtuma2",
            "place": "Maxine",
            "city": "Helsinki",
            "ticketAmount": 100,
            "eventDate": "2023-09-27"
        }
    }
]
```
### Error Responses

**Code** : `404 Not Found`

**Content** : `TicketType not found`