# Ticket API

This API allows you to manage Tickets in the TicketGuru system.
* [Get all tickets](#getall)
* [Get ticket with id](#getid)
* [Create new ticket](#post)
* [Update ticket info](#put)
* [Delete ticket](#delete)

**URL Params**

* `ticket_id`: id for ticket entity, acts as primary key in the table

## <a name="getall"></a>List Tickets

Get a list of all Tickets.

**URL**: `/api/tickets/`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User or Scanner

### Success Response

**Code**: `200 OK`

**Content examples**

```json
    [
    {
        "id": 17,
        "event": {
            "id": 3,
            "name": "Joulun tähdet -juhlakonsertti",
            "place": "Aleksanterin teatteri",
            "city": "Helsinki",
            "ticketAmount": 1000,
            "eventDate": "2023-11-21"
        },
        "ticketType": {
            "id": 13,
            "description": "Aitio",
            "price": 100.0,
            "event": {
                "id": 3,
                "name": "Joulun tähdet -juhlakonsertti",
                "place": "Aleksanterin teatteri",
                "city": "Helsinki",
                "ticketAmount": 1000,
                "eventDate": "2023-11-21"
            }
        },
        "transaction": {
            "id": 13,
            "date": "2023-11-20",
            "amount": 100.0,
            "customer": {
                "id": 1,
                "name": "DBTesti",
                "email": "dbmail@mail.com"
            }
        },
        "verified": false,
        "code": "e5c7aee8-429f-4757-b581-cca5e6764158"
    },
    ]
```


## <a name="getid"></a>Get Ticket by ID

Get details of a specific Ticket by its ID.

**URL**: `/api/tickets/{id}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or User or Scanner

### Success Response

**Code**: `200 OK`

**Content examples**

For a ticket with ID 1:

```json
    {
    "id": 1,
    "event": {
        "id": 3,
        "name": "Joulun tähdet -juhlakonsertti",
        "place": "Aleksanterin teatteri",
        "city": "Helsinki",
        "ticketAmount": 1000,
        "eventDate": "2023-11-21"
    },
    "ticketType": {
        "id": 10,
        "description": "Permanto (vasen)",
        "price": 58.5,
        "event": {
            "id": 3,
            "name": "Joulun tähdet -juhlakonsertti",
            "place": "Aleksanterin teatteri",
            "city": "Helsinki",
            "ticketAmount": 1000,
            "eventDate": "2023-11-21"
        }
    },
    "transaction": {
        "id": 32,
        "date": "2023-11-22",
        "amount": 58.5,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
    "verified": true,
    "code": "c4749a82-2579-4a2c-b72a-8f518df4bc99"
}
```

**Code**: `404 Not Found`

For a ticket with a non-existent ID:

```json
{
    "error": "Ticket with ID {id} not found"
}
```



## <a name="post"></a>Create Ticket

Get details of a specific Ticket by its ID.

**URL**: `/api/tickets/`

**Method**: `POST`

**Auth required**: YES

**Permissions required**: Admin or User

**Request Body**
Provide the following details to create a new Ticket:

```json
    {
    "customerId": 1,
    "ticketsDTO": 
        [{
            "eventId": 1,
            "ticketTypeId": 4,
            "ticketAmount": 1
        }]
    }
```
### Success Response
**Code** : `201 Created`

**Content examples**

For a newly created Ticket:

```json
    {
        "id": 73,
        "event": {
            "id": 1,
            "name": "Assembly Winter 24",
            "place": "Helsingin Messukeskus",
            "city": "Helsinki",
            "ticketAmount": 1497,
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
                "ticketAmount": 1497,
                "eventDate": "2024-02-22"
            }
        },
        "transaction": {
            "id": 53,
            "date": "2023-12-08T11:58:04.864+00:00",
            "amount": 40.0,
            "customer": {
                "id": 1,
                "name": "DBTesti",
                "email": "dbmail@mail.com"
            }
        },
        "verified": false,
        "code": "9c1bd588-e5f9-4cb8-aca0-02b275c98d4b"
    }
```


  
## <a name="put"></a>Update Ticket

Update an existing Ticket.

**URL** : `/api/tickets/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin or User

### Parameters

- `{id}`: The ID of the Ticket to update.

### Request Body

Provide the updated details for the Ticket.

```json
    {
    "event": {
        "id": 1
    },
    "ticketType": { 
        "id": 4 
    },
    "transaction": {
        "id": 53
    },
    "verified": false,
    "code": "edited code"
    }
```
### Success Response

**Code** : `200 OK`

**Content examples**

For an updated Ticket with ID 73:

```json
{
    "id": 73,
    "event": {
        "id": 1,
        "name": "Assembly Winter 24",
        "place": "Helsingin Messukeskus",
        "city": "Helsinki",
        "ticketAmount": 1497,
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
            "ticketAmount": 1497,
            "eventDate": "2024-02-22"
        }
    },
    "transaction": {
        "id": 53,
        "date": "2023-12-08",
        "amount": 40.0,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
    "verified": false,
    "code": "edited code"
}
```



# <a name="delete"></a>Delete Ticket

Delete an Customer by providing its ID.

**URL** : `/api/tickets/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin or User


### Parameters

- `{id}`: The ID of the Ticket to delete.

### Success Responses

**Code** : `200 OK`

**Content examples**

For a list of remaining Tickets after deletion:

```json
    {
        "id": 17,
        "event": {
            "id": 3,
            "name": "Joulun tähdet -juhlakonsertti",
            "place": "Aleksanterin teatteri",
            "city": "Helsinki",
            "ticketAmount": 1000,
            "eventDate": "2023-11-21"
        },
        "ticketType": {
            "id": 13,
            "description": "Aitio",
            "price": 100.0,
            "event": {
                "id": 3,
                "name": "Joulun tähdet -juhlakonsertti",
                "place": "Aleksanterin teatteri",
                "city": "Helsinki",
                "ticketAmount": 1000,
                "eventDate": "2023-11-21"
            }
        },
        "transaction": {
            "id": 13,
            "date": "2023-11-20",
            "amount": 100.0,
            "customer": {
                "id": 1,
                "name": "DBTesti",
                "email": "dbmail@mail.com"
            }
        },
        "verified": false,
        "code": "EVT-3-1700483748955"
    },
    {
        "id": 18,
        "event": {
            "id": 3,
            "name": "Joulun tähdet -juhlakonsertti",
            "place": "Aleksanterin teatteri",
            "city": "Helsinki",
            "ticketAmount": 1000,
            "eventDate": "2023-11-21"
        },
        "ticketType": {
            "id": 12,
            "description": "Parvi",
            "price": 58.5,
            "event": {
                "id": 3,
                "name": "Joulun tähdet -juhlakonsertti",
                "place": "Aleksanterin teatteri",
                "city": "Helsinki",
                "ticketAmount": 1000,
                "eventDate": "2023-11-21"
            }
        },
        "transaction": {
            "id": 14,
            "date": "2023-11-20",
            "amount": 58.5,
            "customer": {
                "id": 1,
                "name": "DBTesti",
                "email": "dbmail@mail.com"
            }
        },
        "verified": true,
        "code": "EVT-3-1700483835936"
    "rest of tickets...."
    }
```
### Error Responses

**Code** : `404 Not Found`

**Content** : `Ticket with ID {id} not found`
