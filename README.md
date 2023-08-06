# Тестовое задание - Автоматизация учета склада носков

## Описание
Приложение для учета носков на складе.

## Техническое задание
[Ссылка](https://github.com/11th/socks-store/wiki/%D0%A2%D0%B5%D1%85%D0%BD%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B5-%D0%B7%D0%B0%D0%B4%D0%B0%D0%BD%D0%B8%D0%B5)

## Стек
- Java 17
- Spring Boot
- PostgreSQL

## Тест приложения
- Поступление носков
http://81.163.28.234:8080/api/socks/income
- Отпуск носков
http://81.163.28.234:8080/api/socks/outcome
- Запрос запасов носков
http://81.163.28.234:8080/api/socks?color=green&operation=moreThan&cottonPart=70
