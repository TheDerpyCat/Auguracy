package thederpycat.auguracy.capabilities.mana;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import thederpycat.auguracy.api.events.ManaUpdateEvent;

public class Mana implements IMana
{
    private int mana;
    private int maxMana;


    @Override
    public void setMana(int mana, PlayerEntity player)
    {
        this.mana = mana;
        MinecraftForge.EVENT_BUS.post(new ManaUpdateEvent(getMana(), getMaxMana(), player));
    }

    @Override
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    @Override
    public int getMana()
    {
        return mana;
    }

    @Override
    public void setMaxMana(int maxMana, PlayerEntity player)
    {
        this.maxMana = maxMana;
        MinecraftForge.EVENT_BUS.post(new ManaUpdateEvent(getMana(), getMaxMana(), player));
    }

    @Override
    public void setMaxMana(int maxMana)
    {
        this.maxMana = maxMana;
    }

    @Override
    public int getMaxMana()
    {
        if(this.maxMana == 0)
        {
            this.maxMana = 20;
        }
        return maxMana;
    }
}
