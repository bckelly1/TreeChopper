package treechopper.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class ReverseShiftCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("reverse_shift")
                .then(
                        Commands.argument("toggle", BoolArgumentType.bool())
                                .executes(source ->
                                        execute(source.getSource(), BoolArgumentType.getBool(source, "toggle"))
                                )
                );
    }

    private static int execute(CommandSource source, boolean choice){
        source.sendFeedback(new TranslationTextComponent("command.choice", "reverse_shift", choice), true);
        return 1;
    }
}