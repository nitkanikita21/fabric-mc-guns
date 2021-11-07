package me.nitkanikita.fabricmcguns.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import me.nitkanikita.fabricmcguns.ModRegister;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.Identifier;

public class ExampleGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    public ExampleGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ModRegister.ModScreenHandlers.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(150, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        itemSlot.setIcon(new ItemIcon(ModRegister.modItems.get("scheme")));
        itemSlot.setFilter(itemStack -> {
            return itemStack.getItem().equals(ModRegister.modItems.get("scheme"));
        });
        itemSlot.setModifiable(true);
        root.add(itemSlot, 4, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 3);

        root.validate(this);
    }
}
