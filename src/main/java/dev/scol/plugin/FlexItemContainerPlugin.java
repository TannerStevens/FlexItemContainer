package dev.scol.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.StateData;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;

import javax.annotation.Nonnull;
import java.util.logging.Level;

@SuppressWarnings({"deprecation", "removal"})
public class FlexItemContainerPlugin extends JavaPlugin {
    public FlexItemContainerPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup(){
        HytaleLogger logger = HytaleLogger.get("FlexContainers");
        logger.at(Level.INFO).log("FlexContainersPlugin Setup Running");

        ItemContainer.CODEC.register("Flex", FlexItemContainer.class, FlexItemContainer.CODEC);
        getBlockStateRegistry().registerBlockState(FlexItemContainerState.class, "flexContainer", FlexItemContainerState.CODEC);
        BlockState.CODEC.register("flexContainer", FlexItemContainerState.class, FlexItemContainerState.CODEC );
        StateData.CODEC.register("flexContainer", FlexItemContainerState.FlexItemContainerStateData.class, FlexItemContainerState.FlexItemContainerStateData.CODEC);
    }
}
