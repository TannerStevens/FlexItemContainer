package dev.scol.mixins;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;

import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.logging.Level;

@Mixin({ItemContainer.class})
public class ItemContainerMixin {
    @Inject(
        method = "addItemStack(Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackTransaction;",
        at = @At("HEAD")
    )
    private void onAddItemStack(
        ItemStack itemStack,
        boolean allOrNothing,
        boolean fullStacks,
        boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        HytaleLogger logger = HytaleLogger.get("Hyxin");
        logger.at(Level.INFO).log("ItemContainer::addItemStack");
    }

    public int getMaxStack(Item item) {
        return 4;
    }


}