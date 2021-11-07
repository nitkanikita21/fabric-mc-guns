package me.nitkanikita.fabricmcguns;

import me.nitkanikita.fabricmcguns.gui.ExampleBlockScreen;
import me.nitkanikita.fabricmcguns.gui.ExampleGuiDescription;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class ClientMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.<ExampleGuiDescription, ExampleBlockScreen>register(ModRegister.ModScreenHandlers.SCREEN_HANDLER_TYPE, (gui, inventory, title) -> new ExampleBlockScreen(gui, inventory.player, title));
    }
}
