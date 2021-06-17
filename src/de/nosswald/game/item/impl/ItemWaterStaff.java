package de.nosswald.game.item.impl;

import de.nosswald.game.item.Item;

/**
 * @author Nils Osswald
 * @version 1.0
 */
@Item.ItemData(
        name = "Water Staff",
        iconPath = "assets/items/water_staff.png",
        color = 0x0000ff,
        description = { "Unusable" })
public class ItemWaterStaff extends Item
{
    /**
     * called when the item is used
     *
     * @return if the item was used successfully
     */
    @Override
    public boolean useItem()
    {
        return false; // unusable
    }
}
