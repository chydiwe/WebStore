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
    If such user does not exist then you will get response with status 404 (NOT FOUND).


* POST /users

    Params:
        ```
            email,
            password,
            name,
            surname,
            patronymic
        ```
        
    If such user already exist then you will get response with status 409 (CONFLICT).

* POST /users

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
            manufacturer,
            cost,
            quantity
        ```

    If such category and\or manufacturer does not exist then you will get response with status 404 (NOT FOUND).

* GET /products

    Params:
        ```
            productId
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
               "quantity": "5000",
               "comments": {
                               {
                                 "comment": "Good!",
                                 "date": "20.11.2018"
                               },
                               {
                                 "comment": "Ok.",
                                 "date": "19.11.2018"
                               }                               
                           },
                "images": {
                            {
                              "image": "https://google.com/images/123332"
                            }
                          }  
                         
            }
    ```
    If such product does not exist then you will get response with status 404 (NOT FOUND).
    
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
                "shortInfo": "Pen for writing.",
                "cost": "10",
                "quantity": "5000"
            },
            {
                "id": "2",
                "name": "Good pen",
                "shortInfo": "Pen for writing.",
                "cost": "20",
                "quantity": "3000"
            }
        }
    ```
    If one or all of optional parameters do not exit then you will get response with status 404 (NOT FOUND).
    
## Orders

* POST /orders

    Params:
        ```
            orderId
        ```
        
* GET /orders

    Params:
        ```
            userId
        ```
    Response body:
    
    ```
        {
            {
                "id": "1",
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
                "id": "2",
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
* GET /orders

    Params:
        ```
            orderId
        ```
    Response body:
    
    ```
        {
            "id": "1",
            "userManager": { "id": "1",
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
            "totalCost": "20000",
            "products": {
                              {
                                "product":
                                          {
                                             "id": "1",
                                             "name": "Simple pen",
                                             "shortInfo": "Pen for writing.",
                                             "cost": "10",
                                             "quantity": "5000"
                                           },
                                           "amount": "100"
                              },
                              {
                                "product":                                 
                                           {
                                             "id": "2",
                                             "name": "Good pen",
                                             "shortInfo": "Pen for writing.",
                                             "cost": "20",
                                             "quantity": "3000"
                                            },
                                            "amount": "200"
                              }
                        }
        }
    ```
