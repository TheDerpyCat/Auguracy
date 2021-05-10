package thederpycat.auguracy.capabilities.mana;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityMana
{
    @CapabilityInject(IMana.class)
    public static Capability<IMana> MANA_CAPABILITY = null;


    public static void register()
    {
        CapabilityManager.INSTANCE.register(IMana.class, new Storage(), Mana::new);
    }

    public static class Storage implements Capability.IStorage<IMana>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IMana> capability, IMana instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("mana", instance.getMana());
            tag.putInt("maxMana", instance.getMana());
            return tag;

        }

        @Override
        public void readNBT(Capability<IMana> capability, IMana instance, Direction side, INBT nbt)
        {
            int mana = ((CompoundNBT)nbt).getInt("mana");
            int maxMana = ((CompoundNBT)nbt).getInt("maxMana");
            instance.setMana(mana);
            instance.setMaxMana(maxMana);

        }
    }
}
