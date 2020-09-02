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
package io.mellen.petinteraction.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PetInteractionCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            //TODO: error message
            return false;
        }
        return false;
    }
}
