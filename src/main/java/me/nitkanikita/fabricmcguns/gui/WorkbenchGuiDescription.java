package me.nitkanikita.fabricmcguns.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBox;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import me.nitkanikita.fabricmcguns.ModRegister;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class WorkbenchGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    public WorkbenchGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModRegister.ModScreenHandlers.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(300, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        itemSlot.setIcon(new TextureIcon(new Identifier("mcguns","scheme_icon")));
        itemSlot.setFilter(itemStack -> {
            return itemStack.getItem().equals(ModRegister.modItems.get("scheme"));
        });
        itemSlot.setModifiable(true);

        WLabel schemeName = new WLabel(new TranslatableText("mcguns.gui.weapon_workbench.scheme_label").formatted(Formatting.BOLD));
        schemeName.setHorizontalAlignment(HorizontalAlignment.CENTER);


        root.add(schemeName,2,1);
        root.add(itemSlot, 2, 2);

        root.add(this.createPlayerInventoryPanel(), 0, 5);

        root.validate(this);
    }


}