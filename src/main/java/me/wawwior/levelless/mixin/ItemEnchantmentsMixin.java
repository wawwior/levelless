package me.wawwior.levelless.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.unimi.dsi.fastutil.objects.AbstractObject2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;
import java.util.stream.Collectors;

@Mixin(ItemEnchantments.class)
public abstract class ItemEnchantmentsMixin {

    @Unique
    private Set<Object2IntMap.Entry<Holder<Enchantment>>> levelless$cachedSet;

    @ModifyReturnValue(method = "getLevel", at = @At("RETURN"))
    private int levelless$returnMaxLevel(int original, Holder<Enchantment> holder) {
        return original > 0 && holder.isBound() ? holder.value().getMaxLevel() : original;
    }

    @ModifyReturnValue(method = "entrySet", at = @At("RETURN"))
    private Set<Object2IntMap.Entry<Holder<Enchantment>>> levelless$returnMaxLevelView(Set<Object2IntMap.Entry<Holder<Enchantment>>> original) {

        if (levelless$cachedSet != null) return levelless$cachedSet;

        Set<Object2IntMap.Entry<Holder<Enchantment>>> result = original.stream().map(entry -> {
            Holder<Enchantment> holder = entry.getKey();
            return new AbstractObject2IntMap.BasicEntry<>(holder, holder.isBound() ? holder.value().getMaxLevel() : entry.getIntValue());
        }).collect(Collectors.toUnmodifiableSet());

        if (original.stream().allMatch(entry -> entry.getKey().isBound())) {
            this.levelless$cachedSet = result;
        }

        return result;
    }

}
