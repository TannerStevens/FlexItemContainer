package dev.scol.mixins;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.MaterialQuantity;
import com.hypixel.hytale.server.core.inventory.ResourceQuantity;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;

import com.hypixel.hytale.server.core.inventory.container.SlotReplacementFunction;
import com.hypixel.hytale.server.core.inventory.container.SortType;
import com.hypixel.hytale.server.core.inventory.transaction.*;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;

@Mixin({ItemContainer.class})
public class ItemContainerMixin {
    private static void log(String context) {
        HytaleLogger logger = HytaleLogger.get("Hyxin");
        logger.at(Level.INFO).log("ItemContainer::"+context);
    }

    @Inject(
        method = "canAddItemStackToSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Z",
        at = @At("HEAD")
    )
    private static void onTestAddToExistingSlot(
        short slot, @Nonnull ItemStack itemStack, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<Boolean> cir
    ) {
        log("canAddItemStackToSlot");
    }

    @Inject(
        method = "addItemStackToSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onAddItemStackToSlot(
        short slot, @Nonnull ItemStack itemStack, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("addItemStackToSlot");
    }

    @Inject(
        method = "setItemStackForSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;Z)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onSetItemStackForSlot(
        short slot, ItemStack itemStack, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("setItemStackForSlot");
    }

    @Inject(
        method = "getItemStack(S)Lcom/hypixel/hytale/server/core/inventory/ItemStack;",
        at = @At("HEAD")
    )
    private static void onGetItemStack(
        short slot,
        CallbackInfoReturnable<ItemStack> cir
    ) {
        log("getItemStack");
    }

    @Inject(
        method = "replaceItemStackInSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;Lcom/hypixel/hytale/server/core/inventory/ItemStack;)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onReplaceItemStackInSlot(
        short slot, ItemStack itemStackToRemove, ItemStack itemStack,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("replaceItemStackInSlot");
    }

    @Inject(
        method = "replaceAll(Lcom/hypixel/hytale/server/core/inventory/container/SlotReplacementFunction;Z)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onReplaceAll(
        SlotReplacementFunction func, boolean ignoreEmpty,
        CallbackInfoReturnable<ListTransaction<ItemStackSlotTransaction>> cir
    ) {
        log("replaceAll");
    }

    @Inject(
        method = "internal_replaceItemStack(SLcom/hypixel/hytale/server/core/inventory/ItemStack;Lcom/hypixel/hytale/server/core/inventory/ItemStack;)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_replaceItemStack(
        short slot, @Nullable ItemStack itemStackToRemove, ItemStack itemStack,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("internal_replaceItemStack");
    }

    @Inject(
        method = "removeItemStackFromSlot(SZ)Lcom/hypixel/hytale/server/core/inventory/transaction/SlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot(
        short slot, boolean filter,
        CallbackInfoReturnable<SlotTransaction> cir
    ) {
        log("removeItemStackFromSlot(SZ)");
    }

    @Inject(
        method = "removeItemStackFromSlot(SIZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot(
        short slot, int quantityToRemove, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("removeItemStackFromSlot(SIZZ)");
    }

    @Inject(
        method = "removeItemStackFromSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot(
        short slot, ItemStack itemStackToRemove, int quantityToRemove, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("removeItemStackFromSlot(SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZZ)");
    }

    @Inject(
        method = "removeResourceFromSlot(SLcom/hypixel/hytale/server/core/inventory/ResourceQuantity;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ResourceSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveResourceFromSlot(
        short slot, @Nonnull ResourceQuantity resource, boolean allOrNothing, boolean exactAmount, boolean filter,
        CallbackInfoReturnable<ResourceSlotTransaction> cir
    ) {
        log("removeResourceFromSlot");
    }

    @Inject(
        method = "removeTagFromSlot(SIIZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/TagSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveTagFromSlot(
        short slot, int tagIndex, int quantity, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<TagSlotTransaction> cir
    ) {
        log("removeTagFromSlot");
    }

    @Inject(
        method = "moveItemStackFromSlot(SLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        at = @At("HEAD")
    )
    private static void onMoveItemStackFromSlot(
        short slot, @Nonnull ItemContainer containerTo, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<MoveTransaction<ItemStackTransaction>> cir
    ) {
        log("moveItemStackFromSlot");
    }

    @Inject(
        method = "internal_moveItemStackFromSlot(SLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_moveItemStackFromSlot(
        short slot, @Nonnull ItemContainer containerTo, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<MoveTransaction<ItemStackTransaction>> cir
    ) {
        log("internal_moveItemStackFromSlot(SLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;ZZ)");
    }

    @Inject(
        method = "internal_moveItemStackFromSlot(SILcom/hypixel/hytale/server/core/inventory/container/ItemContainer;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_moveItemStackFromSlot(
        short slot, int quantity, @Nonnull ItemContainer containerTo, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<MoveTransaction<ItemStackTransaction>> cir
    ) {
        log("internal_moveItemStackFromSlot(SILcom/hypixel/hytale/server/core/inventory/container/ItemContainer;ZZ)");
    }

