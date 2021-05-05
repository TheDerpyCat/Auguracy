package thederpycat.auguracy.capabilities.elements;

import net.minecraft.entity.player.PlayerEntity;

public interface IElements
{
    void addXP(int index, int xp, PlayerEntity player);
    void setXP(int index, int xp);
    void addLevel(int index, int level, PlayerEntity player);
    void setLevel(int index, int level);
    int getLevel(int index);
    int getXP(int index);
    int getXPCap(int index);
    int[] setAllLevels(int[] array);
    int[] setAllXP(int[] array);
    int[] getAllLevels();
    int[] getAllXP();

}
