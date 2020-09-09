package treechopper.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class DecayLeavesCommand {
    public static ArgumentBuilder<CommandSource, ?> register() {
        return Commands.literal("decay_leaves")
                .then(
                        Commands.argument("toggle", BoolArgumentType.bool())
                        .executes(source ->
                                execute(source.getSource(), BoolArgumentType.getBool(source, "toggle"))
                        )
                );
    }

    private static int execute(CommandSource source, boolean choice){
        source.sendFeedback(new TranslationTextComponent("command.choice", "decay_leaves", choice), true);
        return 1;
    }
}