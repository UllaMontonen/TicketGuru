**Ticket**
----

* **URL**

  /api/tickets | /api/tickets/{ticket_id}

* **Method:**
  
  **`GET`** /tickets for all | /tickets/{ticket_id} for single ticket
  **Auth required**: YES
  **Permissions required**: Admin or User or Scanner
  **`POSt`** /tickets
  **Auth required**: YES
  **Permissions required**: Admin or User
  **`DELETE`** /tickets/{ticket_id}
  **Auth required**: YES
  **Permissions required**: Admin or User
  **`PUT`** /tickets/{ticket_id}
  **Auth required**: YES
  **Permissions required**: Admin or User
  
*  **URL Params**

   `ticket_id`: id for ticket entity, acts as primary key in the table

* **Data Params**

  All colums in table are nullable, none are required
    "event": event entity
    "tickettype": tickettype entity
    "transaction": transaction entity
    "verified": boolean value
    "code": String 



* **Success Response:**
  
    **GET**
  * **Code:** 200 <br />
    **Content:** `all tickets in list or ticket with id given as a parameter`

    **POST**
    * **Code:** 201 <br />
    **Content:** `Created ticket`

    **DELETE**
    * **Code:** 200 <br />
    **Content:** `Rest of the remaining tickets in list`

    **PUT**
    * **Code:**  200 <br />
    **Content:** `Modified ticket`
 
* **Error Response:**

  * **Code:** 404 NOT_FOUND <br />
    **Content:** `None`

* **Sample Body:**

```
{
    "event": {
        "id": 2,
        "name": "TestiTapahtuma",
        "place": "Apollo",
        "city": "Helsinki",
        "ticketAmpunt": 100,
        "eventDate": "2023-09-22"
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
            "eventDate": "2023-09-22"
            }
        },
        "transaction": {
            "id": 1,
            "date": "2023-09-22",
            "amount": 56.34,
            "customer": {
                "id": 1,
                "name": "Testi Pesti",
                "email": "email@mail.com"
            }
        },
        "veridied": true,
        "code": "benis"
}
```


* **Notes:**

