package ru.ryatronth.service.desk.data.address.number;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TicketNumberGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try {
            long nextVal = session
                    .createNativeQuery("SELECT nextval('address_ticket_seq')", Long.class)
                    .getSingleResult();


            String prefix = LocalDate.now().getYear() + "." + LocalDate.now().getMonth().getValue() + "." + LocalDate.now().getDayOfMonth();
            return prefix + "-" + String.format("%06d", nextVal);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось сгенерировать ticketNumber", e);
        }
    }
}
