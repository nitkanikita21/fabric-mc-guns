package me.nitkanikita.fabricmcguns.content.items.weapons;

import me.nitkanikita.fabricmcguns.content.items.WeaponBasic;
import me.nitkanikita.fabricmcguns.content.items.parts.MetallicPart;
import me.nitkanikita.fabricmcguns.content.items.parts.PartBasic;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class M4A4s extends WeaponBasic {

     private PartBasic handStickPart = new MetallicPart();
     private PartBasic basePart = new MetallicPart();

     public M4A4s(){
          setSystemName("m4a4s");
          setDisplayName("M4A4s");
     }

     @Override
     public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
          super.appendTooltip(stack, world, tooltip, context);

          tooltip.add(new LiteralText("Hand Stick: ").formatted(Formatting.GRAY).append(handStickPart.getDisplayName()));
          tooltip.add(new LiteralText("") );
          tooltip.add(new LiteralText("Base: ").formatted(Formatting.GRAY).append(basePart.getDisplayName()));
     }

     @Override
     public ItemStack getDefaultStack() {
          ItemStack defaultStack = super.getDefaultStack();
          NbtCompound nbt = new NbtCompound();
          nbt.putString("handStick","metallic");
          nbt.putString("base","metallic");
          defaultStack.setNbt(nbt);
          return defaultStack;
     }
}
