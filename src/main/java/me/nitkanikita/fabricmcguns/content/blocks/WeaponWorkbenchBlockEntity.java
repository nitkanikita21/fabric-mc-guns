package me.nitkanikita.fabricmcguns.content.blocks;

import me.nitkanikita.fabricmcguns.ModRegister;
import me.nitkanikita.fabricmcguns.gui.WorkbenchGuiDescription;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class WeaponWorkbenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {
    public WeaponWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegister.ModBlockEntities.WEAPON_WORKBENCH_TYPE, pos, state);
    }

    @Override
    public Text getDisplayName() {
        // Using the block name as the screen title
        return new TranslatableText("mcguns.gui.weapon_workbench.title");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new WorkbenchGuiDescription(syncId, inv, ScreenHandlerContext.create(world, pos));
    }
}
