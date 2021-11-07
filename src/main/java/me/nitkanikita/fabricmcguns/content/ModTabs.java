package me.nitkanikita.fabricmcguns.content;

import me.nitkanikita.fabricmcguns.MainMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModTabs {

    public static ItemGroup COMMON_GROUP = FabricItemGroupBuilder.build(
            new Identifier("mcguns", "common"),
            () -> new ItemStack(MainMod.ModItems.SCHEME));

    public static ItemGroup WEAPON_GROUP = FabricItemGroupBuilder.create(new Identifier("mcguns", "weapons"))
            .icon(() -> new ItemStack(MainMod.ModItems.M4A4S))
            .build();
}
