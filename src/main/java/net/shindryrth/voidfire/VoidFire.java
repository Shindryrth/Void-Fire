package net.shindryrth.voidfire;

import it.crystalnest.cobweb.api.pack.fixed.StaticDataPack;
import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.NeoForge;
import net.shindryrth.voidfire.fire.FireRegistry;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static net.shindryrth.voidfire.fire.FireRegistry.VOID_FIRE_TYPE;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(VoidFire.MOD_ID)
public class VoidFire {
    public static final String MOD_ID = "voidfire";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public VoidFire(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        FireRegistry.register();
        new StaticDataPack(ResourceLocation.fromNamespaceAndPath(VoidFire.MOD_ID, "enchantments"), Pack.Position.TOP).register();

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
            ResourceKey<CreativeModeTab> key = event.getTabKey();
            if (key == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
                event.insertAfter(
                        Items.CAMPFIRE.getDefaultInstance(),
                        FireManager.getRequiredComponent(VOID_FIRE_TYPE, Fire.Component.CAMPFIRE_ITEM).getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                );
                event.insertAfter(
                        Items.LANTERN.getDefaultInstance(),
                        FireManager.getRequiredComponent(VOID_FIRE_TYPE, Fire.Component.LANTERN_ITEM).getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                );
                event.insertAfter(
                        Items.TORCH.getDefaultInstance(),
                        FireManager.getRequiredComponent(VOID_FIRE_TYPE, Fire.Component.TORCH_ITEM).getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                );
            } else if (key == CreativeModeTabs.INGREDIENTS || key == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                event.insertAfter(
                        Items.FIRE_CHARGE.getDefaultInstance(),
                        FireManager.getRequiredComponent(VOID_FIRE_TYPE, Fire.Component.FIRE_CHARGE_ITEM).getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                );
            }
        }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

}
