package thederpycat.auguracy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import thederpycat.auguracy.Auguracy;

public class ModCommands
{
    public static void register(CommandDispatcher<CommandSource> dispatcher)
    {
        LiteralCommandNode<CommandSource> cmdElementLvl = dispatcher.register(
                Commands.literal(Auguracy.MODID)
                         .then(ElementLvlCommand.register(dispatcher))
        );
        dispatcher.register(Commands.literal("elementlvl").redirect(cmdElementLvl));
    }
}
