Given running server

!-- [START] Test user controller.
When POST /users?email=admin@mail.ru&password=admin&name=admin&surname=admin&patronymic=admin return status code 200
When GET /users?id=1 then json has values id=1;email=admin@mail.ru;name=admin;surname=admin;patronymic=admin
When POST /users?email=admin@mail.ru&password=admin&name=admin&surname=admin&patronymic=admin return status code 409
!-- [END] Test user controller.

!-- [START] Test category controller.
When POST /products/categories?name=School return status code 200
When POST /products/categories?name=Office return status code 200
When GET /products/categories then json has values id=1;name=School###id=2;name=Office
When DELETE /products/categories?id=2 return status code 200
When GET /products/categories then json hasn't values id=2;name=Office
!-- [END] Test category controller.

!-- [START] Test manufacturer controller.
When POST /products/manufacturers?name=Hans&logo=image1&info=Good_store return status code 200
When POST /products/manufacturers?name=Lars&logo=image2&info=Good_store return status code 200
When GET /products/manufacturers then json has values id=1;name=Hans;logo=image2;info=Good_store###id=2;name=Lars
When DELETE /products/manufacturers?id=2 return status code 200
When GET /products/manufacturers then json hasn't values id=2;name=Lars
!-- [END] Test manufacturer controller.

!-- [START] Test product controller.
When POST /products?name=Pen&categoryId=1&manufacturerId=1&cost=100&quantity=100 return status code 200
When POST /products?name=Pencil&categoryId=1&manufacturerId=1&cost=50&quantity=300 return status code 200
When GET /products?id=1 then json has values id=1;name=Pen;cost=100;quantity=100
When GET /products?id=2 then json has values id=2;name=Pencil;cost=50;quantity=300
When GET /products?categoryId=1 then json has values id=1;name=Pen;cost=100;quantity=100###id=2;name=Pencil;cost=50;quantity=300
When GET /products?manufacturerId=1 then json has values id=1;name=Pen;cost=100;quantity=100###id=2;name=Pencil;cost=50;quantity=300
!-- [END] Test product controller.

!-- [START] Test bucket controller.
When POST /users/bucket?userId=1&productId=1&amount=10 return status code 200
When GET /users/bucket?userId=1 then json has values amount=10
!-- [END] Test bucket controller.