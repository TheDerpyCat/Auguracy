package thederpycat.auguracy.capabilities.mana;

import net.minecraft.entity.player.PlayerEntity;

public interface IMana
{
    void setMana(int mana, PlayerEntity player);
    void setMana(int mana);
    int getMana();
    void setMaxMana(int maxMana, PlayerEntity player);
    void setMaxMana(int maxMana);
    int getMaxMana();

}
