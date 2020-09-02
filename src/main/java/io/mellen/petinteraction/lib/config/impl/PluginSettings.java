package io.mellen.petinteraction.lib.config.impl;

import io.mellen.petinteraction.lib.config.Settings;
import io.mellen.petinteraction.lib.config.annotation.ConfigName;
import io.mellen.petinteraction.lib.config.annotation.ConfigPath;
import io.mellen.petinteraction.lib.config.object.impl.PetsConfigSection;
import org.bukkit.plugin.java.JavaPlugin;

@ConfigName
public class PluginSettings extends Settings {
    public PluginSettings(JavaPlugin context) { super(context); }

    @ConfigPath(path = "core.ticksPerParticle")
    public int ticksPerParticle;

    @ConfigPath(path = "core.ticksPerFood")
    public int ticksPerFood;

    @ConfigPath(path = "pets")
    public PetsConfigSection petsConfig = new PetsConfigSection();
}
