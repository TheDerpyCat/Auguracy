package thederpycat.auguracy.capabilities.elements;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import thederpycat.auguracy.api.events.LevelUpEvent;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Elements implements IElements
{
    //index 0 is earth, index 1 is fire, index 2 is water, index 3 is air, index 4 is corruption;
    private int[] levelsArray = new int[5];
    //current amount of experience for each element the player has
    private int[] xpArray = new int[5];

    @Override
    public void addXP(int index, int xp, PlayerEntity player)
    {
        if(Array.getInt(levelsArray, index) == 50)
        {
            Array.setInt(xpArray, index, 0);
        }

        int currXP = Array.getInt(xpArray, index);
        int newXP = currXP + xp;
        Array.setInt(xpArray, index, newXP);

        if(Array.getInt(xpArray, index) == getXPCap(index))
        {
            addLevel(index, 1, player);
        }

    }

    @Override
    public void setXP(int index, int xp)
    {
        Array.setInt(xpArray, index, xp);

    }

    @Override
    public void addLevel(int index, int level, PlayerEntity player)
    {
        int currLvl = Array.getInt(levelsArray, index);
        int newLvl = currLvl + level;
        Array.setInt(levelsArray, index, level);
        setXP(index, 0);
        MinecraftForge.EVENT_BUS.post(new LevelUpEvent(getLevel(index), player, index));

    }

    @Override
    public void setLevel(int index, int level)
    {
        Array.setInt(levelsArray, index, level);
    }

    @Override
    public int getLevel(int index)
    {
        return Array.getInt(levelsArray, index);
    }

    @Override
    public int getXP(int index)
    {
        return Array.getInt(levelsArray, index);
    }

    //Returns amount of experience needed to get to the next level
    @Override
    public int getXPCap(int index)
    {
        if(Array.getInt(levelsArray, index) > 0)
        {
            return (10 * Array.getInt(levelsArray, index)) * 2;
        }
        else
        {
            return 10;
        }
    }

    @Override
    public int[] setAllLevels(int[] array)
    {
       return levelsArray = Arrays.copyOf(array, array.length);
    }

    @Override
    public int[] setAllXP(int[] array)
    {

        return xpArray = Arrays.copyOf(array, array.length);
    }

    @Override
    public int[] getAllLevels() {
        return levelsArray;
    }

    @Override
    public int[] getAllXP() {
        return xpArray;
    }
}
