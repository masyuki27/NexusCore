package nexus_core.common.world.featute;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class MixedVeinFeature extends Feature<MixedVeinConfiguration> {
    public MixedVeinFeature(Codec<MixedVeinConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MixedVeinConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos center = context.origin();
        MixedVeinConfiguration config = context.config();
        RandomSource random = context.random();

        int radius = config.radius;
        int rX = radius + random.nextInt(2);
        int rY = radius / 2 + random.nextInt(2);
        int rZ = radius + random.nextInt(2);

        boolean placedAny = false;

        for (int x = -rX; x <= rX; x++) {
            for (int y = -rY; y <= rY; y++) {
                for (int z = -rZ; z <= rZ; z++) {
                    BlockPos pos = center.offset(x, y, z);

                    double dist = ((double) (x * x) / (rX * rX)) + ((double) (y * y) / (rY * rY)) + ((double) (z * z) / (rZ * rZ));

                    if (dist <= 1.0 - (random.nextDouble() * 0.15)) {
                        if (config.target.test(level.getBlockState(pos), random))

                            level.setBlock(pos, config.getRandomBlock(random), 2);
                        placedAny = true;
                    }
                }
            }
        }

        if (placedAny) {
            placeSurfaceIndicators(level, center, config, random);
        }
        return placedAny;
    }

    private void placeSurfaceIndicators(WorldGenLevel level, BlockPos center, MixedVeinConfiguration config, RandomSource random) {
        int count = 3 + random.nextInt(4);

        int spread = 5;

        for (int i = 0; i < count; i++) {
            int dx = random.nextInt(spread * 2 + 1) - spread;
            int dz = random.nextInt(spread * 2 + 1) - spread;

            int targetX = center.getX() + dx;
            int targetZ = center.getZ() + dz;

            int surfaceY = level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, targetX, targetZ);

            BlockPos surfacePos = new BlockPos(targetX, surfaceY, targetZ);

            BlockState groundState = level.getBlockState(surfacePos.below());

            if (groundState.isSolid() && level.getBlockState(surfacePos).canBeReplaced()) {
                level.setBlock(surfacePos, config.indicator, 2);
            }
        }
    }
}
