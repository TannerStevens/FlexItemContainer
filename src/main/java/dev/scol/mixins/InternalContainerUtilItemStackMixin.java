package dev.scol.mixins;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.container.InternalContainerUtilItemStack;

import com.hypixel.hytale.server.core.inventory.transaction.ItemStackSlotTransaction;
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction;
import com.hypixel.hytale.server.core.inventory.transaction.ListTransaction;
import com.hypixel.hytale.server.core.inventory.transaction.SlotTransaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.logging.Level;

@Mixin({InternalContainerUtilItemStack.class})
public class InternalContainerUtilItemStackMixin {
    private static final boolean bLog = false;
    private static void log(String context) {
        if(bLog) {
            HytaleLogger logger = HytaleLogger.get("FlexItemContainer-Hyxin");
            logger.at(Level.INFO).log("InternalContainerUtilItemStack::"+context);
        }
    }

    @Inject(
        method = "testAddToExistingSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IIZ)I",
        at = @At("HEAD")
    )
    private static void onTestAddToExistingSlot(
        @Nonnull ItemContainer container, short slot, ItemStack itemStack, int itemMaxStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testAddToExistingSlot"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_addToExistingSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onAddToExistingSlot(
        @Nonnull ItemContainer container, short slot, @Nonnull ItemStack itemStack, int itemMaxStack, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("internal_addToExistingSlot ["+container.getCapacity()+"] -> "+slot+"("+itemStack.getQuantity()+"/"+itemMaxStack+")");
    }

    @Inject(
        method = "internal_addToEmptySlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onAddToEmptySlot(
        @Nonnull ItemContainer container, short slot, @Nonnull ItemStack itemStack, int itemMaxStack, boolean filter,
        CallbackInfoReturnable<ItemStackSlotTransaction> cir
    ) {
        log("internal_addToEmptySlot"+" ["+container.getCapacity()+"] -> "+slot+"("+itemStack.getQuantity()+"/"+itemMaxStack+")");
    }

