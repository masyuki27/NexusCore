package nexus_core.common.block.ore;

import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;

public class BlockSilicastoneOre extends DropExperienceBlock {
    public BlockSilicastoneOre() {
        super(Properties.of()
                .strength(3.0F, 3.5F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
        );
    }
}
