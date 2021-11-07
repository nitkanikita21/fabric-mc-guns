package me.nitkanikita.fabricmcguns.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class WorkbenchBlockScreen extends CottonInventoryScreen<WorkbenchGuiDescription> {
    public WorkbenchBlockScreen(WorkbenchGuiDescription gui, PlayerEntity player, Text title) {
        super(gui, player, title);
    }
}