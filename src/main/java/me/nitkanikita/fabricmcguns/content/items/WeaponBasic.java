package me.nitkanikita.fabricmcguns.content.items;

import me.nitkanikita.fabricmcguns.content.ModTabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WeaponBasic extends Item {
    public WeaponBasic() {
        super(new Settings().maxCount(1).group(ModTabs.WEAPON_GROUP));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
