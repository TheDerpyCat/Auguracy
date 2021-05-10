package thederpycat.auguracy.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HUDEventHandler
{
    public static void onDrawHUDPost(RenderGameOverlayEvent.Post event)
    {
        MatrixStack matrixStack = event.getMatrixStack();

        if(event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            ManaBar.renderManaBar(matrixStack);
        }
    }
}
