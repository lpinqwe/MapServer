package Fabrics;

import ClassesDTO.CommandDTO;
import commands.CommandGetMoonMapFragment;
import commands.CommandGetPageWithMoonMap;
import interfaces.CommandInterface;
import utils.ResponseWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {

    private final Map<String, Function<CommandDTO, CommandInterface>> commands = new HashMap<>();

    public CommandFactory() {
        commands.put("image", CommandGetPageWithMoonMap::new);
        commands.put("command_post", CommandGetMoonMapFragment::new);
    }

    public ResponseWrapper createCommand(CommandDTO dto) {
        String cmdName = dto.commandType.toLowerCase();
        Function<CommandDTO, CommandInterface> constructor = commands.get(cmdName);

        if (constructor == null) {
            throw new IllegalArgumentException("Unknown command: " + cmdName);
        }

        CommandInterface command = constructor.apply(dto);
        return command.execute();
    }

}
