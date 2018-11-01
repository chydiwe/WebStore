# API

Base url for all api requests: ~/api/ .

## Users

* GET /users

    Params:
        ```
            email, 
            password
        ```

    Response body:
    
    ```
        {
            "id": "1",
            "email": "admin@mail.ru",
            "name": "admin",
            "surname": "admin",
            "patronymic": "admin"
        }
    ```
    If such user does not exits then you will get response with status 404 (NOT FOUND).


* PUT /users

    Params:
        ```
            email,
            password,
            name,
            surname,
            patronymic
        ```
        
    If such user already exits then you will get response with status 409 (CONFLICT).

* PUT /users

    Params:
        ```
            token
        ```
        
## Products

* POST /products

    Params:
        ```
            name,
            category,
            subCategory,
            manufacture,
            cost,
            quantity
        ```

    If such user does not exits then you will get response with status 404 (NOT FOUND).

* GET /products

    Params:
        ```
            id
        ```

    Response body:
    
    ```
           {
               "id": "1",
               "name": "Simple pen",
               "category": {
                               "id": "1",
                               "name": "Writing",
                               "subCategory": "Pen"
                           },
               "manufacturer": {
                                   "id": "1",
                                   "name": "H&M",
                                   "logo": "https://google.com/img/221231421",
                                   "info": "Good firm."
                               }
               "shortInfo": "Pen for writing.",
               "cost": "10",
               "quantity": "5000"
            }
    ```
    If such user does not exits then you will get response with status 404 (NOT FOUND).
    
* GET /products

    Params:
        ```
            category (optional),
            subCategory (optional),
            manufacturer (optional)
        ```

    Response body:
    
    ```
        {
            {
                "id": "1",
                "name": "Simple pen",
                "category": {
                                "id": "1",
                                "name": "Writing",
                                "subCategory": "Pen"
                            },
                "manufacturer": {
                                    "id": "1",
                                    "name": "H&M",
                                    "logo": "https://google.com/img/221231421",
                                    "info": "Good firm."
                                }
                "shortInfo": "Pen for writing.",
                "cost": "10",
                "quantity": "5000"
            },
            {
                "id": "2",
                "name": "Good pen",
                "category": {
                                "id": "2",
                                "name": "Writing",
                                "subCategory": "Pen"
                            },
                "manufacturer": {
                                    "id": "1",
                                    "name": "H&M",
                                    "logo": "https://google.com/img/2212313532",
                                    "info": "Good firm."
                                }
                "shortInfo": "Pen for writing.",
                "cost": "20",
                "quantity": "3000"
            },
        }
    ```
    If such user does not exits then you will get response with status 404 (NOT FOUND).
    
## Orders

* POST /orders

    Params:
        ```
            id
        ```
        
* GET /orders

    Params:
        ```
            id
        ```
    Response body:
    
    ```
        {
            {
                "userManager": {
                                   "id": "1",
                                   "email": "admin@mail.ru",
                                   "name": "admin",
                                   "surname": "admin",
                                   "patronymic": "admin",
                                   "phone": "3232323"
                               },
                "orderStatus": {
                                    "name": "IN_PROGRESS"
                               },
                "delivery": {
                                "name": "SELF_DELIVERY"
                            },
                "paiment": {
                                "name": "CASH",
                           }
                "dateOpened": "12.11.2018",
                "dateFinished": "21.11.2018",
                "userComment": "Need fast.",
                "totalCost": "20000"
            },
            {
                "userManager": {
                                   "id": "1",
                                   "email": "admin@mail.ru",
                                   "name": "admin",
                                   "surname": "admin",
                                   "patronymic": "admin",
                                   "phone": "3232323"
                               },
                "orderStatus": {
                                    "name": "IN_PROGRESS"
                               },
                "delivery": {
                                "name": "SELF_DELIVERY"
                            },
                "paiment": {
                                "name": "CASH",
                           }
                "dateOpened": "12.11.2018",
                "dateFinished": "21.11.2018",
                "userComment": "Need fast.",
                "totalCost": "20000"
            }
        }
    ```