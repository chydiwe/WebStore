# API

Base url for all api requests: ~/api/ .

## Users

#### Authentication

GET /users?email=admin@mail.ru

```
    {
        "email": "admin@mail.ru",
        "name": "admin",
        "surname": "admin",
        "patronymic": "admin"
    }
```
If such user does not exits then you will get response with status 404 (NOT FOUND).