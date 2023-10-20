# Login

Currently all endpoints require a valid Token to be included in the request. This API is used to collect a Token for a registered User.

**URL**: `/api/auth/login`

**Method**: `POST`

**Auth required**: NO

### Data constraints

```json
{
    "user": "[user in plain text]",
    "password": "[password in plain text]"
}
```

**Data example**

```json
{
    "username": "abc",
    "password": "abcd1234"
}
```

### Success Response

**Code**: `200 OK`


### Error Response
**Condition**: `If 'username' and 'password' combination is wrong.`

**Code**: `400 BAD REQUEST`
