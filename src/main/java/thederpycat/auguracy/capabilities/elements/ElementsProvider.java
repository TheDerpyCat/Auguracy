package thederpycat.auguracy.capabilities.elements;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ElementsProvider implements ICapabilitySerializable<CompoundNBT>
{

    public final Elements elements = new Elements();
    private final LazyOptional<IElements> elementsOptional = LazyOptional.of(() -> elements);

    public void invalidate()
    {
        elementsOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        return elementsOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT()
    {
        if(CapabilityElements.ELEMENTS_CAPABILITY == null)
        {
            return new CompoundNBT();
        }
        else
        {
            return (CompoundNBT) CapabilityElements.ELEMENTS_CAPABILITY.writeNBT(elements, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt)
    {
        if(CapabilityElements.ELEMENTS_CAPABILITY != null)
        {
            CapabilityElements.ELEMENTS_CAPABILITY.readNBT(elements, null, nbt);
        }
    }
}
