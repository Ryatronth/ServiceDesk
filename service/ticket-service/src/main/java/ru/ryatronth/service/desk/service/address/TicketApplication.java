package ru.ryatronth.service.desk.service.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.ryatronth.service.desk")
public class TicketApplication {

    public static void main(final String[] args) {
        SpringApplication.run(TicketApplication.class, args);
    }

}
