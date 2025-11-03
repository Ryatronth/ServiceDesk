package ru.ryatronth.service.desk.module.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryatronth.service.desk.dto.persona.UserDto;

@Tag(name = "Синхронизация пользователей",
     description = "API для синхронизации данных пользователя из Keycloak с локальной базой")
@RequestMapping("/api/users")
public interface UserSyncApi {

    @Operation(summary = "Синхронизировать пользователя", description = """
                                                                        Синхронизирует данные пользователя из Keycloak с локальной БД.
                                                                        Данные берутся из JWT токена, переданного в Authorization: Bearer <token>.
                                                                        Если пользователь не найден — создаётся, иначе обновляется.
                                                                        Возвращает актуальную запись пользователя.
                                                                        """, responses = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно синхронизирован",
                     content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизован — отсутствует или неверный JWT токен",
                     content = @Content),
        @ApiResponse(responseCode = "500", description = "Ошибка на стороне сервера", content = @Content)})
    @PostMapping("/sync")
    ResponseEntity<UserDto> syncUser(@Parameter(hidden = true, description = "JWT токен текущего пользователя") Jwt jwt);

}
