package io.mellen.petinteraction.lib.config.object.impl;

import io.mellen.petinteraction.lib.config.annotation.ConfigPath;
import io.mellen.petinteraction.lib.config.object.ConfigSection;
import org.bukkit.Particle;

import java.util.ArrayList;
import java.util.List;

public class PetsConfigSection implements ConfigSection {

    @ConfigPath(path = "pets.availableFood")
    public List<String> availableFood = new ArrayList<String>();

    @ConfigPath(path = "pets.happyParticle")
    public String happyParticle;

    public Particle getHappyParticle() {
        return Particle.valueOf(happyParticle);
    }
}
