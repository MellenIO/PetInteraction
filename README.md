# PetInteraction
An extension for [MiniaturePets](https://miniaturepets.net/).

## What does this plugin add?
* Pet your pets! You can /ipet whilst targeting a pet in order to give it a lil pat.
* Feed your pets! You can drop specified food near a pet, and it will eat it!

## Supported Versions
This plugin has been tested on PaperMC for 1.16.1 with MiniaturePets 2.4.4-beta. Your mileage may vary.

## API
You can get an instance of the plugin like so:
```java
Plugin petInteractionPlugin = io.mellen.petinteraction.Plugin.getInstance();
// access settings
getLogger().info(petInteractionPlugin.getSettings().petsConfig.availableFood);
```

### Events
* `PetEatFoodEvent` is fired off every single time a pet consumes food. The event contains the pet entity, the material consumed and the amount of the material consumed.

## Releases
This plugin is currently not feature complete, and as a result no download link is available.

## License
This project is licensed under the GNU General Public License V3 (GPL-3).

A summary of the terms of the license [can be found here.](https://tldrlegal.com/license/gnu-general-public-license-v3-(gpl-3)
