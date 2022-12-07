# jwt-test




Описание:
---
Перед запуском приложения необходимо запустить файл data.sql для создания и заполнения таблиц базы данных.
Приложение содержит два HTTP POST endpoint контроллера UserController:

'http://localhost:9150/users/authenticate'- проверяет пароль по БД, создает и отправляет в ответ jwt токен:
![](https://github.com/tsarikevich/jwt-test/blob/master/src/main/resources/static/Token.jpg)
