package dev.scol.plugin;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.event.EventPriority;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.StateData;
import com.hypixel.hytale.server.core.inventory.container.SimpleItemContainer;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;
import com.hypixel.hytale.server.core.universe.world.meta.state.DestroyableBlockState;
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerBlockState;
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState;
import com.hypixel.hytale.server.core.universe.world.meta.state.MarkerBlockState;
import com.hypixel.hytale.server.core.universe.world.worldmap.WorldMapManager;

import javax.annotation.Nonnull;

@SuppressWarnings({"deprecation", "removal"})
public class FlexItemContainerState  extends ItemContainerState implements ItemContainerBlockState, DestroyableBlockState, MarkerBlockState {
    public static final Codec<FlexItemContainerState> CODEC;
    static {
        CODEC = BuilderCodec.builder(FlexItemContainerState.class, FlexItemContainerState::new, ItemContainerState.BASE_CODEC)
            .append(
                new KeyedCodec<>("Custom", Codec.BOOLEAN),
                (o, b) -> o.custom = b,
                (o) -> o.custom
            ).add()
            .append(
                new KeyedCodec<>("AllowViewing", Codec.BOOLEAN),
                (o, b) -> o.allowViewing = b,
                (o) -> o.allowViewing
            ).add()
            .append(
                new KeyedCodec<>("DropList", Codec.STRING),
                (o,s) -> o.droplist = s,
                (o) -> o.droplist
            ).add()
            .append(
                new KeyedCodec<>("Marker", WorldMapManager.MarkerReference.CODEC),
                (o, m) -> o.marker = m,
                (o) -> o.marker
            ).add()
            .append(
                new KeyedCodec<>("ItemContainer", FlexItemContainer.CODEC),
                (o, ic) -> o.itemContainer = ic,
                (o) -> (FlexItemContainer) o.itemContainer
            ).add()
            .build();
    }

    public boolean initialize(@Nonnull BlockType blockType) {
        if (!super.initialize(blockType)) {
            return false;
        } else if (this.custom) {
            return true;
        } else {
            StateData stateData = blockType.getState();

            if (stateData instanceof FlexItemContainerStateData data) {
                this.itemContainer = new FlexItemContainer(data.getStackSize(), data.getCapacity());
            } else {
                this.itemContainer = new FlexItemContainer();
            }

            this.itemContainer.registerChangeEvent(EventPriority.LAST, this::onItemChange);
        }

        return true;
    }

    public static class FlexItemContainerStateData extends ItemContainerStateData {
        public static final BuilderCodec<FlexItemContainerStateData> CODEC;
        private short stackSize = -1;

        protected FlexItemContainerStateData() { }

        public short getStackSize() { return this.stackSize; }

        @Nonnull
        public String toString() {
            short stackSize = this.stackSize;
            return "FlexItemContainerStateData{stackSize="+stackSize+"} " + super.toString();
        }

        static {
            CODEC = BuilderCodec.builder(FlexItemContainerStateData.class, FlexItemContainerStateData::new, ItemContainerStateData.CODEC)
                .append(
                    new KeyedCodec<>("StackSize", Codec.SHORT),
                    (o, s) -> o.stackSize = s,
                    (o) -> o.stackSize
                ).add()
                .build();
        }
    }
}
