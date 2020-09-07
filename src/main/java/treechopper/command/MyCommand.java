package treechopper.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import treechopper.core.TreeChopper;

import java.util.ArrayList;

public class MyCommand {
    private static final DynamicCommandExceptionType UNKNOWN_COLOR = new DynamicCommandExceptionType(color -> {
        return new TranslationTextComponent("command.hello.unknown", color);
    });

    private static final SuggestionProvider<CommandSource> SUGGEST_BOOLEANS = (source, builder) -> {
        ArrayList<String> booleans = new ArrayList<>();
        booleans.add("true");
        booleans.add("false");
//        return ISuggestionProvider.suggest(TextFormatting.getValidValues(true, false).stream(), builder);
        return ISuggestionProvider.suggest(booleans.stream(), builder);
    };

    public static void registerInfo(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tch").executes(source -> {
            return hello(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("info", EntityArgument.player()).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
        })));
    }

    public static void registerPlantSapling(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tch").executes(source -> {
            return hello(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("plantSapling", EntityArgument.player()).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
        }).then(Commands.argument("toggle", BoolArgumentType.bool()).suggests(SUGGEST_BOOLEANS).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "toggle"));
        }))));
    }

    public static void registerDecayLeaves(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tch").executes(source -> {
            return hello(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("decayLeaves", EntityArgument.player()).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
        }).then(Commands.argument("toggle", BoolArgumentType.bool()).suggests(SUGGEST_BOOLEANS).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "toggle"));
        }))));
    }

    public static void registerReverseShift(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tch").executes(source -> {
            return hello(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("reverseShift", EntityArgument.player()).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
        }).then(Commands.argument("toggle", BoolArgumentType.bool()).suggests(SUGGEST_BOOLEANS).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "toggle"));
        }))));
    }

    public static void registerDisableShift(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("tch").executes(source -> {
            return hello(source.getSource(), source.getSource().asPlayer());
        }).then(Commands.argument("disableShift", EntityArgument.player()).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"));
        }).then(Commands.argument("toggle", BoolArgumentType.bool()).suggests(SUGGEST_BOOLEANS).executes(source -> {
            return hello(source.getSource(), EntityArgument.getPlayer(source, "target"), StringArgumentType.getString(source, "toggle"));
        }))));
    }

    private static int hello(CommandSource source, PlayerEntity playerEntity){
        source.sendFeedback(new TranslationTextComponent("command.hello", playerEntity.getDisplayName()), true);
        return 1;
    }

    private static int hello(CommandSource source, PlayerEntity player, String toggle) throws CommandSyntaxException {
		if(TextFormatting.getValueByName(color) == null) {
            boolean parsedBoolean = Boolean.parseBoolean(toggle); //Add some error handling
		}
        TreeChopper.LOGGER.info("Color: " + color);
        TreeChopper.LOGGER.info("Color value: " + TextFormatting.getValueByName(color));
		source.sendFeedback(new TranslationTextComponent("command.hello.color", player.getDisplayName()).mergeStyle(TextFormatting.getValueByName(color)), true);
		return 1;
	}
}
