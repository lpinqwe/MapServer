package commands;

import ClassesDTO.CommandDTO;
import ClassesDTO.ImageRequestDTO;
import ClassesDTO.ImageResponseDTO;
import Core.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.CommandInerface;
import utils.ResponseWrapper;

public class CommandGetMoonMapFragment implements CommandInerface {
    //todo error processing
    int xId;
    int yId;

    public CommandGetMoonMapFragment(CommandDTO cmd)         {
        try{
        String payload = cmd.Payload;
        ObjectMapper mapper = new ObjectMapper();
        ImageRequestDTO dto = mapper.readValue(payload, ImageRequestDTO.class);

        xId = dto.x_id;
        yId = dto.y_id;
    } catch (Exception e){
        System.out.println("err");

    }

    }

    //    public ResponseWrapper(int statusCode, String contentType, byte[] body) {
    public ResponseWrapper execute() {
        ImageResponseDTO resp = new ImageResponseDTO();
        resp.x_id = this.xId;
        resp.y_id = this.yId;
        resp.image = this.getImageById();
        return ResponseWrapper.json(resp);
    }

    public byte[] getImageById() {
        try (Connection conn = DatabaseConnection.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT image_data FROM images WHERE X_id = ? and Y_id=?")

        ) {

            stmt.setInt(1, this.xId);
            stmt.setInt(2, this.yId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBytes("image_data");  // read bytes of image
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}