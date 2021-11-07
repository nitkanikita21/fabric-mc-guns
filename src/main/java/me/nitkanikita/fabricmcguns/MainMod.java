package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlock;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlockEntity;
import me.nitkanikita.fabricmcguns.content.items.Scheme;
import me.nitkanikita.fabricmcguns.content.items.WeaponBasic;
import me.nitkanikita.fabricmcguns.content.items.weapons.Glock15;
import me.nitkanikita.fabricmcguns.content.items.weapons.M4A4s;
import me.nitkanikita.fabricmcguns.gui.WorkbenchGuiDescription;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
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
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,
				"glock15"),ModItems.GLOCK15);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,
				"m4a4s"),ModItems.M4A4S);

		Registry.register(Registry.ITEM,new Identifier(MOD_ID,
				"scheme"),ModItems.SCHEME);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,
				"weapon_workbench"),ModItems.WEAPON_WORKBENCH_BLOCKITEM);



		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,
				"weapon_workbench"),ModBlocks.WEAPON_WORKBENCH);

		ModBlockEntities.WEAPON_WORKBENCH_TYPE = Registry.register(
				Registry.BLOCK_ENTITY_TYPE,
				new Identifier("mcguns","weapon_workbench"),
				FabricBlockEntityTypeBuilder.create(
						WeaponWorkbenchBlockEntity::new,
						ModBlocks.WEAPON_WORKBENCH
				).build(null)
		);

		ModScreenHandlers.SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
				new Identifier("mcguns","weapon_workbench"),
				(syncId, inventory) -> new WorkbenchGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY)
		);
	}

	public static String MOD_ID = "mcguns";

	public static class ModItems {
		public final static Item M4A4S = new M4A4s();
		public final static Item GLOCK15 = new Glock15();

		public final static Item SCHEME = new Scheme(new Glock15());

		public final static Item WEAPON_WORKBENCH_BLOCKITEM = new Item(new Item.Settings().group(ModTabs.COMMON_GROUP));
	}

	public static class ModBlocks {
		public final static Block WEAPON_WORKBENCH = new WeaponWorkbenchBlock();
	}

	public static class ModScreenHandlers{
		public static ScreenHandlerType<WorkbenchGuiDescription> SCREEN_HANDLER_TYPE;
	}

	public static class ModBlockEntities{
		public static BlockEntityType<WeaponWorkbenchBlockEntity> WEAPON_WORKBENCH_TYPE;
	}
}
