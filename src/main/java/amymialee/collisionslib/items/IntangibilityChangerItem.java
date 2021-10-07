package amymialee.collisionslib.items;

import amymialee.collisionslib.CollisionsLib;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class IntangibilityChangerItem extends Item {
    public IntangibilityChangerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (user.getAttributeInstance(CollisionsLib.TANGIBLE).getBaseValue() == 0) {
                user.getAttributeInstance(CollisionsLib.TANGIBLE).setBaseValue(1);
            } else {
                user.getAttributeInstance(CollisionsLib.TANGIBLE).setBaseValue(0);
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
