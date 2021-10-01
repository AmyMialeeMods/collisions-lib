package amymialee.collisionslib.items;

import amymialee.collisionslib.client.LivingEntityAccessor;
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
            ((LivingEntityAccessor) user).setIntangible(!((LivingEntityAccessor) user).isIntangible());
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
