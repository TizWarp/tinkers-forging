/*
 * Part of the Tinkers Forging Mod by alcatrazEscapee
 * Work under Copyright. Licensed under the GPL-3.0.
 * See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.tinkersforging.client;

import javax.annotation.Nonnull;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.alcatrazescapee.tinkersforging.common.blocks.BlockTinkersAnvil;
import com.alcatrazescapee.tinkersforging.util.material.MaterialRegistry;

import static com.alcatrazescapee.tinkersforging.TinkersForging.MOD_ID;

public class ModCreativeTabs
{
    public static final CreativeTabs TAB_ITEMS = new CreativeTabs(MOD_ID + ".stuff")
    {
        @SideOnly(Side.CLIENT)
        @Override
        @Nonnull
        public ItemStack createIcon()
        {
            //noinspection ConstantConditions
            return BlockTinkersAnvil.get(MaterialRegistry.getMaterial("iron"), 1);
        }
    };
}
