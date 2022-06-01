## ПРИМЕРЫ ЗАПРОСОВ

http://www.sql-ex.ru/ <br>
http://www.sql-ex.ru/db_script_download.php - учебные БД <br>
http://www.sql-tutorial.ru/book_exercise_6.html

Задание: 6 Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт, найти скорости таких ПК-блокнотов. Вывод: производитель, скорость.

JOIN

```postgresql
SELECT DISTINCT Product.maker, Laptop.speed
--DISTINCT  -работает только с уникальными значениями столбца
FROM Product INNER JOIN Laptop
ON Product.model = Laptop.model
WHERE Laptop.hd >= 10 AND type = 'laptop'
```

Задание: 7
Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква).

```text
UNION - объединение запросов. 
Приводит к появлению в результирующем наборе всех строк каждого из запросов


Операция объединения может быть выполнена только при выполнении следующих условий:

 - количество выходных столбцов каждого из запросов должно быть одинаковым;
 - выходные столбцы каждого из запросов должны быть совместимы между собой (в порядке их следования) по типам данных;
 - в результирующем наборе используются имена столбцов, заданные в первом запросе;
 - предложение ORDER BY применяется к результату соединения, поэтому оно может быть указано только в конце всего составного запроса.
```
см.: http://www.sql-tutorial.ru/ru/book_union.html


```postgresql
SELECT * FROM
    (SELECT model, price
    FROM PC
    
    UNION
    
    SELECT model, price
    FROM Laptop
    
    UNION
    
    SELECT model, price
    FROM Printer
    ) AS a
         
WHERE a.model IN 
    (SELECT model FROM Product WHERE maker = 'B')
```

Задание: 8
Найдите производителя, выпускающего ПК, но не ПК-блокноты.

```text
EXCEPT - разность.

В результирующий набор попадают только те строки, которые присутствуют в обоих запросах (INTERSECT) или 
только те строки первого запроса, которые отсутствуют во втором (EXCEPT). 

При этом оба запроса, участвующих в операции, должны иметь одинаковое число столбцов, и соответствующие столбцы должны иметь одинаковые (или неявно приводимые) типы данных. 
Имена столбцов результирующего набора формируются из заголовков первого запроса.
```

```postgresql
SELECT maker FROM Product WHERE type='PC'
EXCEPT
SELECT maker FROM Product WHERE type='laptop'
```
см.: <br>
http://www.sql-tutorial.ru/ru/book_intersect_except.html <br>
http://www.sql-tutorial.ru/ru/book_order_of_operators_union_except_intersect.html


Задание: 9
Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker

```text
DISTINCT - используется для удаления дубликатов из результирующего набора оператора SELECT.
```

```postgresql
SELECT DISTINCT Product.maker
FROM Product RIGHT JOIN PC 
    ON Product.model = PC.model
WHERE PC.speed >= 450
```

см.: http://www.sql-tutorial.ru/ru/book_count_distinct_over.html

Задание: 10
Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price
```postgresql
SELECT model, price
FROM printer
WHERE price =
(
SELECT MAX(price) FROM printer
)
```

Задание: 11
Найдите среднюю скорость ПК.
```postgresql
SELECT AVG(speed)
FROM PC
```

см.: http://www.sql-tutorial.ru/ru/book_getting_summarizing_values.html

Задание: 12
Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
```postgresql
SELECT AVG (speed) AS AVG_speed
FROM Laptop
Where price > 1000
```

Задание: 13
Найдите среднюю скорость ПК, выпущенных производителем A.
```postgresql
SELECT AVG(speed) AS avg_speed
FROM Product RIGHT JOIN PC ON Product.model = PC.model
WHERE product.maker = 'A'
```

Задание: 14
Найти производителей, которые выпускают более одной модели, при этом все выпускаемые производителем модели являются продуктами одного типа. Вывести: maker, type
```postgresql
SELECT maker, MAX(type)
FROM product
GROUP BY maker
HAVING COUNT(DISTINCT type) = 1 AND COUNT(model) > 1
```

Задание: 15
Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
```postgresql
SELECT HD
FROM PC
GROUP BY HD
HAVING COUNT(model)>=2
```

