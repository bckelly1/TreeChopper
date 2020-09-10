package treechopper.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

/**
 * Print information on how the "/tch" family of commands is used
 */
public class InfoCommand {
    private static final String COMMAND_NAME = "info";

    /**
     * Registers the command to the dispatcher tree's command configuration
     * @return ArgumentBuilder: Attaches the command to the parent "/tch" command
     */
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal(COMMAND_NAME)
                    .executes(source ->
                            execute(source.getSource())
                );
    }

    /**
     * What the Info command actually does.
     *
     * @param source CommandSource: Event's command source
     * @return int: Anything >= 0 is considered a successful run of the command
     */
    private static int execute(CommandSource source){
        source.sendFeedback(new TranslationTextComponent("command.info", COMMAND_NAME), true);
        return 1;
    }
}
