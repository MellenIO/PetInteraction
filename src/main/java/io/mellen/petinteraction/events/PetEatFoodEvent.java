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
package io.mellen.petinteraction.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PetEatFoodEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private Entity petEntity;
    private Material foodMaterial;
    private int foodQuantity;

    public PetEatFoodEvent(Entity petEntity, Material foodMaterial, int foodQuantity) {
        super();

        this.petEntity = petEntity;
        this.foodMaterial = foodMaterial;
        this.foodQuantity = foodQuantity;
    }

    public PetEatFoodEvent(Entity petEntity, Material foodMaterial) {
        this(petEntity, foodMaterial, 1);
    }

    public Entity getPetEntity() {
        return petEntity;
    }

    public Material getFoodMaterial() {
        return foodMaterial;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
