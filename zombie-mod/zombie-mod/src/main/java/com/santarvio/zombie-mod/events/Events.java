package com.${username}.${project_name}.events;

import com.${username}.${project_name}.${project_name};
import com.${username}.${project_name}.config.Config;
import com.${username}.${project_name}.entities.Attributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = ${project_name}.MOD_ID)
public class Events {

    // Random number generator for spawning logic
    private static final Random RANDOM = new Random();

    public static void register() {
        ${project_name}.LOGGER.info("Registering Zombie Events");
    }

    /**
     * Handles attribute modifications for zombies when they join the world
     */
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Zombie zombie) {
            // Apply speed boost
            double speedMultiplier = Config.COMMON.zombieSpeedMultiplier.get();
            zombie.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(
                new AttributeModifier(
                    com.${username}.${project_name}.entities.Attributes.SPEED_MODIFIER_ID,
                    "Zombie speed boost",
                    speedMultiplier - 1.0, // Subtract 1 because it's a multiplier
                    AttributeModifier.Operation.MULTIPLY_TOTAL
                )
            );
            
            // Apply increased follow range (detection distance)
            double detectionMultiplier = Config.COMMON.zombieDetectionRangeMultiplier.get();
            zombie.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(
                new AttributeModifier(
                    com.${username}.${project_name}.entities.Attributes.FOLLOW_RANGE_MODIFIER_ID,
                    "Zombie detection range boost",
                    detectionMultiplier - 1.0, // Subtract 1 because it's a multiplier
                    AttributeModifier.Operation.MULTIPLY_TOTAL
                )
            );
        }
    }

    /**
     * Handles zombie spawn counts, creating groups of zombies
     */
    @SubscribeEvent
    public static void onCheckSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getEntity() instanceof Zombie) {
            // If this is already a naturally spawning zombie, spawn additional zombies in a group
            if (event.getSpawnType().equals(MobSpawnEvent.SpawnPlacementType.NATURAL)) {
                int minGroupSize = Config.COMMON.minZombieGroupSize.get();
                int maxGroupSize = Config.COMMON.maxZombieGroupSize.get();
                
                // Determine how many additional zombies to spawn
                int additionalZombies = RANDOM.nextInt(maxGroupSize - minGroupSize + 1) + minGroupSize - 1;
                
                // Get position data
                double x = event.getEntity().getX();
                double y = event.getEntity().getY();
                double z = event.getEntity().getZ();
                
                // Spawn additional zombies
                for (int i = 0; i < additionalZombies; i++) {
                    // Add some randomness to position
                    double offsetX = RANDOM.nextDouble() * 3.0 - 1.5;
                    double offsetZ = RANDOM.nextDouble() * 3.0 - 1.5;
                    
                    Zombie newZombie = EntityType.ZOMBIE.create(event.getLevel());
                    if (newZombie != null) {
                        newZombie.setPos(x + offsetX, y, z + offsetZ);
                        event.getLevel().addFreshEntity(newZombie);
                    }
                }
            }
        }
    }
    
    /**
     * Increases the frequency of zombie spawns
     */
    @SubscribeEvent
    public static void onEntitySpawn(MobSpawnEvent.PositionCheck event) {
        if (event.getEntity() instanceof Zombie) {
            double spawnFrequency = Config.COMMON.zombieSpawnFrequencyMultiplier.get();
            
            // The higher the spawn frequency value, the more likely we are to allow the spawn
            if (RANDOM.nextDouble() <= (1.0 / spawnFrequency)) {
                // Do nothing, let the vanilla spawn logic proceed
            } else {
                // Force the spawn to be allowed by setting the result to allow
                event.setResult(net.neoforged.bus.api.Event.Result.ALLOW);
            }
        }
    }
}