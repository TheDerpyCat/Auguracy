package thederpycat.auguracy.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.capabilities.mana.IMana;
import thederpycat.auguracy.capabilities.mana.ManaProvider;

public class ManaCapabilityHandler
{
    public static final ResourceLocation MANA_CAPABILITY = new ResourceLocation(Auguracy.MODID, "mana");

    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        ManaProvider provider = new ManaProvider();
        event.addCapability(MANA_CAPABILITY, provider);
        event.addListener(provider::invalidate);
    }

    public static void onPlayerCloneEvent(PlayerEvent.Clone event)
    {
        PlayerEntity player = event.getPlayer();
        IMana mana = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
        IMana oldMana = event.getOriginal().getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
        mana.setMana(oldMana.getMana());
    }
}
