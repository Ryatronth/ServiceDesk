package ru.ryatronth.service.desk.module.file.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ryatronth.service.desk.module.file.config.properties.S3Properties;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
@EnableConfigurationProperties(value = S3Properties.class)
public class S3Config {

    @Bean
    public S3Client s3Client(S3Properties s3Properties) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3Properties.accessKey(), s3Properties.secretKey());
        return S3Client.builder()
                       .endpointOverride(URI.create(s3Properties.endpoint()))
                       .credentialsProvider(StaticCredentialsProvider.create(credentials))
                       .serviceConfiguration(S3Configuration.builder()
                                                            .pathStyleAccessEnabled(true)
                                                            .build())
                       .region(Region.US_EAST_1)
                       .build();
    }

    @Bean
    public S3Presigner s3Presigner(S3Properties s3Properties) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(s3Properties.accessKey(), s3Properties.secretKey());
        return S3Presigner.builder()
                          .endpointOverride(URI.create(s3Properties.endpoint()))
                          .credentialsProvider(StaticCredentialsProvider.create(credentials))
                          .serviceConfiguration(S3Configuration.builder()
                                                               .pathStyleAccessEnabled(true)
                                                               .build())
                          .region(Region.US_EAST_1)
                          .build();
    }

}
