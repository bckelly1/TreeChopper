package treechopper.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class TreeChopperCommand {
    private static final String COMMAND_NAME = "tch";

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal(COMMAND_NAME)
                    .then(DecayLeavesCommand.register())
                    .then(DisableShiftCommand.register())
                    .then(InfoCommand.register())
                    .then(PlantSaplingCommand.register())
                    .then(ReverseShiftCommand.register())
        );
    }
}
