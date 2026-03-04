package com.example.todo.client;

import com.example.todo.dto.TaskResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;
import java.util.List;
import java.util.Scanner;

public class TodoClient {

    private static final String BASE_URL = "http://todo-server:8080/tasks";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {

        // Цикл для вывода меню и обработки действий пользователя
        while (true) {
            System.out.println("\n1. Показать задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Обновить статус задачи");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Выход");

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> getTasks();
                case 2 -> addTask();
                case 3 -> updateTaskStatus();
                case 4 -> deleteTask();
                case 5 -> System.exit(0);
                default -> System.out.println("Такого выбора нет");
            }
        }
    }

    // Вывод всех задач
    private static void getTasks() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Преобразуем JSON массив в список объектов
        List<TaskResponse> tasks = mapper.readValue(
                response.body(),
                new TypeReference<List<TaskResponse>>() {}
        );

        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }

        //Вывод на консоль
        for (TaskResponse task : tasks) {

            String status = task.completed ? "Выполнен" : "Не выполнен";

            System.out.println("\nID: " + task.id);
            System.out.println("Задача: " + task.title);
            System.out.println("Статус: " + status);
            System.out.println("-------------------------");
        }

    }

    // Добавление новой задачи
    private static void addTask() throws Exception {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();

        String json = String.format("""
                {
                  "title": "%s",
                  "completed": false
                }
                """, title);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        TaskResponse task = mapper.readValue(response.body(), TaskResponse.class);

        System.out.println("Задача " + task.title + " добавлена с ID: " + task.id);
    }

    // Обновление статуса задачи
    private static void updateTaskStatus() throws Exception {
        System.out.print("Введите ID задачи: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        // Получаем задачу по Id
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (getResponse.statusCode() == 404) {
            System.out.println("Задача с таким ID не найдена");
            return;
        }

        TaskResponse task = mapper.readValue(getResponse.body(), TaskResponse.class);

        if (task.completed) {
            System.out.println("Задача \"" + task.title + "\" уже выполнена");
            return;
        }

        // Изменение статус на true
        String json = String.format("""
            {
              "title": "%s",
              "completed": true
            }
            """, task.title);

        HttpRequest putRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

        TaskResponse updatedTask = mapper.readValue(putResponse.body(), TaskResponse.class);

        System.out.println("Задача " + updatedTask.title + " выполнена");

    }

    // Удаление задачи
    private static void deleteTask() throws Exception {
        System.out.print("Введите ID задачи: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            System.out.println("Задача с ID " + id + " не найдена");
        }
        else {
            System.out.println("Задача удалена");
        }


    }
}