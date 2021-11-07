package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlock;
import me.nitkanikita.fabricmcguns.content.blocks.WeaponWorkbenchBlockEntity;
import me.nitkanikita.fabricmcguns.gui.ExampleGuiDescription;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModRegister {
    public static String MOD_ID = "mcguns";

    public static HashMap<String, Item> modItems = new HashMap<>();
    public static HashMap<String, Block> modBlocks = new HashMap<>();

    public static class ModScreenHandlers{
        public static ScreenHandlerType<ExampleGuiDescription> SCREEN_HANDLER_TYPE;
    }

    public static class ModBlockEntities{
        public static BlockEntityType<WeaponWorkbenchBlockEntity> WEAPON_WORKBENCH_TYPE;
    }

    public static void registryItem(String id, Item item){
        modItems.put(id,item);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,id),item);
    }

    public static void registryBlock(String id, Block block){
        modBlocks.put(id,block);
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,id),block);
    }

    public static void registryGiveableBlock(String id, Block block){
        modBlocks.put(id,block);
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,id),block);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,id),
                new BlockItem(block,new Item.Settings().group(ModTabs.COMMON_GROUP))
                );
    }

    public static void postInit(){
        ModScreenHandlers.SCREEN_HANDLER_TYPE =  ScreenHandlerRegistry.registerSimple(new Identifier("mcguns","weapon_workbench"), (syncId, inventory) -> new ExampleGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY));
        ModBlockEntities.WEAPON_WORKBENCH_TYPE = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier("mcguns","weapon_workbench"),
                FabricBlockEntityTypeBuilder.create(
                        WeaponWorkbenchBlockEntity::new,
                        modBlocks.get("weapon_workbench")
                ).build(null)
        );
    }
}
