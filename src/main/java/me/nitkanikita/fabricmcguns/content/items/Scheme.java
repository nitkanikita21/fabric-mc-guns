package me.nitkanikita.fabricmcguns.content.items;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;
import net.minecraft.text.NbtText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class Scheme extends Item {
    public WeaponBasic weapon;

    public Scheme(WeaponBasic weapon) {
        super(new Settings().group(ModTabs.COMMON_GROUP).maxCount(1));

        this.weapon = weapon;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.mcguns.scheme.tooltip_1").formatted(Formatting.DARK_GRAY));
        tooltip.add(new LiteralText(weapon.display_name).formatted(Formatting.GOLD,Formatting.BOLD));
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        NbtCompound compound = new NbtCompound();
        compound.putString("for_weapon",weapon.system_name);
        itemStack.setNbt(compound);
        return itemStack.setCustomName(new LiteralText("Scheme").formatted(Formatting.YELLOW));
    }
}
