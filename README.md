# Веб-сервис "Путешествие дня"
****************************
********************************
Сервис предлагает купить тур по определённой цене, одним из 2 способов:
+ Оплата по дебетовой карте
+ Оплата по кредитной карте

![Cервис](pictures/service.png)

Само приложение не обрабатывает данные по картам, а отправляет их банковским сервисам:
+ сервису платежей, далее **Payment Gate**;
+ кредитному сервису, далее **Credit Gate**;
  
Приложение в собственной СУБД сохраняет информацию о том, успешно ли был совершён платёж и каким способом.
*******************************
## Начало работы
GitHUB - склонировать данный проект
Для запуска тестов потребуется следующее ПО:
+ IntelliJ IDEA
+ Git
+ Docker Desktop
+ Google Chrome
**************************************
### Установка и запуск
1. Запускаем контейнеры с помощью команды в терминале

```docker-compose up```

2. Запускаем SUT. 
+ для MySQL: 

  + В консоле ввести команду: ```java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar``` 
    
+ для PostgreSQL: 

  + В консоле ввести команду: 
  ```java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar```


3. Запускаем авто-тесты
+ для MySQL:
  + В классе `src/test/java/data/SQLHelper.java` вставить значение `url` в функции `getConnection` `jdbc:mysql://localhost:3306/app`
  +  В консоле ввести команду: ```./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"```
+ для PostgreSQL:
    + В классе `src/test/java/data/SQLHelper.java` вставить значение `url` в функции `getConnection` `jdbc:postgresql://localhost:5432/app`
    + В консоле ввести команду: ```./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"```
5. Генерируем отчёт по итогам тестирования с помощью Allure командой:

   ```./gradlew allureServe```

Отчет автоматически откроется в браузере.

После работы с отчетом, останавливаем работу **АllureServe** в терминале при помощи сочетания клавиш Ctrl + C и подтверждаем в терминале клавишей Y

## Документация
[План автоматизации тестирования](docs/Plan.md)


   