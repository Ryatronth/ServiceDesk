package ru.ryatronth.service.desk.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Информация о файле")
public record FileDto(@Schema(description = "Идентификатор файла") UUID id,
                      @Schema(description = "Исходное имя файла") String originalName,
                      @Schema(description = "Тип содержимого") String contentType,
                      @Schema(description = "Размер в байтах") Long sizeBytes,
                      @Schema(description = "Bucket, где хранится файл") String bucket,
                      @Schema(description = "Ключ (путь в S3)") String key,
                      @Schema(description = "Presigned URL для скачивания") String url,
                      @Schema(description = "Статус файла") FileClientStatus status,
                      @Schema(description = "Временная метка создания") Instant createdAt,
                      @Schema(description = "Временная метка обновления") Instant updatedAt) {
}
