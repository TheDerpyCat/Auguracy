package thederpycat.auguracy.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import thederpycat.auguracy.capabilities.mana.CapabilityMana;
import thederpycat.auguracy.capabilities.mana.IMana;
import thederpycat.auguracy.setup.ModSetup;

import javax.annotation.Nonnull;

public class DivineWand extends Item
{
    public DivineWand()
    {
        super(new Item.Properties()
                .tab(ModSetup.AUGURACY_GROUP));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player,@Nonnull Hand hand)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        IMana manaCap = player.getCapability(CapabilityMana.MANA_CAPABILITY).orElse(null);
        manaCap.setMana(10, player);
        return ActionResult.pass(itemStack);
    }
}
