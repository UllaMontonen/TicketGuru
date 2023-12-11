# Transaction

This API allows you to manage Transacyions in the TicketGuru system.
* [Get all transactions](#getall)
* [Get transaction with id](#getid)
* [Create new transaction](#post)
* [Update transaction info](#put)
* [Delete transaction](#delete)


**URL Params**

* `transaction_id`: id for transaction entity, acts as primary key in the table


## <a name="getall"></a>List Transactions 

Get a list of all Transactions.

**URL**: `/api/transactions/`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or user


### Success Response

**Code**: `200 OK`
**Content examples**

```json
{
        "id": 1,
        "date": "2023-12-12",
        "amount": 40.4,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
{
        "id": 2,
        "date": "2023-11-12",
        "amount": 60.6,
        "customer": {
            "id": 1,
            "name": "DBTesti",
            "email": "dbmail@mail.com"
        }
    },
```

## <a name="getid"></a>Get Transaction by ID

Get details of a specific Transaction by its ID.

**URL**: `/api/transactions/{id}`

**Method**: `GET`

**Auth required**: YES

**Permissions required**: Admin or user

### Success Response

**Code**: `200 OK`

**Content examples**

For an Transaction with ID 1:

```json
{
    "id": 1,
    "date": "2023-12-12",
    "amount": 40.4,
    "customer": {
        "id": 1,
        "name": "DBTesti",
        "email": "dbmail@mail.com"
    }
}
```

**Code**: `404 Not Found`

For a Tranasction with a non-existent ID:

```json
{
    "error": "Transaction with ID {id} not found"
}
```

## <a name="post"></a>Create Transaction
Create a new Transaction.

**URL** : `/api/transactions/`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Admin or user

**Request Body**
Provide the following details to create a new Transaction:

```json
    {
        "date": "2023-10-08",
        "amount": 25.34,
        "customer": {
            "id": 1
                    }
    }
```
### Success Response
**Code** : `201 Created`

**Content examples**

For a newly created Transaction:

```json
    {
        "date": "2023-10-08",
        "amount": 25.34,
        "customer": {
            "id": 1
                    }
    }
```

  
## <a name="put"></a>Update Transaction

Update an existing Transaction.

**URL** : `/api/transactions/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Admin or user

### Parameters

- `{id}`: The ID of the Transaction to update.

### Request Body

Provide the updated details for the Transaction.

```json
    {
        "date": "2023-10-08",
        "amount": 30,
        "customer": {
            "id": 1
                    }
    }
```
### Success Response

**Code** : `200 OK`

**Content examples**

For an updated Transaction with ID 1:

```json
    {
        "date": "2023-10-08",
        "amount": 30,
        "customer": {
            "id": 1
                    }
    }
```

## <a name="delete"></a>Delete Transaction

Delete a Transaction by providing its ID.

**URL** : `/api/transactions/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : Admin or user

### Parameters

- `{id}`: The ID of the Transaction to delete.

### Success Response

**Code** : `200 OK`

**Content examples**

For a list of remaining Transactions after deletion:

```json
    {
        "date": "2023-10-08",
        "amount": 30,
        "customer": {
            "id": 1
                    }
    }
```
### Error Responses

**Code** : `404 Not Found`

**Content** : `Transaction not found`



 



