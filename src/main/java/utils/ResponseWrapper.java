package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

public class ResponseWrapper {
    public int statusCode;
    public String contentType;
    public byte[] body;

    public ResponseWrapper(int statusCode, String contentType, byte[] body) {
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.body = body;
    }

    // JSON response
    public static ResponseWrapper json(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonBytes = mapper.writeValueAsBytes(data);
            return new ResponseWrapper(200, "application/json", jsonBytes);
        } catch (Exception e) {
            return error(500, "error JSON init");
        }
    }

    // HTML response
    public static ResponseWrapper html(String html) {
        return new ResponseWrapper(200, "text/html", html.getBytes(StandardCharsets.UTF_8));
    }

    // image response
    public static ResponseWrapper image(byte[] imageData) {
        return new ResponseWrapper(200, "image/png", imageData); // Или image/jpeg, если нужно
    }

    // Text response
    public static ResponseWrapper text(String text) {
        return new ResponseWrapper(200, "text/plain", text.getBytes(StandardCharsets.UTF_8));
    }

    // Error response
    public static ResponseWrapper error(int code, String message) {
        return new ResponseWrapper(code, "text/plain", message.getBytes(StandardCharsets.UTF_8));
    }
}
