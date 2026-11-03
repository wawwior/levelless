package me.wawwior.levelless.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEnchantments.Mutable.class)
public class ItemEnchantmentsMutableMixin {

    @ModifyReturnValue(method = "getLevel", at = @At("RETURN"))
    private int levelless$getLevel(int original, Holder<Enchantment> holder) {
        return original > 0 && holder.isBound() ? holder.value().getMaxLevel() : original;
    }

}