    @Inject(
        method = "internal_moveItemStackFromSlot(SILcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SZ)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_moveItemStackFromSlot(
        short slot, int quantity, @Nonnull ItemContainer containerTo, short slotTo, boolean filter,
        CallbackInfoReturnable<MoveTransaction<SlotTransaction>> cir
    ) {
        log("internal_moveItemStackFromSlot(SILcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SZ)");
    }

    @Inject(
        method = "internal_moveAllItemStacksTo(Ljava/util/function/Predicate;[Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_moveAllItemStacksTo(
        @Nullable Predicate<ItemStack> itemPredicate, ItemContainer[] containerTo,
        CallbackInfoReturnable<ListTransaction<MoveTransaction<ItemStackTransaction>>> cir
    ) {
        log("internal_moveAllItemStacksTo(Ljava/util/function/Predicate;[Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;)");
    }

    @Inject(
        method = "quickStackTo([Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onQuickStackTo(
        @Nonnull ItemContainer[] containerTo,
        CallbackInfoReturnable<ListTransaction<MoveTransaction<ItemStackTransaction>>> cir
    ) {
        log("quickStackTo");
    }

    @Inject(
        method = "internal_combineItemStacksIntoSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;S)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_combineItemStacksIntoSlot(
        @Nonnull ItemContainer containerTo, short slotTo,
        CallbackInfoReturnable<ListTransaction<MoveTransaction<SlotTransaction>>> cir
    ) {
        log("internal_combineItemStacksIntoSlot");
    }

    @Inject(
        method = "internal_swapItems(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SS)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_swapItems(
        @Nonnull ItemContainer containerTo, short slotFrom, short slotTo,
        CallbackInfoReturnable<ListTransaction<MoveTransaction<SlotTransaction>>> cir
    ) {
        log("internal_swapItems");
    }

    @Inject(
        method = "canAddItemStack(Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Z",
        at = @At("HEAD")
    )
    private static void onCanAddItemStack(
        @Nonnull ItemStack itemStack, boolean fullStacks, boolean filter,
        CallbackInfoReturnable<Boolean> cir
    ) {
        log("canAddItemStack");
    }

    @Inject(
        method = "isEmpty()Z",
        at = @At("HEAD")
    )
    private static void onIsEmpty(
        CallbackInfoReturnable<Boolean> cir
    ) {
        log("isEmpty");
    }

    @Inject(
        method = "countItemStacks(Ljava/util/function/Predicate;)I",
        at = @At("HEAD")
    )
    private static void onCountItemStacks(
        @Nonnull Predicate<ItemStack> itemPredicate,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("countItemStacks");
    }

    @Inject(
        method = "containsItemStacksStackableWith(Lcom/hypixel/hytale/server/core/inventory/ItemStack;)Z",
        at = @At("HEAD")
    )
    private static void onContainsItemStacksStackableWith(
        @Nonnull ItemStack itemStack,
        CallbackInfoReturnable<Boolean> cir
    ) {
        log("containsItemStacksStackableWith");
    }

    @Inject(
        method = "internal_sortItems(Lcom/hypixel/hytale/server/core/inventory/container/SortType;)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onInternal_sortItems(
        @Nonnull SortType sort,
        CallbackInfoReturnable<ListTransaction<SlotTransaction>> cir
    ) {
        log("internal_sortItems");
    }

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
        log("addItemStack");
    }


    public int getMaxStack(@Nonnull Item item) {
        log("getMaxStack");
        return item.getMaxStack();
    }

    private static int getMaxStack(ItemContainer itemContainer, Item item) {
        try {
            Method m = itemContainer.getClass().getMethod("getMaxStack", Item.class);
            return (int) m.invoke(itemContainer, item);
        } catch (Exception e) {
            return 1;
        }
    }

    @Redirect(
        method = {
            "lambda$internal_moveItemStackFromSlot$5(ZSLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;IS)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        remap = false
    )
    private int redirectMaxStack(
        Item item,
        boolean boolCapture,
        short shortCapture1,
        ItemContainer itemContainer,
        int intCapture,
        short shortCapture2
    ) {
        return getMaxStack(itemContainer, item);
    }

    @Redirect(
        method = {
            "lambda$internal_moveItemStackFromSlot$5(ZSLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;IS)Lcom/hypixel/hytale/server/core/inventory/transaction/MoveTransaction;",
        },
        at = @At(
            value = "INVOKE",
            target = "com/hypixel/hytale/server/core/inventory/container/ItemContainer.internal_removeItemStack(SI)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;"
        ),
        remap = false
    )
    private ItemStackSlotTransaction redirectInternalRemoveItemStack(
        ItemContainer containerFrom,
        short slot,
        int quantity,
        boolean boolCapture,
        short shortCapture1,
        ItemContainer containerTo,
        int intCapture,
        short shortCapture2
    ) {
        ItemStack stack = containerFrom.getItemStack(slot);
        quantity = Math.min(getMaxStack(containerTo, stack.getItem()), quantity);
        return containerFrom.internal_removeItemStack(slot, quantity);
    }

    @Redirect(
        method = {
            "lambda$internal_sortItems$0(Lcom/hypixel/hytale/server/core/inventory/container/SortType;)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        remap = false
    )
    private int redirectMaxStack(
        Item item,
        SortType sortType
    ) {
        return getMaxStack((ItemContainer) (Object) this, item);
    }
}