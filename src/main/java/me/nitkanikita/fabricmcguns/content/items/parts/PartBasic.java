package me.nitkanikita.fabricmcguns.content.items.parts;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public abstract class PartBasic {

    public String getSystemName(){
        return null;
    }

    public Text getDisplayName() {
        return new TranslatableText("mcguns.parts_material."+getSystemName());
    }
}
