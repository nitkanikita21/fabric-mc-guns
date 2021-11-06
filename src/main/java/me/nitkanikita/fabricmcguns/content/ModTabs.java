package me.nitkanikita.fabricmcguns.content;

import me.nitkanikita.fabricmcguns.ModRegister;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModTabs {

    public static ItemGroup COMMON_GROUP = FabricItemGroupBuilder.build(
            new Identifier("mcguns", "common"),
            () -> new ItemStack(ModRegister.modItems.get("glock15")));

    public static ItemGroup WEAPON_GROUP = FabricItemGroupBuilder.create(new Identifier("mcguns", "weapons"))
            .icon(() -> new ItemStack(ModRegister.modItems.get("m4a4s")))
            .build();
}
