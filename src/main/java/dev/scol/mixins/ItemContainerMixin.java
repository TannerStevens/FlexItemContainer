package dev.scol.mixins;

import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

@Debug(export = true, print = true)
@Mixin({ItemContainer.class})
public class ItemContainerMixin {

    public int getMaxStack(Item item) {
        return 4;
    }

    @WrapOperation(
        method = {
            "canAddItemStackToSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Z",
            "internal_moveItemStackFromSlot(SILcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SZ)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
            "internal_combineItemStacksIntoSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;S)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
            "canAddItemStack(Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Z",
            "canAddItemStacks(Ljava/util/List;ZZ)Z",
            "internal_sortItems(Lcom/hypixel/hytale/server.core/inventory/container/SortType;)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;"
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        require = 6
    )
    private int wrapGetMaxStack(Item itemInstance, Operation<Integer> original) {
        return this.getMaxStack(itemInstance);
    }


}