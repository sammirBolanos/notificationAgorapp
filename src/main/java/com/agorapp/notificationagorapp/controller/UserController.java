package com.agorapp.notificationagorapp.controller;

import com.agorapp.notificationagorapp.dto.LoginRequest;
import com.agorapp.notificationagorapp.dto.UserResponse;
import com.agorapp.notificationagorapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuarios", description = "Endpoints para gestión de usuarios y autenticación básica")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/first")
    @Operation(
        summary = "Obtener el primer usuario registrado",
        description = "Retorna los datos del primer usuario registrado en la base de datos. " +
                     "Útil para pruebas iniciales o para obtener el usuario por defecto."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No hay usuarios registrados"
        )
    })
    public ResponseEntity<UserResponse> getFirstUser() {
        return userService.getFirstUser()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    @Operation(
        summary = "Login de usuario",
        description = "Autentica un usuario con username y password. " +
                     "Este es un login básico sin encriptación - la contraseña se compara directamente. " +
                     "Retorna 401 si las credenciales no coinciden."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login exitoso - Credenciales válidas",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Login fallido - Usuario no existe o contraseña incorrecta"
        )
    })
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest.username(), loginRequest.password())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }

    @GetMapping("/{username}")
    @Operation(
        summary = "Obtener usuario por username",
        description = "Retorna los datos públicos de un usuario específico buscando por su username. " +
                     "Retorna 404 si el usuario no existe."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UserResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado - El username especificado no existe"
        )
    })
    public ResponseEntity<UserResponse> getUserByUsername(
        @Parameter(
            name = "username",
            description = "Nombre de usuario único",
            required = true,
            example = "admin"
        )
        @PathVariable String username
    ) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
