Given running server

!-- Check registration
When POST /user? email=admin@mail.ru & password=admin & name=admin & surname=admin & patronymic=admin
Then user with email admin@mail.ru has token