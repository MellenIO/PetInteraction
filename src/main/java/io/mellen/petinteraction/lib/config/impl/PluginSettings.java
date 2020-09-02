/*
 * Copyright (C) 2020 Adam Mellen
 * This file is part of PetInteraction <https://github.com/MellenIO/PetInteraction>
 *
 * PetInteraction is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * PetInteraction is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with PetInteraction.
 * If not, see <http://www.gnu.org/licenses/>.
 * */
package io.mellen.petinteraction.lib.config.impl;

import io.mellen.petinteraction.lib.config.Settings;
import io.mellen.petinteraction.lib.config.annotation.ConfigName;
import io.mellen.petinteraction.lib.config.annotation.ConfigPath;
import io.mellen.petinteraction.lib.config.object.impl.PetsConfigSection;
import org.bukkit.plugin.java.JavaPlugin;

@ConfigName
public class PluginSettings extends Settings {
    public PluginSettings(JavaPlugin context) {
        super(context);
    }

    @ConfigPath(path = "core.ticksPerParticle")
    public int ticksPerParticle;

    @ConfigPath(path = "core.ticksPerFood")
    public int ticksPerFood;

    @ConfigPath(path = "pets")
    public PetsConfigSection petsConfig = new PetsConfigSection();
}
