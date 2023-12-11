# Register API

This API allows you to register a new user in the TicketGuru system.
* [Register a new user](#register)

## <a name="register"></a>Register a new user

Register a new user in the system.

**URL**: `/api/auth/register`

**Method**: `POST`

**Auth required**: NO

**Data constraints**

```json
{
    "username": "newUser",
    "password": "password123"
}
```

## Responses

### 201 Created
If the user is successfully registered, the server will respond with a status code of 201 and a body containing the message "User registered successfully".

```json
{
    "message": "User registered successfully"
}
```

### 400 Bad Request
If the username is already taken, the server will respond with a status code of 400 and a body containing the message "Username is already taken".

```json
{
    "message": "Username is already taken"
}
```

## Implementation
The endpoint is implemented in the `RestAuthController` class in the `register` method. The method checks if the username is already taken. If it is, it responds with a 400 status code. If the username is not taken, it hashes the password using BCrypt, creates a new `User` object, saves it to the `UserRepository`, and responds with a 201 status code.