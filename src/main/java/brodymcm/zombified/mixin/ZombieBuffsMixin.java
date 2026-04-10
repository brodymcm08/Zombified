package brodymcm.zombified.mixin;

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
public class ZombieBuffsMixin {
    @Inject(method = "finalizeSpawn(Lnet/minecraft/world/level/ServerLeveAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;", at = @At("TAIL"))
    private void buffZombie(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason entitySpawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable ci) {
        Zombie self = (Zombie) (Object) this;
        if (self.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            self.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.50);
        }
        if (self.getAttribute(Attributes.FOLLOW_RANGE) != null) {
            self.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(70.0);
        }

    }
}