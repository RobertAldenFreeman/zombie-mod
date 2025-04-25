package com.${username}.${project_name}.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON = specPair.getLeft();
        COMMON_SPEC = specPair.getRight();
    }

    public static class Common {
        // Configuration for zombie speed multiplier
        public final ModConfigSpec.DoubleValue zombieSpeedMultiplier;
        
        // Configuration for zombie spawn group size
        public final ModConfigSpec.IntValue minZombieGroupSize;
        public final ModConfigSpec.IntValue maxZombieGroupSize;
        
        // Configuration for zombie spawn frequency
        public final ModConfigSpec.DoubleValue zombieSpawnFrequencyMultiplier;
        
        // Configuration for zombie detection range
        public final ModConfigSpec.DoubleValue zombieDetectionRangeMultiplier;

        Common(ModConfigSpec.Builder builder) {
            builder.comment("Zombie Mod Configuration")
                   .push("zombies");

            zombieSpeedMultiplier = builder
                    .comment("Speed multiplier for zombies (default: 1.5x faster)")
                    .defineInRange("speedMultiplier", 1.5D, 0.5D, 3.0D);

            minZombieGroupSize = builder
                    .comment("Minimum number of zombies in a spawn group (default: 4)")
                    .defineInRange("minGroupSize", 4, 1, 10);

            maxZombieGroupSize = builder
                    .comment("Maximum number of zombies in a spawn group (default: 8)")
                    .defineInRange("maxGroupSize", 8, 1, 20);

            zombieSpawnFrequencyMultiplier = builder
                    .comment("Multiplier for zombie spawn frequency (default: 2.0x more frequent)")
                    .defineInRange("spawnFrequencyMultiplier", 2.0D, 1.0D, 5.0D);

            zombieDetectionRangeMultiplier = builder
                    .comment("Multiplier for zombie detection range (default: 2.0x farther)")
                    .defineInRange("detectionRangeMultiplier", 2.0D, 1.0D, 3.0D);

            builder.pop();
        }
    }
}