package com.example.api.controller;

import com.example.api.dto.UserRequest;
import com.example.api.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    @Operation(summary = "Получить список всех пользователей")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Список пользователей успешно получен")})
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Добавить нового пользователя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Пользователь успешно создан")})
    @PostMapping
    public User createUser(@RequestBody UserRequest user) {
        User newUser = new User(idCounter++, user.getName(), user.getEmail());
        users.add(newUser);
        return newUser;
    }

    @Operation(summary = "Удалить пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удалён"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}