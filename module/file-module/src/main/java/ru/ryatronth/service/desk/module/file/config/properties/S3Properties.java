package ru.ryatronth.service.desk.module.file.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "s3")
public record S3Properties(
    String accessKey,
    String secretKey,
    String endpoint,
    String bucket,
    String keyPrefix,
    Long urlExpirationMinutes
) {}
