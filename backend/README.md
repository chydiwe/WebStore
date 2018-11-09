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
            categoryId,
            manufacturerId,
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


## Manufacturers

* GET /products/manufacturers

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "H&M",
            "logo": "https://google.com/images/123332",
            "info": "Good company"
        }, 
        {
            "id": "2",
            "name": "K&L",
            "logo": "https://google.com/images/123332",
            "info": "Good company"
        }
    }
    ```
    
     If such manufactury does not exist then you will get response with status 404 (NOT FOUND).

* POST /products/manufacturers

    Params:
        ```
            name, logo, info
        ```
    
     If such manufactury does not exist then you will get response with status 32 (CONFLICT).
 
* DELETE /products/manufacturers

    Params:
        ```
            id
        ```
    
     If such manufactury does not exist then you will get response with status 404 (NOT FOUND).

## Deliveries

* GET /orders/deliveries

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "By own"
        }, 
        {
            "id": "2",
            "name": "With delivery"
        }
    }
    ```
    
     If such delivry does not exist then you will get response with status 404 (NOT FOUND).

* POST /orders/deliveries

    Params:
        ```
            name
        ```
    
     If such delivery does not exist then you will get response with status 32 (CONFLICT).
 
* DELETE /orders/deliveries

    Params:
        ```
            id
        ```
    
     If such delivery does not exist then you will get response with status 404 (NOT FOUND).

## Categories

* GET /products/categories

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "Office stuff",
            "subCategories": {
                                {
                                    "categoryId": "1",
                                    "name": "Pen"
                                },
                                {
                                    "categoryId": "1",
                                    "name": "Pencil"
                                }                              
                             }
        }, 
        {
            "id": "2",
            "name": "School stuff",
            "subCategories": {
                                {
                                    "categoryId": "2",
                                    "name": "Pen"
                                },
                                {
                                    "categoryId": "2",
                                    "name": "Pencil"
                                }                              
                             }
        }
    }
    ```
    
     If such delivry does not exist then you will get response with status 404 (NOT FOUND).

* POST /products/categories

    Params:
        ```
            name
        ```
    
     If such delivery does not exist then you will get response with status 32 (CONFLICT).
 
* DELETE /products/categories

    Params:
        ```
            id
        ```
    
     If such delivery does not exist then you will get response with status 404 (NOT FOUND).

## Payments

* GET /orders/payments

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "Credit card"
        }, 
        {
            "id": "2",
            "name": "Cash"
        }
    }
    ```
    
     If such payment does not exist then you will get response with status 404 (NOT FOUND).

* POST /orders/payments

    Params:
        ```
            name
        ```
    
     If such payment exists then you will get response with status 32 (CONFLICT).
 
* DELETE /orders/payments

    Params:
        ```
            id
        ```
    
     If such payment does not exist then you will get response with status 404 (NOT FOUND).
     
## Order Status

* GET /orders/statuses

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "Ready"
        }, 
        {
            "id": "2",
            "name": "Cancelled"
        }
    }
    ```
    
     If such order status does not exist then you will get response with status 404 (NOT FOUND).

* POST /orders/statuses

    Params:
        ```
            name
        ```
    
     If such order status exists then you will get response with status 32 (CONFLICT).
 
* DELETE /orders/statuses

    Params:
        ```
            id
        ```
    
     If such orded status does not exist then you will get response with status 404 (NOT FOUND).
     
## Payment Status

* GET /orders/payments/statuses

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "Done"
        }, 
        {
            "id": "2",
            "name": "Waiting"
        }
    }
    ```
    
     If such payment status does not exist then you will get response with status 404 (NOT FOUND).

* POST /orders/payments/statuses

    Params:
        ```
            name
        ```
    
     If such payment status exists then you will get response with status 32 (CONFLICT).
 
* DELETE /orders/payments/statuses

    Params:
        ```
            id
        ```
    
     If such payment status does not exist then you will get response with status 404 (NOT FOUND).
     
## Groups

* GET /users/groups

    Response body:
    
    ```
    {
        {
            "id": "1",
            "name": "Admin"
        }, 
        {
            "id": "2",
            "name": "Manager"
        }
    }
    ```
    
     If such group does not exist then you will get response with status 404 (NOT FOUND).

* POST /users/groups

    Params:
        ```
            name
        ```
    
     If such group exists then you will get response with status 32 (CONFLICT).
 
* DELETE /users/groups

    Params:
        ```
            id
        ```
    
     If such group does not exist then you will get response with status 404 (NOT FOUND).
