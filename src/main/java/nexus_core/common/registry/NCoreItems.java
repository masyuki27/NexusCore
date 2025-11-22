package nexus_core.common.registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nexus_core.common.NexusCore;
import nexus_core.common.item.raw_material.ItemRawHematite;
import nexus_core.common.item.raw_material.ItemRawMagnetite;
import nexus_core.common.item.raw_material.ItemRawSilicastone;

public class NCoreItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NexusCore.MOD_ID);

    public static final RegistryObject<Item> RAW_HEMATITE = ITEMS.register("raw_hematite", ItemRawHematite::new);
    public static final RegistryObject<Item> RAW_MAGNETITE = ITEMS.register("raw_magnetite", ItemRawMagnetite::new);
    public static final RegistryObject<Item> RAW_SILICASTONE = ITEMS.register("raw_silicastone", ItemRawSilicastone::new);
}
