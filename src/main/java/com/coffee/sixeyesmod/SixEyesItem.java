package com.coffee.sixeyesmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class SixEyesItem extends Item {
    public SixEyesItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote) {
            player.getCapability(ManaStorage.MANA_CAPABILITY).ifPresent(mana -> {
                player.sendMessage(new StringTextComponent("Шесть Глаз пробуждены! Ваша мана начала расти.")
                        .mergeStyle(TextFormatting.AQUA, TextFormatting.BOLD), player.getUniqueID());
                stack.shrink(1); // Удаляем предмет после использования
            });
        }
        return ActionResult.resultSuccess(stack);
    }
}
