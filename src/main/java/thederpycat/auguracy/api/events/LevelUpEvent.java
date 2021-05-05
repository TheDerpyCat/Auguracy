package thederpycat.auguracy.api.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

public class LevelUpEvent extends Event
{
    private final int level;
    private final PlayerEntity player;
    private final int index;

    public LevelUpEvent(int level, PlayerEntity player, int index)
    {
        this.level = level;
        this.player = player;
        this.index = index;
    }

    public int getLevel()
    {
        return level;
    }
    public int getIndex()
    {
        return index;
    }
    public PlayerEntity getPlayer()
    {
        return player;
    }

}
