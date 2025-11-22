package nexus_core.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import nexus_core.common.NexusCore;

public class NCoreCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NexusCore.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEXUS_ORE = MOD_TABS.register("nexus_ore",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(NCoreItems.RAW_HEMATITE.get()))
                    .title(Component.translatable("creativetab.nexus_ore"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(NCoreItems.RAW_HEMATITE.get());
                        pOutput.accept(NCoreItems.RAW_MAGNETITE.get());
                        pOutput.accept(NCoreItems.RAW_SILICASTONE.get());

                        pOutput.accept(NCoreBlocks.BlockItems.HEMATITE_ORE.get());
                        pOutput.accept(NCoreBlocks.BlockItems.MAGNETITE_ORE.get());
                        pOutput.accept(NCoreBlocks.BlockItems.SILICASTONE_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        MOD_TABS.register(eventBus);
    }
}
