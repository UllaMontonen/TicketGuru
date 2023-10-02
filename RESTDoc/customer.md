# Customer API

This API allows you to manage Customers in the TicketGuru system.

## List Customers

Get a list of all Customers.

**URL**: `/api/customers/`

**Method**: `GET`

**Auth required**: NO

**Permissions required**: None

### Success Response

**Code**: `200 OK`

**Content examples**

```json
    {
        "id": 1,
        "firstName": "John Doe",
        "email": "john.doe@example.com"
    },
    {
        "id": 2,
        "firstName": "Jane Smith",
        "email": "jane.smith@example.com"
    }
```

## Get Customer by ID

Get details of a specific Customer by its ID.

**URL**: `/api/customers/{id}`

**Method**: `GET`

**Auth required**: NO

**Permissions required**: None

### Success Response

**Code**: `200 OK`

**Content examples**

For an Customer with ID 1:

```json
    {
        "id": 1,
        "firstName": "John Doe",
        "email": "john.doe@example.com"
    }
```

**Code**: `404 Not Found`

For an Customer with a non-existent ID:

```json
{
    "error": "Customer not found"
}
```

## Create Customer
Create a new Customer.

**URL** : `/api/customers/`

**Method** : `POST`

**Auth required** : `NO`

**Permissions required** : `None`

**Request Body**
Provide the following details to create a new Customer:

```json
    {
        "firstName": "John Doe",
        "email": "john.doe@example.com"
    }
```
### Success Response
**Code** : `201 Created`

**Content examples**

For a newly created Customer:

```json
    {
        "id": 3,
        "firstName": "John Doe",
        "email": "john.doe@example.com"
    }
```

## Update Customer

Update an existing Customer.

**URL** : `/api/customers/{id}`

**Method** : `PUT`

**Auth required** : NO

**Permissions required** : None

### Parameters

- `{id}`: The ID of the Customer to update.

### Request Body

Provide the updated details for the Customer.

```json
    {
        "id": 1,
        "firstName": "Updated name",
        "email": "updatedemail@example.com"
    }
```
### Success Response

**Code** : `200 OK`

**Content examples**

For an updated Customer with ID 1:

```json
{
        "id": 1,
        "firstName": "Updated name",
        "email": "updatedemail@example.com"
}
```

## Delete Customer

Delete an Customer by providing its ID.

**URL** : `/api/customers/{id}`

**Method** : `DELETE`

**Auth required** : NO

**Permissions required** : None

### Parameters

- `{id}`: The ID of the Customer to delete.

### Success Response

**Code** : `200 OK`

**Content examples**

For a list of remaining Customers after deletion:

```json
    {
        "id": 2,
        "firstName": "John Doe",
        "email": "john.doe@example.com"
    }
```
### Error Responses

**Code** : `404 Not Found`

**Content** : `Customer not found`