Задание: 16
Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз, т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.
```postgresql
SELECT DISTINCT
pc1.model AS model_max,
pc2.model AS model_min,
pc1.speed,
pc1.ram
FROM
pc AS pc1,
pc AS pc2
WHERE
pc1.speed = pc2.speed
and pc1.ram = pc2.ram
and pc1.model > pc2.model
```


Задание: 17
Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК. Вывести: type, model, speed
```postgresql
SELECT DISTINCT p.type, p.model, l.speed
FROM laptop AS l INNER JOIN product AS p ON l.model = p.model
WHERE l.speed < (
SELECT min(speed)
FROM pc
)
```

Задание: 18
Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
```postgresql
SELECT DISTINCT product.maker, printer.price
FROM product, printer
WHERE
Product.model = Printer.model
and Printer.color = 'y'
and Printer.price = (
SELECT MIN(price) FROM printer
WHERE printer.color = 'y'
)
```

Задание: 19
Для каждого производителя, имеющего модели в таблице Laptop, найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана.

>GROUP BY используется для определения групп выходных строк, к которым могут применяться агрегатные функции (COUNT, MIN, MAX, AVG и SUM). <br>
>Если это предложение отсутствует, и используются агрегатные функции, то все столбцы с именами, упомянутыми в SELECT, должны быть включены в агрегатные функции, и эти функции будут применяться ко всему набору строк, которые удовлетворяют предикату запроса. В противном случае все столбцы списка SELECT, не вошедшие в агрегатные функции, должны быть указаны в предложении GROUP BY.

```postgresql
SELECT product.maker, AVG(screen)
FROM product RIGHT JOIN laptop ON product.model = laptop.model
GROUP BY product.maker
```

см.: http://www.sql-tutorial.ru/ru/book_group_by_clause.html

Задание: 20
Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей ПК.
```postgresql
SELECT maker, COUNT(model) AS model
FROM product
WHERE type = 'PC'
GROUP BY maker
HAVING count(model)>=3;
```

Задание: 21
Найдите максимальную цену ПК, выпускаемых каждым производителем, у которого есть модели в таблице PC.
Вывести: maker, максимальная цена.
```postgresql
SELECT product.maker, max(pc.price)
FROM product RIGHT JOIN pc ON product.model = pc.model
GROUP BY maker
```

Задание: 22
Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
```postgresql
SELECT speed, AVG(price)
FROM PC
WHERE speed > 600
GROUP BY speed
```

Задание: 23
Найдите производителей, которые производили бы как ПК
со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц.
Вывести: Maker
```postgresql
SELECT maker
FROM product AS pr INNER JOIN pc ON pr.model = pc.model
WHERE pc.speed >=750
INTERSECT
SELECT pr.maker
FROM product AS pr INNER JOIN laptop AS l ON pr.model = l.model
WHERE l.speed >=750
```

см.: http://www.sql-tutorial.ru/ru/book_intersect_except.html

Задание: 24
Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции
WITH max_price_all_type AS

```postgresql
(SELECT model, price FROM pc WHERE price = (SELECT MAX(price) FROM pc)
UNION
SELECT model, price FROM laptop WHERE price = (SELECT MAX(price) FROM laptop)
UNION
SELECT model, price FROM printer WHERE price = (SELECT MAX(price) FROM printer))
```

```postgresql
SELECT model FROM max_price_all_type WHERE price = (SELECT MAX(price) FROM max_price_all_type)
```

см.: http://www.sql-tutorial.ru/ru/book_common_table_expressions_cte.html

Задание: 25
Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker
Решение 1
```postgresql

SELECT DISTINCT product.maker
FROM Product
WHERE product.model IN --выбираем производителя из нескольких полученных результатов. IN - позволяет это делать.

(
    SELECT product.model
    FROM Product INNER JOIN PC ON Product.model = PC.model -- объединяем таблицы  Product и PC по столбцу MODEL
    WHERE
    PC.ram = (SELECT min(ram) FROM pc) -- условие отбора минимальная память
    and
    PC.speed = (SELECT MAX(speed) FROM (SELECT speed FROM PC WHERE ram = (SELECT MIN(ram) FROM PC) ) AS z4) --условие отбора максимальная скорость процессора ПК у ПК с минимальной памятью
)
  
and
    
product.maker IN (SELECT maker FROM Product WHERE type = 'Printer')
```

