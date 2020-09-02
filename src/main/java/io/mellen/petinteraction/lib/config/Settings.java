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
package io.mellen.petinteraction.lib.config;

import io.mellen.petinteraction.lib.config.annotation.ConfigName;
import io.mellen.petinteraction.lib.config.annotation.ConfigPath;
import io.mellen.petinteraction.lib.config.object.ConfigSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class Settings {
    private JavaPlugin context;

    private Field[] serializableFields;
    private String fileName;

    public Settings(JavaPlugin context) {
        this.context = context;

        //Init reflected fields on object init, rather than during load/save methods
        serializableFields = this.getClass().getDeclaredFields();
        fileName = this.getClass().getAnnotation(ConfigName.class).file();
    }

    public void load() {
        for (Field field : serializableFields) {
            setField(field);
        }
    }

    private void setField(Field field) {
        if (Modifier.isPublic(field.getModifiers())) {
            ConfigPath configPath = field.getAnnotation(ConfigPath.class);
            if (null != configPath) {
                if (field.getType().isAssignableFrom(ConfigSection.class)) {
                    Field[] sectionFields = field.getType().getDeclaredFields();
                    for (Field internalField : sectionFields) {
                        setField(internalField);
                    }
                } else {
                    try {
                        field.set(this, context.getConfig().get(configPath.path()));
                    } catch (IllegalAccessException ignored) {
                    }
                }

            }
        }
    }

    public void save() {
        for (Field field : serializableFields) {
            ConfigPath configPath = field.getAnnotation(ConfigPath.class);
            if (null != configPath) {
                try {
                    context.getConfig().set(configPath.path(), field.get(this));
                } catch (IllegalAccessException ignored) {
                }
            }
        }

        try {
            context.getConfig().save(fileName);
        } catch (IOException ignored) {
        }
    }
}
