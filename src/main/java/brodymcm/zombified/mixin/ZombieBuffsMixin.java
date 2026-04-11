package brodymcm.zombified.mixin;

import brodymcm.zombified.BreakAnyBlockGoal;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public class ZombieBuffsMixin
{
    @Inject(method = "finalizeSpawn(Lnet/minecraft/world/level/ServerLeveAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;", at = @At("TAIL"))
    private void moreZombies(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable ci) {
        Zombie self = (Zombie) (Object) this;
        double roll = (Math.random() * 0.43) + 0.03;
        double scaler = roll / 0.23;
        double boss = Math.random();
        double bossScale = 0.01 / 0.23;
        float maxHealth = (float)(20 / scaler);
        self.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
        self.setHealth(maxHealth);
        ((MobAccessor) self).getGoalSelector().addGoal(2, new BreakAnyBlockGoal(self));
            if(self.getAttribute(Attributes.MOVEMENT_SPEED) != null)
            {
                self.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(roll);
            }
            if(self.getAttribute(Attributes.FOLLOW_RANGE) != null)
            {
                self.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(160.0);
            }
            if(self.getAttribute(Attributes.ARMOR) != null)
            {
                self.getAttribute(Attributes.ARMOR).setBaseValue(2.0 / scaler);
            }
            if(self.getAttribute(Attributes.ATTACK_DAMAGE) != null)
            {
                self.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0 / (scaler * scaler));
            }
        if(boss < 0.01)
        {
            float bossHealth = (float) (20 / bossScale);
            self.getAttribute(Attributes.MAX_HEALTH).setBaseValue(bossHealth);
            self.setHealth(bossHealth);
            self.getAttribute(Attributes.SCALE).setBaseValue(1.0 / bossScale);
            if (self.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
                self.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.40);
            }
            if (self.getAttribute(Attributes.FOLLOW_RANGE) != null) {
                self.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(160.0);
            }
            if (self.getAttribute(Attributes.ARMOR) != null) {
                self.getAttribute(Attributes.ARMOR).setBaseValue(2.0 / bossScale);
            }
            if (self.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
                self.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0 / (bossScale * bossScale));
            }
        }
    }
}