Решение 2
```postgresql
SELECT DISTINCT  Product.maker
FROM Product
WHERE
Product.maker IN (SELECT maker FROM Product WHERE type = 'Printer')
and
Product.model IN

(
    SELECT model
    FROM PC
    WHERE RAM = (SELECT MIN(ram) FROM PC)
    and
    SPEED = (
                SELECT MAX(speed) FROM 
                                      (SELECT speed FROM PC WHERE ram = 
                                                                  (SELECT MIN(ram) FROM PC)
                                      ) AS z4
            )
)
```

Задание: 26
Найдите среднюю цену ПК и ПК-блокнотов, выпущенных производителем A (латинская буква). Вывести: одна общая средняя цена.
```postgresql
SELECT sum(s.price)/sum(s.kol)
FROM

(SELECT price, 1 AS kol
FROM Product, PC
WHERE Product.model = PC.model and Product.maker = 'A'

UNION all

SELECT price, 1 AS kol
FROM Product, laptop
WHERE Product.model = laptop.model and Product.maker = 'A') AS s;
```

Задание: 27
Найдите средний размер диска ПК каждого из тех производителей, которые выпускают и принтеры. Вывести: maker, средний размер HD.
```postgresql
SELECT product.maker, AVG(PC.hd) AS avg_hd
FROM Product, PC
WHERE Product.model = Pc.model and maker IN (SELECT maker FROM Product WHERE type = 'printer')
GROUP BY product.maker
```

Задание: 28
Используя таблицу Product, определить количество производителей, выпускающих по одной модели.
```postgresql
SELECT COUNT(maker) FROM
(SELECT maker, count(model) AS count_model
FROM Product
GROUP BY maker) AS itog
WHERE itog.count_model = 1
```

Задание: 29
В предположении, что приход и расход денег на каждом пункте приема фиксируется не чаще одного раза в день [т.е. первичный ключ (пункт, дата)], написать запрос с выходными данными (пункт, дата, приход, расход). Использовать таблицы Income_o и Outcome_o.
```postgresql
SELECT t1.point, t1.date, inc, out
FROM income_o t1 LEFT JOIN outcome_o t2 ON t1.point = t2.point
AND t1.date = t2.date
UNION
SELECT t2.point, t2.date, inc, out
FROM income_o t1 RIGHT JOIN outcome_o t2 ON t1.point = t2.point
AND t1.date = t2.date
```

Задание: 30
В предположении, что приход и расход денег на каждом пункте приема фиксируется произвольное число раз (первичным ключом в таблицах является столбец code), требуется получить таблицу, в которой каждому пункту за каждую дату выполнения операций будет соответствовать одна строка.
Вывод: point, date, суммарный расход пункта за день (out), суммарный приход пункта за день (inc). Отсутствующие значения считать неопределенными (NULL).

> ORDER BY 

```postgresql
SELECT point, date, sum(sum_out), sum(sum_inc)
FROM
(
SELECT point, date, sum(inc) as sum_inc, null as sum_out
FROM Income
GROUP BY point, date

UNION

SELECT point, date, null as sum_inc, sum(out) as sum_out
FROM Outcome
GROUP BY point, date
) as t
GROUP BY point, date
ORDER BY point
```

см.: <br>
http://www.sql-tutorial.ru/ru/book_case_order_by_clause.html <br>
http://old.code.mu/sql/order-by.html

Задание: 31
Для классов кораблей, калибр орудий которых не менее 16 дюймов, укажите класс и страну.
```postgresql
SELECT class, country
FROM classes
Where Bore >= 16
```

Задание: 32
Одной из характеристик корабля является половина куба калибра его главных орудий (mw). С точностью до 2 десятичных знаков определите среднее значение mw для кораблей каждой страны, у которой есть корабли в базе данных.


Задание: 33
Укажите корабли, потопленные в сражениях в Северной Атлантике (North Atlantic). Вывод: ship.
```postgresql
SELECT ship
FROM Outcomes
Where Battle = 'North Atlantic' and result = 'sunk'
```

