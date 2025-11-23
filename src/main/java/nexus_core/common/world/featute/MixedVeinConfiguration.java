package nexus_core.common.world.featute;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class MixedVeinConfiguration implements FeatureConfiguration {
    public static final Codec<MixedVeinConfiguration> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    RuleTest.CODEC.fieldOf("target").forGetter(c -> c.target),
                    Codec.INT.fieldOf("radius").forGetter(c -> c.radius),
                    WeightedBlockState.CODEC.listOf().fieldOf("blocks").forGetter(c -> c.blocks),
                    BlockState.CODEC.fieldOf("indicator").forGetter(c -> c.indicator)
            ).apply(instance, MixedVeinConfiguration::new));

    public final RuleTest target;
    public final int radius;
    public final List<WeightedBlockState> blocks;
    public final BlockState indicator;

    public MixedVeinConfiguration(RuleTest target, int radius, List<WeightedBlockState> blocks, BlockState indicator) {
        this.target = target;
        this.radius = radius;
        this.blocks = blocks;
        this.indicator = indicator;
    }

    public BlockState getRandomBlock(RandomSource random) {
        int totalWeight = blocks.stream().mapToInt(b -> b.weight).sum();
        int randomValue = random.nextInt(totalWeight);
        int currentWeight = 0;

        for (WeightedBlockState entry : blocks) {
            currentWeight += entry.weight;
            if (randomValue < currentWeight) {
                return entry.state;
            }
        }
        return blocks.get(0).state;
    }

    public record WeightedBlockState(BlockState state, int weight) {
        public static final Codec<WeightedBlockState> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                BlockState.CODEC.fieldOf("state").forGetter(WeightedBlockState::state),
                Codec.INT.fieldOf("weight").forGetter(WeightedBlockState::weight)
        ).apply(inst, WeightedBlockState::new));
    }
}
