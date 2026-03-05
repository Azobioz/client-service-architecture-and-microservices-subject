# KT 3

## Что можно сделать

Тестировать API через Swagger UI 

## Какие инструменты я использовал при разработке 

- **Java**
- **Gradle**
- **Spring Boot Starter Web**
- **Springdoc Openapi Starter Webmvc Ui**
- **Lombok**
- **Docker & Docker Compose**

## Что нужно для запуска
* Скачать Docker Desktop [здесь](https://www.docker.com/products/docker-desktop/)
* Скачать файл docker-compose.yml из репозитория 

### Локальный запуск с помощью Docker Compose

1. В командой строке перейдите на директорию с файлом docker-compose.yml
2. Пропишите в этой директории:
```
docker compose up
```

Перейдите в браузере URL http://localhost:8080/swagger-ui/index.html

Откроется пользовательский интерфейс, где можно протестировать API

## Завершить работу с API
1. В командой строке перейдите на директорию с файлом docker-compose.yml
2. Прописать в командной строке: 
```
docker compose down
```
