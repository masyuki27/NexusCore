package nexus_core.common.datagen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import nexus_core.common.NexusCore;
import nexus_core.common.registry.NCoreBlocks;
import nexus_core.common.registry.NCoreFeatures;
import nexus_core.common.world.featute.MixedVeinConfiguration;

import java.util.List;

public class NCoreWorldGenProvider {

    //BIF
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIF_VEIN_KEY =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(NexusCore.MOD_ID, "bif_vein"));
    public static final ResourceKey<PlacedFeature> BIF_VEIN_PLACED_KEY =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(NexusCore.MOD_ID, "bif_vein"));
    public static final ResourceKey<BiomeModifier> ADD_BIF_VEIN_KEY =
            ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(NexusCore.MOD_ID, "add_bif_vein"));

    public static void bootstrapConfigured(BootstapContext<ConfiguredFeature<?, ?>> context) {
        //bif
        var stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        var oreMix = List.of(
                new MixedVeinConfiguration.WeightedBlockState(NCoreBlocks.Blocks.MAGNETITE_ORE.get().defaultBlockState(), 30),
                new MixedVeinConfiguration.WeightedBlockState(NCoreBlocks.Blocks.HEMATITE_ORE.get().defaultBlockState(), 20),
                new MixedVeinConfiguration.WeightedBlockState(NCoreBlocks.Blocks.SILICASTONE_ORE.get().defaultBlockState(), 20),
                new MixedVeinConfiguration.WeightedBlockState(Blocks.STONE.defaultBlockState(),30)
        );
        var bif_config = new MixedVeinConfiguration(stoneReplaceables, 7, oreMix, NCoreBlocks.Blocks.MAGNETITE_ORE.get().defaultBlockState());
        context.register(BIF_VEIN_KEY, new ConfiguredFeature<> (NCoreFeatures.MIXED_VEIN.get(), bif_config));


    }

    public static void bootstrapPlaced(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(BIF_VEIN_PLACED_KEY, new PlacedFeature(configuredFeatures.getOrThrow(BIF_VEIN_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(5),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(10), VerticalAnchor.absolute(80)),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome()
                )
        ));
    }

    public static void bootstrapBiomeModifier(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_BIF_VEIN_KEY, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD),

                HolderSet.direct(placedFeatures.getOrThrow(BIF_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }
}
