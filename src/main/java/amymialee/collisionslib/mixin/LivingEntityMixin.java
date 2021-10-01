package amymialee.collisionslib.mixin;

import amymialee.collisionslib.Collisionslib;
import amymialee.collisionslib.client.LivingEntityAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityAccessor {
    @Unique private static final TrackedData<Boolean> INTANGIBLE;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "pushAway", at = @At("HEAD"), cancellable = true)
    protected void pushAway(Entity entity, CallbackInfo ci) {
        if (entity instanceof LivingEntity && ((LivingEntityAccessor) entity).isIntangible()) {
            ci.cancel();
        }
    }

    @Unique
    public boolean collidesWith(Entity other) {
        return !this.isIntangible() && BoatEntity.canCollide(this, other);
    }

    @Unique
    public boolean isCollidable() {
        return !isIntangible();
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    protected void initDataTracker(CallbackInfo ci) {
        this.getDataTracker().startTracking(INTANGIBLE, false);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("intangible", this.getDataTracker().get(INTANGIBLE));
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.getDataTracker().set(INTANGIBLE, nbt.getBoolean("intangible"));
    }

    @Unique
    @Override
    public boolean isIntangible() {
        return this.getDataTracker().get(INTANGIBLE);
    }

    @Unique
    @Override
    public void setIntangible(boolean intangible) {
        this.getDataTracker().set(INTANGIBLE, intangible);
    }

    static {
        INTANGIBLE = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
