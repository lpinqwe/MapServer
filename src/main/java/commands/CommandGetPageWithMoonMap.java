package commands;

import ClassesDTO.CommandDTO;
import ClassesDTO.ImageRequestDTO;
import ClassesDTO.ImageResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.CommandInerface;
import utils.ResponseWrapper;

import java.nio.charset.StandardCharsets;

public class CommandGetPageWithMoonMap implements CommandInerface {

    int xId;
    int yId;

    public CommandGetPageWithMoonMap(CommandDTO cmd)  {
        try {


            String payload = cmd.Payload;
            ObjectMapper mapper = new ObjectMapper();
            ImageRequestDTO dto = mapper.readValue(payload, ImageRequestDTO.class);

            xId = dto.x_id;
            yId = dto.y_id;
        } catch (Exception e){
            System.out.println("err");

        }
    }


    public ResponseWrapper execute() {
        String html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Moon Map Fragment</title>
                </head>
                <body>
                    <h1>Moon Fragment</h1>
                    <img src="/image?x=%d&y=%d" alt="Moon Fragment">
                </body>
                </html>
                """.formatted(xId, yId);

        return new ResponseWrapper(200, "text/html", html.getBytes(StandardCharsets.UTF_8));
    }
}
