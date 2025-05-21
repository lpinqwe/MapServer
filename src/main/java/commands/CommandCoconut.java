package commands;

import ClassesDTO.CommandDTO;
import interfaces.CommandInterface;
import utils.ResponseWrapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class CommandCoconut implements CommandInterface {

    public CommandCoconut(CommandDTO commandDTO) {
    }

    @Override
    public ResponseWrapper execute() {
        try {
            byte[] fileContent = Files.readAllBytes(Path.of("java/coconut/coconut.jpg"));

            return ResponseWrapper.image(fileContent);
        } catch (Exception e) {
            System.out.println("F");
        }
        return ResponseWrapper.error(404, "coconut not found");
    }
}
