package ru.ryatronth.service.desk.module.file.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.ryatronth.service.desk.data.file.model.FileEntity;
import ru.ryatronth.service.desk.data.file.model.FileStatus;
import ru.ryatronth.service.desk.dto.file.FileClientStatus;
import ru.ryatronth.service.desk.dto.file.FileDto;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "originalName", source = "originalName")
    @Mapping(target = "contentType", source = "contentType")
    @Mapping(target = "sizeBytes", source = "sizeBytes")
    @Mapping(target = "bucket", source = "bucket")
    @Mapping(target = "key", source = "key")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "createdAt", source = "createdAt")
    FileDto toDto(FileEntity entity);

    @Named("mapStatus")
    default FileClientStatus mapStatus(FileStatus status) {
        if (status == null) {
            return FileClientStatus.FAILED;
        }

        return FileClientStatus.valueOf(status.name());
    }
}
