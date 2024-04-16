/*
 * Part of the Tinkers Forging Mod by alcatrazEscapee
 * Work under Copyright. Licensed under the GPL-3.0.
 * See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.tinkersforging.common.network;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.alcatrazescapee.alcatrazcore.AlcatrazCore;
import com.alcatrazescapee.alcatrazcore.util.CoreHelpers;
import com.alcatrazescapee.tinkersforging.common.recipe.AnvilRecipe;
import com.alcatrazescapee.tinkersforging.common.tile.TileTinkersAnvil;
import io.netty.buffer.ByteBuf;

public class PacketAnvilRecipeUpdate implements IMessage
{
    private BlockPos pos;
    private AnvilRecipe recipe;

    @SuppressWarnings("unused")
    public PacketAnvilRecipeUpdate() {}

    public PacketAnvilRecipeUpdate(TileTinkersAnvil tile)
    {
        this.pos = tile.getPos();
        this.recipe = tile.getRecipe();
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
        pos = BlockPos.fromLong(buffer.readLong());
        boolean isNotNull = buffer.readBoolean();
        if (isNotNull)
            recipe = AnvilRecipe.fromSerialized(buffer);
        else
            recipe = null;
    }

    @Override
    public void toBytes(ByteBuf buffer)
    {
        buffer.writeLong(pos.toLong());
        buffer.writeBoolean(recipe != null);
        if (recipe != null)
            recipe.serialize(buffer);
    }

    public static class Handler implements IMessageHandler<PacketAnvilRecipeUpdate, IMessage>
    {
        @Override
        public IMessage onMessage(PacketAnvilRecipeUpdate message, MessageContext ctx)
        {
            AlcatrazCore.getProxy().getThreadListener(ctx).addScheduledTask(() ->
            {
                World world = AlcatrazCore.getProxy().getClientWorld();
                TileTinkersAnvil tile = CoreHelpers.getTE(world, message.pos, TileTinkersAnvil.class);
                if (tile != null)
                {
                    tile.setRecipe(message.recipe);
                }
            });
            return null;
        }
    }
}
