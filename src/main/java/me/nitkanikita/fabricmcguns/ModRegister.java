package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ModRegister {
    public static String MOD_ID = "mcguns";

    public static HashMap<String, Item> modItems = new HashMap<>();
    public static HashMap<String, Block> modBlocks = new HashMap<>();

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
}
