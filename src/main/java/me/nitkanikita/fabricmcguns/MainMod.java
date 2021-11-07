package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlock;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlockEntity;
import me.nitkanikita.fabricmcguns.content.fluids.WeaponOil;
import me.nitkanikita.fabricmcguns.content.items.Scheme;
import me.nitkanikita.fabricmcguns.content.items.WeaponBasic;
import me.nitkanikita.fabricmcguns.content.items.weapons.Glock15;
import me.nitkanikita.fabricmcguns.content.items.weapons.M4A4s;
import me.nitkanikita.fabricmcguns.gui.WorkbenchGuiDescription;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

public class MainMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static String MOD_ID = "mcguns";

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
				"scheme_icon"),ModItems.SCHEME_ICON);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,
				"weapon_workbench"),ModItems.WEAPON_WORKBENCH_BLOCKITEM);



		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,
				"weapon_workbench"),ModBlocks.WEAPON_WORKBENCH);
		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "acid"),ModBlocks.ACID);

		ModBlockEntities.WEAPON_WORKBENCH_TYPE = Registry.register(
				Registry.BLOCK_ENTITY_TYPE,
				new Identifier("mcguns","weapon_workbench"),
				FabricBlockEntityTypeBuilder.create(
						WeaponWorkbenchBlockEntity::new,
						ModBlocks.WEAPON_WORKBENCH
				).build(null)
		);

		ModItems.ACID_BUCKET = Registry.register(Registry.ITEM,new Identifier(MOD_ID, "oil_bucket"),ModItems.OIL_BUCKET);

		Registry.register(Registry.FLUID, new Identifier(MOD_ID, "acid"), ModFluilds.STILL_ACID);
		Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_acid"), ModFluilds.FLOWING_ACID);

		setupFluidRendering(MainMod.ModFluilds.STILL_ACID, MainMod.ModFluilds.FLOWING_ACID, new Identifier("minecraft", "water"), 0x4CC248);

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), MainMod.ModFluilds.STILL_ACID, MainMod.ModFluilds.FLOWING_ACID);


		ModScreenHandlers.SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(
				new Identifier("mcguns","weapon_workbench"),
				(syncId, inventory) -> new WorkbenchGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY)
		);
	}

	public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
		final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
		final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");

		// If they're not already present, add the sprites to the block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(stillSpriteId);
			registry.register(flowingSpriteId);
		});

		final Identifier fluidId = Registry.FLUID.getId(still);
		final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");

		final Sprite[] fluidSprites = { null, null };

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return listenerId;
			}

			/**
			 * Get the sprites from the block atlas when resources are reloaded
			 */
			@Override
			public void reload(ResourceManager resourceManager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillSpriteId);
				fluidSprites[1] = atlas.apply(flowingSpriteId);
			}
		});

		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		final FluidRenderHandler renderHandler = new FluidRenderHandler()
		{
			@Override
			public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
				return fluidSprites;
			}

			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return color;
			}
		};

		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
	}

	public static class ModFluilds {
		public static FlowableFluid STILL_ACID = new WeaponOil.Still();
		public final static Fluid FLOWING_ACID = new WeaponOil.Flowing();
	}

	public static class ModItems {
		public final static Item M4A4S = new M4A4s();
		public final static Item GLOCK15 = new Glock15();
		public static Item ACID_BUCKET;

		public static final Item OIL_BUCKET = new Item(new Item.Settings().group(ModTabs.COMMON_GROUP));

		public final static Item SCHEME = new Scheme(new Glock15());
		public final static Item SCHEME_ICON = new Item(new Item.Settings());

		public final static Item WEAPON_WORKBENCH_BLOCKITEM = new BlockItem(ModBlocks.WEAPON_WORKBENCH, new Item.Settings().group(ModTabs.COMMON_GROUP));
	}

	public static class ModBlocks {
		public final static Block WEAPON_WORKBENCH = new WeaponWorkbenchBlock();
		public final static Block ACID = new FluidBlock(ModFluilds.STILL_ACID, FabricBlockSettings.copy(Blocks.WATER)){};
	}

	public static class ModScreenHandlers{
		public static ScreenHandlerType<WorkbenchGuiDescription> SCREEN_HANDLER_TYPE;
	}

	public static class ModBlockEntities{
		public static BlockEntityType<WeaponWorkbenchBlockEntity> WEAPON_WORKBENCH_TYPE;
	}
}
