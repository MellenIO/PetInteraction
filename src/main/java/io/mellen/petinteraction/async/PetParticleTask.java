package io.mellen.petinteraction.async;

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
        Iterator<Map.Entry<Entity, Particle>> particlesToPlace = context.getPetParticles().entrySet().iterator();
        while (particlesToPlace.hasNext()) {
            Map.Entry<Entity, Particle> availableEffect = particlesToPlace.next();
            Entity pet = availableEffect.getKey();
            if (null == pet || pet.isDead()) {
                particlesToPlace.remove();
            }
            else {
                pet.getWorld().spawnParticle(availableEffect.getValue(), pet.getLocation(), 3);
                particlesToPlace.remove();
            }
        }
    }
}
