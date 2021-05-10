package thederpycat.auguracy.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class ManaUpdateEvent extends Event
{
    private final int mana;
    private final int maxMana;
    private final PlayerEntity player;

    public ManaUpdateEvent(int mana, int maxMana, PlayerEntity player)
    {

        this.mana = mana;
        this.maxMana = maxMana;
        this.player = player;
    }

    public int getMana()
    {
        return mana;
    }
    public int getMaxMana()
    {
        return maxMana;
    }
    public PlayerEntity getPlayer()
    {
        return player;
    }
}
