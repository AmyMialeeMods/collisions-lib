package amymialee.collisionslib.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.util.PehkuiEntityExtensions;

public class SizeChangerItem extends Item {
    public SizeChangerItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (((PehkuiEntityExtensions) user).pehkui_getScaleData(ScaleType.WIDTH).getTargetScale() != 0.4f) {
                ((PehkuiEntityExtensions) user).pehkui_constructScaleData(ScaleType.WIDTH).setTargetScale(0.4f);
            } else {
                ((PehkuiEntityExtensions) user).pehkui_constructScaleData(ScaleType.WIDTH).setTargetScale(1);
            }
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return super.use(world, user, hand);
    }
}
