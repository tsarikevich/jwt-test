# jwt-test




Описание:
---
Перед запуском приложения необходимо запустить файл data.sql для создания и заполнения таблиц БД.
Для запуска программы через Intellij Idea в настройках (application.properties) указать: _spring.datasource.url=jdbc:mysql://localhost:3306/jwt_

Приложение содержит два HTTP POST endpoint (контроллер UserController):

http://localhost:9150/users/authenticate - проверяет пароль по БД, создает и отправляет в ответ jwt токен:
![](https://github.com/tsarikevich/jwt-test/blob/master/src/main/resources/static/Token.jpg)

http://localhost:9150/users/message - принимает на вход "имя пользователя" и "сообщение", проверяет токен, в случае успешной проверки токена, полученное сообщение сохраняется в БД:
![](https://github.com/tsarikevich/jwt-test/blob/master/src/main/resources/static/newMessage.jpg)

При вводе сообщения вида "history 3" происходит проверка токена, в случае успешной проверки токена отправляется 3 последних сообщения пользователя из БД:
![](https://github.com/tsarikevich/jwt-test/blob/master/src/main/resources/static/historyMessage.jpg)
![](https://github.com/tsarikevich/jwt-test/blob/master/src/main/resources/static/jwtToken.jpg)

Технологии:
---
* Spring Boot
* Spring Data JPA
* MySQL
* Docker

Требования:
---

* JDK 17 или новее
* Lombok
* Apache Commons Lang
* Maven
