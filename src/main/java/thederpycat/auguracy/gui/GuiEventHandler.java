package thederpycat.auguracy.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GuiEventHandler
{
    private static InventoryScreen inventoryScreen;
    private static ElementsGui elementsGui;
    PlayerEntity player = Minecraft.getInstance().player;

    @SubscribeEvent
    public void OnInventoryGuiInit(GuiScreenEvent.InitGuiEvent.Post event)
    {
        if(event.getGui() instanceof InventoryScreen)
       {
           if(event.getGui() instanceof CreativeScreen)
           {
               return;
           }
          elementsGui = new ElementsGui(this, inventoryScreen);
       }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onDrawScreen(GuiContainerEvent.DrawBackground event)
    {
        elementsGui.renderElementLevels(event.getMatrixStack());

    }

}
