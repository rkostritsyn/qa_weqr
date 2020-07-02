package com.griddynamics.meetapp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.griddynamics.meetapp.model.exception.HttpStreamOperationException;
import com.griddynamics.meetapp.model.exception.JsonParsingException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class ObjectMapperHolder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void sendResponseJson(HttpServletResponse response, Object o, int statusCode) {
        try {
            response.setContentType(Constant.CONTENT_TYPE_JSON);
            response.setCharacterEncoding(Constant.ENCODING_UTF_8);
            response.setStatus(statusCode);

            String json = objectMapper.writeValueAsString(o);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(json.getBytes());
            outputStream.flush();

        } catch (JsonProcessingException e) {
            throw new JsonParsingException(Constant.ERRMSG_SERIALIZE_TO_JSON + o.toString(), e);
        } catch (IOException e) {
            throw new HttpStreamOperationException(Constant.ERRMSG_SEND_RESPONSE, e);
        }
    }

    public <T> T extractJsonFromRequest(HttpServletRequest request, Class<T> valueType) throws IOException {
        String reqBody = request.getReader().lines()
                // fix for Windows
                .map(s -> s.replaceAll("\r", ""))
                .collect(Collectors.joining("\n"));
        return objectMapper.readValue(reqBody, valueType);
    }
}
