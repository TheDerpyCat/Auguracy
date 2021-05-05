package thederpycat.auguracy.blocks;

import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;

public class ManaFlower extends FlowerBlock
{
    public ManaFlower() {
        super(Effects.WEAKNESS,
                5,
                Properties.of(Material.PLANT)
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.GRASS)
        );
    }
}
