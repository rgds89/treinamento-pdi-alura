package br.com.alura.roger.fipetable.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertData(String data, Class<T> type) {
        try {
            return mapper.readValue(data, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> convertDataList(String data, Class<T> type) {
        List<T>  list = new ArrayList<>();
        try {
            JsonNode jsonArray = mapper.readTree(data);
            for (JsonNode jsonNode : jsonArray) {
                list.add(mapper.readValue(jsonNode.toString(), type));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
