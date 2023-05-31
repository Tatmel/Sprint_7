<h1 align="center">Тестирование API учебного сервиса Яндекс.Самокат</h1>

  <p align="center" id="project-name">
    Яндекс.Практикум: Финальный проект 7 спринта
    <br />
    <a href="https://github.com/Tatmel/Sprint_7"><strong>git *</strong></a>
    <a href="https://qa-scooter.praktikum-services.ru/docs/"><strong>docs API *</strong></a>
    <a href="http://qa-scooter.praktikum-services.ru/"><strong>site</strong></a>
    <br />

  </p>



<!-- Содержание -->
<details>
  <summary><b>Содержание</b></summary>
  <ol>
    <li><a href="#tasks">Задачи проекта</a></li>
    <li><a href="#technologies">Использованные технологии</a></li>
    <li><a href="#start-tests">Генерирование отчета</a></li>
  </ol>
</details>



<!-- Задачи проекта -->
<h2 id="tasks">Задачи проекта</h2>

_**Общее описание:**_ 
<p>Тестирование API учебного сервиса Яндекс.Самокат.</p>

_**Протестировать ручки:**_
1. Создание курьера
<p style="margin: 5px 30px;">a) курьера можно создать;</p>
<p style="margin: 5px 30px;">б) нельзя создать двух одинаковых курьеров;</p>
<p style="margin: 5px 30px;">в) чтобы создать курьера, нужно передать в ручку все обязательные поля;</p>
<p style="margin: 5px 30px">г) запрос возвращает правильный код ответа;</p>
<p style="margin: 5px 30px">д) успешный запрос возвращает ok: true;</p>
<p style="margin: 5px 30px">е) если одного из полей нет, запрос возвращает ошибку;</p>
<p style="margin: 5px 30px">ж) если создать пользователя с логином, который уже есть, возвращается ошибка.</p>

2. Логин курьера
<p style="margin: 5px 30px;">a) курьер может авторизоваться;</p>
<p style="margin: 5px 30px;">б) для авторизации нужно передать все обязательные поля;</p>
<p style="margin: 5px 30px;">в) система вернёт ошибку, если неправильно указать логин или пароль;</p>
<p style="margin: 5px 30px">г) если какого-то поля нет, запрос возвращает ошибку;</p>
<p style="margin: 5px 30px">д) если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;</p>
<p style="margin: 5px 30px">е) успешный запрос возвращает id.</p>

3. Создание заказа
<p style="margin: 5px 30px;">a) можно указать один из цветов — BLACK или GREY;</p>
<p style="margin: 5px 30px;">б) можно указать оба цвета;</p>
<p style="margin: 5px 30px;">в) можно совсем не указывать цвет;</p>
<p style="margin: 5px 30px">г) тело ответа содержит track.</p>

4. Список заказов
<p style="margin: 5px 30px;">a) тело ответа возвращается список заказов.</p>

5. Отчет Allure

<!-- Использованные технологии -->
<h2 id="technologies">Использованные технологии</h2>

В проекте были использованы следующие фреймворки/библиотеки:
* Java 11, 
* JUnit 4.13.2, 
* RestAssured 5.3.0,
* Allure-junit4 2.22.0,
* GSON 2.10.1

<p align="right">(<a href="#project-name">back to top</a>)</p>



<!-- Генерирование отчета -->
<h2 id="start-tests">Генерирование отчета</h2>

* mvn
  ```sh
  mvn clean test
  ```
* mvn
  ```sh
  mvn allure:serve
  ```

<p align="right">(<a href="#project-name">back to top</a>)</p>
