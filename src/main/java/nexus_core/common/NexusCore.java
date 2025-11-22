package nexus_core.common;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import nexus_core.common.datagen.NCoreWorldGenProvider;
import nexus_core.common.registry.NCoreBlocks;
import nexus_core.common.registry.NCoreCreativeTabs;
import nexus_core.common.registry.NCoreFeatures;
import nexus_core.common.registry.NCoreItems;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod("nexus_core")

public class NexusCore {

    public static final String MOD_ID = "nexus_core";

    public NexusCore() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NCoreBlocks.Blocks.BLOCKS.register(bus);
        NCoreBlocks.BlockItems.BLOCK_ITEMS.register(bus);
        NCoreItems.ITEMS.register(bus);
        NCoreCreativeTabs.MOD_TABS.register(bus);
        NCoreFeatures.FEATURES.register(bus);

        bus.addListener(this::gatherData);

    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(
                event.includeServer(),
                new DatapackBuiltinEntriesProvider(
                        packOutput,
                        lookupProvider,
                        new RegistrySetBuilder()
                                .add(Registries.CONFIGURED_FEATURE, NCoreWorldGenProvider::bootstrapConfigured)
                                .add(Registries.PLACED_FEATURE, NCoreWorldGenProvider::bootstrapPlaced)
                                .add(ForgeRegistries.Keys.BIOME_MODIFIERS, NCoreWorldGenProvider::bootstrapBiomeModifier),
                        Set.of(NexusCore.MOD_ID)
                )
        );
    }
}
