package ru.ryatronth.service.desk.module.file.api;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ryatronth.service.desk.dto.file.FileDto;
import ru.ryatronth.service.desk.module.file.service.S3Service;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController implements FileApiV1 {

    private final S3Service s3Service;

    @Override
    public ResponseEntity<FileDto> initUpload(String originalName, String contentType, Long sizeBytes) {
        try {
            FileDto dto = s3Service.initUpload(originalName, contentType, sizeBytes);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            log.error("Ошибка при инициализации загрузки файла: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка инициализации загрузки");
        }
    }

    @Override
    public ResponseEntity<FileDto> confirmUpload(UUID fileId) {
        try {
            FileDto dto = s3Service.confirmUpload(fileId);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            log.warn("Не найден файл для подтверждения: {}", fileId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (RuntimeException e) {
            log.warn("Файл {} не найден в S3 при подтверждении загрузки: {}", fileId, e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка при подтверждении загрузки файла {}", fileId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка подтверждения загрузки");
        }
    }

    @Override
    public ResponseEntity<FileDto> getFile(UUID fileId) {
        try {
            FileDto dto = s3Service.getFile(fileId);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка при получении файла {}: {}", fileId, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при получении файла");
        }
    }

    @Override
    public ResponseEntity<Void> deleteFile(UUID fileId) {
        try {
            s3Service.deleteFile(fileId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            log.error("Ошибка при удалении файла {}: {}", fileId, e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при удалении файла");
        }
    }
}
