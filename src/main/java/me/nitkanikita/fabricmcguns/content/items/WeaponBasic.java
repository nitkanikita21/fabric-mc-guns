package me.nitkanikita.fabricmcguns.content.items;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class WeaponBasic extends Item {
    protected String system_name;
    protected String display_name;

    public WeaponBasic() {
        super(new Settings().maxCount(1).group(ModTabs.WEAPON_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    public void setSystemName(String system_name) {
        this.system_name = system_name;
    }
    public void setDisplayName(String displayName){
        this.display_name = displayName;
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack item = new ItemStack(this);
        return item.setCustomName(new LiteralText(system_name).formatted(Formatting.GOLD, Formatting.BOLD));
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.mcguns."+ system_name +".tooltip_title").formatted(Formatting.DARK_GRAY));
    }
}
