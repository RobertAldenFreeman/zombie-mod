# Zombie Horde Mod

A Minecraft mod that enhances zombies to create a more challenging survival experience.

## Features

- **Faster Zombies**: Zombies move 1.5x as fast as vanilla zombies
- **Group Spawning**: Zombies spawn in groups of 4-8 instead of individually
- **Increased Frequency**: Zombies spawn much more frequently in the world
- **Enhanced Detection**: Zombies can detect players from twice as far away

## Installation

1. Make sure you have Minecraft with NeoForge installed
2. Download the latest release from the Releases page
3. Place the JAR file in your Minecraft `mods` folder
4. Start Minecraft and enjoy the zombie apocalypse!

## Configuration

The mod includes several configuration options that can be adjusted in the `config/${project_name}-common.toml` file:

- `zombieSpeedMultiplier` - How much faster zombies move (default: 1.5)
- `minZombieGroupSize` - Minimum number of zombies in a spawn group (default: 4)
- `maxZombieGroupSize` - Maximum number of zombies in a spawn group (default: 8)
- `zombieSpawnFrequencyMultiplier` - How much more frequently zombies spawn (default: 2.0)
- `zombieDetectionRangeMultiplier` - How much farther zombies can detect players (default: 2.0)

## Development

### Requirements

- JDK 17 or newer
- Gradle 7.4 or newer

### Setup Development Environment

1. Clone the repository
2. Run `gradlew genVSCodeRuns` (Windows) or `./gradlew genVSCodeRuns` (Mac/Linux) to set up the VSCode run configurations
3. Run `gradlew build` to build the project

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Credits

Created by ${username}