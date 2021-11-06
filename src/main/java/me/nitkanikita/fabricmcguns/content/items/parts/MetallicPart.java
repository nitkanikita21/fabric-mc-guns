package me.nitkanikita.fabricmcguns.content.items.parts;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class MetallicPart extends PartBasic{
    @Override
    public String getSystemName() {
        return "metallic";
    }

    @Override
    public Text getDisplayName() {
        return super.getDisplayName().copy().formatted(Formatting.WHITE,Formatting.BOLD);
    }
}