    @Inject(
        method = "testAddToEmptySlots(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;IIZ)I",
        at = @At("HEAD")
    )
    private static void onTestAddToEmptySlots(
        @Nonnull ItemContainer container, ItemStack itemStack, int itemMaxStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testAddToEmptySlots"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_addItemStackToSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onAddItemStackToSlot(
       @Nonnull ItemContainer container, short slot, @Nonnull ItemStack itemStack, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_addItemStackToSlot ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_setItemStackForSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;Z)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onSetItemStackForSlot(
        @Nonnull ItemContainer container, short slot, ItemStack itemStack, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_setItemStackForSlot ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_removeItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SZ)Lcom/hypixel/hytale/server/core/inventory/transaction/SlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot(
        @Nonnull ItemContainer container, short slot, boolean filter,
        CallbackInfoReturnable<SlotTransaction> cir
    ) {
        log("internal_removeItemStackFromSlot ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_removeItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SIZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot2(
        @Nonnull ItemContainer container, short slot, int quantityToRemove, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_removeItemStackFromSlot2 ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_removeItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot3(
        @Nonnull ItemContainer container, short slot, @Nullable ItemStack itemStackToRemove, int quantityToRemove, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_removeItemStackFromSlot3 ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_removeItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZZLjava/util/function/BiPredicate;)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStackFromSlot4(
        @Nonnull ItemContainer container, short slot, @Nullable ItemStack itemStackToRemove, int quantityToRemove, boolean allOrNothing, boolean filter, BiPredicate<ItemStack, ItemStack> predicate,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_removeItemStackFromSlot4 ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "testRemoveItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZ)I",
        at = @At("HEAD")
    )
    private static void onTestRemoveItemStackFromSlot(
        @Nonnull ItemContainer container, short slot, ItemStack itemStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testRemoveItemStackFromSlot ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "testRemoveItemStackFromSlot(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLcom/hypixel/hytale/server/core/inventory/ItemStack;IZLjava/util/function/BiPredicate;)I",
        at = @At("HEAD")
    )
    private static void onTestRemoveItemStackFromSlot2(
        @Nonnull ItemContainer container, short slot, ItemStack itemStack, int testQuantityRemaining, boolean filter, BiPredicate<ItemStack, ItemStack> predicate,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testRemoveItemStackFromSlot2 ["+container.getCapacity()+"] -> "+slot);
    }

    @Inject(
        method = "internal_addItemStack(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackTransaction;",
        at = @At("HEAD")
    )
    private static void onAddItemStack(
        @Nonnull ItemContainer container, @Nonnull ItemStack itemStack, boolean allOrNothing, boolean fullStacks, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_addItemStack"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_addItemStacks(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Ljava/util/List;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onAddItemStacks(
        @Nonnull ItemContainer container, @Nullable List<ItemStack> itemStacks, boolean allOrNothing, boolean fullStacks, boolean filter,
        CallbackInfoReturnable<ListTransaction<ItemStackTransaction>> cir
    ) {
        log("internal_addItemStackS"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_addItemStacksOrdered(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SLjava/util/List;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onAddItemStacksOrdered(
        @Nonnull ItemContainer container, short offset, @Nullable List<ItemStack> itemStacks, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ListTransaction<ItemStackSlotTransaction>> cir
    ) {
        log("internal_addItemStackSOrdered ["+container.getCapacity()+"] -> "+offset);
    }

    @Inject(
        method = "testAddToExistingItemStacks(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;IIZ)I",
        at = @At("HEAD")
    )
    private static void onTestAddToExistingItemStacks(
        @Nonnull ItemContainer container, ItemStack itemStack, int itemMaxStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testAddToExistingItemStackS"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_removeItemStack(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStack(
        @Nonnull ItemContainer container, @Nonnull ItemStack itemStack, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_removeItemStack"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "internal_removeItemStacks(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Ljava/util/List;ZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        at = @At("HEAD")
    )
    private static void onRemoveItemStacks(
        @Nonnull ItemContainer container, @Nullable List<ItemStack> itemStacks, boolean allOrNothing, boolean filter,
        CallbackInfoReturnable<ItemStackTransaction> cir
    ) {
        log("internal_removeItemStackS"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "testRemoveItemStackFromItems(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;IZ)I",
        at = @At("HEAD")
    )
    private static void onTestRemoveItemStackFromItems(
        @Nonnull ItemContainer container, ItemStack itemStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testRemoveItemStackFromItems"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "testRemoveItemStackSlotFromItems(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;IZ)Lcom/hypixel/hytale/server/core/inventory/container/TestRemoveItemSlotResult;",
        at = @At("HEAD")
    )
    private static void onTestRemoveItemStackSlotFromItems(
        @Nonnull ItemContainer container, ItemStack itemStack, int testQuantityRemaining, boolean filter,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testRemoveItemStackSlotFromItems"+" ["+container.getCapacity()+"]");
    }

    @Inject(
        method = "testRemoveItemStackSlotFromItems(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;IZLjava/util/function/BiPredicate;)Lcom/hypixel/hytale/server/core/inventory/container/TestRemoveItemSlotResult;",
        at = @At("HEAD")
    )
    private static void onTestRemoveItemStackSlotFromItems(
        @Nonnull ItemContainer container, ItemStack itemStack, int testQuantityRemaining, boolean filter, BiPredicate<ItemStack, ItemStack> predicate,
        CallbackInfoReturnable<Integer> cir
    ) {
        log("testRemoveItemStackSlotFromItems2"+" ["+container.getCapacity()+"]");
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
        method = {"lambda$internal_addItemStackToSlot$0(Lcom/hypixel/hytale/server/core/inventory/ItemStack;Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;SZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackSlotTransaction;"},
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        remap = false
    )
    private static int redirectMaxStack(
        Item item,
        ItemStack stack,
        ItemContainer itemContainer,
        short slot,
        boolean allOrNothing,
        boolean filter
    ) {
        return getMaxStack(itemContainer, item);
    }

    @Redirect(
        method = {"internal_addItemStack(Lcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Lcom/hypixel/hytale/server/core/inventory/ItemStack;ZZZ)Lcom/hypixel/hytale/server/core/inventory/transaction/ItemStackTransaction;"},
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        )
    )
    private static int redirectMaxStack(
        Item item,
        ItemContainer itemContainer,
        ItemStack itemStack,
        boolean allOrNothing,
        boolean fullStacks,
        boolean filter
    ) {
        return getMaxStack(itemContainer, item);
    }

    @Redirect(
        method = {
            "lambda$internal_addItemStacks$0(ZLjava/util/List;ZLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Z)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        remap = false
    )
    private static int redirectMaxStack(
        Item item,
        boolean allOrNothing,
        List<ItemStack> itemStacks,
        boolean fullStacks,
        ItemContainer itemContainer,
        boolean filter
    ) {
        return getMaxStack(itemContainer, item);
    }

    @Redirect(
        method = {
            "lambda$internal_addItemStacksOrdered$0(ZLjava/util/List;SLcom/hypixel/hytale/server/core/inventory/container/ItemContainer;Z)Lcom/hypixel/hytale/server/core/inventory/transaction/ListTransaction;",
        },
        at = @At(
            value = "INVOKE",
            target = "Lcom/hypixel/hytale/server/core/asset/type/item/config/Item;getMaxStack()I"
        ),
        remap = false
    )
    private static int redirectMaxStack(
        Item item,
        boolean allOrNothing,
        List<?> itemStacks,
        short startSlot,
        ItemContainer itemContainer,
        boolean filter
    ) {
        return getMaxStack(itemContainer, item);
    }
}
