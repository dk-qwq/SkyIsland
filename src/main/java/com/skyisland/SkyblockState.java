package com.skyisland;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import org.jetbrains.annotations.NotNull;

public class SkyblockState extends PersistentState {
    public boolean initialized;

    public SkyblockState(boolean initialized) {
        this.initialized = initialized;
    }

    public SkyblockState() {
        this(false);
    }

    public static Codec<SkyblockState> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.BOOL.fieldOf("initialized")
                            .forGetter(state -> state.initialized)
                ).apply(instance, SkyblockState::new)
            );

    public static PersistentStateType<SkyblockState> TYPE =
            new PersistentStateType<>(
                    "skyblock_init",
                    SkyblockState::new,
                    CODEC,
                    DataFixTypes.LEVEL
            );
}
