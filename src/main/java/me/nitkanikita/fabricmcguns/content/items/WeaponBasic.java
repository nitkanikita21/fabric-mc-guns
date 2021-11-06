package me.nitkanikita.fabricmcguns.content.items;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class WeaponBasic extends Item {
    public WeaponBasic() {
        super(new Settings().maxCount(1).group(ModTabs.WEAPON_GROUP));
    }

    protected String name;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.mcguns."+name+".tooltip_title").formatted(Formatting.DARK_GRAY));
    }
}
