package treechopper.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class InfoCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("info")
                    .executes(source ->
                            execute(source.getSource())
                );
    }

    private static int execute(CommandSource source){
        source.sendFeedback(new TranslationTextComponent("command.infoInfo", "info"), true);
        return 1;
    }
}