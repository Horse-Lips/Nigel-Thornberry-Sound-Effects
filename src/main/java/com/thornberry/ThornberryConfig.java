package com.thornberry;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("thornberry")
public interface ThornberryConfig extends Config {
	@ConfigItem(
		keyName = "dragonPickaxe",
		name = "Dragon Pickaxe",
		description = "Play \"smashing\" sound effect when using dragon pickaxe special attack"
	)
	default boolean dragonPickaxe()
	{
		return true;
	}
}
