# API

Base url for all api requests: ~/api/ .

## Users

### Authentication

* GET /users

    Params:
        ```
            email, 
            password
        ```

    Response body:
    
    ```
        {
            "email": "admin@mail.ru",
            "name": "admin",
            "surname": "admin",
            "patronymic": "admin"
        }
    ```
    If such user does not exits then you will get response with status 404 (NOT FOUND).


* PUT /users

    Request body:
    
    ```
        {
            "email": "admin@mail.ru",
            "password": "qwerty",
            "name": "admin",
            "surname": "admin",
            "patronymic": "admin"
        }
    ```
    If such user already exits then you will get response with status 409 (CONFLICT).
