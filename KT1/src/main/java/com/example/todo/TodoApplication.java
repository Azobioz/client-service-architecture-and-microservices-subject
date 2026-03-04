package com.example.todo;

import com.example.todo.client.TodoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) throws Exception {
        if (args.length > 0 && args[0].equalsIgnoreCase("client")) {
            // Запуск консольного клиента
            TodoClient.main(args);
        }
        else {
            // Запуск сервера
            SpringApplication.run(TodoApplication.class, args);
        }
    }
}
