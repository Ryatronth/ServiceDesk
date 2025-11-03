package ru.ryatronth.service.desk.data.persona.model.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        try {
            return list == null ? "[]" : objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сериализации списка", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String json) {
        try {
            return json == null ? Collections.emptyList()
                       : objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при десериализации списка", e);
        }
    }
}

