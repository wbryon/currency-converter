# currency-converter

# ТЗ на приложение:
а) Приложение использует любое стороннее бесплатное апи по курсам валют.

б) Приложение имеет два эндпойнта:
  - получить список валют относительно заданной валюты (минимум должно работать для USD, RUR).
  - конвертировать Х единиц валюты в другую валюту (пример: 100 USD = 9100 RUR).

в) В приложении должны быть написаны тесты с использованием Junit 5, Mockito, Test Containers PostgreSQL, Flyway migration.

г) В приложении должен быть использован механизм Spring Boot Validation с применением кастомного валидатора для введённых наименований валют (USD, RUR etc).

д) В приложении должен быть минимум один любой @Aspect для любой функции (например логгирование).

е) Сборщик gradle.
