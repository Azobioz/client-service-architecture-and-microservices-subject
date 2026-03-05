# KT 4

## Что можно сделать

Запустить API и делать CRUD-операции над сущностью users
  
## Какие инструменты я использовал при разработке 

- **Java**
- **Gradle**
- **Spring Boot Starter Web**
- **Spring Boot Starter Data JPA**
- **H2 Database**
- **Lombok**
- **Flyway**
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
API запуститься и будет доступен в localhost:8080



## Доступные операции
Для удобного выполнения запросов можно использовать postman

### GET /users - получить всех пользователей

Вывод: 
```
[
    {
        "id": Integer,
        "name": Varchar,
        "email": Varchar,
        "age": Integer
    },
    {
        "id": Integer,
        "name": Varchar,
        "email": Varchar,
        "age": Integer
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
        "email": Varchar, 
        "age": Integer
```


### POST /users - создать пользователя

Входные данные:
```
{
    "name": Varchar,
    "email": Varchar, # Должно быть уникальным
    "age": Integer
}
```

Вывод:
```
{
    "id": Intger,
    "name": Varchar,
    "email": Varchar, # Должно быть уникальным
    "age": Integer
}
```

### PUT /users/{id} - обновить данные пользователя

Входные данные:
```
{    
    "name": Varchar, # Обязателен для указанаия
    "email": Varchar, # Обязателен для указанаия
    "age": Integer # Обязателен для указанаия
}
```

Вывод:
```
{
    "id": Intger,
    "name": Varchar,
    "email": Varchar,
    "age": Integer
}
```

### DELETE /users/{id} - удалить пользователя 

Вывод:
```
204
No Content
```



## Завершить работу с приложением
1. В командой строке перейдите на директорию с файлом docker-compose.yml
2. Прописать в командной строке: 
```
docker compose down
```
