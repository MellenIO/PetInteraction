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
            }
            else {
                for (Entity nearEntity : pet.getNearbyEntities(1, 1, 1)) {
                    if (nearEntity instanceof Item) {
                        Item droppedItem = (Item)nearEntity;
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
