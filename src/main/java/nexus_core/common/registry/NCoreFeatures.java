package nexus_core.common.registry;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nexus_core.common.NexusCore;
import nexus_core.common.world.featute.MixedVeinConfiguration;
import nexus_core.common.world.featute.MixedVeinFeature;

public class NCoreFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, NexusCore.MOD_ID);

    public static final RegistryObject<Feature<MixedVeinConfiguration>> MIXED_VEIN =
            FEATURES.register("mixed_vein", () -> new MixedVeinFeature(MixedVeinConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }
}