Задание: 34
По Вашингтонскому международному договору от начала 1922 г. запрещалось строить линейные корабли водоизмещением более 35 тыс.тонн. Укажите корабли, нарушившие этот договор (учитывать только корабли c известным годом спуска на воду). Вывести названия кораблей.
```postgresql
SELECT ships.name
FROM Classes, Ships
WHERE Classes.class = Ships.class and Classes.Displacement > 35000 and Classes.Type = 'bb' and Ships.launched >= 1922
```

Задание: 35
В таблице Product найти модели, которые состоят только из цифр или только из латинских букв (A-Z, без учета регистра).
Вывод: номер модели, тип модели.
```postgresql
SELECT model, type
FROM product
WHERE upper(model) NOT like '%[^A-Z]%'
OR model not like '%[^0-9]%'
```

см.: http://www.sql-tutorial.ru/ru/book_lower_upper_soundex_difference_functions.html

Задание: 36
Перечислите названия головных кораблей, имеющихся в базе данных (учесть корабли в Outcomes).
```postgresql
Select name from ships
where class = name
UNION
select ship as name from classes, outcomes where classes.class = outcomes.ship
```

Задание: 37
Найдите классы, в которые входит только один корабль из базы данных (учесть также корабли в Outcomes).
```postgresql
SELECT classes.class
FROM classes LEFT JOIN
(
SELECT class, name FROM ships
UNION
SELECT ship, ship FROM outcomes
) AS s ON s.class = classes.class
GROUP BY classes.class
HAVING COUNT(s.name) = 1
```

см.: http://www.sql-tutorial.ru/ru/book_having_clause.html

Задание: 38
Найдите страны, имевшие когда-либо классы обычных боевых кораблей ('bb') и имевшие когда-либо классы крейсеров ('bc').
```postgresql
Select Country From Classes Where Type = 'bb'
INTERSECT
Select Country From Classes Where Type = 'bc'
```

Задание: 39
Найдите корабли, `сохранившиеся для будущих сражений`; т.е. выведенные из строя в одной битве (damaged), они участвовали в другой, произошедшей позже.
```postgresql
select distinct ccc.sh
from

(select aaa.ship as sh, aaa.[date] as d1, bbb.[date] as d2
from  
(select ship, [date]
from outcomes as o inner join battles as b on o.battle=b.name where result = 'damaged')
as aaa

inner join

(select ship, [date]
from outcomes as o inner join battles as b on o.battle=b.name)  
as bbb

    ON  aaa.ship=bbb.ship 
    where bbb.date > aaa.date
) as ccc
```

Задание: 40
Найдите класс, имя и страну для кораблей из таблицы Ships, имеющих не менее 10 орудий.
```postgresql
SELECT classes.class, name, country
FROM classes inner JOIN ships ON classes.class = Ships.class
where numGuns >= 10
```

Задание: 41
Укажите сражения, которые произошли в годы, не совпадающие ни с одним из годов спуска кораблей на воду.



Задание: 42
Найдите названия кораблей, потопленных в сражениях, и название сражения, в котором они были потоплены.
```postgresql
SELECT Ship, battle
From Outcomes
Where Result = 'sunk'
```

Задание: 43

Задание: 44
Найдите названия всех кораблей в базе данных, начинающихся с буквы R.
```postgresql
SELECT name FROM Ships
Where name LIKE 'R%'
union
SELECT ship as name FROM Outcomes
Where ship LIKE 'R%'
```

Задание: 45
Найдите названия всех кораблей в базе данных, состоящие из трех и более слов (например, King George V).
Считать, что слова в названиях разделяются единичными пробелами, и нет концевых пробелов.
```postgresql
SELECT name FROM Ships
Where name LIKE '% % %'
union
SELECT ship as name FROM Outcomes
Where ship LIKE '% % %'
```

Задание: 46
Задание: 47
Задание: 48

Задание: 49
Найдите названия кораблей с орудиями калибра 16 дюймов (учесть корабли из таблицы Outcomes).
```postgresql
select name
FROM Classes inner JOIN ships ON classes.class = Ships.class
where Bore = 16
union
select ship as name
from Outcomes inner join Classes ON Outcomes.ship = Classes.class
where Bore = 16
```

Задание: 50
Найдите сражения, в которых участвовали корабли класса Kongo из таблицы Ships.
```postgresql
select distinct battle
from Outcomes inner join Ships ON Outcomes.ship = Ships.name
where class = 'Kongo'
```


