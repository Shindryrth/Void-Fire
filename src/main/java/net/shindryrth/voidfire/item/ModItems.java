package net.shindryrth.voidfire.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.shindryrth.voidfire.VoidFire;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(VoidFire.MOD_ID);

    public static final DeferredItem<Item> VOID_INFUSED_COAL = ITEMS.register("void_infused_coal",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
