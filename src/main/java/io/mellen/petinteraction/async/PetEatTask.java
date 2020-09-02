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

import io.mellen.petinteraction.Plugin;
import io.mellen.petinteraction.events.PetEatFoodEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class PetEatTask extends BukkitRunnable {
    private Plugin context;

    public PetEatTask(Plugin context) {
        this.context = context;
    }

    public void run() {
        Iterator<Entity> petsIterator = context.getPets().iterator();
        while (petsIterator.hasNext()) {
            Entity pet = petsIterator.next();

            if (null == pet || pet.isDead()) {
                petsIterator.remove();
            } else {
                for (Entity nearEntity : pet.getNearbyEntities(1, 1, 1)) {
                    if (nearEntity instanceof Item) {
                        Item droppedItem = (Item) nearEntity;
                        Material droppedItemMaterial = droppedItem.getItemStack().getType();

                        //We've found a valid item entity!
                        if (context.getSettings().petsConfig.availableFood.contains(droppedItemMaterial.name())) {
                            droppedItem.remove();

                            //Call event for third-third-party plugins
                            Bukkit.getPluginManager().callEvent(new PetEatFoodEvent(pet, droppedItemMaterial, droppedItem.getItemStack().getAmount()));

                            //Queue the particle to be rendered
                            context.getPetParticles().put(pet, context.getSettings().petsConfig.getHappyParticle());
                            break;
                        }
                    }
                }
            }
        }
    }
}
