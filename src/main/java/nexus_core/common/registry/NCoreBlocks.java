package nexus_core.common.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nexus_core.common.NexusCore;
import nexus_core.common.block.ore.BlockHematiteOre;
import nexus_core.common.block.ore.BlockMagnetiteOre;
import nexus_core.common.block.ore.BlockSilicastoneOre;

public class NCoreBlocks {
    public static class Blocks{
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NexusCore.MOD_ID);

        public static final RegistryObject<Block> HEMATITE_ORE = BLOCKS.register("hematite_ore", BlockHematiteOre::new);
        public static final RegistryObject<Block> MAGNETITE_ORE = BLOCKS.register("magnetite_ore", BlockMagnetiteOre::new);
        public static final RegistryObject<Block> SILICASTONE_ORE = BLOCKS.register("silicastone_ore", BlockSilicastoneOre::new);
    }

    public static class BlockItems {
        public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NexusCore.MOD_ID);

        public static final RegistryObject<Item> HEMATITE_ORE = BLOCK_ITEMS.register("hematite_ore",
                () -> new BlockItem(Blocks.HEMATITE_ORE.get(), new Item.Properties()));
        public static final RegistryObject<Item> MAGNETITE_ORE = BLOCK_ITEMS.register("magnetite_ore",
                () -> new BlockItem(Blocks.MAGNETITE_ORE.get(), new Item.Properties()));
        public static final RegistryObject<Item> SILICASTONE_ORE = BLOCK_ITEMS.register("silicastone_ore",
                () -> new BlockItem(Blocks.SILICASTONE_ORE.get(), new Item.Properties()));
    }
}
