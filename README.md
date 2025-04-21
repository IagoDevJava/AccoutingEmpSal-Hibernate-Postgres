# Accounting Employees & Salaries (AES)

#### *"No one is forgotten!"*

## Это репозиторий проекта "AES"

#### Архитектура монолитная. Подключена тестовая БД.

Pet-project № 1

#### Он позволяет работать с  базой данный в компании, включающей в себя сотрудников, данные их карточек трудоустройства, сведения о графике работы, количестве отработанных дней. Позволяет делать расчеты заработной платы, бонусов, оставшихся дней отпуска и их оплаты.

Поставленные задачи:

- [X] Создать БД сотрудников, департаментов, грейдов
- [X] Создать эндпойнты, согласно REST
- [X] Создать функционал DAL
- [X] Создать openapi
- [ ] Создать калькулятор зарплат
- [ ] Рефакторинг кода - 2024
- [ ] Создать калькулятор отпусков
- [ ] Проверить мапперы, согласно шаблону ниже
- [ ] Написать Unit и Moсkito тесты
- [ ] Написать Postman тесты

Мапперы: https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application

Приложение написано на Java при помощи Spring и сопустствующих библиотек. Пример кода:

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankEmployeesSalariesApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankEmployeesSalariesApplication.class, args);
  }

}
```

### ER-диаграмма базы данных для приложения:

![This is ER-diagramme](temp-diagramm.png)

### Описание диаграммы. Основные сущности:

#### departments 
- таблица отделов компании:
Содержит информацию об отделах (название, адрес, контакты)
Имеет ссылку на руководителя отдела (сотрудника)

#### grades 
- таблица грейдов сотрудников:
Хранит наименования грейдов (Junior, Middle, Senior)

#### positions 
- таблица должностей:
Содержит наименования должностей
Связана с отделами и грейдами

#### work_schedules 
- таблица графиков работы:
Хранит информацию о рабочих днях, выходных и рабочих часах

#### employees 
- таблица сотрудников:
Содержит персональные данные сотрудников
Связана с отделами, должностями и графиками работы
Хранит информацию о датах приема/увольнения и статусе

#### attendance_data 
- таблица посещаемости:
Фиксирует статусы присутствия сотрудников (работа, отпуск, больничный и т.д.)

#### kpis
- таблица показателей эффективности:
Хранит персональные, командные и общие KPI сотрудников
Привязана к конкретному месяцу и году

#### salaries_data
- таблица базовых данных о зарплатах:
Содержит информацию об окладах и бонусах по должностям

#### salaries
- таблица начисленных зарплат:
Фиксирует фактические начисления зарплат сотрудникам за периоды

#### holidays
- таблица праздничных дней:
Содержит даты праздников и их описания
Позволяет отмечать ежегодные праздники

#### salary_calculation_log 
- таблица истории расчетов зарплат:
Детально хранит информацию о каждом расчете зарплаты
Содержит все коэффициенты и параметры расчета
Фиксирует статус утверждения расчетов

<!-- Получаем наименование жанров по названиям фильмов:
```
SELECT f.name,
	   g.name
FROM film AS f
JOIN genre AS g ON g.genre_id=f.genre_id 
```
Получаем названия фильмов с рейтингом:
```
SELECT f.name,
	   r.name
FROM film AS f
JOIN rate AS r ON r.rate_id=f.rate_id
```
Получаем количество лайков у фильмов:
```
SELECT f.name,
	   COUNT(l.user_id)
FROM film AS f
JOIN like AS l ON l.film_id=f.film_id
GROUP BY f.name
```
Получаем фильмы, которые нравятся Васе:
```
SELECT u.name,
	   f.name
FROM film AS f
JOIN like AS l ON l.film_id=f.film_id 
JOIN user AS u ON l.user_id=u.user_id
WHERE u.name = 'Vasya'
GROUP BY user_name
```
Получаем пользователей, которым нравится фильм Дюна:
```
SELECT u.name
FROM user AS u
JOIN like AS l ON l.user_id=f.user_id 
JOIN film AS f ON f.film_id=l.film_id
WHERE f.name = 'Dune'
```
Получаем друзей Васи:
```
SELECT f.name
FROM user AS u
JOIN friendly_status AS fs u.user_id=fs.user_id
JOIN friend AS f ON fs.user_id=f.friend_id
WHERE user_name='Vasya'
``` -->
