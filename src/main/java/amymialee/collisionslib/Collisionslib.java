package amymialee.collisionslib;

import amymialee.collisionslib.items.IntangibilityChangerItem;
import amymialee.collisionslib.items.SizeChangerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;

public class Collisionslib implements ModInitializer {
    public static final String MODID = "collisionslib";
    public static final Item THINIFIER = new SizeChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final Item PHASIFIER = new IntangibilityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final GameRules.Key<GameRules.BooleanRule> DO_MOB_COLLISIONS =
            GameRuleRegistry.register("doMobCollisions", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "phasifier"), PHASIFIER);
        if (FabricLoader.getInstance().isModLoaded("pehkui")) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "thinifier"), THINIFIER);
        }
    }
}
