package thederpycat.auguracy.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import thederpycat.auguracy.capabilities.elements.CapabilityElements;
import thederpycat.auguracy.capabilities.elements.IElements;
import thederpycat.auguracy.network.PacketChannel;
import thederpycat.auguracy.network.packets.SyncElementDataPacket;


public class ElementLvlCommand
{
    private ElementLvlCommand(){}

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
    {
        return Commands.literal("elementlvl")
                .requires(cs -> cs.hasPermission(0))
                .then(
                        Commands.literal("get")
                                .then(
                                        Commands.argument("element", StringArgumentType.word())
                                                .executes(ElementLvlCommand::getLevel)
                                     )

                     )

                .then(
                        Commands.literal("set")
                                .then(
                                        Commands.argument("element", StringArgumentType.word())
                                                .then(
                                                        Commands.argument("level", IntegerArgumentType.integer(0, 50))
                                                                .executes(ElementLvlCommand::setLevel)
                                                     )

                                      )

                     );
    }


    public static int getLevel(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        int playerlvl;
        PlayerEntity player = context.getSource().getPlayerOrException();
        IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
        String elements = StringArgumentType.getString(context, "element");
        switch(elements)
        {
            case "earth":
                playerlvl = element.getLevel(0);
                context.getSource().sendSuccess(new StringTextComponent("Your earth level is " + playerlvl), false);
                break;
            case "fire":
                playerlvl = element.getLevel(1);
                context.getSource().sendSuccess(new StringTextComponent("Your fire level is " + playerlvl), false);
                break;
            case "water":
                playerlvl = element.getLevel(2);
                context.getSource().sendSuccess(new StringTextComponent("Your water level is " + playerlvl), false);
                break;
            case "air":
                playerlvl = element.getLevel(3);
                context.getSource().sendSuccess(new StringTextComponent("Your air level is " + playerlvl), false);
                break;
            case "corruption":
                playerlvl = element.getLevel(4);
                context.getSource().sendSuccess(new StringTextComponent("Your corruption level is " + playerlvl), false);
                break;
            default:
                context.getSource().sendFailure(new StringTextComponent("Don't recognize that element"));
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int setLevel(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        ServerPlayerEntity player = context.getSource().getPlayerOrException();
        int level = IntegerArgumentType.getInteger(context, "level");
        String elements = StringArgumentType.getString(context, "element");
        IElements element = player.getCapability(CapabilityElements.ELEMENTS_CAPABILITY).orElse(null);
        switch(elements)
        {
            case "earth":
                element.setLevel(0 , level);
                context.getSource().sendSuccess(new StringTextComponent("Your earth level is " + level), false);
                break;
            case "fire":
                element.setLevel(1 , level);
                context.getSource().sendSuccess(new StringTextComponent("Your fire level is " + level), false);
                break;
            case "water":
                element.setLevel(2 , level);
                context.getSource().sendSuccess(new StringTextComponent("Your water level is " + level), false);
                break;
            case "air":
                element.setLevel(3 , level);
                context.getSource().sendSuccess(new StringTextComponent("Your air level is " + level), false);
                break;
            case "corruption":
                element.setLevel(4 , level);
                context.getSource().sendSuccess(new StringTextComponent("Your corruption level is " + level), false);
                break;
            default:
                context.getSource().sendFailure(new StringTextComponent("Don't recognize that element"));
        }
        PacketChannel.sendToClient(new SyncElementDataPacket(element.getAllLevels(), element.getAllXP()), player);
        return Command.SINGLE_SUCCESS;
    }
}
