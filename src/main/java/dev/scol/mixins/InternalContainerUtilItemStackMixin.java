package dev.scol.mixins;

import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.container.InternalContainerUtilItemStack;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.lang.reflect.Method;

@Debug(export = true, print = true)
@Mixin({InternalContainerUtilItemStack.class})
public class InternalContainerUtilItemStackMixin {
    @WrapOperation(
        method = {
            "internal_addItemStackToSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
            "internal_addItemStack(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackTransaction;",
            "internal_addItemStacks(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
            "internal_addItemStacksOrdered(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;"
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        require = 4
    )
    private int wrapMaxStackWithContainer(
        Item item,
        Operation<Integer> original,
        @Local(ordinal = 0) ItemContainer itemContainer
    ) {
        try {
            Method m = itemContainer.getClass().getMethod("getMaxStack", Item.class);
            return (int) m.invoke(itemContainer, item);
        } catch (Exception e) {
            return 2;
        }
    }
}
