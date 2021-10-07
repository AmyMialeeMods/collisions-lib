package amymialee.collisionslib;

import amymialee.collisionslib.items.IntangibilityChangerItem;
import amymialee.collisionslib.items.SizeChangerItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class CollisionsLib implements ModInitializer {
    public static final String MODID = "collisionslib";
    public static final Item THINIFIER = new SizeChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final Item PHASIFIER = new IntangibilityChangerItem(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1).rarity(Rarity.RARE).fireproof());
    public static final EntityAttribute TANGIBLE = new ClampedEntityAttribute(
            "attribute." + MODID + '.' + "tangibility", 0, 0, 1).setTracked(true);

    public static boolean getTangibility(final LivingEntity entity) {
        return entity.getAttributeInstance(TANGIBLE) != null && (entity.getAttributeInstance(TANGIBLE).getValue() > 0);
    }

    @Override
    public void onInitialize() {
        Registry.register(Registry.ATTRIBUTE, new Identifier(MODID, "tangibility"), TANGIBLE);
        Registry.register(Registry.ITEM, new Identifier(MODID, "phasifier"), PHASIFIER);
        if (FabricLoader.getInstance().isModLoaded("pehkui")) {
            Registry.register(Registry.ITEM, new Identifier(MODID, "thinifier"), THINIFIER);
        }
    }
}
