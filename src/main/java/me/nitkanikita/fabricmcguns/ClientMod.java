package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.gui.WorkbenchBlockScreen;
import me.nitkanikita.fabricmcguns.gui.WorkbenchGuiDescription;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.<WorkbenchGuiDescription, WorkbenchBlockScreen>register(ModRegister.ModScreenHandlers.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new WorkbenchBlockScreen(gui, inventory.player, title));
    }
}
