/*
 * Part of the Tinkers Forging Mod by alcatrazEscapee
 * Work under Copyright. Licensed under the GPL-3.0.
 * See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.tinkersforging.util.forge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.alcatrazescapee.tinkersforging.util.forge.ForgeStep.*;

public enum ForgeRule
{
    HIT_ANY(Order.ANY, HIT_LIGHT),
    HIT_NOT_LAST(Order.NOT_LAST, HIT_LIGHT),
    HIT_LAST(Order.LAST, HIT_LIGHT),
    HIT_SECOND_LAST(Order.SECOND_LAST, HIT_LIGHT),
    HIT_THIRD_LAST(Order.THIRD_LAST, HIT_LIGHT),
    DRAW_ANY(Order.ANY, DRAW),
    DRAW_LAST(Order.LAST, DRAW),
    DRAW_NOT_LAST(Order.NOT_LAST, DRAW),
    DRAW_SECOND_LAST(Order.SECOND_LAST, DRAW),
    DRAW_THIRD_LAST(Order.THIRD_LAST, DRAW),
    PUNCH_ANY(Order.ANY, PUNCH),
    PUNCH_LAST(Order.LAST, PUNCH),
    PUNCH_NOT_LAST(Order.NOT_LAST, PUNCH),
    PUNCH_SECOND_LAST(Order.SECOND_LAST, PUNCH),
    PUNCH_THIRD_LAST(Order.THIRD_LAST, PUNCH),
    BEND_ANY(Order.ANY, BEND),
    BEND_LAST(Order.LAST, BEND),
    BEND_NOT_LAST(Order.NOT_LAST, BEND),
    BEND_SECOND_LAST(Order.SECOND_LAST, BEND),
    BEND_THIRD_LAST(Order.THIRD_LAST, BEND),
    UPSET_ANY(Order.ANY, UPSET),
    UPSET_LAST(Order.LAST, UPSET),
    UPSET_NOT_LAST(Order.NOT_LAST, UPSET),
    UPSET_SECOND_LAST(Order.SECOND_LAST, UPSET),
    UPSET_THIRD_LAST(Order.THIRD_LAST, UPSET),
    SHRINK_ANY(Order.ANY, SHRINK),
    SHRINK_LAST(Order.LAST, SHRINK),
    SHRINK_NOT_LAST(Order.NOT_LAST, SHRINK),
    SHRINK_SECOND_LAST(Order.SECOND_LAST, SHRINK),
    SHRINK_THIRD_LAST(Order.THIRD_LAST, SHRINK);

    private static final ForgeRule[] values = values();

    public static int getID(@Nullable ForgeRule rule)
    {
        return rule == null ? -1 : rule.ordinal();
    }

    @Nullable
    public static ForgeRule valueOf(int id)
    {
        return id < 0 || id >= values.length ? null : values[id];
    }

    private final int iconU;
    private final int iconV;

    private final Order order;
    private final ForgeStep type;

    ForgeRule(@Nonnull Order order, @Nonnull ForgeStep type)
    {
        this.order = order;
        this.type = type;

        if (type == HIT_LIGHT || type == HIT_MEDIUM || type == HIT_HARD)
        {
            iconU = 176;
            iconV = 54;
        }
        else
        {
            iconU = type.textureU + 3;
            iconV = type.textureV + 3;
        }
    }

    public boolean matches(@Nonnull ForgeSteps steps)
    {
        switch (this.order)
        {
            case ANY:
                return matchesStep(steps.getStep(2)) || matchesStep(steps.getStep(1)) || matchesStep(steps.getStep(0));
            case NOT_LAST:
                return matchesStep(steps.getStep(1)) || matchesStep(steps.getStep(0));
            case LAST:
                return matchesStep(steps.getStep(2));
            case SECOND_LAST:
                return matchesStep(steps.getStep(1));
            case THIRD_LAST:
                return matchesStep(steps.getStep(0));
            default:
                return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public int getIconU()
    {
        return iconU;
    }

    @SideOnly(Side.CLIENT)
    public int getIconV()
    {
        return iconV;
    }

    @SideOnly(Side.CLIENT)
    public int getOutlineU()
    {
        return 210;
    }

    @SideOnly(Side.CLIENT)
    public int getOutlineV()
    {
        return order.textureV;
    }

    private boolean matchesStep(@Nullable ForgeStep step)
    {
        switch (this.type)
        {
            case HIT_LIGHT:
                return step == HIT_LIGHT || step == ForgeStep.HIT_MEDIUM || step == ForgeStep.HIT_HARD;
            default:
                return type == step;
        }
    }

    private enum Order
    {
        ANY(40),
        LAST(88),
        NOT_LAST(64),
        SECOND_LAST(112),
        THIRD_LAST(136);

        private final int textureV;

        Order(int textureV)
        {
            this.textureV = textureV;
        }
    }

}
