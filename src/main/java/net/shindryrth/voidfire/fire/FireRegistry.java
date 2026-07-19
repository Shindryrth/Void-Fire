package net.shindryrth.voidfire.fire;

import it.crystalnest.prometheus.api.Fire;
import it.crystalnest.prometheus.api.FireManager;
import it.crystalnest.prometheus.api.FireRegistrar;
import it.crystalnest.prometheus.api.block.CustomFireBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;

import static net.shindryrth.voidfire.VoidFire.MOD_ID;

public final class FireRegistry {
    /**
     * Fire type of your Fire. Use your actual mod ID for the MOD_ID.
     */
    public static final ResourceLocation VOID_FIRE_TYPE = ResourceLocation.fromNamespaceAndPath(MOD_ID, "void");

    static {
        Fire.Builder builder = FireManager.fireBuilder(VOID_FIRE_TYPE)
                .setDefaultComponents() // This sets all the ResourceLocation references, linking your fire to other game objects like block, campfire, etc. However, such objects they must be registered as well (which we do below using FireRegistrar), otherwise references will become invalid.
                .setLight(13) // Set the light value, defaults to 15
                .setDamage(3F); // Set the damage, defaults to 1
        // You can also set other properties, check https://github.com/Crystal-Nest/prometheus/wiki/Fire#fire-properties
        FireManager.registerFire(builder.build()); // Register your fire within Prometheus API
        FireRegistrar.registerFireSource(VOID_FIRE_TYPE, MapColor.COLOR_PURPLE, CustomFireBlock::new); // Use this for the time being, we can see later how to use a custom block class if you need it. Just use the color purple, I guess
        FireRegistrar.registerDefaultFireComponents(VOID_FIRE_TYPE, Fire.Component.CAMPFIRE_BLOCK, Fire.Component.CAMPFIRE_ITEM, Fire.Component.FLAME_PARTICLE, Fire.Component.LANTERN_BLOCK, Fire.Component.LANTERN_ITEM, Fire.Component.TORCH_BLOCK, Fire.Component.TORCH_ITEM, Fire.Component.FIRE_CHARGE_ITEM); // Register all default components, meaning here you are actually registering within the game the blocks and items. If you later need something more custom, it can be done, but let's start with this
    }

    private FireRegistry() {}

    /**
     * Call in your mod laoder to load this class and automatically run the register code above.
     */
    public static void register() {}
}