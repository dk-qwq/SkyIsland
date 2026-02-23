package com.skyisland;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkyIslandMod implements ModInitializer {
    public static final String MOD_ID = "skyisland";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            ServerWorld world = server.getOverworld();
            BlockPos spawnPos = world.getSpawnPos();

            PersistentStateManager manager = world.getPersistentStateManager();
            SkyblockState state = manager.getOrCreate(SkyblockState.TYPE);

            if(state.initialized) return ;

            int y = spawnPos.getY();

            for(int dx = -1; dx <= 1; ++dx) for(int dz = -1; dz <= 1; ++dz) {
                BlockPos pos = new BlockPos(
                        spawnPos.getX() + dx,
                        y - 1,
                        spawnPos.getZ() + dz
                );
                world.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState());
            }

            state.initialized = true;
            state.markDirty();
        });
    }
}
