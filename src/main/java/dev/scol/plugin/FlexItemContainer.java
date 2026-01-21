package dev.scol.plugin;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.asset.type.item.config.ItemStackContainerConfig;
import com.hypixel.hytale.server.core.inventory.container.SimpleItemContainer;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class FlexItemContainer extends SimpleItemContainer {
    public static final BuilderCodec<FlexItemContainer> CODEC;
    static {
        CODEC = BuilderCodec.<FlexItemContainer>builder(
                FlexItemContainer.class,
                FlexItemContainer::new,
                SimpleItemContainer.CODEC
            )
            .append(new KeyedCodec("StackSize", Codec.SHORT),
                (config, s, e) -> config.stackSize = s,
                (config, e) -> config.stackSize
            ).add()
            .build();
    }

    protected short stackSize = -1;

    public FlexItemContainer() {
        super();
    }

    public FlexItemContainer(short capacity) {
        super();

        this.capacity = capacity;
    }

    public FlexItemContainer(short capacity, short stackSize) {
        super(capacity);

        this.stackSize = stackSize;
    }

    public int getMaxStack(@Nonnull Item item) {
        HytaleLogger logger = HytaleLogger.get("FlexContainers");
        logger.at(Level.INFO).log("FlexItemContainer::getMaxStack");
        if( this.stackSize < 0 ){
            return item.getMaxStack();
        }
        return this.stackSize;
    }


}
