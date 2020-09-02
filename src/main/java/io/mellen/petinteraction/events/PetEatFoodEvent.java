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
