package com.${username}.${project_name}.entities;

import java.util.UUID;

/**
 * Constants for zombie entity attributes and modifications
 */
public class Attributes {
    // Unique IDs for our attribute modifiers to avoid duplicates
    public static final UUID SPEED_MODIFIER_ID = UUID.fromString("7E0292F2-9434-4E1A-B324-6ABA488AFC17");
    public static final UUID FOLLOW_RANGE_MODIFIER_ID = UUID.fromString("3CF75F19-B446-46E0-A5DD-1358F689333D");
    
    // Base values for zombie attributes
    public static final double DEFAULT_ZOMBIE_SPEED = 0.23D;
    public static final double DEFAULT_ZOMBIE_FOLLOW_RANGE = 35.0D;
    
    // Modified values based on our configuration
    public static double getModifiedSpeed(double multiplier) {
        return DEFAULT_ZOMBIE_SPEED * multiplier;
    }
    
    public static double getModifiedFollowRange(double multiplier) {
        return DEFAULT_ZOMBIE_FOLLOW_RANGE * multiplier;
    }
}