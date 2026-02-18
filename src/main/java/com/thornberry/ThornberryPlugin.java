package com.thornberry;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Nigel Thornberry Sounds"
)
public class ThornberryPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ThornberryConfig config;

	private static final String nigelSmashing = "nigel_smashing.wav";

	@Override
	protected void startUp() throws Exception { }

	@Override
	protected void shutDown() throws Exception { }

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		if (config.dragonPickaxe() && hasDragonPickaxeEquipped() && event.getMenuTarget().contains("Special Attack")) {
			new PlaySound(nigelSmashing).play();
		}
	}

	private boolean hasDragonPickaxeEquipped() {
		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);

		if (equipment != null) {
			for (Item item: equipment.getItems()) {
				if (item != null && item.getId() == net.runelite.api.gameval.ItemID.DRAGON_PICKAXE) {
					return true;
				}
			}
		}

		return false;
	}

	@Provides
	ThornberryConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(ThornberryConfig.class);
	}
}
