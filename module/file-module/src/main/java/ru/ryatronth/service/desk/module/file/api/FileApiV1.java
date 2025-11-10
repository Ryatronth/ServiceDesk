package ru.ryatronth.service.desk.module.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryatronth.service.desk.dto.file.FileDto;

import java.util.UUID;

@Tag(name = "File Management API", description = "API для отложенной загрузки файлов в MinIO/S3 через pre-signed URL. " + "Позволяет создавать запись файла, загружать напрямую в S3, подтверждать и управлять файлами.")
@RequestMapping("/api/v1/files")
public interface FileApiV1 {

    @Operation(summary = "Инициализация загрузки файла", description = """
            Создаёт запись о файле в БД со статусом `PENDING_UPLOAD` и
            возвращает pre-signed PUT URL для загрузки файла напрямую в S3.
            После успешной загрузки фронтенд должен вызвать `/confirm`.
            """, parameters = {@Parameter(name = "originalName", description = "Исходное имя файла", required = true, example = "document.pdf"), @Parameter(name = "contentType", description = "MIME-тип файла", required = true, example = "application/pdf"), @Parameter(name = "sizeBytes", description = "Размер файла в байтах", required = true, example = "1024000")}, responses = {@ApiResponse(responseCode = "201", description = "Файл успешно инициализирован", content = @Content(schema = @Schema(implementation = FileDto.class))), @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса", content = @Content), @ApiResponse(responseCode = "500", description = "Ошибка при инициализации загрузки", content = @Content)})
    @PostMapping("/init")
    ResponseEntity<FileDto> initUpload(@RequestParam String originalName, @RequestParam String contentType, @RequestParam Long sizeBytes);


    @Operation(summary = "Подтверждение успешной загрузки файла", description = """
            Проверяет наличие объекта в S3 и переводит файл из статуса
            `PENDING_UPLOAD` в `READY`. Возвращает актуальный GET URL.
            """, parameters = {@Parameter(name = "fileId", description = "UUID файла", required = true, example = "123e4567-e89b-12d3-a456-426614174000")}, responses = {@ApiResponse(responseCode = "200", description = "Файл успешно подтверждён", content = @Content(schema = @Schema(implementation = FileDto.class))), @ApiResponse(responseCode = "400", description = "Файл отсутствует в S3", content = @Content), @ApiResponse(responseCode = "404", description = "Файл не найден", content = @Content), @ApiResponse(responseCode = "500", description = "Ошибка при подтверждении загрузки", content = @Content)})
    @PostMapping("/{fileId}/confirm")
    ResponseEntity<FileDto> confirmUpload(@PathVariable UUID fileId);


    @Operation(summary = "Получение метаданных файла", description = """
            Возвращает метаданные файла (имя, размер, тип, статус, bucket и пр.)
            и актуальный pre-signed GET URL, если файл в статусе `READY`.
            """, parameters = {@Parameter(name = "fileId", description = "UUID файла", required = true)}, responses = {@ApiResponse(responseCode = "200", description = "Файл найден", content = @Content(schema = @Schema(implementation = FileDto.class))), @ApiResponse(responseCode = "404", description = "Файл не найден", content = @Content), @ApiResponse(responseCode = "409", description = "Файл не готов к скачиванию", content = @Content), @ApiResponse(responseCode = "500", description = "Ошибка при получении файла", content = @Content)})
    @GetMapping("/{fileId}")
    ResponseEntity<FileDto> getFile(@PathVariable UUID fileId);


    @Operation(summary = "Удаление файла", description = """
            Удаляет файл из S3 и базы данных.
            Если файл отсутствует в S3 — запись всё равно удаляется.
            """, parameters = {@Parameter(name = "fileId", description = "UUID файла", required = true)}, responses = {@ApiResponse(responseCode = "204", description = "Файл успешно удалён"), @ApiResponse(responseCode = "404", description = "Файл не найден", content = @Content), @ApiResponse(responseCode = "500", description = "Ошибка при удалении файла", content = @Content)})
    @DeleteMapping("/{fileId}")
    ResponseEntity<Void> deleteFile(@PathVariable UUID fileId);

}
