# Этап 1: Сборка приложения
FROM openjdk:17-jdk-slim as build

# рабочая директория для сборки
WORKDIR /app

# копируем файлы Gradle wrapper в контейнер
COPY gradlew .
COPY gradle gradle

# копируем файлы конфигурации Gradle
COPY build.gradle .
COPY settings.gradle .

# копируем исходный код приложения
COPY src src

# Предварительная загрузка зависимостей для кеширования слоев
RUN ./gradlew dependencies

# Сборка приложения, пропуская тесты
RUN ./gradlew build -x test

# Этап 2: Запуск собранного приложения
FROM openjdk:17-jre-slim

# Копируем собранный jar файл из предыдущего этапа
COPY --from=build /app/build/libs/*.jar app.jar

# Запуск приложения
ENTRYPOINT ["java","-jar","/app.jar"]