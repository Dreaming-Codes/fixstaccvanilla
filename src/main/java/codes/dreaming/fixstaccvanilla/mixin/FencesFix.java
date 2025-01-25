package codes.dreaming.fixstaccvanilla.mixin;

import codes.dreaming.fixstaccvanilla.FixStaccClient;
import com.bawnorton.mixinsquared.TargetHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(value = FenceBlock.class, priority = 1500)
public class FencesFix {
    @TargetHandler(
            mixin = "fuzs.diagonalfences.mixin.FenceBlockMixin",
            name = "createBlockStateDefinition",
            prefix = "handler"
    )
    @Inject(method = "@MixinSquared:Handler", at = @At(value = "HEAD"), cancellable = true)
    protected void createBlockStateDefinition(StateManager.Builder<Block, BlockState> builder, CallbackInfo originalCallback, CallbackInfo callback) {
        if (!FixStaccClient.IsFenced) {
            callback.cancel();
        }
    }
}
