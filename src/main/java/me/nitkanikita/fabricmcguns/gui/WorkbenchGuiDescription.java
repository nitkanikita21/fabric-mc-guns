package me.nitkanikita.fabricmcguns.gui;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import me.nitkanikita.fabricmcguns.MainMod;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class WorkbenchGuiDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    WGridPanel panelCraft = new WGridPanel();

    public WorkbenchGuiDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(MainMod.ModScreenHandlers.SCREEN_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(150, 180);
        root.setInsets(Insets.ROOT_PANEL);

        WItemSlot scheme = WItemSlot.of(blockInventory, 0);
        scheme.setIcon(new ItemIcon(new ItemStack(MainMod.ModItems.SCHEME_ICON)));
        scheme.setFilter(itemStack -> {
            return itemStack.getItem().equals(MainMod.ModItems.SCHEME);
        });
        scheme.setModifiable(true);

        WLabel schemeName = new WLabel(new TranslatableText("mcguns.gui.weapon_workbench.scheme_label").formatted(Formatting.BOLD));
        schemeName.setHorizontalAlignment(HorizontalAlignment.CENTER);

        scheme.addChangeListener((slot, inventory, index, stack) -> {
            if(inventory.getStack(0).equals(ItemStack.EMPTY)){
                root.remove(panelCraft);
            }else {
                root.add(panelCraft,4,0);
            }
        });

        panelCraft.add(WItemSlot.of(blockInventory,1),2,1);
        panelCraft.add(WItemSlot.of(blockInventory,2),1,2);
        panelCraft.add(WItemSlot.of(blockInventory,3),2,2);
        panelCraft.add(WItemSlot.of(blockInventory,4),3,2);
        panelCraft.add(WItemSlot.of(blockInventory,1),2,3);

        root.add(panelCraft,4,0);
        root.add(schemeName,1,1);
        root.add(scheme, 1, 2);

        root.add(this.createPlayerInventoryPanel(), 0, 5);

        root.validate(this);
    }



    @Override
    public HorizontalAlignment getTitleAlignment() {
        return HorizontalAlignment.CENTER;
    }
}
