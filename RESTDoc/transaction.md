**Transaction**
----

* **URL**

  /api/transactions | /api/transactions/{transaction_id}

* **Method:**
  
  **`GET`** /transactions for all | /transactions/{transaction_id} for single transaction
  **Auth required**: YES
  **Permissions required**: Admin or User
  **`POSt`** /transactions
  **Auth required**: YES
  **Permissions required**: Admin or User
  **`DELETE`** /transactions/{transaction_id}
  **Auth required**: YES
  **Permissions required**: Admin or User
  **`PUT`** /transactions/{transaction_id}
  **Auth required**: YES
  **Permissions required**: Admin or User
  
*  **URL Params**

   `transaction_id`: id for transaction entity, acts as primary key in the table

* **Data Params**

  All colums in table are nullable, none are required
    "date": date object/string
    "amount": double
    "customer: customer entity



* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `All transactions or transaction`

    * **Code:** 201 <br />
    **Content:** `Created transaction`
 
* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `None`

* **Sample Body:**

* **GET**
```
{
    "id": 1,
    "date": "2023-09-25",
    "amount": 56.34,
    "customer": {
        "id": 1,
        "name": "Testi Pesti",
        "email": "email@mail.com"
    }
}
```

* **POST**
```
{
    "date": "2023-09-25",
    "amount": 56.34,
    "customer": {
        "id": 1,
    }
}
```


* **Notes:**

