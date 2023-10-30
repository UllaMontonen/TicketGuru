**Transaction**
----

* **URL**

  /api/transactions | /api/transactions/{transaction_id}

* **Method:**
  
  **`GET`** /transactions for all | /transactions/{transaction_id} for single transaction<br />
  **Auth required**: YES<br />
  **Permissions required**: Admin or User<br />
  **`POSt`** /transactions<br />
  **Auth required**: YES<br />
  **Permissions required**: Admin or User<br />
  **`DELETE`** /transactions/{transaction_id}<br />
  **Auth required**: YES<br />
  **Permissions required**: Admin or User<br />
  **`PUT`** /transactions/{transaction_id}<br />
  **Auth required**: YES<br />
  **Permissions required**: Admin or User<br />
  
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

