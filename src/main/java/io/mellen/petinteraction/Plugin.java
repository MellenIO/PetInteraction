package io.mellen.petinteraction;

import io.mellen.petinteraction.async.PetEatTask;
import io.mellen.petinteraction.async.PetParticleTask;
import io.mellen.petinteraction.lib.config.impl.PluginSettings;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Plugin extends JavaPlugin {

    private static Plugin INSTANCE;

    private PluginSettings settings;

    //Async collections since we want to process tasks async wherever possible
    private ConcurrentLinkedQueue<Entity> pets;
    private ConcurrentHashMap<Entity, Particle> petParticles;

    //Async tasks
    private PetEatTask eatTask;
    private PetParticleTask particleTask;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        settings = new PluginSettings(this);
        settings.load();

        INSTANCE = this;

        pets = new ConcurrentLinkedQueue<Entity>();
        petParticles = new ConcurrentHashMap<Entity, Particle>();

        //Set a small delay for each task, to make sure that everything available might be loaded in
        eatTask = new PetEatTask(this);
        particleTask = new PetParticleTask(this);
        eatTask.runTaskTimerAsynchronously(this, 100L, settings.ticksPerFood);
        particleTask.runTaskTimerAsynchronously(this, 100L, settings.ticksPerParticle);
    }

    @Override
    public void onDisable() {
        eatTask.cancel();
        particleTask.cancel();
        settings.save();
    }

    public static Plugin getInstance() {
        return INSTANCE;
    }

    /**
     * Get the list of all pets in the world
     *
     * @return all pets currently in the world
     */
    public ConcurrentLinkedQueue<Entity> getPets() {
        return pets;
    }

    /**
     * Get the loaded plugin settings from config.yml
     *
     * @return plugin settings
     */
    public PluginSettings getSettings() {
        return settings;
    }

    /**
     * Get available particles for each {@link Entity}
     *
     * @return available particles for each pet currently in the world
     */
    public ConcurrentHashMap<Entity, Particle> getPetParticles() {
        return petParticles;
    }
}
