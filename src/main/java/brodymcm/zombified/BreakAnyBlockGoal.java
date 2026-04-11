package brodymcm.zombified;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BreakAnyBlockGoal extends Goal
{
    private final Mob mob;
    public BreakAnyBlockGoal(Mob mob)
    {
        this.mob = mob;
    }
    @Override
    public boolean canUse()
    {
        return true;
    }
    @Override
    public void tick()
    {
        BlockPos front = mob.blockPosition().relative(mob.getDirection());
        for(BlockPos checkPos : new BlockPos[]{front, front.above()})
        {
            BlockState state = mob.level().getBlockState(checkPos);
            if(!state.isAir()
                    && !state.is(Blocks.BEDROCK)
                    && !state.is(Blocks.SPAWNER)
                    && !state.is(Blocks.COMMAND_BLOCK)
                    && !state.is(Blocks.CHAIN_COMMAND_BLOCK)
                    && !state.is(Blocks.CHAIN_COMMAND_BLOCK)
                    && !state.is(Blocks.BARRIER))
            {
                mob.level().removeBlock(checkPos, false);
            }
        }
    }
}
