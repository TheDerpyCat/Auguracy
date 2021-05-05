package thederpycat.auguracy.capabilities.mana;

public class Mana implements IMana
{
    private int mana;

    @Override
    public void setMana(int mana)
    {
        this.mana = mana;
    }

    @Override
    public int getMana()
    {
        return this.mana;
    }
}
