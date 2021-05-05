package thederpycat.auguracy.setup;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import thederpycat.auguracy.Auguracy;
import thederpycat.auguracy.blocks.ManaFlower;
import thederpycat.auguracy.items.ManaShard;

public class Registration
{
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Auguracy.MODID);
    private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Auguracy.MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Auguracy.MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Auguracy.MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Auguracy.MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Auguracy.MODID);

    public static void init()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);
        TILES.register(modEventBus);
        CONTAINERS.register(modEventBus);
        ENTITIES.register(modEventBus);
    }

    public static final RegistryObject<Block> MANAFLOWER = BLOCKS.register("manaflower", ManaFlower::new);
    public static final RegistryObject<Item> MANAFLOWER_ITEM = ITEMS.register("manaflower", () -> new BlockItem(MANAFLOWER.get(), new Item.Properties().tab(ModSetup.AUGURACY_GROUP)));

    public static final RegistryObject<Item> MANASHARD = ITEMS.register("manashard", ManaShard::new);
}
