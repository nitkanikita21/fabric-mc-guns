package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbench;
import me.nitkanikita.fabricmcguns.content.items.Scheme;
import me.nitkanikita.fabricmcguns.content.items.WeaponBasic;
import me.nitkanikita.fabricmcguns.content.items.weapons.Glock15;
import me.nitkanikita.fabricmcguns.content.items.weapons.M4A4s;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("mcguns");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModRegister.registryItem("m4a4s",new M4A4s());
		ModRegister.registryItem("glock15",new Glock15());
		ModRegister.registryItem("scheme",new Scheme((WeaponBasic) ModRegister.modItems.get("m4a4s")));

		ModRegister.registryGiveableBlock("weapon_workbench",new WeaponWorkbench());

	}
}
