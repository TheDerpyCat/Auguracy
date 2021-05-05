package thederpycat.auguracy.capabilities.elements;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityElements
{
    @CapabilityInject(IElements.class)
    public static Capability<IElements> ELEMENTS_CAPABILITY = null;


    public static void register()
    {
        CapabilityManager.INSTANCE.register(IElements.class, new Storage(), Elements::new);
    }

    public static class Storage implements Capability.IStorage<IElements>
    {
        @Nullable
        @Override
        public INBT writeNBT(Capability<IElements> capability, IElements instance, Direction side)
        {
            CompoundNBT tag = new CompoundNBT();
            tag.putIntArray("lvl", instance.getAllLevels());
            tag.putIntArray("xp", instance.getAllLevels());
            return tag;

        }

        @Override
        public void readNBT(Capability<IElements> capability, IElements instance, Direction side, INBT nbt)
        {
            int[] lvl = ((CompoundNBT)nbt).getIntArray("lvl");
            int[] xp = ((CompoundNBT)nbt).getIntArray("xp");
            instance.setAllLevels(lvl);
            instance.setAllXP(xp);

        }
    }

}
