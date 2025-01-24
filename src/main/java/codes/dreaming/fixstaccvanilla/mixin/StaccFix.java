package codes.dreaming.fixstaccvanilla.mixin;

import codes.dreaming.fixstaccvanilla.FixStaccClient;
import com.bawnorton.mixinsquared.TargetHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(value = PacketByteBuf.class, priority = 1500)
public class StaccFix {
    @TargetHandler(
            mixin = "net.devtech.stacc.mixin.DesyncFixin",
            name = "write",
            prefix = "handler"
    )
    @Inject(method = "@MixinSquared:Handler", at = @At(value = "HEAD"), cancellable = true)
    public void fixWrite(ItemStack itemStack, CallbackInfoReturnable<PacketByteBuf> originalCi, CallbackInfo ci) {
        if (!FixStaccClient.IsStacced) {
            ci.cancel();
        }
    }


    @TargetHandler(
            mixin = "net.devtech.stacc.mixin.DesyncFixin",
            name = "doThing",
            prefix = "modify"
    )
    @Inject(method = "@MixinSquared:Handler", at = @At(value = "HEAD"), cancellable = true)
    public void fixDoThing(int amount, CallbackInfoReturnable<Integer> ci) {
        if (!FixStaccClient.IsStacced) {
            ci.setReturnValue(amount);
            ci.cancel();
        }
    }

}
