/*
 * Part of the Tinkers Forging Mod by alcatrazEscapee
 * Work under Copyright. Licensed under the GPL-3.0.
 * See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.tinkersforging.integration.jei;

import com.alcatrazescapee.tinkersforging.client.gui.GuiTinkersAnvil;
import com.alcatrazescapee.tinkersforging.common.blocks.BlockTinkersAnvil;
import com.alcatrazescapee.tinkersforging.common.items.ItemHammer;
import com.alcatrazescapee.tinkersforging.common.items.ItemToolHead;
import com.alcatrazescapee.tinkersforging.common.recipe.AnvilRecipe;
import com.alcatrazescapee.tinkersforging.common.recipe.ModRecipes;
import com.alcatrazescapee.tinkersforging.util.material.MaterialType;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import static com.alcatrazescapee.tinkersforging.TinkersForging.MOD_ID;

@JEIPlugin
public class JEIIntegration implements IModPlugin
{
    static final String ANVIL_UID = MOD_ID + ".anvil";
    static IGuiHelper guiHelper = null;
    private static Boolean isEnabled = null;

    public static boolean isEnabled()
    {
        if (isEnabled == null)
        {
            isEnabled = Loader.isModLoaded("jei");
        }
        return isEnabled;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(
                new AnvilRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void register(IModRegistry registry)
    {
        // Blacklist for not-enabled blocks
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

        for (BlockTinkersAnvil block : BlockTinkersAnvil.getAll())
        {
            if (block.getMaterial().isEnabled())
                registry.addRecipeCatalyst(new ItemStack(block), ANVIL_UID);
            else
                blacklist.addIngredientToBlacklist(new ItemStack(block));
        }

        for (ItemToolHead item : ItemToolHead.getAll())
        {
            if (!item.getMaterial().isEnabled())
                blacklist.addIngredientToBlacklist(new ItemStack(item));
        }

        for (ItemHammer item : ItemHammer.getAll())
        {
            MaterialType material = item.getMaterial();
            if (material != null && !material.isEnabled())
                blacklist.addIngredientToBlacklist(new ItemStack(item));
        }

        // Anvil Recipes
        registry.handleRecipes(AnvilRecipe.class, AnvilRecipeCategory.Wrapper::new, ANVIL_UID);
        registry.addRecipes(ModRecipes.ANVIL.getAll(), ANVIL_UID);
        registry.addRecipeClickArea(GuiTinkersAnvil.class, 163, 4, 9, 11, ANVIL_UID);
    }
}
