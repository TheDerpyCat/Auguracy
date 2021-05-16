package thederpycat.auguracy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import thederpycat.auguracy.Auguracy;

public class HUDEventHandler
{
    static ResourceLocation wandTag = new ResourceLocation(Auguracy.MODID, "wands");

    public static void onDrawHUDPost(RenderGameOverlayEvent.Post event)
    {
        PlayerEntity player = Minecraft.getInstance().player;
        ItemStack main = player.getMainHandItem();
        Item item = main.getItem();
        MatrixStack matrixStack = event.getMatrixStack();

        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL && ItemTags.getAllTags().getTag(wandTag).contains(item))
        {
            ManaBar.renderManaBar(matrixStack);
        }
    }
}
