/*
 * Part of the Tinkers Forging Mod by alcatrazEscapee
 * Work under Copyright. Licensed under the GPL-3.0.
 * See the project LICENSE.md for more information.
 */

package com.alcatrazescapee.tinkersforging.common.container;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import com.alcatrazescapee.alcatrazcore.network.PacketTContainerUpdate;
import com.alcatrazescapee.alcatrazcore.network.capability.CapabilityContainerListener;
import com.alcatrazescapee.tinkersforging.TinkersForging;
import com.alcatrazescapee.tinkersforging.common.capability.CapabilityForgeItem;
import com.alcatrazescapee.tinkersforging.common.capability.IForgeItem;
import com.alcatrazescapee.tinkersforging.common.network.PacketUpdateForgeItem;

@ParametersAreNonnullByDefault
public class ContainerListenerForgeItem extends CapabilityContainerListener<IForgeItem>
{
    public ContainerListenerForgeItem(EntityPlayerMP player)
    {
        super(player, CapabilityForgeItem.CAPABILITY, null);
    }

    @Override
    protected PacketTContainerUpdate<IForgeItem, ?> createBulkUpdateMessage(int windowID, NonNullList<ItemStack> items)
    {
        return new PacketUpdateForgeItem(windowID, items);
    }

    @Override
    protected PacketTContainerUpdate<IForgeItem, ?> createSingleUpdateMessage(int windowID, int slotID, IForgeItem instance)
    {
        return new PacketUpdateForgeItem(windowID, slotID, instance);
    }

    @Override
    protected void sendToPlayer(EntityPlayerMP player, PacketTContainerUpdate<IForgeItem, ?> packet)
    {
        TinkersForging.getNetwork().sendTo(packet, player);
    }

    @Override
    public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) {

    }

    @Override
    public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {

    }

    @Override
    public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {

    }

    @Override
    public void sendAllWindowProperties(Container containerIn, IInventory inventory) {

    }
}
