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
