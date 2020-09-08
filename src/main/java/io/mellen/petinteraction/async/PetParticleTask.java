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
package io.mellen.petinteraction.async;

import com.kirelcodes.miniaturepets.pets.Pet;
import io.mellen.petinteraction.Plugin;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PetParticleTask extends BukkitRunnable {
    private Plugin context;

    public PetParticleTask(Plugin context) {
        this.context = context;
    }

    public void run() {
        Iterator<Map.Entry<Pet, Particle>> particlesToPlace = context.getPetParticles().entrySet().iterator();
        while (particlesToPlace.hasNext()) {
            Map.Entry<Pet, Particle> availableEffect = particlesToPlace.next();
            Pet pet = availableEffect.getKey();
            Entity petEntity = pet.getNavigator();
            if (null == petEntity || petEntity.isDead()) {
                particlesToPlace.remove();
            } else {
                petEntity.getWorld().spawnParticle(availableEffect.getValue(), pet.getLocation(), 3);
                particlesToPlace.remove();
            }
        }
    }
}
