# Testing

This document lists the core things for our project that we need to test, and how we are going to do them.

## JUnit Tests

1. **Test findByCode Method in TicketRepository**

    - **Goal**: Ensure that the `findByCode` method in the `TicketRepository` returns the correct ticket when given a valid code.
    - **Expected Result**: A ticket whose code matches the input.

2. **Test POST Method in RestTicketController**

    - **Goal**: Ensure that the `POST` method in the `RestTicketController` correctly saves a ticket.
    - **Expected Result**: A saved ticket in the database with correct input.

3. **Test markTicketUsedByCode Method in RestTicketController**

    - **Goal**: Ensure that the `markTicketUsedByCode` method in the `RestTicketController` returns the correct HTTP status and body when given a valid code.
    - **Expected Result**: Ticket whose code matches the input is `verified=true`.

## Integration Tests

1. **Test /api/events/{id} Endpoint**

    - **Goal**: Ensure that the `/api/events/{id}` endpoint returns the correct HTTP status and body when given a valid ID.
    - **Expected Result**: An event whose ID matches the input, or "404 Not found" if incorrect ID.

2. **Test /api/tickets/check/{ticketcode} Endpoint**

    - **Goal**: Ensure that the `/api/tickets/check/{ticketcode}` endpoint returns the correct HTTP status and body when given a valid code.
    - **Expected Result**: JSON data with the correct Ticket that matches the input code.

3. **Test /api/tickets/markused/{ticketcode} Endpoint**

    - **Goal**: Ensure that the `/api/tickets/markused/{ticketcode}` endpoint returns the correct HTTP status and body when given a valid code.
    - **Expected Result**: JSON data with the correct Ticket that matches the input code and `verified=true`, or "Ticket has already been checked."

## End-to-End Tests

1. **Test Ticket Fetch and Mark Used**

    - **Goal**: Ensure that a user can successfully fetch a ticket's information using the `/api/tickets/check/{ticketcode}` endpoint and then mark it as used using the `/api/tickets/markused/{ticketcode}` endpoint.
    - **Expected Result**: A correct ticket with the matching code, and confirmation of being marked checked or an error that the ticket was already used.

2. **Test Bearer Token Distribution and Usage**

    - **Goal**: Ensure that the bearer token is being distributed correctly when you log in, and that it's being used in every fetch.
    - **Expected Result**: Receiving token when you login and correctly inserting it to every fetch used on the app after that.

3. **Test Ticket Types Matching with Event**

    - **Goal**: Ensure Ticket types are matched correctly with corresponding event when fetched from `/api/events/{id}/tickettypes`.
    - **Expected Result**: Ticket type info that is linked to the event which ID is inserted.