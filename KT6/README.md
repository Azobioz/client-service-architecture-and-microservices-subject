# KT 6

## Что можно сделать

Запустить 2 микросервиса, которые взаимодействуют между собой 

user-service: сервис для CRUD операций по пользователям
order-service: сервис для CRUD операций по заказам


## Какие инструменты я использовал при разработке 

- **Java**
- **Gradle**
- **Spring Boot Starter Web**
- **Spring Boot Starter Data JPA**
- **PostgreSQL**
- **Lombok**
- **Docker & Docker Compose**

## Что нужно для запуска
* Скачать Docker Desktop [здесь](https://www.docker.com/products/docker-desktop/)
* Скачать файл docker-compose.yml из репозитория 

### Локальный запуск с помощью Docker Compose

1. В командой строке перейдите на директорию с файлом docker-compose.yml
2. Пропишите в этой директории:
```
docker compose up -d
```

Сервисы будут доступны в:

user-service - localhost:8081

order-service - localhost:8082

## Доступные операции
Для удобного выполнения запросов можно использовать postman

### GET /users - получить всех пользователей

Вывод: 
```
[
    {
        "id": Integer,
        "name": Varchar,
        "email": Varchar
    },
    {
        "id": Integer,
        "name": Varchar,
        "email": Varchar
    },
    {
      ...
    }
]
```

### GET /users/{id} - получить пользователя по id

Вывод:
```
        "id": Intger,
        "name": Varchar,
        "email": Varchar
```

### POST /users - создать пользователя

Входные данные:
```
{
    "name": Varchar,
    "email": Varchar
}
```

Вывод:
```
{
    "id": Intger,
    "name": Varchar,
    "email": Varchar
}
```

### PUT /users/{id} - обновить данные пользователя

Входные данные:
```
{    
    "name": Varchar, # Обязателен для указанаия
    "email": Varchar, # Обязателен для указанаия
}
```

Вывод:
```
{
    "id": Intger,
    "name": Varchar,
    "email": Varchar
}
```

### DELETE /users/{id} - удалить пользователя

Вывод:
```
    200 OK
```

### GET /orders/{id} - получить заказ по id

Вывод: 
```
[
   {
    "user": {
        "id": Integer,
        "name": Varchar,
        "email": Varchar
    },
    "order": {
        "id": Integer,
        "userId": Integer,
        "product": Varchar,
        "quantity": Integer
    }
}
]
```

### POST /orders - создать заказ

Входные данные:
```
{
    "userId": Integer,
    "product": Varchar,
    "quantity": Integer
}
```

Вывод:
```
{
    "id": Intger,
    "userId": Integer,
    "product": Varchar,
    "quantity": Integer
}
```

### PUT /orders/{id} - обновить данные заказа

Входные данные:
```
{    
    "userId": Integer,  # Обязателен для указания
    "product": Varchar,  # Обязателен для указания
    "quantity": Intger # Обязателен для указания
}
```

Вывод:
```
{
    "id": Intger
    "userId": Integer,
    "product": Varchar,
    "quantity": Intger
}
```

### DELETE /orders/{id} - удалить заказ

Вывод:
```
    200 OK
```


## Завершить работу с приложением
1. В командой строке перейдите на директорию с файлом docker-compose.yml
2. Прописать в командной строке: 
```
docker compose down
```
